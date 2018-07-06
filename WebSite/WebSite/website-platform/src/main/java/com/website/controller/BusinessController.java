package com.website.controller;

import com.website.common.RegexUtils;
import com.website.common.ServerResponse;
import com.website.controller.vo.BusinessVo;
import com.website.controller.vo.CodeVo;
import com.website.service.IBusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/validate",method = RequestMethod.POST)
    public ServerResponse<CodeVo> validate(@RequestParam("account") String account){
        String validateCode = "";
        try {
            validateCode = iBusinessService.regist(account);
        } catch (Exception e) {
            LOGGER.error("BusinessController regist exception is {}", e);
            return ServerResponse.createByFailure(e.getMessage());
        }

        return ServerResponse.createBySuccess(new CodeVo(validateCode));
    }

    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public ServerResponse<String> regist(@RequestBody BusinessVo businessVo, HttpServletRequest request){

        validateParams(businessVo);

        try {
            validateParams(businessVo);

            //短信或者邮件验证码校验
            String expectedNumCode = (String)request.getAttribute("expectedNumCode");
            String actualNumCode = (String) request.getAttribute("actualNumCode");

            //图形验证码校验
            String expectedPictureCode = (String)request.getAttribute("expectedPictureCode");
            String actualPictureNumCode =  (String)request.getAttribute("actualPictureNumCode");

        } catch (Exception e) {
            LOGGER.error("BusinessController regist exception is {}", e);
            return ServerResponse.createByFailure(e.getMessage());
        }

        return ServerResponse.createBySuccess();
    }

    private void validateParams(BusinessVo businessVo) {

        if (null == businessVo) {
            throw new IllegalArgumentException("businessVo is null");
        }

        if (!RegexUtils.isMobileExact(businessVo.getTel())) {

            throw new IllegalArgumentException("Tel is Illegal");

        }

        if (!RegexUtils.isEmail(businessVo.getEmail())) {

            throw new IllegalArgumentException("Email is Illegal");
        }

        final String idCard = businessVo.getRepresentationIdcard();
        if (!RegexUtils.isIDCard15(idCard) && !RegexUtils.isIDCard18(idCard)) {
            throw new IllegalArgumentException("IDCard is Illegal");
        }
    }

}
