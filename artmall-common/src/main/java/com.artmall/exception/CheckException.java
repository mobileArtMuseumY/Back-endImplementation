package com.artmall.exception;

/**
 * 格式有误
 *
 * @author mllove
 * @create 2018-11-26 9:48
 **/

public class CheckException extends RuntimeException {
    private String msg;

    public CheckException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

