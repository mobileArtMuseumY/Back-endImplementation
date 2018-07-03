package com.website.dao;

import com.website.po.Project;

public interface ProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
}