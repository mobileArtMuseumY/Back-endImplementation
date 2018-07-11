package com.website.dao;

import com.website.po.Business;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface BusinessMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Business record);

    int insertSelective(Business record);

    Business selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);

    int selectCountByParams(@Param("params") Map<String, String> params);
}