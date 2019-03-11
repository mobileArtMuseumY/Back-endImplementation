package com.artmall.mapper;

import com.artmall.DO.resourceRole;
import com.artmall.DO.resourceRoleExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface resourceRoleMapper {
    long countByExample(resourceRoleExample example);

    int deleteByExample(resourceRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(resourceRole record);

    int insertSelective(resourceRole record);

    List<resourceRole> selectByExample(resourceRoleExample example);

    resourceRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") resourceRole record, @Param("example") resourceRoleExample example);

    int updateByExample(@Param("record") resourceRole record, @Param("example") resourceRoleExample example);

    int updateByPrimaryKeySelective(resourceRole record);

    int updateByPrimaryKey(resourceRole record);
}