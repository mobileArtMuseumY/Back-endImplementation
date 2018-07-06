package com.website.dao;

import com.website.pojo.BusinessAttachment;
import com.website.pojo.BusinessAttachmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessAttachmentMapper {
    long countByExample(BusinessAttachmentExample example);

    int deleteByExample(BusinessAttachmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BusinessAttachment record);

    int insertSelective(BusinessAttachment record);

    List<BusinessAttachment> selectByExample(BusinessAttachmentExample example);

    BusinessAttachment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BusinessAttachment record, @Param("example") BusinessAttachmentExample example);

    int updateByExample(@Param("record") BusinessAttachment record, @Param("example") BusinessAttachmentExample example);

    int updateByPrimaryKeySelective(BusinessAttachment record);

    int updateByPrimaryKey(BusinessAttachment record);
}