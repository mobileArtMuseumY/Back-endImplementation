package com.website.controller.vo;

import com.website.service.dto.ProjectDto;

public class ProjectVo {

    private String projectName;

    private String projectDescription;
    private String tenderPeriod;

    private String budget;

    private String expectedTime;

    private String attachmentList;

    private String skillList;

    public ProjectVo() {
    }

    public ProjectVo(ProjectDto projectDto) {

        this.setAttachmentList(projectDto.getAttachmentList());
        this.setBudget(projectDto.getBudget());
        this.setExpectedTime(projectDto.getExpectedTime());
        this.setProjectDescription(projectDto.getProjectDescription());
        this.setProjectName(projectDto.getProjectName());
        this.setSkillList(projectDto.getSkillList());
        this.setTenderPeriod(projectDto.getTenderPeriod());
    }

    public ProjectDto convertProjectDto() {

        ProjectDto projectDto = new ProjectDto();

        projectDto.setTenderPeriod(this.tenderPeriod);
        projectDto.setSkillList(this.skillList);
        projectDto.setAttachmentList(this.attachmentList);
        projectDto.setBudget(this.budget);
        projectDto.setProjectDescription(this.projectDescription);
        projectDto.setExpectedTime(this.expectedTime);
        projectDto.setProjectName(this.projectName);
        projectDto.setTenderPeriod(this.tenderPeriod);

        return projectDto;
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
