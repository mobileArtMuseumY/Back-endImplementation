package dao;

import beans.FavoriteProject;
import beans.FavoriteProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FavoriteProjectMapper {
    long countByExample(FavoriteProjectExample example);

    int deleteByExample(FavoriteProjectExample example);

    int insert(FavoriteProject record);

    int insertSelective(FavoriteProject record);

    List<FavoriteProject> selectByExample(FavoriteProjectExample example);

    int updateByExampleSelective(@Param("record") FavoriteProject record, @Param("example") FavoriteProjectExample example);

    int updateByExample(@Param("record") FavoriteProject record, @Param("example") FavoriteProjectExample example);
}