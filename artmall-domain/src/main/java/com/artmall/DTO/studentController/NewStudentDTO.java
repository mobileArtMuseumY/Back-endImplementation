package com.artmall.DTO.studentController;

import com.artmall.DO.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 添加学生时学生信息
 *
 * @author mllove
 * @create 2018-10-11 19:57
 **/

public class NewStudentDTO {
    @JsonIgnore
    private Long id;

    private String studentId;

    private String loginName;

    private String sex;

    private String bankAccount;

    private String tel;

    private String cardId;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "NewStudentDTO{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", sex='" + sex + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", tel='" + tel + '\'' +
                ", cardId='" + cardId + '\'' +
                '}';
    }
}
