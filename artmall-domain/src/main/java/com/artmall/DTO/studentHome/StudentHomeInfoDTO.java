package com.artmall.DTO.studentHome;

import com.artmall.DO.Skill;

import java.util.List;

/**
 * 学生个人主页个人信息
 *
 * @author mllove
 * @create 2018-10-01 19:08
 **/

public class StudentHomeInfoDTO {

    private Long id;

    private String avatar;

    private String studentId;

    private String loginName;

    //关注我的人总数
    private Integer followerCount;
    //我关注的人总数
    private Integer followingCount;

    private Integer breakTime;

    private Integer orderCount;

    private String introduction;

    private Byte isFollowed;

    private List<Skill> skillList;

    public Byte getIsFollowed() {
        return isFollowed;
    }

    public void setIsFollowed(Byte isFollowed) {
        this.isFollowed = isFollowed;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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

    public Integer getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(Integer breakTime) {
        this.breakTime = breakTime;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }
}
