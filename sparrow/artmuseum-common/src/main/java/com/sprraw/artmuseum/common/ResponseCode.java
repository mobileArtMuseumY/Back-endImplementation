package com.sprraw.artmuseum.common;

/**
 * @program: artmuseum
 * @description: 响应码
 * @author: smallsoup
 * @create: 2018-06-25 16:54
 **/

public enum ResponseCode {

    /**
     * 成功
     */
    SUCCESS(0,"SUCCESS"),

    /**
     * 失败
     */
    FAILURE(1,"FAILURE"),

    /**
     * 需要登录
     */
    NEED_LOGIN(2,"NEED_LOGIN"),

    /**
     * 非法参数
     */
    ILLEGAL_ARGUMENT(3,"ILLEGAL_ARGUMENT");

    private final int code;

    private final String desc;

    ResponseCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return this.code;
    }

    public String getDesc(){
        return this.desc;
    }

}
