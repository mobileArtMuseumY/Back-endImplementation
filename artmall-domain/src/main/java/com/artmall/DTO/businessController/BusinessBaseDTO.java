package com.artmall.DTO.businessController;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 更新信息
 *
 * @author mllove
 * @create 2018-11-20 12:19
 **/

public class BusinessBaseDTO {


    private Long id;

    private String businessName;

    private String representationName;

    private String representationIdcard;

    private String email;

    private String tel;

    //统一信用代码
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
}
