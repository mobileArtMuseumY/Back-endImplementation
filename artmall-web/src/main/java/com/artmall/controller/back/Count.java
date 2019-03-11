package com.artmall.controller.back;

import com.artmall.response.ServerResponse;
import com.artmall.service.BusinessService;
import com.artmall.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 总数
 *
 * @author mllove
 * @create 2018-11-19 16:26
 **/
@RestController
@RequestMapping("/count")
public class Count {

    @Autowired
    ContentService contentService;
    @RequestMapping(value = "/business",method = RequestMethod.GET )
    public ServerResponse showCount (){
        Long num = contentService.getAllBusinessCount();
        return ServerResponse.Success("总数为",num);
    }


    @RequestMapping(value = "/student",method = RequestMethod.GET)
    public ServerResponse studentCount (){
        Long num = contentService.getAllStudentCount();
        return ServerResponse.Success("总数为",num);
    }

    @RequestMapping(value = "/works",method = RequestMethod.GET)
    public ServerResponse worksCount(){
        Long num = contentService.getAllWorksCount();
        return ServerResponse.Success("总数为",num);
    }



    @RequestMapping(value = "/project",method = RequestMethod.GET)
    public ServerResponse projectCount (){
        Long num = contentService.getAllProjectCount();
        return ServerResponse.Success("总数为",num);
    }

    /**
     * 正在审核的project总数
     * @return
     */
    @RequestMapping(value = "/project/verified",method = RequestMethod.GET )
    public ServerResponse proVerCount (){
        Long num = contentService.getAllProVerCount();
        return ServerResponse.Success("总数为",num);
    }

    /**
     * 正在审核的business总数
     * @return
     */
    @RequestMapping(value = "/business/verified",method = RequestMethod.GET )
    public ServerResponse BusVerCount (){
        Long num = contentService.getAllBusVerCount();
        return ServerResponse.Success("总数为",num);
    }

    /**
     * 订单总数
     * @return
     */
    @RequestMapping(value = "/order",method = RequestMethod.GET )
    public ServerResponse OrderCount (){
        Long num = contentService.getAllOrderCount();
        return ServerResponse.Success("总数为",num);
    }

}
