package com.artmall.controller.back;

import com.artmall.DO.OrderForm;
import com.artmall.DO.Student;
import com.artmall.response.ServerResponse;
import com.artmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 订单管理
 *
 * @author0
 * @create 2018-09-07 13:44
 **/
@RestController
@RequestMapping("/orderController")
public class OrderBackController {


    @Autowired
    OrderService orderService;

    /**
     * 订单的删除
     * @param map
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ServerResponse delete (@RequestBody Map<String,String> map){
        Long orderId = Long.valueOf(map.get("orderId"));
        OrderForm orderForm = orderService.selectOrderById(orderId);
        orderService.delete(orderForm);
        return ServerResponse.Success("删除成功");
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ServerResponse update (@RequestBody OrderForm orderForm){
        orderService.update(orderForm);
        return ServerResponse.Success("更改成功");
    }

    @RequestMapping(value = "/show",method = RequestMethod.GET )
    public ServerResponse show (@RequestParam("page") int page,
                                @RequestParam("rows") int rows){
        List<OrderForm> formList=orderService.show(page,rows);
        return ServerResponse.Success("展示成功",formList);
    }

    @RequestMapping(value = "/delete/all",method = RequestMethod.POST )
    public ServerResponse deleteAll (@RequestBody Map<String,String> map){
        String data = map.get("id");
        String params[] = data.split(",");
        for (int i=0;i<params.length;i++){
            if (params[i]==null){
                i++;
            }
            OrderForm orderForm = orderService.selectOrderById(Long.valueOf(params[i]));
            orderService.delete(orderForm);
        }
        return ServerResponse.Success("删除成功");
    }


}
