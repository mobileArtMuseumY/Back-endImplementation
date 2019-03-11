package com.artmall.DTO.order;

import java.util.Date;

/**
 * 订单确认界面
 *
 * @author mllove
 * @create 2018-10-25 10:43
 **/

public class OrderConfirm {

    private Long businessId;

    private String businessName;

    private String businessEmail;

    private String businessTel;

    private Long projectId;

    private Long studentId;

    private Long worksId;

    private String worksName;

    private Long price;

    private Integer expectedTime;
    private Byte type;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Integer expectedTime) {
        this.expectedTime = expectedTime;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getBusinessTel() {
        return businessTel;
    }

    public void setBusinessTel(String businessTel) {
        this.businessTel = businessTel;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getWorksId() {
        return worksId;
    }

    public void setWorksId(Long worksId) {
        this.worksId = worksId;
    }

    public String getWorksName() {
        return worksName;
    }

    public void setWorksName(String worksName) {
        this.worksName = worksName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
