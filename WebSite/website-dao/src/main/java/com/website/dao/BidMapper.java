package dao;

import beans.Bid;
import beans.BidExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BidMapper {
    long countByExample(BidExample example);

    int deleteByExample(BidExample example);

    int insert(Bid record);

    int insertSelective(Bid record);

    List<Bid> selectByExample(BidExample example);

    int updateByExampleSelective(@Param("record") Bid record, @Param("example") BidExample example);

    int updateByExample(@Param("record") Bid record, @Param("example") BidExample example);
}