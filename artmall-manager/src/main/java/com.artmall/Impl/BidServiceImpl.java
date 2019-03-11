package com.artmall.Impl;

import com.artmall.mapper.BidMapper;
import com.artmall.mapper.ProjectMapper;
import com.artmall.DO.*;
import com.artmall.response.Const;
import com.artmall.service.BidService;
import com.artmall.service.StudentService;
import com.artmall.DTO.studentHome.StudentBiddingProjectDTO;
import com.artmall.service.WorksService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mllove
 * @create 2018-09-17 17:42
 **/
@Service
public class BidServiceImpl implements BidService {

    @Override
    public List<Works> getWorksList(Long projectId) {
        return null;
    }

    @Autowired
    BidMapper bidMapper;
    @Autowired
    StudentService studentService;

    @Autowired
    WorksService worksService;
    @Override
    public Bid addBidById(Long projectId, Long worksId) throws Exception {
        Student student = studentService.getStudent();
        if (isRepeatBid(projectId,student.getId())){
            throw new Exception("同一个project，只能投标一次");
        }
        Bid bid = new Bid();

        bid.setStudentId(student.getId());
        bid.setProjectId(projectId);
        bid.setWorksId(worksId);
        bid.setStatus(Const.BID_STATUS_BIDDING);
        bid.setGmtCreate(new Date());
        bid.setGmtModified(new Date());
        bidMapper.insert(bid);

        //修改投标作品的状态，正在投标的作品是不能展示的
        Works works =worksService.selectWorksById(worksId);
        worksService.setWorksStatus(works,Const.WORKS_STATUS_BIDDING);
        return bid;
    }

    /**
     * 一个项目不可重复投标，判断是否是重复投标
     * @param projectId
     * @param studentId
     * @return
     */
    private boolean isRepeatBid(Long projectId, Long studentId) {
        BidExample example = new BidExample();
        BidExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        criteria.andStudentIdEqualTo(studentId);
        int worksNum = (int) bidMapper.countByExample(example);
        if (worksNum == 0||worksNum == 1)
            return false;
        else
            return true;
    }

    /**
     * 投标的作品
     * @param studentId
     * @return
     */
    @Override
    public List<StudentBiddingProjectDTO> setStudentBidingProject(Long studentId) {

        List<Project> projectList = getProjectList(studentId);
        List<StudentBiddingProjectDTO> studentBiddingProjectDTOList = new ArrayList<>();

        for (Project project :projectList){

            Bid bid = selectBidByStudentIdAndProjectId(studentId,project.getId());
            StudentBiddingProjectDTO studentBiddingProjectDTO = new StudentBiddingProjectDTO();
            studentBiddingProjectDTO.setStudentId(studentId);
            studentBiddingProjectDTO.setProjectId(project.getId());
            studentBiddingProjectDTO.setProjectDescription(project.getProjectDescription());
            studentBiddingProjectDTO.setStatus(Integer.valueOf(bid.getStatus()));
            studentBiddingProjectDTOList.add(studentBiddingProjectDTO);
        }
        return studentBiddingProjectDTOList;
    }

    public Bid selectBidByStudentIdAndProjectId(Long studentId, Long projectId) {
        BidExample example = new BidExample();
        BidExample.Criteria criteria = example.createCriteria();
        criteria.andStudentIdEqualTo(studentId);
        criteria.andProjectIdEqualTo(projectId);
        List<Bid> bidList = bidMapper.selectByExample(example);
        if (bidList.size()==0){
            return null;
        }
        return bidList.get(0);
    }

    /**
     * bid的状态改变
     * @param bid
     * @param bidStatus
     */
    @Override
    public void setBidStatus(Bid bid, byte bidStatus) {
        bid.setStatus(bidStatus);
        bid.setGmtModified(new Date());
        bidMapper.updateByPrimaryKey(bid);
    }

    @Override
    public List<Bid> selectBidByProjectId(Long id) {
        BidExample example = new BidExample();
        BidExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(id);
        List<Bid> list = bidMapper.selectByExample(example);
        return list;
    }

    @Override
    public void setProjectBidStatus(Long id) {
        List<Bid> bids =selectBidByProjectId(id);
        if (bids.size()!=0){
            for (Bid deleteBid:bids){
                setBidStatus(deleteBid,Const.BID_STATUS_FAILE);
                Works deleteWorks = worksService.selectWorksById(deleteBid.getWorksId());
                worksService.setWorksStatus(deleteWorks,Const.WORKS_STATUS_SHOW);
            }
        }
    }


    /**
     * 通过studentId获取Project列表
     * @param studentId
     * @return
     */
    @Autowired
    ProjectMapper projectMapper;
    private List<Project> getProjectList(Long studentId) {
        List<Project> projectList = projectMapper.selectProjectByStudentId(studentId);
        return projectList;
    }


}
