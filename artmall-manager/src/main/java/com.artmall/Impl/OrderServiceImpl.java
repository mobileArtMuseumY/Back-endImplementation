package com.artmall.Impl;

import com.artmall.DTO.order.OrderConfirm;
import com.artmall.exception.ArtmallException;
import com.artmall.exception.NullException;
import com.artmall.mapper.OrderFormMapper;
import com.artmall.DO.*;
import com.artmall.response.Const;
import com.artmall.service.*;
import com.artmall.utils.DateUtil;
import com.artmall.utils.IDUtils;
import com.artmall.DTO.order.OrderDTO;
import com.artmall.utils.Tools;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mllove
 * @create 2018-10-14 9:06
 **/

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderFormMapper orderFormMapper;

    /**
     * 未给出过期时间
     * @param orderConfirm
     * @return
     */
    @Override
    public OrderForm addOrder(OrderConfirm orderConfirm) {
        OrderForm orderForm = new OrderForm();
        //如果多个订单同时下单，就会出现ID相同的情况
        orderForm.setId(Tools.initId());
        orderForm.setProjectId(orderConfirm.getProjectId());
        orderForm.setSellerId(orderConfirm.getStudentId());
        orderForm.setWorksId(orderConfirm.getWorksId());
        orderForm.setBuyerId(orderConfirm.getBusinessId());
        orderForm.setPrice(orderConfirm.getPrice());
        orderForm.setGmtCreate(new Date());
        orderForm.setGmtModified(new Date());
        orderForm.setFinishTime(DateUtil.getDeadline(orderForm.getGmtCreate(),orderConfirm.getExpectedTime()));
        orderForm.setStatus(Const.ORDER_NOPAY);
        orderForm.setType(orderConfirm.getType());

        orderFormMapper.insert(orderForm);

        //project改状态
        Project project = projectService.selectById(orderForm.getProjectId());
        projectService.setProjectStatus(project,Const.PROJECT_ORDER_FAILE);

        return orderForm;
    }

    @Autowired
    ProjectService projectService;
    @Autowired
    WorksService worksService;
    @Autowired
    StudentService studentService;
    @Autowired
    BusinessService businessService;
    @Override
    public List<OrderDTO> selectOrderByStudentId(Long id) {

        OrderFormExample example = new OrderFormExample();
        OrderFormExample.Criteria criteria = example.createCriteria();
        criteria.andSellerIdEqualTo(id);
        List<OrderForm> orderForms = orderFormMapper.selectByExample(example);

        return setOrderVO(orderForms);

    }


    @Override
    public List<OrderDTO> selectOrderStatus(Long businessId, byte status) {
        List<OrderDTO> orderFormList = selectOrderByBusinessId(businessId);
        List<OrderDTO> orderFormsStatusList = new ArrayList<>();
        for (OrderDTO orderForm : orderFormList) {
            if (orderForm.getStatus().equals(status)) {
                orderFormsStatusList.add(orderForm);
            }
        }
            return orderFormsStatusList;
    }

    @Override
    public List<OrderDTO> selectOrderByBusinessId(Long id) {
        OrderFormExample example = new OrderFormExample();
        OrderFormExample.Criteria criteria = example.createCriteria();
        criteria.andBuyerIdEqualTo(id);
        List<OrderForm> orderFormList = orderFormMapper.selectByExample(example);
        return setOrderVO(orderFormList);
    }

    @Override
    public List<OrderDTO> setOrderVO(List<OrderForm> orderForms) {
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (OrderForm orderForm:orderForms){
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(orderForm.getId());
            orderDTO.setStatus(orderForm.getStatus());
            orderDTO.setProjectId(orderForm.getProjectId());
            orderDTO.setSellerId(orderForm.getSellerId());
            orderDTO.setWorksId(orderForm.getWorksId());
            orderDTO.setBuyerId(orderForm.getBuyerId());
            orderDTO.setPrice(orderForm.getPrice());
            orderDTO.setFinishTime(orderForm.getFinishTime());
            orderDTO.setGmtCreate(orderForm.getGmtCreate());
            orderDTO.setGmtModified(orderForm.getGmtModified());

            Project project =projectService.selectById(orderForm.getProjectId());
            orderDTO.setProjectName(project.getProjectName());

            Works works = worksService.selectWorksById(orderForm.getWorksId());
            orderDTO.setWorksName(works.getWorksName());

            Business business = businessService.selectBusinessById(orderForm.getBuyerId());
            orderDTO.setBusinessName(business.getBusinessName());

            Student student = studentService.selectStudentById(orderForm.getSellerId());
            orderDTO.setStudentName(student.getLoginName());

            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

    /**
     * 确认订单，把订单的信息返回
     * 通过发布项目选标
     * @param business
     * @param project
     * @param works
     * @return
     */
    @Autowired
    BidService bidService;

    @Override
    public OrderConfirm confirmBidOrder(Business business,Student student, Project project, Works works) {
        Bid bid = bidService.selectBidByStudentIdAndProjectId(student.getId(),project.getId());
        OrderConfirm orderConfirm = confirmOrder(business,student,works);
        orderConfirm.setExpectedTime(project.getExpectedTime());
        orderConfirm.setProjectId(project.getId());
        orderConfirm.setType(Const.ORDER_TYPE_BID);
        //修改未投中此项目的作品的状态
        bidService.setProjectBidStatus(project.getId());
        //修改作品和项目的状态
        projectService.setProjectStatus(project,Const.PROJECT_BIDDING_DONE);
        bidService.setBidStatus(bid,Const.BID_STATUS_SUCCESS);
        worksService.setWorksStatus(works,Const.WORKS_STATUS_CONFIRM);


        return orderConfirm;
    }

    /**
     * 单独购买作品的买者
     * @param business
     * @param student
     * @param works
     * @return
     */
    @Override
    public OrderConfirm confirmWorksOrder(Business business,Student student, Works works) {
        OrderConfirm orderConfirm = confirmOrder(business,student,works);
        orderConfirm.setType(Const.ORDER_TYPE_ONLY);
        return orderConfirm;
    }

    /**
     * 买家已付款
     * 修改orderform状态
     * @param out_trade_no
     * @return
     */
    @Override
    public OrderForm pay(String out_trade_no) {
        OrderForm orderForm = updateStatus(Long.valueOf(out_trade_no),Const.ORDER_TYPE_BID);
        //project修改状态
        Project project = projectService.selectById(orderForm.getProjectId());
        projectService.setProjectStatus(project,Const.PROJECT_ORDER_BEING);
        return orderForm;
    }

    /**
     * 完成订单
     * @param orderId
     * @return
     */
    @Override
    public OrderForm doneOrder(Long orderId) {
        //修改订单状态
        OrderForm orderForm = selectOrderById(orderId);
        orderForm.setFinishTime(new Date());
        setOrderStatus(orderForm,Const.ORDER_SUCCESS);

        //修改project状态
        Project project = projectService.selectById(orderForm.getProjectId());
        projectService.setProjectStatus(project,Const.PROJECT_ORDER_DONE);

        //修改works状态
        Works works = worksService.selectWorksById(orderForm.getWorksId());
         worksService.setWorksStatus(works,Const.WORKS_STATUS_SUCESS);
        return orderForm;
    }

    @Override
    public OrderForm selectOrderById(Long orderId) {
        OrderForm orderForm = orderFormMapper.selectByPrimaryKey(orderId);
        if (orderForm==null){
            throw new NullException("不存在此order");
        }
        return orderForm;
    }

    @Override
    @Transactional
    public void delete(OrderForm orderForm) {
        try {
            orderFormMapper.deleteByPrimaryKey(orderForm.getId());
//            deleteOrderComplaint(orderForm.getId());
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ArtmallException("订单删除失败");
        }
    }



    @Override
    public void update(OrderForm orderForm) {
        if (orderFormMapper.updateByPrimaryKey(orderForm)!=1){
            throw new ArtmallException("order更新失败");
        }
    }

    @Override
    public List<OrderForm> show(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<OrderForm> list = new ArrayList<>();
        OrderFormExample example = new OrderFormExample();
        example.setOrderByClause("`gmt_create` desc");
        list = orderFormMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<OrderForm> selectOrderByStatus(byte status) {
        OrderFormExample example = new OrderFormExample();
        example.or()
                .andStatusEqualTo(status);
        List<OrderForm> list = orderFormMapper.selectByExample(example);
        return list;
    }

    @Override
    public void setOrderStatus(OrderForm orderForm, byte stauts) {
        orderForm.setGmtModified(new Date());
        orderForm.setStatus(stauts);
        update(orderForm);
    }


    private OrderForm updateStatus(Long orderId, byte orderTypeBid) {
        OrderForm orderForm = orderFormMapper.selectByPrimaryKey(orderId);
        orderForm.setStatus(orderTypeBid);
        orderForm.setGmtModified(new Date());
        orderFormMapper.updateByPrimaryKey(orderForm);
        return orderForm;
    }

    private OrderConfirm confirmOrder(Business business, Student student, Works works) {
        OrderConfirm orderConfirm = new OrderConfirm();
        orderConfirm.setBusinessId(business.getId());
        orderConfirm.setBusinessEmail(business.getEmail());
        orderConfirm.setBusinessName(business.getBusinessName());
        orderConfirm.setBusinessTel(business.getTel());
        orderConfirm.setStudentId(student.getId());
        orderConfirm.setWorksId(works.getId());
        orderConfirm.setWorksName(works.getWorksName());
        orderConfirm.setPrice(works.getPrice());
        worksService.setWorksStatus(works,Const.WORKS_STATUS_CONFIRM);

        return orderConfirm;
    }


}

