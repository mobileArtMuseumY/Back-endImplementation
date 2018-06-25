package com.sparrow.artmuseum.dao;

import com.sparrow.artmuseum.pojo.ProjectAttachment;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectAttachmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectAttachment record);

    int insertSelective(ProjectAttachment record);

    ProjectAttachment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectAttachment record);

    int updateByPrimaryKey(ProjectAttachment record);
}