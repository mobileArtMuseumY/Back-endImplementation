package com.artmall.DTO.businessController;

import com.artmall.DO.BusinessAttachment;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 注册界面
 *
 * @author mllove
 * @create 2018-09-18 21:30
 **/

public class NewBusinessDTO extends BusinessBaseDTO {

    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
