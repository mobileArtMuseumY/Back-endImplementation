package com.sparrow.artmuseum.dao;

import com.sparrow.artmuseum.pojo.Project;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
}