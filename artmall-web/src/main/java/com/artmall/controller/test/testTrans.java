package com.artmall.controller.test;

import com.artmall.DO.Student;
import com.artmall.config.RedisTokenManager;
import com.artmall.email.EmailService;
import com.artmall.exception.ArtmallException;
import com.artmall.response.Const;
import com.artmall.response.ServerResponse;
import com.artmall.service.StudentService;
import com.artmall.utils.JWTUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author
 * @create 2018-09-07 8:23
 **/
@RestController

public class testTrans {


        @RequestMapping("/json")
        public String json() throws ArtmallException {
            throw new ArtmallException("发生错误2");
        }






}
