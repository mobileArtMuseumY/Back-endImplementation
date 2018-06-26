package dao;

import beans.FavoriteWorks;
import beans.FavoriteWorksExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FavoriteWorksMapper {
    long countByExample(FavoriteWorksExample example);

    int deleteByExample(FavoriteWorksExample example);

    int insert(FavoriteWorks record);

    int insertSelective(FavoriteWorks record);

    List<FavoriteWorks> selectByExample(FavoriteWorksExample example);

    int updateByExampleSelective(@Param("record") FavoriteWorks record, @Param("example") FavoriteWorksExample example);

    int updateByExample(@Param("record") FavoriteWorks record, @Param("example") FavoriteWorksExample example);
}