package com.sprraw.artmuseum.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @program: artmuseum
 * @description: 通用响应对象
 * @author: smallsoup
 * @create: 2018-06-25 16:48
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {

    private int status;

    private String msg;

    private T data;

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){
        return this.status;
    }


    public String getMsg(){
        return this.msg;
    }

    public T getDate(){
        return this.data;
    }

    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccess(String msg) {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ServerResponse<T> createByFailure() {
        return new ServerResponse<T>(ResponseCode.FAILURE.getCode(), ResponseCode.FAILURE.getDesc());
    }

    public static <T> ServerResponse<T> createByFailure(String errMsg) {
        return new ServerResponse<T>(ResponseCode.FAILURE.getCode(), errMsg);
    }

    public static <T> ServerResponse<T> createByFailure(int code, String errMsg) {
        return new ServerResponse<T>(code, errMsg);
    }
}
