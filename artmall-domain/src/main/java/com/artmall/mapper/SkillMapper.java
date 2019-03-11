package com.artmall.mapper;

import com.artmall.DO.Skill;
import com.artmall.DO.SkillExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SkillMapper {
    long countByExample(SkillExample example);

    int deleteByExample(SkillExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Skill record);

    int insertSelective(Skill record);

    List<Skill> selectByExample(SkillExample example);

    Skill selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Skill record, @Param("example") SkillExample example);

    int updateByExample(@Param("record") Skill record, @Param("example") SkillExample example);

    int updateByPrimaryKeySelective(Skill record);

    int updateByPrimaryKey(Skill record);

    List<Skill> getAll();

    List<Skill> getProjectSkill(@Param("projectId")Long project);

    List<Skill> getStudentSkill(@Param("studentId") Long studentId);
}