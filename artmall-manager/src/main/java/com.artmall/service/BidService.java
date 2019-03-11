package com.artmall.service;

import com.artmall.DO.Bid;
import com.artmall.DO.Works;
import com.artmall.DTO.studentHome.StudentBiddingProjectDTO;

import java.util.List;

/**
 * 投标管理
 *
 * @author mllove
 * @create 2018-09-17 17:41
 **/

public interface BidService {
    List<Works> getWorksList(Long projectId);

    Bid addBidById(Long projectId, Long worksId) throws Exception;

    List<StudentBiddingProjectDTO> setStudentBidingProject(Long id);

    Bid selectBidByStudentIdAndProjectId(Long studentId, Long projectId);

    void setBidStatus(Bid bid, byte bidStatus);

    List<Bid> selectBidByProjectId(Long id);

    void setProjectBidStatus(Long id);
}
