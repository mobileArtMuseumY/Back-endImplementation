package com.artmall.DO;

import java.util.Date;

public class UserRelation {
    private Long id;

    private Long followedId;

    private Byte relation;

    private Date gmtCreate;

    private Date gmtModified;

    private Long followingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Long followedId) {
        this.followedId = followedId;
    }

    public Byte getRelation() {
        return relation;
    }

    public void setRelation(Byte relation) {
        this.relation = relation;
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

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }
}