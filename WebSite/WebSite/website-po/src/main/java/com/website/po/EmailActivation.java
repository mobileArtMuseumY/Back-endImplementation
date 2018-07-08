package com.website.po;

import java.util.Date;

public class EmailActivation {
    private Long id;

    private String email;

    private Byte state;

    private String token;

    private Date tokenExpriedTime;

    private Date gmtRegistered;

    private Date gmtActivated;

    public EmailActivation(Long id, String email, Byte state, String token, Date tokenExpriedTime, Date gmtRegistered, Date gmtActivated) {
        this.id = id;
        this.email = email;
        this.state = state;
        this.token = token;
        this.tokenExpriedTime = tokenExpriedTime;
        this.gmtRegistered = gmtRegistered;
        this.gmtActivated = gmtActivated;
    }

    public EmailActivation() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Date getTokenExpriedTime() {
        return tokenExpriedTime;
    }

    public void setTokenExpriedTime(Date tokenExpriedTime) {
        this.tokenExpriedTime = tokenExpriedTime;
    }

    public Date getGmtRegistered() {
        return gmtRegistered;
    }

    public void setGmtRegistered(Date gmtRegistered) {
        this.gmtRegistered = gmtRegistered;
    }

    public Date getGmtActivated() {
        return gmtActivated;
    }

    public void setGmtActivated(Date gmtActivated) {
        this.gmtActivated = gmtActivated;
    }
}