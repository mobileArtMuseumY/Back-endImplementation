package com.artmall.DTO.projectShow;

import java.util.List;

/**
 * 学生看到的
 *
 * @author mllove
 * @create 2018-09-24 18:16
 **/

public class ProjectInfoToStudentDTO extends ProjectInfoDTO {
    private Byte isCollect;
    private List<BiddingLessInfoDTO> biddingLessInfoDTOList;

    private BiddingMoreInfoDTO myWorks;

    public BiddingMoreInfoDTO getMyWorks() {
        return myWorks;
    }

    public Byte getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Byte isCollect) {
        this.isCollect = isCollect;
    }

    public void setMyWorks(BiddingMoreInfoDTO myWorks) {
        this.myWorks = myWorks;
    }

    public List<BiddingLessInfoDTO> getBiddingLessInfoDTOList() {
        return biddingLessInfoDTOList;
    }

    public void setBiddingLessInfoDTOList(List<BiddingLessInfoDTO> biddingLessInfoDTOList) {
        this.biddingLessInfoDTOList = biddingLessInfoDTOList;
    }
}
