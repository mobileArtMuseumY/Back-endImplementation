package com.artmall.DTO.home;

import com.artmall.DO.Skill;

import java.util.Date;
import java.util.List;

/**
 * 浏览项目展示的数据
 *
 * @author mllove
 * @create 2018-09-27 16:54
 **/

public class BrowseProjectDTO {
    private Long projectId;

    private Long businessId;
    private String avatar;

    private String businessName;

    private String projectName;

    private String projectDescription;

    private List<Skill> skillList;

    private Date gmtCreate;

    private Long leftTime;

    private Long budget;

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
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

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(Long leftTime) {
        this.leftTime = leftTime;
    }


}
