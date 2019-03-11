package com.artmall.exception;

/**
 * token错误
 *
 * @author mllove
 * @create 2018-11-19 12:52
 **/

public class TokenException extends RuntimeException {
    private String msg;

    public TokenException(String msg) {
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
