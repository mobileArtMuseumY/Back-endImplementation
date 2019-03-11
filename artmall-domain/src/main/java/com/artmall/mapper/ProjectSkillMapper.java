package com.artmall.mapper;

import com.artmall.DO.ProjectSkill;
import com.artmall.DO.ProjectSkillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectSkillMapper {
    long countByExample(ProjectSkillExample example);

    int deleteByExample(ProjectSkillExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectSkill record);

    int insertSelective(ProjectSkill record);

    List<ProjectSkill> selectByExample(ProjectSkillExample example);

    ProjectSkill selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectSkill record, @Param("example") ProjectSkillExample example);

    int updateByExample(@Param("record") ProjectSkill record, @Param("example") ProjectSkillExample example);

    int updateByPrimaryKeySelective(ProjectSkill record);

    int updateByPrimaryKey(ProjectSkill record);
}