package com.sparrow.artmuseum.service.dto;

/**
 * @program: artmuseum
 * @description: project 数据传输Object
 * @author: smallsoup
 * @create: 2018-06-25 21:13
 **/

public class ProjectDto {

    private String projectName;

    private String projectDescription;

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
        final StringBuilder sb = new StringBuilder("ProjectDto{");
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
