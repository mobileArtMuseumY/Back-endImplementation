package com.website.dao;

import com.website.pojo.WorksComment;
import com.website.pojo.WorksCommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorksCommentMapper {
    long countByExample(WorksCommentExample example);

    int deleteByExample(WorksCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WorksComment record);

    int insertSelective(WorksComment record);

    List<WorksComment> selectByExample(WorksCommentExample example);

    WorksComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WorksComment record, @Param("example") WorksCommentExample example);

    int updateByExample(@Param("record") WorksComment record, @Param("example") WorksCommentExample example);

    int updateByPrimaryKeySelective(WorksComment record);

    int updateByPrimaryKey(WorksComment record);
}