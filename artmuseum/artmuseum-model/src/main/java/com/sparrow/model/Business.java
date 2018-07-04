package com.sparrow.model;

import java.io.Serializable;
import java.util.Date;

public class Business implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.hashed_pwd
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.salt
     *
     * @mbggenerated
     */
    private String salt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.avatar
     *
     * @mbggenerated
     */
    private String avatar;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.business_name
     *
     * @mbggenerated
     */
    private String businessName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.representation_name
     *
     * @mbggenerated
     */
    private String representationName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.representation_idcard
     *
     * @mbggenerated
     */
    private String representationIdcard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.email
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.tel
     *
     * @mbggenerated
     */
    private String tel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.introduction
     *
     * @mbggenerated
     */
    private String introduction;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.login_time
     *
     * @mbggenerated
     */
    private Date loginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.is_verified
     *
     * @mbggenerated
     */
    private Byte isVerified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.gmt_create
     *
     * @mbggenerated
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.gmt_modified
     *
     * @mbggenerated
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.follower_count
     *
     * @mbggenerated
     */
    private Integer followerCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table business
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.id
     *
     * @return the value of business.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.id
     *
     * @param id the value for business.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.hashed_pwd
     *
     * @return the value of business.hashed_pwd
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.hashed_pwd
     *
     * @param password the value for business.hashed_pwd
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.salt
     *password
     * @return the value of business.salt
     *
     * @mbggenerated
     */
    public String getSalt() {
        return salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.salt
     *
     * @param salt the value for business.salt
     *
     * @mbggenerated
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.avatar
     *
     * @return the value of business.avatar
     *
     * @mbggenerated
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.avatar
     *
     * @param avatar the value for business.avatar
     *
     * @mbggenerated
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.business_name
     *
     * @return the value of business.business_name
     *
     * @mbggenerated
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.business_name
     *
     * @param businessName the value for business.business_name
     *
     * @mbggenerated
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName == null ? null : businessName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.representation_name
     *
     * @return the value of business.representation_name
     *
     * @mbggenerated
     */
    public String getRepresentationName() {
        return representationName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.representation_name
     *
     * @param representationName the value for business.representation_name
     *
     * @mbggenerated
     */
    public void setRepresentationName(String representationName) {
        this.representationName = representationName == null ? null : representationName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.representation_idcard
     *
     * @return the value of business.representation_idcard
     *
     * @mbggenerated
     */
    public String getRepresentationIdcard() {
        return representationIdcard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.representation_idcard
     *
     * @param representationIdcard the value for business.representation_idcard
     *
     * @mbggenerated
     */
    public void setRepresentationIdcard(String representationIdcard) {
        this.representationIdcard = representationIdcard == null ? null : representationIdcard.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.email
     *
     * @return the value of business.email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.email
     *
     * @param email the value for business.email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.tel
     *
     * @return the value of business.tel
     *
     * @mbggenerated
     */
    public String getTel() {
        return tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.tel
     *
     * @param tel the value for business.tel
     *
     * @mbggenerated
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.introduction
     *
     * @return the value of business.introduction
     *
     * @mbggenerated
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.introduction
     *
     * @param introduction the value for business.introduction
     *
     * @mbggenerated
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.login_time
     *
     * @return the value of business.login_time
     *
     * @mbggenerated
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.login_time
     *
     * @param loginTime the value for business.login_time
     *
     * @mbggenerated
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.is_verified
     *
     * @return the value of business.is_verified
     *
     * @mbggenerated
     */
    public Byte getIsVerified() {
        return isVerified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.is_verified
     *
     * @param isVerified the value for business.is_verified
     *
     * @mbggenerated
     */
    public void setIsVerified(Byte isVerified) {
        this.isVerified = isVerified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.gmt_create
     *
     * @return the value of business.gmt_create
     *
     * @mbggenerated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.gmt_create
     *
     * @param gmtCreate the value for business.gmt_create
     *
     * @mbggenerated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.gmt_modified
     *
     * @return the value of business.gmt_modified
     *
     * @mbggenerated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.gmt_modified
     *
     * @param gmtModified the value for business.gmt_modified
     *
     * @mbggenerated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.follower_count
     *
     * @return the value of business.follower_count
     *
     * @mbggenerated
     */
    public Integer getFollowerCount() {
        return followerCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.follower_count
     *
     * @param followerCount the value for business.follower_count
     *
     * @mbggenerated
     */
    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }



}