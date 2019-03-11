package com.artmall.DTO.businessController;

import java.util.Date;

/**
 * @author mllove
 * @create 2018-11-14 10:22
 **/

public class BusinessShowDTO {

    private Long id;

    private String businessName;

    private String representationName;

    private String representationIdcard;

    private String email;

    private String tel;

    private Date loginTime;

    private Byte isVerified;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer followerCount;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getRepresentationName() {
        return representationName;
    }

    public void setRepresentationName(String representationName) {
        this.representationName = representationName;
    }

    public String getRepresentationIdcard() {
        return representationIdcard;
    }

    public void setRepresentationIdcard(String representationIdcard) {
        this.representationIdcard = representationIdcard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Byte getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Byte isVerified) {
        this.isVerified = isVerified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }
}
