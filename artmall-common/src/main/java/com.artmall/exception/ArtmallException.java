package com.artmall.exception;

/**
 * @author
 * @create 2018-08-06 21:19
 **/

public class ArtmallException extends RuntimeException{
    private String msg;

    public ArtmallException(String msg){
        super(msg);
        this.msg=msg;
    }
    public String getMsg(){
        return msg;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
}
