package com.website.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.website.pojo.ProjectAttachment;
import com.website.pojo.ProjectAttachmentExample;

public interface ProjectAttachmentMapper {
    long countByExample(ProjectAttachmentExample example);

    int deleteByExample(ProjectAttachmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProjectAttachment record);

    int insertSelective(ProjectAttachment record);

    List<ProjectAttachment> selectByExample(ProjectAttachmentExample example);

    ProjectAttachment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProjectAttachment record, @Param("example") ProjectAttachmentExample example);

    int updateByExample(@Param("record") ProjectAttachment record, @Param("example") ProjectAttachmentExample example);

    int updateByPrimaryKeySelective(ProjectAttachment record);

    int updateByPrimaryKey(ProjectAttachment record);
}