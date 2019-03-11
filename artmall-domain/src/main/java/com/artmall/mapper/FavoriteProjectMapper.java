package com.artmall.mapper;

import com.artmall.DO.FavoriteProject;
import com.artmall.DO.FavoriteProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FavoriteProjectMapper {
    long countByExample(FavoriteProjectExample example);

    int deleteByExample(FavoriteProjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FavoriteProject record);

    int insertSelective(FavoriteProject record);

    List<FavoriteProject> selectByExample(FavoriteProjectExample example);

    FavoriteProject selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FavoriteProject record, @Param("example") FavoriteProjectExample example);

    int updateByExample(@Param("record") FavoriteProject record, @Param("example") FavoriteProjectExample example);

    int updateByPrimaryKeySelective(FavoriteProject record);

    int updateByPrimaryKey(FavoriteProject record);
}