package com.artmall.controller.front;

import com.artmall.DO.User;
import com.artmall.DTO.studentController.UpStuDTO;
import com.artmall.captcha.CaptchaService;
import com.artmall.config.RedisTokenManager;
import com.artmall.email.EmailService;
import com.artmall.DO.Student;
import com.artmall.exception.ArtmallException;
import com.artmall.exception.CheckException;
import com.artmall.response.Const;
import com.artmall.response.ServerResponse;
import com.artmall.service.*;
import com.artmall.utils.JWTUtil;
import com.artmall.DTO.order.OrderDTO;
import com.artmall.DTO.studentHome.StudentBiddingProjectDTO;
import com.artmall.DTO.studentHome.StudentHomeInfoDTO;
import com.artmall.DTO.home.WorksDTO;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author mllove
 * @create 2018-08-20 10+:08
 **/
@RequestMapping("/student")
@RestController

public class StudentController {
    private final static Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @ApiOperation("学生登录，如果是第一次登录，则返回1111")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ServerResponse login (@RequestBody Map<String,String> map){

        String studentId = map.get("studentId");
        String password = map.get("password");


        UsernamePasswordToken token = new UsernamePasswordToken(studentId,password);
        Student student = studentService.selectStudentByStuId(studentId);

        if (student==null){
            throw new ArtmallException("没有此用户");
        }
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            studentService.login(student);
        }catch (UnknownAccountException uae){
            return ServerResponse.Failure("用户不存在");
        }catch (IncorrectCredentialsException ice) {
            throw new ArtmallException("账号密码有误");
        }catch (AuthenticationException ae){
            return ServerResponse.Failure("账号密码有误");
        }
        //如果是第一次登入，由前端控制路由跳转
        if (student.getIsVerified().equals(Const.STUDENT_INIT)){
            return ServerResponse.FirstSuccess(JWTUtil.sign(student.getId(),Const.LoginType.STUDENT));
        }else
            return ServerResponse.Success(JWTUtil.sign(student.getId(),Const.LoginType.STUDENT),student.getId());
    }


    @Autowired
    RedisTokenManager redisTokenManager;
    @Autowired
    EmailService emailService;
    /**
     * 发送重置密码验证码的邮件
     * @param student
     * @return
     */
    @ApiOperation("发送重置密码验证码的邮件")
    private ServerResponse sendResetPasswordEmail(Student student){
        try {
            String code = redisTokenManager.getCodeOfResetPassword(student);
            if (student.getEmail() != null) {
                return emailService.sendResetEmail(student, code);
            } else
                return ServerResponse.Failure("请在第一次登录后验证邮箱");
        }catch(Exception e){
            throw new CheckException("邮箱格式有误");
        }
    }

    /**
     * 忘记密码
     * @return
     */
    @ApiOperation("忘记密码")
    @RequestMapping(value = "/password",method = RequestMethod.POST)
    public ServerResponse forget (@RequestBody Map<String,String> map){

        String studId = map.get("studentId");
        Student student = studentService.selectStudentByStuId(studId);
        if (student != null){
            return sendResetPasswordEmail(student);
        }else {
            return ServerResponse.Failure("没有此学生");
        }
    }


    /**
     * 第一次登入时填入邮箱修改密码
     * @param
     * @return
     */
    @ApiOperation("第一次登录时填入邮箱修改密码")
    @RequestMapping(value = "/firstLogin/SendEmail",method = RequestMethod.POST)
    public ServerResponse firstLogin (@RequestBody Map<String,String> map){
        String email = map.get("email");
        Student student=studentService.getStudent();
        if (studentService.selectStudentByEmail(email)!=null){
            throw new ArtmallException("此邮箱已经被注册了");
        }
        student.setEmail(email);
        return sendResetPasswordEmail(student);
    }

    @RequestMapping(value = "/email/verified",method = RequestMethod.POST)
    public ServerResponse emailVerified (@RequestBody Map<String,String> map){
        String newEmail = map.get("email");
        String code = map.get("code");
        Student student = studentService.getStudent();
        if (studentService.selectStudentByEmail(newEmail)!=null){
            throw new ArtmallException("此邮箱已经被注册了");
        }
        if(emailService.codeVerify(student,code)){
            studentService.resetEmail(student,newEmail);
            return ServerResponse.Success("验证成功");
        }else {
            throw new ArtmallException("验证码有误");
        }
    }

    @ApiOperation("邮件验证码验证")
    @RequestMapping(value = "/firstLogin/verified",method = RequestMethod.POST)
//    @Transactional
    public ServerResponse firstLoginVeried(@RequestBody Map<String,String> map){
        String email = map.get("email");
        String code = map.get("code");
        String newPassword = map.get("newPassword");
        Student student = studentService.getStudent();
        if (emailService.codeVerify(student,code)){
            try {
                //重置密码
                studentService.resetPassword(email, newPassword);

            }catch (Exception e){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new ArtmallException("密码修改失败");
            }return ServerResponse.Success("密码修改成功");
        }else {
            return ServerResponse.Failure("验证码错误");
        }

    }

    /**
     * 修改密码
     * @return
     */
    @ApiOperation("修改密码")
    @RequestMapping(value = "/password/resetByEmail",method = RequestMethod.POST)
    public ServerResponse resetPassword (@RequestBody Map<String,String> map){
        String studentId = map.get("studentId");
        String code = map.get("code");
        String newPassword = map.get("newPassword");
        //当前用户是否是缓存中code对应的用户
        Student student = studentService.selectStudentByStuId(studentId);
        if (emailService.codeVerify(student,code)){
            studentService.resetPasswordByEmail(student,newPassword);
            return ServerResponse.Success("密码修改成功");
        }else{
            return ServerResponse.Failure("验证码错误");
        }
    }


//    @RequestMapping(value = "/test",method = RequestMethod.GET)
//    public ServerResponse article(@RequestParam("id") String id) {
//        Subject subject = SecurityUtils.getSubject();
//        if (subject.isAuthenticated()) {
//
//            return ServerResponse.Success("You are already logged in",id);
//        } else {
//            System.out.println("id"+id);
//            return ServerResponse.Failure( "You are guest",id);
//        }
//    }


    @Autowired
    UserService userService;
    @Autowired
    FollowerSerivce followerSerivce;
    @RequestMapping(value = "/home/info",method = RequestMethod.GET)
    public ServerResponse showHomeInfo(@RequestParam("id") Long id){
        User user = userService.getUser();
        Byte isFollowed = followerSerivce.isFollowed(user.getUserId(),id);
        StudentHomeInfoDTO studentHomeInfoDTO =studentService.showHomeInfo(id);
        studentHomeInfoDTO.setIsFollowed(isFollowed);
        return ServerResponse.Success("展示成功", studentHomeInfoDTO);
    }



    @RequestMapping(value = "/home/worksShow",method = RequestMethod.GET)
    public ServerResponse showHomeWorks(@RequestParam("id") Long id){
        List<WorksDTO> worksDTOList = studentService.showWorks(id);
        return ServerResponse.Success("作品展示成功", worksDTOList);
    }

    /**
     * 投标展示
     * @return
     */
    @Autowired
    BidService bidService;
    @Autowired
    ProjectService projectService;
    @RequestMapping(value = "/home/biddingProject",method = RequestMethod.GET)
    public ServerResponse showBidingProject(@RequestParam("id") Long id ){
        Student student = studentService.selectStudentById(id);
        List<StudentBiddingProjectDTO> studentBiddingProjectDTOList = bidService.setStudentBidingProject(student.getId());
        return ServerResponse.Success("投标情况展示成功", studentBiddingProjectDTOList);
    }

    /**
     * 订单展示
     * @return
     */
    @Autowired
    OrderService orderService;
    @RequestMapping(value = "/home/orderShow",method = RequestMethod.GET )
    public ServerResponse showOrder (@RequestParam("id") Long id){
        Student student = studentService.selectStudentById(id);
        List<OrderDTO> orderList = orderService.selectOrderByStudentId(student.getId());
        return ServerResponse.Success("订单展示成功",orderList);
    }


    /**
     * 学生个人简介修改
     *
     * @return
     */
    @RequestMapping(value = "/update/introduction",method = RequestMethod.POST)
    public ServerResponse updateDes (@RequestBody Map<String,String> map){
        String description = map.get("introduction");
        studentService.updateDescription(description);
        return ServerResponse.Success("学生信息修改成功");
    }



    @Autowired
    CaptchaService captchaService;
    @RequestMapping(value = "/update/tel",method = RequestMethod.POST )
    public ServerResponse updateTel (@RequestBody Map<String,String> map){
        String tel = map.get("tel");
        String code = map.get("code");
        if (captchaService.captchaValidate(tel,code)) {
            studentService.updatePhone(tel);
            return ServerResponse.Success("更改成功");
        }
        else
            return ServerResponse.Failure("更改失败");
    }


}
