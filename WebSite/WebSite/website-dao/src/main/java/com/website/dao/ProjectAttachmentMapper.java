package com.website.dao;

import com.website.po.ProjectAttachment;

public interface ProjectAttachmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectAttachment record);

    int insertSelective(ProjectAttachment record);

    ProjectAttachment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectAttachment record);

    int updateByPrimaryKey(ProjectAttachment record);
}