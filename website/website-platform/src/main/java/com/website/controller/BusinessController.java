package com.website.controller;

import com.alibaba.fastjson.JSONObject;
import com.website.common.*;
import com.website.controller.vo.BusinessVo;
import com.website.controller.vo.CodeVo;
import com.website.service.IBusinessService;
import com.website.service.dto.BusinessDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

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

    /**
     * 验证手机号以及发送短信验证码
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/sendverifycode", method = RequestMethod.GET)
    public ServerResponse sendVerifyCode(HttpServletRequest request) {
        String telephone = HttpServletRequestUtils.getString(request, "telephone");
        if (null == telephone) {
            return ServerResponse.createByFailure("手机号为空");
        }

        String validateCode;
        try {
            validateCode = iBusinessService.sendVerifyCode(telephone);

            LOGGER.debug("validateCode is {}", validateCode);

            request.getSession().setAttribute("mobileVerifyCodeExpected", new CodeVo(validateCode));
        } catch (Exception e) {
            LOGGER.error("BusinessController sendVerifyCode exception is {}", e);
            return ServerResponse.createByFailure(e.getMessage());
        }

        return ServerResponse.createBySuccess();
    }

    /**
     * 验证手机验证码是否正确
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/verifycode", method = RequestMethod.POST)
    public ServerResponse verifyCode(HttpServletRequest request) {

        String mobileVerifyCodeActual = HttpServletRequestUtils.getString(request, "mobileVerifyCodeActual");
        if (null == mobileVerifyCodeActual) {
            return ServerResponse.createByFailure("手机验证码为空");
        }

        CodeVo code = (CodeVo) request.getSession().getAttribute("mobileVerifyCodeExpected");

        if (null == code) {
            return ServerResponse.createByFailure("请重新获取验证码");
        }

        if (!StringUtils.equalsIgnoreCase(mobileVerifyCodeActual, code.getCode())) {
            return ServerResponse.createByFailure("验证码不正确");
        }

        if (new Date().after(code.getExpiredTime())) {
            return ServerResponse.createByFailure("验证码失效");
        }

        return ServerResponse.createBySuccess();
    }

    /**
     * 注册
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public ServerResponse regist(HttpServletRequest request) {

        LOGGER.debug("regist is ---------------------start--");

        try {

            if (!CodeUtils.checkVerifyCode(request)) {
                return ServerResponse.createByFailure("验证码不对！");
            }

            String businessStr = HttpServletRequestUtils.getString(request, "businessStr");

            if (null == businessStr) {
                return ServerResponse.createByFailure("businessVo is null");
            }

            //转换表单json为实体类
            BusinessVo businessVo = JSONObject.parseObject(businessStr, BusinessVo.class);

            //校验
            validateParams(businessVo);

            LOGGER.debug("businessVo is {}", businessVo);

            CommonsMultipartFile businessLicenseImg;

            CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            //判断是否有文件流
            if (commonsMultipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

                businessLicenseImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("businessLicense");
            } else {
                return ServerResponse.createByFailure("请上传营业执照！");
            }

            //执行注册
            if (businessLicenseImg == null) {
                return ServerResponse.createByFailure("请上传营业执照！");
            }

            //生成随机文件名的文件
            String pathDir = ImageUtils.makePathDir("businessLicenseImg/");
            File licenseImg = new File(pathDir + ImageUtils.getRandomFileName() + ImageUtils.getFileExtensionName(businessLicenseImg));
            IOUtils.inputStreamToFile(businessLicenseImg.getInputStream(), licenseImg);

            BusinessDto businessDto = businessVo.convertBusinessDto(licenseImg);
            iBusinessService.regist(businessDto);

        } catch (Exception e) {
            LOGGER.error("BusinessController regist exception is {}", e);
            return ServerResponse.createByFailure(e.getMessage());
        }

        return ServerResponse.createBySuccess("ok");
    }

    /**
     * 参数校验
     *
     * @param businessVo
     */
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
