package com.sparrow.artmuseum.pojo;

import java.util.Date;

public class Project {
    private Long id;

    private Long businessId;

    private String projectName;

    private String projectDescription;

    private Byte isVaried;

    private Long budget;

    private Integer tenderPeriod;

    private Integer expectedTime;

    private Date finishTime;

    private Date gmtCreate;

    private Date gmtModified;

    public Project(Long id, Long businessId, String projectName, String projectDescription, Byte isVaried, Long budget, Integer tenderPeriod, Integer expectedTime, Date finishTime, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.businessId = businessId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.isVaried = isVaried;
        this.budget = budget;
        this.tenderPeriod = tenderPeriod;
        this.expectedTime = expectedTime;
        this.finishTime = finishTime;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public Project() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription == null ? null : projectDescription.trim();
    }

    public Byte getIsVaried() {
        return isVaried;
    }

    public void setIsVaried(Byte isVaried) {
        this.isVaried = isVaried;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public Integer getTenderPeriod() {
        return tenderPeriod;
    }

    public void setTenderPeriod(Integer tenderPeriod) {
        this.tenderPeriod = tenderPeriod;
    }

    public Integer getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Integer expectedTime) {
        this.expectedTime = expectedTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}