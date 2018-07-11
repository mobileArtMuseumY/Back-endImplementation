package com.website.service;

import com.website.service.dto.BusinessDto;

/**
 * @program: WebSite
 * @description: 企业业务逻辑处理接口
 * @author: smallsoup
 * @create: 2018-06-30 18:52
 **/

public interface IBusinessService {

    int regist(BusinessDto businessDto) throws Exception;
    String sendVerifyCode(String account) throws Exception;

    void emailActivate(String id, String token) throws Exception;
}
