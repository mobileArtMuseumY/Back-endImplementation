package com.website.service.dto;

import com.website.common.IDUtils;
import com.website.po.Project;

import java.sql.Date;

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

    public ProjectDto(){}

    /**
     * @author: smallsoup
     * @create: 2018-06-25 21:13
     * @description: PO转DTO
     * @param project
     */
    public ProjectDto(Project project){

        this.setBudget(String.valueOf(project.getBudget()));
        this.setExpectedTime(String.valueOf(project.getExpectedTime()));
        this.setProjectDescription(project.getProjectDescription());
        this.setProjectName(project.getProjectName());
        this.setSkillList(project.getSkill());
        this.setTenderPeriod(String.valueOf(project.getTenderPeriod()));
    }

    public Project convertToProject(){
        //插入project表数据
        Project project= new Project();
        //生成主键ID
        project.setId(IDUtils.getProjectId());
        //生成项目ID
        project.setBusinessId(IDUtils.getProjectId());
        project.setProjectName(this.projectName);

        //1为审核未通过
        project.setIsVerified(Byte.valueOf("1"));
        project.setBudget(Long.valueOf(this.budget));
        project.setTenderPeriod(Integer.valueOf(this.tenderPeriod));
        project.setFinishTime(new Date(7000));
        project.setExpectedTime(Integer.valueOf(this.expectedTime));
        project.setFinishTime(new Date(7000));
        //完成时间应该不是直接获取当前时间
        project.setGmtCreate(new Date(7000));
        project.setGmtModified(new Date(7000));
        project.setProjectDescription(this.projectDescription);
        project.setSkill(this.skillList);

        return project;
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
