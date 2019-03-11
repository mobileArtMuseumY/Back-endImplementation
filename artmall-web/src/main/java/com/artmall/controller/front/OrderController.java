package com.artmall.controller.front;

import com.artmall.DO.*;
import com.artmall.DTO.order.OrderConfirm;
import com.artmall.response.Const;
import com.artmall.response.ServerResponse;
import com.artmall.service.BusinessService;
import com.artmall.service.OrderService;
import com.artmall.service.ProjectService;
import com.artmall.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 订单管理
 *
 * @author mllove
 * @create 2018-10-25 10:35
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    WorksService worksService;
    @Autowired
    BusinessService businessService;
    @Autowired
    ProjectService projectService;

    /**
     * 选标后进行订单确认
     * @param map
     * @return
     */
    @RequestMapping(value = "/bid/confirm",method = RequestMethod.POST)
    public ServerResponse bidOrderConfirm(@RequestBody Map<String,Long> map) throws Exception {
        Long projectId = map.get("projectId");
        Long worksId = map.get("worksId");
        Business business =businessService.getBusiness();
        Project project = projectService.selectById(projectId);
        Works works = worksService.selectWorksById(worksId);
        Student student = worksService.selectStudentByWorksId(works.getId());
        //生成订单
        OrderConfirm orderConfirm = orderService.confirmBidOrder(business,student,project,works);
        return ServerResponse.Success("确认订单信息展示完成",orderConfirm);
    }

    @RequestMapping(value = "/works/confirm",method = RequestMethod.POST)
    public ServerResponse worksOrderConfirm (@RequestBody Map<String,Long> map) throws Exception {
        Long worksId = map.get("worksId");
        Business business = businessService.getBusiness();
        Works works=worksService.selectWorksById(worksId);
        Student student = worksService.selectStudentByWorksId(works.getId());
        OrderConfirm orderConfirm = orderService.confirmWorksOrder(business,student,works);
        return ServerResponse.Success("确认订单信息展示完成",orderConfirm);
    }

    /**
     * 生成订单，但未付款，将数据写入数据库
     * @param orderConfirm
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ServerResponse addOrder (@RequestBody OrderConfirm orderConfirm){
        OrderForm orderForm =  orderService.addOrder(orderConfirm);
        return ServerResponse.Success("订单生成成功",orderForm);
    }


    /**
     * 订单完成
     * 修改works_status
     * order status
     */
    @RequestMapping(value = "/done",method = RequestMethod.POST )
    public ServerResponse DoneOrder (@RequestBody Map<String,Long> map){
        Long orderId = map.get("orderId");
        OrderForm orderForm = orderService.doneOrder(orderId);
        return ServerResponse.Success("订单完成",orderForm);
    }




}
