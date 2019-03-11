package com.artmall.shiro.config;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 登入的类型
 */
public class UserToken extends UsernamePasswordToken {
    private String loginType;


    public UserToken(final String username, String password, String loginType){
        super(username,password);
        this.loginType = loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginType(){
        return loginType;

    }
}
