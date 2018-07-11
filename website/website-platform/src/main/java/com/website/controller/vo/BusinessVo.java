package com.website.controller.vo;

import com.website.service.dto.BusinessDto;

import java.io.File;

/**
 * @program: WebSite
 * @description: 企业VO类
 * @author: smallsoup
 * @create: 2018-07-05 20:04
 **/

public class BusinessVo {

    private String hashedPwd;

    private String avatar;

    private String businessName;

    private String representationName;

    private String representationIdcard;

    private String email;

    private String tel;

    private String introduction;

    public BusinessDto convertBusinessDto(File businessLicenseImg) {

        BusinessDto businessDto = new BusinessDto();

        businessDto.setAvatar(this.getAvatar());
        businessDto.setBusinessName(this.getBusinessName());
        businessDto.setEmail(this.getEmail());
        businessDto.setHashedPwd(this.getHashedPwd());
        businessDto.setIntroduction(this.getIntroduction());
        businessDto.setRepresentationIdcard(this.getRepresentationIdcard());
        businessDto.setRepresentationName(this.getRepresentationName());
        businessDto.setTel(this.getTel());
        businessDto.setLicenseImg(businessLicenseImg);

        return businessDto;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BusinessVo{");
        sb.append("hashedPwd='").append(hashedPwd).append('\'');
        sb.append(", avatar='").append(avatar).append('\'');
        sb.append(", businessName='").append(businessName).append('\'');
        sb.append(", representationName='").append(representationName).append('\'');
        sb.append(", representationIdcard='").append(representationIdcard).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", tel='").append(tel).append('\'');
        sb.append(", introduction='").append(introduction).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
