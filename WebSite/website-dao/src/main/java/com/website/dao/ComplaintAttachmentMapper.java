package dao;

import beans.ComplaintAttachment;
import beans.ComplaintAttachmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ComplaintAttachmentMapper {
    long countByExample(ComplaintAttachmentExample example);

    int deleteByExample(ComplaintAttachmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ComplaintAttachment record);

    int insertSelective(ComplaintAttachment record);

    List<ComplaintAttachment> selectByExample(ComplaintAttachmentExample example);

    ComplaintAttachment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ComplaintAttachment record, @Param("example") ComplaintAttachmentExample example);

    int updateByExample(@Param("record") ComplaintAttachment record, @Param("example") ComplaintAttachmentExample example);

    int updateByPrimaryKeySelective(ComplaintAttachment record);

    int updateByPrimaryKey(ComplaintAttachment record);
}