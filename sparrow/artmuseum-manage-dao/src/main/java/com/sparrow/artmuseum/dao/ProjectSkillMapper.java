package com.sparrow.artmuseum.dao;

import com.sparrow.artmuseum.pojo.ProjectSkill;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectSkillMapper {
    int insert(ProjectSkill record);

    int insertSelective(ProjectSkill record);
}