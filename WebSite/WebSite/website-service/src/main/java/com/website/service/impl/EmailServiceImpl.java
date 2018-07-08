package com.website.service.impl;

import com.website.service.EmailService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.*;

/**
 * @program: WebSite
 * @description: SpringMvc实现的发送email
 * @author: smallsoup
 * @create: 2018-06-30 20:29
 **/
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    private String excelPath = "D:\\SoftwareAndProgram\\program\\git\\111\\smallsoup\\Back-endImplementation\\WebSite\\WebSite\\website-service\\src\\main\\java\\com\\website\\service";

    private JavaMailSender javaMailSender;

    private SimpleMailMessage simpleMailMessage;

    /**
     * @方法名: sendMail
     * @参数名：@param subject  邮件主题
     * @参数名：@param content 邮件内容
     * @参数名：@param to     收件人Email地址
     * @描述语: 发送邮件
     */
    @Override
    public void sendMailSimple(String to, String subject, String content) throws Exception {

        try {
            //用于接收邮件的邮箱
            simpleMailMessage.setTo(to);
            //邮件的主题
            simpleMailMessage.setSubject(subject);
            //邮件的正文，第二个boolean类型的参数代表html格式
            simpleMailMessage.setText(content);

            LOGGER.info("---------------------------{}", simpleMailMessage);
            //发送
            javaMailSender.send(simpleMailMessage);

//            emailManage();

        } catch (Exception e) {
            throw new MessagingException("failed to send mail!", e);
        }
    }

    @Override
    public void sendMailHtml(String to, String subject, String content) throws Exception {

        //建立邮件消息,发送简单邮件和html邮件的区别
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);

        try {
            messageHelper.setFrom(simpleMailMessage.getFrom());
            //用于接收邮件的邮箱
            messageHelper.setTo(to);
            //邮件的主题
            messageHelper.setSubject(subject);
            //邮件的正文，第二个boolean类型的参数代表html格式
            messageHelper.setText(content,true);

            LOGGER.info("----------sendMailHtml-----------------");
            //发送
            javaMailSender.send(mailMessage);

        } catch (Exception e) {
            throw new MessagingException("failed to send mail!", e);
        }
    }

    @Override
    public boolean emailManage() throws Exception {
        MailModel mail = new MailModel();
        //主题
        mail.setSubject("ISkillService");

        //附件
        Map<String, String> attachments = new HashMap<>();
        attachments.put("ISkillService", excelPath + "/" + "ISkillService.java");
        mail.setAttachments(attachments);

        //内容
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>你好！<br />");
        builder.append("    附件是个人作品。<br />");
        builder.append("</body></html>");
        String content = builder.toString();

        mail.setContent(content);

        return sendEmail(mail);
    }

    /**
     * 发送邮件
     *
     * @参数名：@param mail  邮件
     * @author smallsoup
     * @date 2018-06-30
     */
    @Override
    public boolean sendEmail(MailModel mail) throws Exception {
        // 建立邮件消息
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            // 设置发件人邮箱
            if (mail.getEmailFrom() != null) {
                messageHelper.setFrom(mail.getEmailFrom());
            } else {
                //默认发件人xml配置
                messageHelper.setFrom(simpleMailMessage.getFrom());
            }

            // 设置收件人邮箱
            if (mail.getToEmails() != null) {
                String[] toEmailArray = mail.getToEmails().split(";");
                List<String> toEmailList = new ArrayList<String>();
                if (ArrayUtils.isEmpty(toEmailArray)) {
                    throw new Exception("收件人邮箱不得为空！");
                } else {
                    for (String s : toEmailArray) {
                        if (StringUtils.isNotEmpty(s)) {
                            toEmailList.add(s);
                        }
                    }
                    if (null == toEmailList || toEmailList.size() <= 0) {
                        throw new Exception("收件人邮箱不得为空！");
                    } else {
                        toEmailArray = new String[toEmailList.size()];
                        for (int i = 0; i < toEmailList.size(); i++) {
                            toEmailArray[i] = toEmailList.get(i);
                        }
                    }
                }
                messageHelper.setTo(toEmailArray);
            } else {
                //默认收件人xml配置
                messageHelper.setTo(simpleMailMessage.getTo());
            }

            // 邮件主题
            if (mail.getSubject() != null) {
                messageHelper.setSubject(mail.getSubject());
            } else {
                //默认主题
                messageHelper.setSubject(simpleMailMessage.getSubject());
            }

            // true 表示启动HTML格式的邮件
            messageHelper.setText(mail.getContent(), true);

            // 添加图片
            if (null != mail.getPictures()) {
                for (Iterator<Map.Entry<String, String>> it = mail.getPictures().entrySet()
                        .iterator(); it.hasNext(); ) {
                    Map.Entry<String, String> entry = it.next();
                    String cid = entry.getKey();
                    String filePath = entry.getValue();
                    if (null == cid || null == filePath) {
                        throw new RuntimeException("请确认每张图片的ID和图片地址是否齐全！");
                    }

                    File file = new File(filePath);
                    if (!file.exists()) {
                        throw new RuntimeException("图片" + filePath + "不存在！");
                    }

                    FileSystemResource img = new FileSystemResource(file);
                    messageHelper.addInline(cid, img);
                }
            }

            // 添加附件
            if (null != mail.getAttachments()) {
                for (Iterator<Map.Entry<String, String>> it = mail.getAttachments()
                        .entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry<String, String> entry = it.next();
                    String cid = entry.getKey();
                    String filePath = entry.getValue();
                    if (null == cid || null == filePath) {
                        throw new RuntimeException("请确认每个附件的ID和地址是否齐全！");
                    }

                    File file = new File(filePath);
                    if (!file.exists()) {
                        throw new RuntimeException("附件" + filePath + "不存在！");
                    }

                    FileSystemResource fileResource = new FileSystemResource(file);
                    messageHelper.addAttachment(cid, fileResource);
                }
            }
            messageHelper.setSentDate(new Date());
            // 发送邮件
            javaMailSender.send(message);
            LOGGER.info("------------发送邮件完成----------");

        } catch (MessagingException e) {

            e.printStackTrace();
        }

        return true;
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }
}
