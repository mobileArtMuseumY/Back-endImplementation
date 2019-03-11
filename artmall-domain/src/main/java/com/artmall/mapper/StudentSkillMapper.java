package com.artmall.mapper;

import com.artmall.DO.StudentSkill;
import com.artmall.DO.StudentSkillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentSkillMapper {
    long countByExample(StudentSkillExample example);

    int deleteByExample(StudentSkillExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentSkill record);

    int insertSelective(StudentSkill record);

    List<StudentSkill> selectByExample(StudentSkillExample example);

    StudentSkill selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentSkill record, @Param("example") StudentSkillExample example);

    int updateByExample(@Param("record") StudentSkill record, @Param("example") StudentSkillExample example);

    int updateByPrimaryKeySelective(StudentSkill record);

    int updateByPrimaryKey(StudentSkill record);
}