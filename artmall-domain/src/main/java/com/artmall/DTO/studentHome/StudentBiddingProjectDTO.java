package com.artmall.DTO.studentHome;

/**
 * 学生个人主页上的参加的项目展示
 *
 * @author mllove
 * @create 2018-10-01 19:45
 **/

public class StudentBiddingProjectDTO {
    private Long studentId;
    private Long projectId;
    private String projectName;

    private String projectDescription;

    private Integer status;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
