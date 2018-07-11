package com.website.controller.vo;

import java.util.Calendar;
import java.util.Date;

/**
 * @program: WebSite
 * @description: 验证码VO类
 * @author: smallsoup
 * @create: 2018-07-05 19:59
 **/
public class CodeVo {

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证码失效时间
     */
    private Date expiredTime;

    public CodeVo() {
    }

    public CodeVo(String code) {
        this.code = code;

        //当前时间
        Calendar now = Calendar.getInstance();
        //时间推后10分钟,为验证码有效时间
        now.add(Calendar.MINUTE, 10);

        this.expiredTime = now.getTime();
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
