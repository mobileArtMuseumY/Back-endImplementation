package com.website.dao;

import com.website.po.BusinessAttachment;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessAttachmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessAttachment record);

    int insertSelective(BusinessAttachment record);

    BusinessAttachment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessAttachment record);

    int updateByPrimaryKey(BusinessAttachment record);
}