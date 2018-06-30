package com.website.po;

import java.util.Date;

public class BusinessAttachment {
    private Long id;

    private String attachmentName;

    private String attachmentPath;

    private Long attachmentSize;

    private Long businessId;

    private Date gmtCreate;

    private Date gmtModified;

    public BusinessAttachment(Long id, String attachmentName, String attachmentPath, Long attachmentSize, Long businessId, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.attachmentName = attachmentName;
        this.attachmentPath = attachmentPath;
        this.attachmentSize = attachmentSize;
        this.businessId = businessId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public BusinessAttachment() {
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

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
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