package com.website.controller;

import com.website.common.ServerResponse;
import com.website.service.IBusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: WebSite
 * @description: 企业控制器
 * @author: smallsoup
 * @create: 2018-06-30 18:47
 **/
@RestController
@RequestMapping(value = "/business")
public class BusinessController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessController.class);

    @Resource
    private IBusinessService iBusinessService;

    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public ServerResponse<String> regist(@RequestParam("account") String account){

        try {
            iBusinessService.regist(account);
        } catch (Exception e) {
            LOGGER.error("BusinessController regist exception is {}", e);
            return ServerResponse.createByFailure(e.getMessage());
        }

        return ServerResponse.createBySuccess();
    }

}
