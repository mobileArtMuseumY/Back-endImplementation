package com.website.dao;

import com.website.po.ProjectAttachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectAttachmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectAttachment record);

    int insertBatch(@Param("records") List<ProjectAttachment> records);

    int insertSelective(ProjectAttachment record);

    ProjectAttachment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectAttachment record);

    int updateByPrimaryKey(ProjectAttachment record);
}