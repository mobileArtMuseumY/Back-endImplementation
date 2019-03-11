package com.artmall.mapper;

import com.artmall.DO.BusinessAttachment;
import com.artmall.DO.BusinessAttachmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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