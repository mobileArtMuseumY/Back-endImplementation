package com.artmall.DO;

/**
 * 学生登入
 *
 * @author mllove
 * @create 2018-09-17 20:07
 **/

public class StudentLoginDto {
    private String studentId;
    private String password;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
