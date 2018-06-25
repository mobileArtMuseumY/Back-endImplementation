package com.sparrow.artmuseum.pojo;

import java.util.Date;

public class ProjectAttachment {
    private Long id;

    private String attachmentName;

    private String attachmentPath;

    private Long attachmentSize;

    private Long projectId;

    private Date gmtCreate;

    private Date gmtModified;

    public ProjectAttachment(Long id, String attachmentName, String attachmentPath, Long attachmentSize, Long projectId, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.attachmentName = attachmentName;
        this.attachmentPath = attachmentPath;
        this.attachmentSize = attachmentSize;
        this.projectId = projectId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public ProjectAttachment() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName == null ? null : attachmentName.trim();
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath == null ? null : attachmentPath.trim();
    }

    public Long getAttachmentSize() {
        return attachmentSize;
    }

    public void setAttachmentSize(Long attachmentSize) {
        this.attachmentSize = attachmentSize;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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