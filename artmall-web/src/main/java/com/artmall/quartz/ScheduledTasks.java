//package com.artmall.quartz;
//
//import com.artmall.DO.OrderForm;
//import com.artmall.DO.Project;
//import com.artmall.DO.Works;
//import com.artmall.response.Const;
//import com.artmall.service.*;
//import com.artmall.utils.DateUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
///**
// * @author mllove
// * @create 2018-12-04 16:09
// **/
//@Component
//public class ScheduledTasks {
//    private final static Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
//
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//
//    @Autowired
//    OrderService orderService;
//    @Autowired
//    StudentService studentService;
//    @Autowired
//    WorksService worksService;
//    @Autowired
//    BidService bidService;
//    /**
//     *  1、获取所有Order
//     *  2、比较finishTime和如今时间，如果过期则修改状态
//     */
//    //每天凌晨一点执行
//    @Scheduled( cron = "0 0 1 * * ?")
////    @Scheduled(cron = "*/5 * * * * ?")
//    public void SetOrderOutOfDate() {
//
//        log.info("SetOrderOutOfDate.log");
//
//        List<OrderForm> orderForms =orderService.selectOrderByStatus(Const.ORDER_TRATING);
//        for (OrderForm orderForm:orderForms){
//            if (DateUtil.isOutOffDate(orderForm.getFinishTime())){
//                log.info("is out of date , update project status");
//                orderService.setOrderStatus(orderForm,Const.ORDER_FAILE);
//                //project也就失败了
//                Project project = projectService.selectById(orderForm.getProjectId());
//                projectService.setProjectStatus(project,Const.PROJECT_ORDER_FAILE);
//                //学生的违约加一
//                studentService.breakTimeAdd(orderForm.getSellerId());
//                //works状态修改
//                Works works =worksService.selectWorksById(orderForm.getWorksId());
//                worksService.setWorksStatus(works,Const.WORKS_STATUS_SHOW);
//            }
//        }
//    }
//
//    @Autowired
//    ProjectService projectService;
//    @Scheduled( cron = "0 0 2 * * ?")
////    @Scheduled(cron = "*/5 * * * * ?")
//    public void SetProjectOutOfDate(){
//        log.info("setProjectOutOfDate.log");
//        List<Project> list =projectService.selectProjectByStatus(Const.PROJECT_BIDDING);
//        for (Project project:list){
//            //过期了,修改状态
//            if (DateUtil.isOutOffDate(project.getFinishTime())){
//                log.info("is out of date , update project status");
//                projectService.setProjectStatus(project,Const.PROJECT_BIDDING_OUT_OFF_DATA);
//                //投标的状态要改
//                bidService.setProjectBidStatus(project.getId());
//                //投标的作品状态要改
//
//            }
//        }
//
//    }
//}
