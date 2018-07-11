package com.website.po;

import java.util.Date;

public class Business {
    private Long id;

    private String hashedPwd;

    private String salt;

    private String avatar;

    private String businessName;

    private String representationName;

    private String representationIdcard;

    private String email;

    private String tel;

    private String introduction;

    private Date loginTime;

    private Byte isVerified;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer followerCount;

    public Business(Long id, String hashedPwd, String salt, String avatar, String businessName, String representationName, String representationIdcard, String email, String tel, String introduction, Date loginTime, Byte isVerified, Date gmtCreate, Date gmtModified, Integer followerCount) {
        this.id = id;
        this.hashedPwd = hashedPwd;
        this.salt = salt;
        this.avatar = avatar;
        this.businessName = businessName;
        this.representationName = representationName;
        this.representationIdcard = representationIdcard;
        this.email = email;
        this.tel = tel;
        this.introduction = introduction;
        this.loginTime = loginTime;
        this.isVerified = isVerified;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.followerCount = followerCount;
    }

    public Business() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashedPwd() {
        return hashedPwd;
    }

    public void setHashedPwd(String hashedPwd) {
        this.hashedPwd = hashedPwd == null ? null : hashedPwd.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName == null ? null : businessName.trim();
    }

    public String getRepresentationName() {
        return representationName;
    }

    public void setRepresentationName(String representationName) {
        this.representationName = representationName == null ? null : representationName.trim();
    }

    public String getRepresentationIdcard() {
        return representationIdcard;
    }

    public void setRepresentationIdcard(String representationIdcard) {
        this.representationIdcard = representationIdcard == null ? null : representationIdcard.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
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