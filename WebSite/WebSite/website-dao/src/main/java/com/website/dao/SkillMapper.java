package com.website.dao;

import com.website.pojo.Skill;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Skill record);

    int insertSelective(Skill record);

    Skill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Skill record);

    int updateByPrimaryKey(Skill record);
}