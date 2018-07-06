package com.website.dao;

import com.website.pojo.ResourceRole;
import com.website.pojo.ResourceRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceRoleMapper {
    long countByExample(ResourceRoleExample example);

    int deleteByExample(ResourceRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ResourceRole record);

    int insertSelective(ResourceRole record);

    List<ResourceRole> selectByExample(ResourceRoleExample example);

    ResourceRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ResourceRole record, @Param("example") ResourceRoleExample example);

    int updateByExample(@Param("record") ResourceRole record, @Param("example") ResourceRoleExample example);

    int updateByPrimaryKeySelective(ResourceRole record);

    int updateByPrimaryKey(ResourceRole record);
}