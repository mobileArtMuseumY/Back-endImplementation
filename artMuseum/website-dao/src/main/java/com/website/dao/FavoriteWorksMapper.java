package com.website.dao;

import com.website.pojo.FavoriteWorks;
import com.website.pojo.FavoriteWorksExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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