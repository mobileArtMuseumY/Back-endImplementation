package com.artmall.shiro.JWT;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author
 * @create 2018-08-09 14:31
 **/

public class JWTToken implements AuthenticationToken {
    //密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
