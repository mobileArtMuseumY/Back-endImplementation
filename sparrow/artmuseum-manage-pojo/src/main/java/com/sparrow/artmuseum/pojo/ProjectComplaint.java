package com.sparrow.artmuseum.pojo;

import java.util.Date;

public class ProjectComplaint {
    private Long id;

    private Long projectId;

    private String complaintDescription;

    private Date gmtCreate;

    private Date gmtModified;

    public ProjectComplaint(Long id, Long projectId, String complaintDescription, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.projectId = projectId;
        this.complaintDescription = complaintDescription;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public ProjectComplaint() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription == null ? null : complaintDescription.trim();
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