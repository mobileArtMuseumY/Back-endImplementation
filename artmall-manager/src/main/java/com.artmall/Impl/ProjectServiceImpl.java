
package com.artmall.Impl;

import com.artmall.DTO.NewProjectDto;
import com.artmall.DTO.home.BrowseProjectDTO;
import com.artmall.exception.ArtmallException;
import com.artmall.exception.FileException;
import com.artmall.exception.NullException;
import com.artmall.mapper.*;
import com.artmall.DO.*;
import com.artmall.response.Const;
import com.artmall.service.*;
import com.artmall.utils.DateUtil;
import com.artmall.utils.IDUtils;
import com.artmall.DTO.projectShow.*;
import com.artmall.utils.JWTUtil;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {


    private BiddingMoreInfoDTO newMyWorks;
    @Autowired
    BusinessService businessService;
    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    SkillService skillService;

    @Override
    public Project addProjectInfo(NewProjectDto project) throws Exception {
        Project newProject = new Project();
        Business business = businessService.getBusiness();
        Long projectId = new IDUtils(2, 5).nextId();
        if (business == null)
            return null;
        if (project.getProjectAttachmentList() != null) {
            ProjectAttachment projectAttachment = project.getProjectAttachmentList().get(0);
            projectId = projectAttachment.getProjectId();
        }

        newProject.setId(projectId);
        newProject.setBusinessId(business.getId());
        newProject.setProjectName(project.getProjectName());
        newProject.setProjectDescription(project.getProjectDescription());
        newProject.setIsVerified(Const.PROJECT_BIDDING_ADD);
        newProject.setBudget(project.getBudget());
        newProject.setTenderPeriod(project.getTenderPeriod());
        newProject.setExpectedTime(project.getExpectedTime());
        newProject.setGmtCreate(new Date());
        newProject.setGmtModified(new Date());
        newProject.setFollowerCount(0);
        newProject.setFinishTime(DateUtil.getDeadline(newProject.getGmtCreate(),newProject.getTenderPeriod()));

        List<Skill> skillList = skillService.addProjectSkillList(project.getSkillList(), newProject.getId());
        newProject.setSkillList(skillList);
        try {
            projectMapper.insert(newProject);

            return newProject;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Project selectById(Long projectId) {
        Project project = projectMapper.selectByPrimaryKey(projectId);
        if (project==null){
            throw new NullException("此id没有对应的project");
        }
        project.setSkillList(skillService.getSkillListByProjectId(projectId));
        return project;
    }

    @Autowired
    BidMapper bidMapper;
    @Autowired
    WorksService worksService;

    @Override
    /**
     * 不是本人查看项目时返回的信息
     */
    public ProjectInfoToStudentDTO selectProjectInfoToOthers(Project project) throws Exception {


        ProjectInfoToStudentDTO projectInfo = (ProjectInfoToStudentDTO) setProjectInfoVO(new ProjectInfoToStudentDTO(), project);
//        projectInfoVO.setBiddingVOList(getProjectBiddingVO(project.getId()));
        List<BiddingLessInfoDTO> biddingLessInfoDTOList = getProjectBidStudentVO(project.getId());
        projectInfo.setBiddingLessInfoDTOList(biddingLessInfoDTOList);
        projectInfo.setIsSelf((byte) 1);

        projectInfo.setMyWorks(newMyWorks);
        return projectInfo;
    }

    /**
     * 获取当前学生的作品
     *
     * @return
     */
    @Autowired
    StudentService studentService;
    @Autowired
    WorksMapper worksMapper;

    private BiddingLessInfoDTO getCurrentStudentWorks(BiddingLessInfoDTO biddingLessInfoDTO) {

        newMyWorks = setBiddingMoreInfo(biddingLessInfoDTO);
        return biddingLessInfoDTO;

    }

    /**
     * +
     * 企业查看项目时返回的信息
     *
     * @param project
     * @return
     */
    @Override
    public ProjectInfoToBusinessDTO selectProjectInfoToSelf(Project project){
        ProjectInfoToBusinessDTO projectInfo = (ProjectInfoToBusinessDTO) setProjectInfoVO(new ProjectInfoToBusinessDTO(), project);
        projectInfo.setBiddingMoreInfoDTOList(getProjectBidBusinessVO(project.getId()));
        return projectInfo;
    }




    public ProjectInfoDTO setProjectInfoVO(Object object, Project project) {
        ProjectInfoDTO projectInfoDTO = (ProjectInfoDTO) object;
        Double sumPrice = Double.valueOf(0);
        List<Bid> bidList = getBidByProjectId(project.getId());
        if (bidList.size() != 0) {
            for (Bid bid : bidList) {

                Works works = worksService.selectWorksById(bid.getWorksId());
                sumPrice = sumPrice + works.getPrice();
            }
        }
        projectInfoDTO.setIsSelf((byte) 0);
        projectInfoDTO.setProjectId(project.getId());
        projectInfoDTO.setBidCount(bidList.size());
        projectInfoDTO.setBudget(project.getBudget());
        projectInfoDTO.setProjectName(project.getProjectName());
        projectInfoDTO.setAveragePrice(sumPrice / bidList.size());
        projectInfoDTO.setLeftTime(getLeftTime(project.getGmtCreate(), project.getTenderPeriod()));
        projectInfoDTO.setProjectDescription(project.getProjectDescription());
        projectInfoDTO.setExpectedTime(project.getExpectedTime());
        projectInfoDTO.setSkillList(project.getSkillList());
        return projectInfoDTO;
    }

    /**
     * 获取不是本人查看项目时的投标作品展示
     *
     * @param id
     * @return
     */
    @Autowired
    UserService userService;
    private List<BiddingLessInfoDTO> getProjectBidStudentVO(Long id) {
        //获取所有投标作品的信息（删减版）
        List<BiddingLessInfoDTO> biddingLessInfoDTOList = getProjectBiddingLessInfoVO(id);
        BiddingLessInfoDTO myWorks = new BiddingLessInfoDTO();
        newMyWorks=null;
        //如果此链表中有当前用户的作品，则移除，myWorks里面有本人上传作品的详情
        for (BiddingLessInfoDTO biddingLessInfoDTO : biddingLessInfoDTOList) {
            if(userService.getUser().getRoleId().equals(Const.ROLE_STUDENT)) {
                if (biddingLessInfoDTO.getStudentId().equals(studentService.getStudent().getId())) {
                    myWorks = getCurrentStudentWorks(biddingLessInfoDTO);

                }
            }
        }
        try{
        biddingLessInfoDTOList.remove(myWorks);
        }catch (Exception e){
            throw new ArtmallException("biddingLessInfo remove failed");
        }

        return biddingLessInfoDTOList;
    }

    private List<BiddingLessInfoDTO> getProjectBiddingLessInfoVO(Long id) {

        List<Bid> bidList = getBidByProjectId(id);
        List<BiddingLessInfoDTO> biddingLessInfoDTOList = new ArrayList<>();

        for (Bid bid : bidList) {
            BiddingLessInfoDTO biddingVO = new BiddingLessInfoDTO();
            Student student = worksService.selectStudentByWorksId(bid.getWorksId());
            Works works = worksService.selectWorksById(bid.getWorksId());
            Long studentId = worksService.selectStudentByWorksId(works.getId()).getId();
            biddingVO.setAvatar(student.getAvatar());
            biddingVO.setStudentId(studentId);
            biddingVO.setWorksId(works.getId());
            biddingVO.setLoginName(student.getLoginName());
            biddingVO.setBreakTime(student.getBreakTime());
            biddingVO.setSkill(skillService.getSkillListByStudentId(student.getId()));
            biddingVO.setCount(worksService.selectWorksByStudentId(studentId).size());
            biddingVO.setPrice(works.getPrice());
            biddingLessInfoDTOList.add(biddingVO);

        }
        return biddingLessInfoDTOList;

    }

    @Autowired
    BidService bidService;

    private List<BiddingMoreInfoDTO> getProjectBidBusinessVO(Long id) {
        List<BiddingLessInfoDTO> biddingLessInfoDTOList = getProjectBiddingLessInfoVO(id);

        List<BiddingMoreInfoDTO> biddingMoreInfoDTOList = new ArrayList<>();

        for (BiddingLessInfoDTO biddingLessInfoDTO : biddingLessInfoDTOList) {
            BiddingMoreInfoDTO biddingMoreInfoDTO = setBiddingMoreInfo(biddingLessInfoDTO);
            biddingMoreInfoDTOList.add(biddingMoreInfoDTO);
        }
        return biddingMoreInfoDTOList;
    }

    private BiddingMoreInfoDTO setBiddingMoreInfo(BiddingLessInfoDTO biddingLessInfoDTO) {

        BiddingMoreInfoDTO biddingMoreInfoDTO = new BiddingMoreInfoDTO();

        biddingMoreInfoDTO.setWorksId(biddingLessInfoDTO.getWorksId());
        biddingMoreInfoDTO.setAvatar(biddingLessInfoDTO.getAvatar());
        biddingMoreInfoDTO.setStudentId(biddingLessInfoDTO.getStudentId());
        biddingMoreInfoDTO.setLoginName(biddingLessInfoDTO.getLoginName());
        biddingMoreInfoDTO.setSkill(biddingLessInfoDTO.getSkill());
        biddingMoreInfoDTO.setCount(biddingLessInfoDTO.getCount());
        biddingMoreInfoDTO.setBreakTime(biddingLessInfoDTO.getBreakTime());
        biddingMoreInfoDTO.setPrice(biddingLessInfoDTO.getPrice());

        Works works = worksService.selectWorksById(biddingLessInfoDTO.getWorksId());
        biddingMoreInfoDTO.setWorksDescribe(works.getWorksDescribe());
        return biddingMoreInfoDTO;
    }


    private Long getLeftTime(Date beginDate, Integer tenderday) {
        Date endDate = DateUtil.getDeadline(beginDate, tenderday);
        Long day = DateUtil.getRemainDay(new Date(), endDate);
        return day;
    }

    @Override
    public void deadProject(Project project) {
        project.setIsVerified(Byte.valueOf("2"));
        projectMapper.updateByPrimaryKey(project);
    }


    @Override
    public Business selectBusinessByProjectId(Long id) throws Exception {
        Project project = selectById(id);
        try {
            return businessService.selectBusinessById(project.getBusinessId());
        } catch (Exception e) {
            throw new Exception("business 找不着");
        }


    }

    /**
     * 通过状态来选择相展示的projectList
     *
     * @param id
     * @param underReviewStatus project状态
     * @return
     */
    @Override
    public List<ProjectInfoToBusinessDTO> selectProjectStatus(Long id, byte underReviewStatus) throws Exception {
        List<Project> projectList = selectProjectByStatusAndBusinessId(id,underReviewStatus);
        List<ProjectInfoToBusinessDTO> projectInfoToBusinessDTOS = selectProjectStatusBase(projectList);
        return projectInfoToBusinessDTOS;
    }

    private List<Project> selectProjectByStatusAndBusinessId(Long id, byte underReviewStatus) {
        ProjectExample example = new ProjectExample();

        example.or()
                .andBusinessIdEqualTo(id)
                .andIsVerifiedEqualTo(underReviewStatus);

        List<Project> list = projectMapper.selectByExample(example);
        return list;
    }

    private List<ProjectInfoToBusinessDTO> selectProjectStatusBase(List<Project> projectList) {
        List<ProjectInfoToBusinessDTO> projectInfoToBusinessDTOS = new ArrayList<>();
        for (Project project : projectList) {
            ProjectInfoToBusinessDTO projectInfoToBusinessDTO = new ProjectInfoToBusinessDTO();
            projectInfoToBusinessDTO = selectProjectInfoToSelf(project);
            projectInfoToBusinessDTOS.add(projectInfoToBusinessDTO);
        }
        return projectInfoToBusinessDTOS;
    }

    @Override
    public List<Project> selectProjectByBusinessId(Long businessId) {
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andBusinessIdEqualTo(businessId);
        List<Project> projectList = projectMapper.selectByExample(example);
        return projectList;
    }

    /**
     * 改变project状态
     *
     * @param project
     * @param projectStatus
     */
    @Override
    public void setProjectStatus(Project project, byte projectStatus) {
        project.setIsVerified(projectStatus);
        project.setGmtModified(new Date());
        projectMapper.updateByPrimaryKey(project);
    }

    /**
     * 获取当前角色
     * @return
     */
    @Override
    public String getRole() {

        Subject subject = SecurityUtils.getSubject();
        String token = (String) subject.getPrincipal();
        String role = JWTUtil.getUserType(token);
        if (role==null){
            throw new ArtmallException("token有误");
        }
        return role;
    }//克鲁苏神话

    /**
     * 判断是不是本人发布的project
     *
     * @param project
     * @return
     * @throws Exception
     */
    @Override
    public boolean isSelf(Project project) {

        if (getRole().equals("Business")) {
            Business business = businessService.getBusiness();
            if (business.getId().equals(project.getBusinessId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 通过状态获取Project

     * @param projectStatusAdd
     * @return
     */
    @Override
    public List<Project> selectProjectByStatus(byte projectStatusAdd) {
        ProjectExample projectExample = new ProjectExample();
        ProjectExample.Criteria criteria = projectExample.createCriteria();
        criteria.andIsVerifiedEqualTo(projectStatusAdd);
        List<Project> projects = projectMapper.selectByExample(projectExample);
        return projects;
    }

    /**
     * 删除作品，同时删除project_attachment,project_complaint,project_skill,bid,
     *
     * @param project
     */


    @Override
    @Transactional
    public void delete(Project project) {
        try{
        projectMapper.deleteByPrimaryKey(project.getId());
        deleteProjectAllAttachment(project.getId());
        deleteProjectComplaint(project.getId());
        deleteProjectSkill(project.getId());
        deleteBid(project.getId());
        }catch (Exception e){
            //回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ArtmallException("projectId:"+project.getId()+"删除失败");
        }

    }

    private void deleteProjectAllAttachment(Long id) {
        List<ProjectAttachment> list = selectProjectAttachmentByProjectId(id);
        //删除文件夹
        
        for (ProjectAttachment projectAttachment:list){
            deleteProjectAttachment(projectAttachment);
        }
    }

    public List<ProjectAttachment> selectProjectAttachmentByProjectId(Long id) {
        ProjectAttachmentExample example = new ProjectAttachmentExample();
        ProjectAttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(id);
        List<ProjectAttachment> list = projectAttachmentMapper.selectByExample(example);
        if (list.size()<1){
            throw new NullException("此project无attachment");
        }
        return list;

    }

    @Override
    public void addProjectCollectCount(Long projectId) {
        Project project = selectById(projectId);
        project.setFollowerCount(project.getFollowerCount()+1);
        project.setGmtModified(new Date());
        projectMapper.updateByPrimaryKey(project);
    }

    @Override
    public void deleteProjectCollectCount(Long projectId) {
        Project project = selectById(projectId);
        project.setFollowerCount(project.getFollowerCount()-1);
        project.setGmtModified(new Date());
        projectMapper.updateByPrimaryKey(project);
    }



    @Override
    public List<Project> selectProject(String condition) {
        ProjectExample example = new ProjectExample();
        example.or()
                .andIsVerifiedBetween(Const.PROJECT_BIDDING_VERIFIED,Const.PROJECT_BIDDING);

        example.setOrderByClause(condition);
        List<Project> list =new ArrayList<>();
        try {
             list = projectMapper.selectByExample(example);
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public BrowseProjectDTO getBrowseProjectDto(Project project) {
        ProjectInfoDTO projectInfoDTO = setProjectInfoVO(new ProjectInfoDTO(),project);
        Business business = businessService.selectBusinessById(project.getBusinessId());
        BrowseProjectDTO browseProjectDTO = new BrowseProjectDTO();
        browseProjectDTO.setProjectId(projectInfoDTO.getProjectId());
        browseProjectDTO.setBusinessId(business.getId());
        browseProjectDTO.setAvatar(business.getAvatar());
        browseProjectDTO.setBusinessName(business.getBusinessName());
        browseProjectDTO.setProjectName(project.getProjectName());
        browseProjectDTO.setProjectDescription(project.getProjectDescription());
        browseProjectDTO.setSkillList(projectInfoDTO.getSkillList());
        browseProjectDTO.setGmtCreate(project.getGmtCreate());
        browseProjectDTO.setLeftTime(projectInfoDTO.getLeftTime());
        browseProjectDTO.setBudget(project.getBudget());
        return browseProjectDTO;
    }

    @Override
    public List<ProjectInfoToBusinessDTO> selectProjectBidding(Long id) {

        List<Project> projectList = selectProjectBiddingStatus(id);
        List<ProjectInfoToBusinessDTO> projectInfoToBusinessDTOS = selectProjectStatusBase(projectList);
        return projectInfoToBusinessDTOS;
    }

    private List<Project> selectProjectBiddingStatus(Long id) {
        ProjectExample example = new ProjectExample();

        example.or()
                .andBusinessIdEqualTo(id)
                .andIsVerifiedBetween(Const.PROJECT_BIDDING,Const.PROJECT_ORDER_BEING);

        List<Project> list = projectMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<ProjectInfoToBusinessDTO> selectProjectFailed(Long id) {
        List<Project> projectList = selectProjectFailedStatus(id);
        List<ProjectInfoToBusinessDTO> projectInfoToBusinessDTOS = selectProjectStatusBase(projectList);
        return projectInfoToBusinessDTOS;

    }

    private List<Project> selectProjectFailedStatus(Long id) {
        ProjectExample example = new ProjectExample();
        example.or()
                .andBusinessIdEqualTo(id)
                .andIsVerifiedBetween(Const.PROJECT_ORDER_FAILE,Const.PROJECT_BIDDING_NOT_VERIFIED);
        List<Project> list = projectMapper.selectByExample(example);
        return list;
    }

    @Autowired
    ProjectAttachmentMapper projectAttachmentMapper;
    private void deleteProjectAttachment(ProjectAttachment projectAttachment) {
        try {
            Files.deleteIfExists(Paths.get(projectAttachment.getAttachmentPath()));
            projectAttachmentMapper.deleteByPrimaryKey(projectAttachment.getId());
        } catch (IOException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new FileException("project attachment delete failed");
        }

    }


    private void deleteBid(Long id) {
        BidExample example = new BidExample();
        BidExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(id);
        bidMapper.deleteByExample(example);
    }



    @Autowired
    ProjectSkillMapper projectSkillMapper;
    private void deleteProjectSkill(Long id) {
        ProjectSkillExample example = new ProjectSkillExample();
        ProjectSkillExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(id);
        projectSkillMapper.deleteByExample(example);
    }

    @Autowired
    ProjectComplaintMapper projectComplaintMapper;
    private void deleteProjectComplaint(Long id) {
        ProjectComplaintExample example = new ProjectComplaintExample();
        ProjectComplaintExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(id);
        projectComplaintMapper.deleteByExample(example);
    }



    @Override
    public void update(Project project) {
        project.setGmtModified(new Date());
        projectMapper.updateByPrimaryKey(project);

    }

    @Override
    public List<Project> show(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Project> projects =  projectMapper.selectAll();
        for (Project project:projects){
            project.setSkillList(skillService.getSkillListByProjectId(project.getId()));
        }
        return projects;
    }

    @Override
    public List<Project> showVerified(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Project> projects=selectProjectByStatus(Const.PROJECT_BIDDING_ADD);
        return projects;
    }

    private List getBidByProjectId(Long id) {
        BidExample example = new BidExample();
        BidExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(id);
        List list = bidMapper.selectByExample(example);
        return list;
    }

}

