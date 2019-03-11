package com.artmall.response;

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

    public ServerResponse() {
    }

    public ServerResponse(String msg) {
    }

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

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ServerResponse<T> Expired(String msg, T data) {
        return new ServerResponse<T>(ResponseCode.EXPIRED.getCode(),msg,data);
    }

//    public static <T> ServerResponse<T> NoPermitted(String msg) {
//        return new ServerResponse<T>(ResponseCode.PERMITTED.getCode(),msg,);
//    }


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

    public T getData(){
        return this.data;
    }



    public static <T> ServerResponse<T> Success(String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServerResponse<T> FirstSuccess(String msg){
        return new ServerResponse<T>(ResponseCode.STUDENT_FIRST_LOGIN.getCode(),msg);
    }
    public static <T> ServerResponse<T> Success(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    public static <T> ServerResponse<T> Success(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }
    public static <T> ServerResponse<T> Failure(){
        return new ServerResponse<T>(ResponseCode.FAILURE.getCode());
    }
    public static <T> ServerResponse<T> Failure(String msg){
        return new ServerResponse<T>(ResponseCode.FAILURE.getCode(),msg);
    }
    public static <T> ServerResponse<T> Failure(String msg,T data){
        return new ServerResponse<T>(ResponseCode.FAILURE.getCode(),msg,data);
    }

}
