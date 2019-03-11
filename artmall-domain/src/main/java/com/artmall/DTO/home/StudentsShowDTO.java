package com.artmall.DTO.home;

import java.util.List;

/** 人才排行榜要展示的学生信息
 * @author mllove
 * @create 2018-09-27 21:21
 **/

public class StudentsShowDTO {
    private Long id;
    private String avatar;
    private String loginName;
    private String introduction;
    private Integer followerCount;
    private List<WorksDTO> worksDTOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public List<WorksDTO> getWorksDTOList() {
        return worksDTOList;
    }

    public void setWorksDTOList(List<WorksDTO> worksDTOList) {
        this.worksDTOList = worksDTOList;
    }

    @Override
    public String toString() {
        return "StudentsShowVO{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", loginName='" + loginName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", followerCount=" + followerCount +
                ", worksVOList=" + worksDTOList +
                '}';
    }
}
