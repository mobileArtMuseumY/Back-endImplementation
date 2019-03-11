package com.artmall.DO;

import java.util.Date;

public class Works {
    private Long id;

    private Long studentId;

    private String worksName;

    private String worksDescribe;

    private Byte worksStatus;

    private Long price;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer followerCount;

    private String attachmentShowName;

    private String attachmentShowPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getWorksName() {
        return worksName;
    }

    public void setWorksName(String worksName) {
        this.worksName = worksName == null ? null : worksName.trim();
    }

    public String getWorksDescribe() {
        return worksDescribe;
    }

    public void setWorksDescribe(String worksDescribe) {
        this.worksDescribe = worksDescribe == null ? null : worksDescribe.trim();
    }

    public Byte getWorksStatus() {
        return worksStatus;
    }

    public void setWorksStatus(Byte worksStatus) {
        this.worksStatus = worksStatus;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public String getAttachmentShowName() {
        return attachmentShowName;
    }

    public void setAttachmentShowName(String attachmentShowName) {
        this.attachmentShowName = attachmentShowName == null ? null : attachmentShowName.trim();
    }

    public String getAttachmentShowPath() {
        return attachmentShowPath;
    }

    public void setAttachmentShowPath(String attachmentShowPath) {
        this.attachmentShowPath = attachmentShowPath == null ? null : attachmentShowPath.trim();
    }

    @Override
    public String toString() {
        return "Works{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", worksName='" + worksName + '\'' +
                ", worksDescribe='" + worksDescribe + '\'' +
                ", worksStatus=" + worksStatus +
                ", price=" + price +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", followerCount=" + followerCount +
                ", attachmentShowName='" + attachmentShowName + '\'' +
                ", attachmentShowPath='" + attachmentShowPath + '\'' +
                '}';
    }
}