package com.website.dao;

import com.website.pojo.PrivateMessage;
import com.website.pojo.PrivateMessageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrivateMessageMapper {
    long countByExample(PrivateMessageExample example);

    int deleteByExample(PrivateMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PrivateMessage record);

    int insertSelective(PrivateMessage record);

    List<PrivateMessage> selectByExample(PrivateMessageExample example);

    PrivateMessage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PrivateMessage record, @Param("example") PrivateMessageExample example);

    int updateByExample(@Param("record") PrivateMessage record, @Param("example") PrivateMessageExample example);

    int updateByPrimaryKeySelective(PrivateMessage record);

    int updateByPrimaryKey(PrivateMessage record);
}