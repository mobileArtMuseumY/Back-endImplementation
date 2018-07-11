package com.website.service.dto;

import com.website.common.IDUtils;
import com.website.po.Business;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.util.Date;

/**
 * @program: WebSite
 * @description: 企业data tansfer obj
 * @author: smallsoup
 * @create: 2018-07-07 19:18
 **/

public class BusinessDto {

    private String hashedPwd;

    private String avatar;

    private String businessName;

    private String representationName;

    private String representationIdcard;

    private String email;

    private String tel;

    private String introduction;

    private File licenseImg;


    public BusinessDto() {
    }

    public Business convertBusiness(){

        Business business = new Business();

        business.setAvatar(this.avatar);
        business.setBusinessName(this.businessName);
        business.setEmail(this.email);
        business.setFollowerCount(1);
        business.setGmtCreate(new Date());
        business.setGmtModified(new Date());
        
        business.setId(IDUtils.generateId());
        business.setIntroduction(this.introduction);
        business.setIsVerified(new Byte("1"));
        business.setLoginTime(new Date());

        business.setRepresentationIdcard(this.getRepresentationIdcard());
        business.setRepresentationName(this.getRepresentationName());

        business.setTel(this.tel);

        long s = IDUtils.generateId();
        String salt = String.valueOf(s);

        String password = DigestUtils.md5Hex(this.tel + salt);

        business.setHashedPwd(password);
        
        business.setSalt(salt);

        return business;
    }

    public File getLicenseImg() {
        return licenseImg;
    }

    public void setLicenseImg(File licenseImg) {
        this.licenseImg = licenseImg;
    }

    public String getHashedPwd() {
        return hashedPwd;
    }

    public void setHashedPwd(String hashedPwd) {
        this.hashedPwd = hashedPwd;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
