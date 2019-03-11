package com.artmall.DTO.projectShow;

import com.artmall.DO.Skill;

import java.util.List;

/**
 * @author mllove
 * @create 2018-09-23 15:33
 **/

public class ProjectInfoDTO {



    private  Long projectId;

    private String projectName;
    private Integer bidCount;

    private Long budget;

    private Double averagePrice;

    private Long leftTime;

    private String projectDescription;

    private Integer expectedTime;

    private List<Skill> skillList;


    private byte isSelf;


    @Override
    public String toString() {
        return "ProjectInfoDTO{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", bidCount=" + bidCount +
                ", budget=" + budget +
                ", averagePrice=" + averagePrice +
                ", leftTime=" + leftTime +
                ", projectDescription='" + projectDescription + '\'' +
                ", expectedTime=" + expectedTime +
                ", skillList=" + skillList +
                ", isSelf=" + isSelf +
                '}';
    }


    public byte getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(byte isSelf) {
        this.isSelf = isSelf;
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

    public Integer getBidCount() {
        return bidCount;
    }

    public void setBidCount(Integer bidCount) {
        this.bidCount = bidCount;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Long getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(Long leftTime) {
        this.leftTime = leftTime;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Integer getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Integer expectedTime) {
        this.expectedTime = expectedTime;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }
}
