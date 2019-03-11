package com.artmall.service;

import com.artmall.DO.*;
import com.artmall.DTO.order.OrderConfirm;
import com.artmall.DTO.order.OrderDTO;

import java.util.List;

/**
 * 订单
 *
 * @author mllove
 * @create 2018-10-14 9:06
 **/

public interface OrderService {
    OrderForm addOrder(OrderConfirm confirm);

    List<OrderDTO> selectOrderByStudentId(Long id);

    List<OrderDTO> selectOrderStatus(Long businessId, byte orderSuccess);

    List<OrderDTO> selectOrderByBusinessId(Long id);

    List<OrderDTO> setOrderVO(List<OrderForm> orderForms);

    OrderConfirm confirmBidOrder(Business business,Student student, Project project, Works works);

    OrderConfirm confirmWorksOrder(Business business, Student student, Works works);

    OrderForm pay(String out_trade_no);

    OrderForm doneOrder(Long orderId);

    OrderForm selectOrderById(Long orderId);

    void delete(OrderForm orderForm);

    void update(OrderForm orderForm);

    List<OrderForm> show(int page, int rows);

    List<OrderForm> selectOrderByStatus(byte orderTrating);

    void setOrderStatus(OrderForm orderForm, byte orderFaile);
}