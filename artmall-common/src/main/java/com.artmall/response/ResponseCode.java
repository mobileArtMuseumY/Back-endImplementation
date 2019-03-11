package com.artmall.response;

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
    SUCCESS(200,"SUCCESS"),

    /**
     * 失败
     */
    FAILURE(500,"FAILURE"),

    /**
     * 需要登录
     */
    NEED_LOGIN(401,"NEED_LOGIN"),


    /**
     * 非法参数
     */
    ILLEGAL_ARGUMENT(400,"ILLEGAL_ARGUMENT"),
    /**
     * 过期
     */
    EXPIRED (2222,"EXPIRED"),
    /**
     * token无效
     */
    TOKEN_OUT_OF_DATE(10010,"TOKEN_OUT_OF_DATA"),
    /**
     * 学生第一次登录
     */
    STUDENT_FIRST_LOGIN(1111,"STUDENT_FIRST_LOGIN") ,

    /**
     * 文件有误
     */
    FILE_ERROR(1212,"FILE_ERROR"),

    /**
     * 数据格式有误
     */
    FORMAT_ERROR(4040,"FORMAT_ERROR"),


    /**
     * 为空值
     */
    NULL(1100,"NULL");

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
