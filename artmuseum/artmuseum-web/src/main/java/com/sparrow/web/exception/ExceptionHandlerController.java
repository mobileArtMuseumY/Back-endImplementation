package com.sparrow.web.exception;

import com.sparrow.common.ServerResponse;
import com.sparrow.common.exception.RequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(RequestParameterException.class)
    @ResponseBody
    public ServerResponse<String> requestParameterExceptionHandler(RequestParameterException exception ){
        ServerResponse response = new ServerResponse();
        response.setCode("400");
        response.setMessage(exception.getMessage());
        return response;
    }


}
