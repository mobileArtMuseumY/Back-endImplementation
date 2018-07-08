package com.website.dao;

import com.website.po.EmailActivation;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface EmailActivationMapper {

    int deleteByPrimaryKey(Long id);

    int insert(EmailActivation record);

    int insertSelective(EmailActivation record);

    EmailActivation selectByPrimaryKey(Long id);

    int selectCountByParams(@Param("params") Map<String, String> params);

    int updateByPrimaryKeySelective(EmailActivation record);

    int updateByPrimaryKey(EmailActivation record);
}