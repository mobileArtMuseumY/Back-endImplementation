package com.website.dao;

import com.website.po.Skill;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Skill record);

    int insertSelective(Skill record);

    Skill selectByPrimaryKey(Long id);

    String selectNameByPrimaryKey(Long id);

    List<String> selectNameByPrimaryKeys(List<Long> ids);

    Long selectCountByPrimaryKeys(List<Long> ids);

    int updateByPrimaryKeySelective(Skill record);

    int updateByPrimaryKey(Skill record);
}