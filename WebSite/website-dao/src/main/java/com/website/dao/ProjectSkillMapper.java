package com.website.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.website.pojo.ProjectSkill;
import com.website.pojo.ProjectSkillExample;

public interface ProjectSkillMapper {
    long countByExample(ProjectSkillExample example);

    int deleteByExample(ProjectSkillExample example);

    int insert(ProjectSkill record);

    int insertSelective(ProjectSkill record);

    List<ProjectSkill> selectByExample(ProjectSkillExample example);

    int updateByExampleSelective(@Param("record") ProjectSkill record, @Param("example") ProjectSkillExample example);

    int updateByExample(@Param("record") ProjectSkill record, @Param("example") ProjectSkillExample example);
}