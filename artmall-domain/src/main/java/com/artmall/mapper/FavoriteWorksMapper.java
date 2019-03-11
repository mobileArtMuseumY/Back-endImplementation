package com.artmall.mapper;

import com.artmall.DO.FavoriteWorks;
import com.artmall.DO.FavoriteWorksExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FavoriteWorksMapper {
    long countByExample(FavoriteWorksExample example);

    int deleteByExample(FavoriteWorksExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FavoriteWorks record);

    int insertSelective(FavoriteWorks record);

    List<FavoriteWorks> selectByExample(FavoriteWorksExample example);

    FavoriteWorks selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FavoriteWorks record, @Param("example") FavoriteWorksExample example);

    int updateByExample(@Param("record") FavoriteWorks record, @Param("example") FavoriteWorksExample example);

    int updateByPrimaryKeySelective(FavoriteWorks record);

    int updateByPrimaryKey(FavoriteWorks record);
}