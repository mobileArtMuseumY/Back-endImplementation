package com.artmall.DTO.businessHome;

/**
 * @author mllove
 * @create 2018-10-16 20:36
 **/

public class BusinessHomeInfoDTO {
    private Long id;

    //发布项目数
    private Integer projectCount;

    //关注我的人总数
    private Integer followerCount;
    //我关注的人总数
    private Integer followingCount;

    private String introduction;

    private String avatar;

    private String businessName;

    private String code;

    private Byte isFollowed;


    public Byte getIsFollowed() {
        return isFollowed;
    }

    public void setIsFollowed(Byte isFollowed) {
        this.isFollowed = isFollowed;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public Integer getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
