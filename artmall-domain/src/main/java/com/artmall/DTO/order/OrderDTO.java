package com.artmall.DTO.order;

import com.artmall.DO.OrderForm;

import java.util.Date;

/**
 * 订单展示
 *
 * @author mllove
 * @create 2018-10-17 10:29
 **/

public class OrderDTO extends OrderForm {

    private String projectName;

    private String worksName;

    private String businessName;

    private String studentName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getWorksName() {
        return worksName;
    }

    public void setWorksName(String worksName) {
        this.worksName = worksName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
