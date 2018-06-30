package com.website.po;

import java.util.Date;

public class StudentAttachment {
    private Long id;

    private String attachmentName;

    private String attachmentPath;

    private Long attachmentSize;

    private Long studentId;

    private Date gmtCreate;

    private Date gmtModified;

    public StudentAttachment(Long id, String attachmentName, String attachmentPath, Long attachmentSize, Long studentId, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.attachmentName = attachmentName;
        this.attachmentPath = attachmentPath;
        this.attachmentSize = attachmentSize;
        this.studentId = studentId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public StudentAttachment() {
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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