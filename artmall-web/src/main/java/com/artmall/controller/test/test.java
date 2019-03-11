/*

package com.artmall.controller;



import com.artmall.mapper.AdminMapper;
import com.artmall.pojo.Admin;
import com.artmall.pojo.Business;
import com.artmall.pojo.Student;
import com.artmall.response.Const;
import com.artmall.response.ServerResponse;
import com.artmall.service.AdminService;
import com.artmall.service.BusinessService;
import com.artmall.service.StudentService;
import com.artmall.utils.JWTUtil;
import com.auth0.jwt.JWT;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;


*/
/**
 * @author
 * @create 2018-08-11 16:08
 **//*


@RestController

public class test {
    private final static Logger log = LoggerFactory.getLogger(test.class);
    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/student/test/hello" )

    public String test (){
        log.debug("ewwwwwww");
        return "hello world!";
    }
    @RequestMapping(value = "hello")
    public String hello(){
        return "不用权限的Hello";
    }

    @RequestMapping(value = "/info" )
    public Student getStudentName(){
        Student student = studentService.selectStudentByStuId("20162590");
        return student;
    }

    @RequestMapping(value = "/register" ,method = RequestMethod.POST)
    public ServerResponse<Admin> signin (@RequestParam(name = "username") String username,
                                            @RequestParam(name = "password")  String password
                                            ){
        log.debug(username);
        Admin admin = new Admin();
        admin.setLoginName(username);
        admin.setHashedPwd(password);
//        student.setStudentId("2016");
//        student.setHashedPwd("1234567890");
//        student.setLoginName("尝");
        return adminService.addUser(admin);
    }

    @Autowired
    BusinessService businessService;
    @RequestMapping(value = "/business/register",method = RequestMethod.POST)
    public ServerResponse<Business> BusiSign (@RequestParam(name = "email") String email,
                                              @RequestParam(name = "password") String password
                                              ){
        Business business = new Business();
        business.setEmail(email);
        business.setHashedPwd(password);
        return businessService.addUser(business);
    }

    @RequestMapping(value = "/student/login",method = RequestMethod.POST )
    public ServerResponse<Student> Stulogin (@RequestParam(name = "studentId") String studentId,
                                          @RequestParam(name = "password")String password){
//        String studentId = "20162570";
//        String password = "123456";
        UsernamePasswordToken token = new UsernamePasswordToken(studentId,password);
        Student student = studentService.selectStudentByStuId(studentId);
        System.out.println("token为:"+token.getCredentials());
        Subject subject = SecurityUtils.getSubject();
//        try {
            System.out.println("进行登入验证");
            subject.login(token);
//        }catch (Exception e){
//            return ServerResponse.Failure("登入失败");
//        }
        return ServerResponse.Success(JWTUtil.sign(student.getId(),Const.LoginType.STUDENT));
//        return ServerResponse.Success("登录成功");


    }


    @RequestMapping(value = "/business/login",method = RequestMethod.POST)
    public ServerResponse<Business> BusiLogin (@RequestParam (name = "email") String email,
                                               @RequestParam (name = "password") String password){
        UsernamePasswordToken token = new UsernamePasswordToken(email,password);
        Business business = businessService.selectBusinessByEmail(email);
        Subject subject = SecurityUtils.getSubject();
//        try {
            subject.login(token);
//        }
        return ServerResponse.Success(JWTUtil.sign(business.getId(),Const.LoginType.BUSINESS));
    }

    @Autowired
    AdminService adminService;
    @RequestMapping(value = "/admin/login",method = RequestMethod.POST )
    public ServerResponse<Admin> AdminLogin(@RequestParam(name = "username")String username,
                                            @RequestParam(name = "password") String password) {

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Admin admin = adminService.selectByUsername(username);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return ServerResponse.Success(JWTUtil.sign(admin.getId(),Const.LoginType.ADMIN));

    }
    //没有session，所以无法测试，无法保存登入状态
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ServerResponse<Student> logout (){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        log.debug("logout");
        return ServerResponse.Success("登录成功");

    }

    @GetMapping("/article")
    public ServerResponse article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return ServerResponse.Success("You are already logged in");
        } else {
            return ServerResponse.Failure( "You are guest");
        }
    }


    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ServerResponse<Student> requireAuth() {
        return ServerResponse.Success("You are authenticated");
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ServerResponse<Student> requireRole() {
        return ServerResponse.Success( "You are visiting require_role");
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public ServerResponse<Student> requirePermission() {
        return ServerResponse.Success( "You are visiting permission require edit,view");
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ServerResponse<Student> unauthorized() {
        return ServerResponse.Failure("Unauthorized");
    }
}
//    @GetMapping("/all")
//    public List<Student> getAll()
//    {
//        return studentService.getAll();
//    }


*/
