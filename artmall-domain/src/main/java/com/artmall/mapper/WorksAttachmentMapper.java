package com.artmall.mapper;

import com.artmall.DO.WorksAttachment;
import com.artmall.DO.WorksAttachmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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