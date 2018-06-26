package dao;

import beans.OrdersComplaint;
import beans.OrdersComplaintExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrdersComplaintMapper {
    long countByExample(OrdersComplaintExample example);

    int deleteByExample(OrdersComplaintExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrdersComplaint record);

    int insertSelective(OrdersComplaint record);

    List<OrdersComplaint> selectByExample(OrdersComplaintExample example);

    OrdersComplaint selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrdersComplaint record, @Param("example") OrdersComplaintExample example);

    int updateByExample(@Param("record") OrdersComplaint record, @Param("example") OrdersComplaintExample example);

    int updateByPrimaryKeySelective(OrdersComplaint record);

    int updateByPrimaryKey(OrdersComplaint record);
}