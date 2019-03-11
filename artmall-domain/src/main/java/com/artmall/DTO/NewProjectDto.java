package com.artmall.DTO;

import com.artmall.DO.ProjectAttachment;
import com.artmall.DO.Skill;

import java.util.List;

/**
 * 发布项目的表单
 *
 * @author mllove
 * @create 2018-09-22 17:02
 **/

public class NewProjectDto {


    private String projectName;

    private String projectDescription;

    private List<Skill> skillList;

    private Integer tenderPeriod;

    private Long budget;

    private Integer expectedTime;

    private List<ProjectAttachment> projectAttachmentList;



    public List<ProjectAttachment> getProjectAttachmentList() {
        return projectAttachmentList;
    }

    public void setProjectAttachmentList(List<ProjectAttachment> projectAttachmentList) {
        this.projectAttachmentList = projectAttachmentList;
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

    public Integer getTenderPeriod() {
        return tenderPeriod;
    }

    public void setTenderPeriod(Integer tenderPeriod) {
        this.tenderPeriod = tenderPeriod;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public Integer getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Integer expectedTime) {
        this.expectedTime = expectedTime;
    }
}
