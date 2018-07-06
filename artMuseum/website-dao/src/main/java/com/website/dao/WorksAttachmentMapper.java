package com.website.dao;

import com.website.pojo.WorksAttachment;
import com.website.pojo.WorksAttachmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorksAttachmentMapper {
    long countByExample(WorksAttachmentExample example);

    int deleteByExample(WorksAttachmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WorksAttachment record);

    int insertSelective(WorksAttachment record);

    List<WorksAttachment> selectByExample(WorksAttachmentExample example);

    WorksAttachment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WorksAttachment record, @Param("example") WorksAttachmentExample example);

    int updateByExample(@Param("record") WorksAttachment record, @Param("example") WorksAttachmentExample example);

    int updateByPrimaryKeySelective(WorksAttachment record);

    int updateByPrimaryKey(WorksAttachment record);
}