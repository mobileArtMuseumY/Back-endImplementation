package dao;

import beans.WorksComment;
import beans.WorksCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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