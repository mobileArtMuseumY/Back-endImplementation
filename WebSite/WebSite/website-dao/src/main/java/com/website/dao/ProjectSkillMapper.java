package com.website.dao;

import com.website.pojo.ProjectSkill;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectSkillMapper {
    int insert(ProjectSkill record);

    int insertSelective(ProjectSkill record);
}