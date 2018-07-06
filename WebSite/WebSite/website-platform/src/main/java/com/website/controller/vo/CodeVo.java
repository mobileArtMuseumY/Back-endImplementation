package com.website.controller.vo;

import java.util.Calendar;
import java.util.Date;

/**
 * @program: WebSite
 * @description: 验证码view obj
 * @author: smallsoup
 * @create: 2018-06-30 18:47
 **/
public class CodeVo {

    private String code;

    private Date expiredTime;

    public CodeVo() {
    }

    public CodeVo(String code) {
        this.code = code;
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 10);
        this.expiredTime = nowTime.getTime();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }
}
