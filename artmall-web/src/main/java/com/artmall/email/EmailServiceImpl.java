package com.artmall.email;


import com.artmall.DO.Business;
import com.artmall.DO.OrderForm;
import com.artmall.DTO.businessController.NewBusinessDTO;
import com.artmall.config.RedisTokenManager;
import com.artmall.DO.Student;
import com.artmall.exception.ArtmallException;
import com.artmall.response.Const;
import com.artmall.response.ServerResponse;
import com.artmall.service.BusinessService;
import com.artmall.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author mllove
 * @create 2018-08-22 15:15
 **/
@Service
public class EmailServiceImpl implements EmailService {
    private final static Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);



    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String from;


    /**
     * 发送邮件模板
     * @param to
     * @param subject
     * @param text
     * @return
     */
    private boolean sendEmail(String to,String subject,String text){
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            return false;
        }
        return true;

    }

    /**
     * 发送注册邮件
     * @param business
     * @param token
     * @return
     */
    @Override
    public ServerResponse registerEmail(NewBusinessDTO business, String token) {
        String test = "&";
        URL link = null;
        try {
            link = new URL("http://"+Const.IP_ADDRESS+ "/business/register/verify?token="+token+test+"id="+business.getId());
        } catch (MalformedURLException e) {
            throw new ArtmallException("url有误");
        }
        String message = business.getBusinessName()+Const.SIGN_CONTENT+"\n"+link;
        String subject = "The register massage";

        if(sendEmail(business.getEmail(),subject,message)){
            return ServerResponse.Success("send success");
        }else {
            return ServerResponse.Failure("send failure");
        }


    }

    @Autowired
    RedisTokenManager redisTokenManager;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 验证token是否有效
     * @param id
     * @param token
     * 返回true表示验证成功，返回false表示验证失败
     */
    @Override
    public Boolean userValidate(Long id, String token) {
        NewBusinessDTO newBusinessDTO = (NewBusinessDTO) redisTemplate.opsForValue().get(token);
        if (id.equals(newBusinessDTO.getId()))
            return true;
        else
            return false;
    }

    /**
     * 忘记密码，邮箱找回，发送验证码
     * @param user
     * @return
     */
    @Override
    public ServerResponse sendResetEmail(Business user,String code) {

        String message = user.getBusinessName()+Const.FORGET_CONTENT+"\n"+code;
        if(sendEmail(user.getEmail(),Const.FORGET_TITLE_SIGN_UP,message)){
            return ServerResponse.Success("send success");
        }else {
            return ServerResponse.Failure("send failure");
        }

    }


    public ServerResponse sendResetEmail(Student user, String code) {

        String message = user.getLoginName()+Const.FORGET_CONTENT+"\n"+code;
        if(sendEmail(user.getEmail(),Const.FORGET_TITLE_SIGN_UP,message)){
            return ServerResponse.Success("send success");
        }else {
            return ServerResponse.Failure("send failure");
        }
    }

    /**
     * 判断code是否有效
     * @param business
     * @param code
     * @return
     */
    @Override
    public boolean codeVerify(Business business, String code) {
        Business redisBusiness = (Business) redisTemplate.opsForValue().get(code);
        if (redisBusiness.getId().equals(business.getId()))
            return true;
        else
            return false;
    }

    /**
     * 判断code是否有效
     * @param student
     * @param code
     * @return
     */
    @Override
    public boolean codeVerify(Student student, String code) {
        Student redStudent = (Student) redisTemplate.opsForValue().get(code);
        if (redStudent.getId().equals(student.getId()))
            return true;
        else
            return false;
    }

    /**
     * 企业支付完后发邮件给企业告知学生的联系方式
     * 发邮件给学生告知被录用，且发送企业的联系方式
     * @param order
     */
    @Autowired
    StudentService studentService;
    @Autowired
    BusinessService businessService;
    @Override
    public void payFinish(OrderForm order) throws Exception {
        Student student = studentService.selectStudentById(order.getSellerId());
        Business business = businessService.selectBusinessById(order.getBuyerId());

        //发送给学生的
        String messageToStudent = student.getLoginName() + Const.FORGET_CONTENT + "\n" + business.getEmail() + "\n 电话:" + business.getTel();
        if (!sendEmail(student.getEmail(), Const.ORDER_INFO, messageToStudent)) {
            throw new Exception("发送失败");
        }

        //发送给企业的
        String messageToBusiness = business.getBusinessName() + Const.SEND_TO_BUSINESS + "\n" + student.getEmail() + "\n 电话:" + student.getTel();
        if (!sendEmail(business.getEmail(), Const.ORDER_INFO, messageToBusiness)) {
            throw new Exception("发送失败");
        }
    }
}