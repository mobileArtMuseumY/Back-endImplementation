package com.sparrow.artmuseum.dao;

import com.sparrow.artmuseum.pojo.ProjectComplaint;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectComplaintMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectComplaint record);

    int insertSelective(ProjectComplaint record);

    ProjectComplaint selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectComplaint record);

    int updateByPrimaryKey(ProjectComplaint record);
}