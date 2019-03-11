package com.artmall.exception;

/**
 * 空值抛出异常
 *
 * @author mllove
 * @create 2018-11-19 13:21
 **/

public class NullException extends RuntimeException {
    private String msg;

    public NullException(String msg) {
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
