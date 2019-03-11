package com.artmall.Impl;

import com.artmall.mapper.*;
import com.artmall.DO.*;
import com.artmall.response.Const;
import com.artmall.service.*;
import com.artmall.DTO.home.*;
import com.artmall.DTO.projectShow.ProjectInfoDTO;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mllove
 * @create 2018-09-10 10:36
 **/
@Service
public class ContentServiceImpl implements ContentService {

    private final static Logger log = LoggerFactory.getLogger(ContentService.class);



    @Autowired
    ProjectMapper projectMapper;
    @Override
    public List<BrowseProjectDTO> getProjectByCount(int page, int rows) throws Exception {

        PageHelper.startPage(page,rows);
        List<Project> list = projectService.selectProject("`follower_count` desc");
        List<BrowseProjectDTO> browseProjectDTOList = new ArrayList<>();
        for (Project project:list){
            BrowseProjectDTO browseProjectDTO = projectService.getBrowseProjectDto(project);
            browseProjectDTOList.add(browseProjectDTO);
        }
        return browseProjectDTOList;
    }

    @Override
    public List<BrowseProjectDTO>
    getProjectByTime(int page, int rows) throws Exception {

        PageHelper.startPage(page,rows);

        List<BrowseProjectDTO> browseProjectDTOList = new ArrayList<>();
        //获取满足条件project list
        List<Project> projectList = projectService.selectProject("`gmt_create` desc");


        for (Project project:projectList){
            BrowseProjectDTO projectDTO = projectService.getBrowseProjectDto(project);
            browseProjectDTOList.add(projectDTO);
        }

        return browseProjectDTOList;
    }

    @Autowired
    ProjectService projectService;
    private BrowseProjectDTO setBrowseProjectVO(Project project) throws Exception {
        Business business = projectService.selectBusinessByProjectId(project.getId());
        if (business == null){
            throw new Exception("business 为null");
        }
        ProjectInfoDTO projectInfoDTO = projectService.selectProjectInfoToOthers(project);
        BrowseProjectDTO browseProjectDTO = new BrowseProjectDTO();
        try {
            browseProjectDTO.setBusinessId(business.getId());
            browseProjectDTO.setProjectId(project.getId());
            browseProjectDTO.setAvatar(business.getAvatar());
            browseProjectDTO.setBusinessName(business.getBusinessName());
            browseProjectDTO.setProjectName(projectInfoDTO.getProjectName());
            browseProjectDTO.setProjectDescription(projectInfoDTO.getProjectDescription());
            browseProjectDTO.setSkillList(projectInfoDTO.getSkillList());
            browseProjectDTO.setGmtCreate(project.getGmtCreate());
            browseProjectDTO.setLeftTime(projectInfoDTO.getLeftTime());
            browseProjectDTO.setBudget(projectInfoDTO.getBudget());
        }catch (Exception e){
            throw new Exception("setBrowseProjectVo error");
        }
        return browseProjectDTO;
    }

    @Autowired
    SkillMapper skillMapper;
    @Override
    public List getSkillList() {
        return skillMapper.getAll();
    }


    @Autowired
    ProjectSkillMapper projectSkillMapper;
    @Override
    public List<BrowseProjectDTO> getProjectBySkill(int skill, int page, int rows) throws Exception {



        PageHelper.startPage(page,rows);
        ProjectSkillExample example = new ProjectSkillExample();
        ProjectSkillExample.Criteria criteria = example.createCriteria();
        criteria.andSkillIdEqualTo(skill);
        List<ProjectSkill> list = projectSkillMapper.selectByExample(example);
        List<BrowseProjectDTO> projectList = new ArrayList<>();
        for(ProjectSkill projectSkill:list){

            Project project = projectMapper.selectByPrimaryKey(projectSkill.getProjectId());
            if (project.getIsVerified().equals(Const.PROJECT_BIDDING)){
                BrowseProjectDTO browseProjectDTO = new BrowseProjectDTO();
                project.setSkillList(skillMapper.getProjectSkill(project.getId()));
                browseProjectDTO = setBrowseProjectVO(project);
                projectList.add(browseProjectDTO);
            }
        }
        return projectList;
    }

    @Autowired
    WorksMapper worksMapper;
    @Autowired
    FavoriteService favoriteService;
    @Override
    public List<WorksDTO> getWorksByType(int page, int rows, String type){
       PageHelper.startPage(page,rows);

        WorksExample example = new WorksExample();
        WorksExample.Criteria criteria = example.createCriteria();
        criteria.andWorksStatusEqualTo(Const.WORKS_STATUS_SHOW);
        example.setOrderByClause(type);
        List<Works> list = worksMapper.selectByExample(example);
        List<WorksDTO> worksDTOList = new ArrayList<>();

        for (Works works:list){
            WorksDTO worksDTO = new WorksDTO();
            worksDTO.setId(works.getId());
            worksDTO.setWorkName(works.getWorksName());
            worksDTO.setFavoriteCount(favoriteService.getWorksCount(works.getId()));
            worksDTO.setAttachmentShowName(works.getAttachmentShowName());
            worksDTO.setAttachmentShowPath(works.getAttachmentShowPath());
            worksDTOList.add(worksDTO);
        }
        return worksDTOList;
    }

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    StudentService studentService;
    @Override
    public List<StudentsShowDTO> getStudentsList(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Student> list =studentService.getAllStudent();
        List<StudentsShowDTO> studentsShowDTOList = new LinkedList<>();

        for (Student student:list) {
            StudentsShowDTO studentsShowDTO = new StudentsShowDTO();
            studentsShowDTO.setId(student.getId());
            studentsShowDTO.setAvatar(student.getAvatar());
            studentsShowDTO.setLoginName(student.getLoginName());
            studentsShowDTO.setIntroduction(student.getIntroduction());
            studentsShowDTO.setFollowerCount(student.getFollowerCount());
            studentsShowDTO.setWorksDTOList(getWorksVOList(student.getId()));

            studentsShowDTOList.add(studentsShowDTO);
        }
        return studentsShowDTOList;
    }


    private List<WorksDTO> getWorksVOList(Long studentId) {
        List<Works> worksList = worksService.selectWorksByStudentId(studentId);

        List<WorksDTO> worksDTOList = new ArrayList<>();
        for (Works works :worksList){
            WorksDTO worksDTO = new WorksDTO();
            worksDTO.setId(works.getId());
            worksDTO.setAttachmentShowPath(works.getAttachmentShowPath());
            worksDTO.setAttachmentShowName(works.getAttachmentShowName());
            worksDTOList.add(worksDTO);
        }
        return worksDTOList;
    }

    @Autowired
    WorksService worksService;
    @Override
    public WorksInfoDTO getWorksInfoByWorksId(Long worksId) throws Exception {
        List<WorksAttachment> worksAttachmentList = worksService.selectAttachmentByWorks(worksId);
        Student student = worksService.selectStudentByWorksId(worksId);
        Works works = worksService.selectWorksById(worksId);
        List<WaterPictureDTO> waterPictureDTOList = new ArrayList<>();
        if (worksAttachmentList.size()!=0) {
            for (WorksAttachment worksAttachment : worksAttachmentList) {
                WaterPictureDTO waterPictureDTO = new WaterPictureDTO();
                waterPictureDTO.setId(worksAttachment.getId());
                waterPictureDTO.setAttachmentName(worksAttachment.getAttachmentName());
                waterPictureDTO.setAttachmentWatermarkPath(worksAttachment.getAttachmentWatermarkPath());
                waterPictureDTOList.add(waterPictureDTO);
            }
        }
        try {

            WorksInfoDTO worksInfoDTO = new WorksInfoDTO();
            worksInfoDTO.setWorksId(works.getId());
            worksInfoDTO.setAvatar(student.getAvatar());
            worksInfoDTO.setLoginName(student.getLoginName());
            worksInfoDTO.setWorksName(works.getWorksName());
            worksInfoDTO.setWorksDescribe(works.getWorksDescribe());
            worksInfoDTO.setPrice(works.getPrice());
            worksInfoDTO.setWaterPictureDTOList(waterPictureDTOList);
            worksInfoDTO.setStudentId(student.getId());
            return worksInfoDTO;
        }catch (Exception e){
            throw new Exception("works数据有问题");
        }
    }

    /**
     * 获取所有project的数量
     * @return
     */
    @Override
    public Long getAllProjectCount() {
        ProjectExample example = new ProjectExample();
        example.or()
                .andIsVerifiedBetween(Const.PROJECT_BIDDING_VERIFIED,Const.PROJECT_BIDDING);
        Long num = projectMapper.countByExample(example);
        return num;
    }

    @Override
    public Long getAllStudentCount() {
        StudentExample example = new StudentExample();
        example.setDistinct(true);
        Long num = studentMapper.countByExample(example);
        return num;
    }

    @Autowired
    BusinessMapper businessMapper;
    @Override
    public Long getAllBusinessCount() {
        BusinessExample example = new BusinessExample();
        example.setDistinct(true);
        Long num = businessMapper.countByExample(example);
        return num;
    }

    @Override
    public Long getAllProVerCount() {
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andIsVerifiedEqualTo(Const.PROJECT_BIDDING_ADD);
        example.setDistinct(true);
        Long num = projectMapper.countByExample(example);
        return num;
    }

    @Override
    public Long getAllBusVerCount() {
        BusinessExample example = new BusinessExample();
        BusinessExample.Criteria criteria = example.createCriteria();
        criteria.andIsVerifiedEqualTo(Const.BUSINESS_EMAIL);
        example.setDistinct(true);
        Long num = businessMapper.countByExample(example);
        return num;
    }

    @Autowired
    OrderFormMapper orderFormMapper;
    @Override
    public Long getAllOrderCount() {
        OrderFormExample example = new OrderFormExample();
        example.setDistinct(true);
        Long num = orderFormMapper.countByExample(example);
        return num;
    }

    /**
     * 获取works总数
     * @return
     */
    @Override
    public Long getAllWorksCount() {
        WorksExample example = new WorksExample();
        example.or()
                .andWorksStatusEqualTo(Const.WORKS_STATUS_SHOW);

        Long num = worksMapper.countByExample(example);
        return num;
    }

    private List<Works> getWorksList(Long studentId) {
        WorksExample example = new WorksExample();
        WorksExample.Criteria criteria = example.createCriteria();
        criteria.andStudentIdEqualTo(studentId);
        List<Works> list = worksMapper.selectByExample(example);
        return list;
    }


    @Autowired
    WorksAttachmentMapper worksAttachmentMapper;
    private List<WorksAttachment> getAttachmentByWorksId(Long worksId){
        WorksAttachmentExample example = new WorksAttachmentExample();
        WorksAttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andWorksIdEqualTo(worksId);
        List list= worksAttachmentMapper.selectByExample(example);
        return list;

    }






}
