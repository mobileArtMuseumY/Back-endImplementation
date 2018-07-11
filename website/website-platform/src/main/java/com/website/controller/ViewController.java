package com.website.controller;

import com.website.service.IBusinessService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/view", method = {RequestMethod.GET})
public class ViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewController.class);

    @Resource
    private IBusinessService iBusinessService;

    @RequestMapping(value = "/businessregist")
    public String businessRegistView(){
        return "business/businessregist";
    }

    @RequestMapping(value = "/projectadd")
    public String projectAdd(){
        return "project/projectadd";
    }

    @RequestMapping(value = "/notifyactivate")
    public String notifyActivate(){
        return "business/notifyactivate";
    }

    @RequestMapping(value = "/projectaddsuccess")
    public String projectAddSuccess(){
        return "business/projectaddsuccess";
    }

    /**
     * 验证手机号以及发送短信验证码
     *
     * @param id id
     * @param op 操作
     * @param token 令牌
     * @return
     */
    @RequestMapping(value = "/business/emailconfirm", method = RequestMethod.GET)
    public String verifyEmailCode(@RequestParam("id") String id, @RequestParam("op") String op, @RequestParam("token") String token) {

        try {

            if (StringUtils.isEmpty(id) || StringUtils.isEmpty(token)) {
                return "business/registfailure";
            }

            iBusinessService.emailActivate(id, token);

        } catch (Exception e) {
            LOGGER.error("BusinessController verifyEmailCode exception is {}", e);
            return "business/registfailure";
        }

        return "business/registsuccess";
    }
}
