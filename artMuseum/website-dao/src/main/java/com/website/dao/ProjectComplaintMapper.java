package com.website.dao;

import com.website.pojo.ProjectComplaint;
import com.website.pojo.ProjectComplaintExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectComplaintMapper {
    long countByExample(ProjectComplaintExample example);

    int deleteByExample(ProjectComplaintExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProjectComplaint record);

    int insertSelective(ProjectComplaint record);

    List<ProjectComplaint> selectByExample(ProjectComplaintExample example);

    ProjectComplaint selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProjectComplaint record, @Param("example") ProjectComplaintExample example);

    int updateByExample(@Param("record") ProjectComplaint record, @Param("example") ProjectComplaintExample example);

    int updateByPrimaryKeySelective(ProjectComplaint record);

    int updateByPrimaryKey(ProjectComplaint record);
}