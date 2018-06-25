package com.sparrow.artmuseum.controller.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: artMesuem
 * @description: 前端到后端的数据接受对象
 * @author: smallsoup
 * @create: 2018/6/24
 **/
public class ProjectVo {

    /**
     * @JsonAlias("project_name") 别名project_name和projectName都可以取到值
     */
    private String projectName;

    private String projectDescription;

    /**
     * @JsonProperty("tender_period") 必须tender_period才可以取到值
     */
    private String tenderPeriod;

    private String budget;

    private String expectedTime;

    private String attachmentList;

    private String skillList;

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

    public String getTenderPeriod() {
        return tenderPeriod;
    }

    public void setTenderPeriod(String tenderPeriod) {
        this.tenderPeriod = tenderPeriod;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(String expectedTime) {
        this.expectedTime = expectedTime;
    }

    public String getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(String attachmentList) {
        this.attachmentList = attachmentList;
    }

    public String getSkillList() {
        return skillList;
    }

    public void setSkillList(String skillList) {
        this.skillList = skillList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProjectVo{");
        sb.append("projectName='").append(projectName).append('\'');
        sb.append(", projectDescription='").append(projectDescription).append('\'');
        sb.append(", tenderPeriod='").append(tenderPeriod).append('\'');
        sb.append(", budget='").append(budget).append('\'');
        sb.append(", expectedTime='").append(expectedTime).append('\'');
        sb.append(", attachmentList='").append(attachmentList).append('\'');
        sb.append(", skillList='").append(skillList).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
