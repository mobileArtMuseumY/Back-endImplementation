package com.artmall.service;

import com.artmall.DTO.home.BrowseProjectDTO;
import com.artmall.DTO.home.StudentsShowDTO;
import com.artmall.DTO.home.WorksInfoDTO;
import com.artmall.DTO.home.WorksDTO;

import java.util.List;

/**
 * 面板控制
 *
 * @author
 * @create 2018-09-10 10:36
 **/

public interface ContentService {


    /**
     * 获取首页数据
     * @return
     */
//    List<Works> getHome(int page,int rows);

    /**
     * 分页按照关注度排序
     * @return
     */
    List<BrowseProjectDTO> getProjectByCount(int page, int rows) throws Exception;

    /**
     * 分页按照发布时间排序
     * @return
     */
    List<BrowseProjectDTO> getProjectByTime(int page, int rows) throws Exception;

    /**
     * 获取所有skill的列表
     * @return
     */
    List getSkillList();

    /**
     * 通过skill来搜索project
     * @param page
     * @param rows
     * @return
     */
    List<BrowseProjectDTO> getProjectBySkill(int skill, int page, int rows) throws Exception;


    List<WorksDTO> getWorksByType(int page, int rows, String type);

    List<StudentsShowDTO> getStudentsList(int page, int rows);

    WorksInfoDTO getWorksInfoByWorksId(Long worksId) throws Exception;

    Long getAllWorksCount();

    Long getAllProjectCount();

    Long getAllStudentCount();

    Long getAllBusinessCount();

    Long getAllProVerCount();

    Long getAllBusVerCount();

    Long getAllOrderCount();
}
