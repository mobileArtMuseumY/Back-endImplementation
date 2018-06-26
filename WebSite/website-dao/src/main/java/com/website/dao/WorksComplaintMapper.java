package dao;

import beans.WorksComplaint;
import beans.WorksComplaintExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorksComplaintMapper {
    long countByExample(WorksComplaintExample example);

    int deleteByExample(WorksComplaintExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WorksComplaint record);

    int insertSelective(WorksComplaint record);

    List<WorksComplaint> selectByExample(WorksComplaintExample example);

    WorksComplaint selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WorksComplaint record, @Param("example") WorksComplaintExample example);

    int updateByExample(@Param("record") WorksComplaint record, @Param("example") WorksComplaintExample example);

    int updateByPrimaryKeySelective(WorksComplaint record);

    int updateByPrimaryKey(WorksComplaint record);
}