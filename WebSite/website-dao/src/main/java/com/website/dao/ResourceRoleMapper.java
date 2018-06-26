package dao;

import beans.ResourceRole;
import beans.ResourceRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceRoleMapper {
    long countByExample(ResourceRoleExample example);

    int deleteByExample(ResourceRoleExample example);

    int insert(ResourceRole record);

    int insertSelective(ResourceRole record);

    List<ResourceRole> selectByExample(ResourceRoleExample example);

    int updateByExampleSelective(@Param("record") ResourceRole record, @Param("example") ResourceRoleExample example);

    int updateByExample(@Param("record") ResourceRole record, @Param("example") ResourceRoleExample example);
}