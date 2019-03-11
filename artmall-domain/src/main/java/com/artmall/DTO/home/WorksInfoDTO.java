package com.artmall.DTO.home;

import java.util.List;

/**
 * 发现项目，project展示的详细界面
 *
 * @author mllove
 * @create 2018-09-27 20:46
 **/

public class WorksInfoDTO {

    private Long worksId;

    private String worksName;

    private String worksDescribe;

    private String avatar;

    private String loginName;

    private Long price;

    private Long studentId;

    private Byte isCollect;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Byte getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Byte isCollect) {
        this.isCollect = isCollect;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    private List<WaterPictureDTO> waterPictureDTOList;

    public Long getWorksId() {
        return worksId;
    }

    public void setWorksId(Long worksId) {
        this.worksId = worksId;
    }

    public String getWorksName() {
        return worksName;
    }

    public void setWorksName(String worksName) {
        this.worksName = worksName;
    }

    public String getWorksDescribe() {
        return worksDescribe;
    }

    public void setWorksDescribe(String worksDescribe) {
        this.worksDescribe = worksDescribe;
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

    public List<WaterPictureDTO> getWaterPictureDTOList() {
        return waterPictureDTOList;
    }

    public void setWaterPictureDTOList(List<WaterPictureDTO> waterPictureDTOList) {
        this.waterPictureDTOList = waterPictureDTOList;
    }
}
