package com.artmall.config;

import com.artmall.email.EmailService;
import com.artmall.exception.*;
import com.artmall.response.ResponseCode;
import com.artmall.response.ServerResponse;
import com.artmall.utils.CheckUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 异常处理
 *
 * @author mllove
 * @create 2018-11-19 10:27
 **/

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(ArtmallException.class)
    @ResponseBody
    public ServerResponse<String> ErrorHandler(HttpServletRequest req,ArtmallException e){
        ServerResponse<String> r = new ServerResponse<>();
        r.setMsg(e.getMsg());
        r.setStatus(ResponseCode.FAILURE.getCode());
        r.setData("some data");
        return r;

    }



    @ExceptionHandler(value = UnauthenticatedException.class)
    @ResponseBody
        public ServerResponse<String> TokenError(HttpServletRequest req,UnauthenticatedException e){
            ServerResponse<String> r = new ServerResponse<>();
            r.setStatus(ResponseCode.TOKEN_OUT_OF_DATE.getCode());
            r.setMsg(e.getMessage());
            return r;
    }


    @ExceptionHandler(value = NullException.class)
    @ResponseBody
    public ServerResponse<String> NullError(HttpServletRequest req,NullException e){
        ServerResponse<String> r = new ServerResponse<>();
        r.setStatus(ResponseCode.NULL.getCode());
        r.setMsg(e.getMsg());
        return r;
    }

    @ExceptionHandler(value = FileException.class)
    @ResponseBody
    public ServerResponse<String> FileError(HttpServletRequest req,FileException e){
        ServerResponse<String> r = new ServerResponse<>();
        r.setStatus(ResponseCode.FILE_ERROR.getCode());
        r.setMsg(e.getMessage());
        return r;
    }

    @ExceptionHandler(value = CheckException.class)
    @ResponseBody
    public ServerResponse<String> CheckError(HttpServletRequest req,CheckException e){
        ServerResponse<String> r = new ServerResponse<>();
        r.setStatus(ResponseCode.FORMAT_ERROR.getCode());
        r.setMsg(e.getMessage());
        return r;
    }



}
