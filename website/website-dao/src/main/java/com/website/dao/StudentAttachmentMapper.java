package com.website.dao;

import com.website.po.StudentAttachment;

public interface StudentAttachmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudentAttachment record);

    int insertSelective(StudentAttachment record);

    StudentAttachment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudentAttachment record);

    int updateByPrimaryKey(StudentAttachment record);
}