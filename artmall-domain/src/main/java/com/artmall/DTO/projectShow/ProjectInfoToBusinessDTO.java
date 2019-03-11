package com.artmall.DTO.projectShow;

import java.util.List;

/**
 * 企业看到的详细信息
 *
 * @author mllove
 * @create 2018-09-24 18:15
 **/

public class ProjectInfoToBusinessDTO extends ProjectInfoDTO {

    private List<BiddingMoreInfoDTO> biddingMoreInfoDTOList;

    public List<BiddingMoreInfoDTO> getBiddingMoreInfoDTOList() {
        return biddingMoreInfoDTOList;
    }

    public void setBiddingMoreInfoDTOList(List<BiddingMoreInfoDTO> biddingMoreInfoDTOList) {
        this.biddingMoreInfoDTOList = biddingMoreInfoDTOList;
    }
}
