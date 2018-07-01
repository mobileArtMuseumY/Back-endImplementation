package com.website.service.impl;

import com.website.common.RandomCodeUtils;
import com.website.common.RegexUtils;
import com.website.dao.BusinessMapper;
import com.website.service.EmailService;
import com.website.service.IBusinessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: WebSite
 * @description: 企业业务逻辑处理
 * @author: smallsoup
 * @create: 2018-06-30 18:55
 **/
@Service
public class BusinessImpl implements IBusinessService {

    @Resource
    private BusinessMapper businessMapper;

    @Resource
    private EmailService emailService;

    @Override
    public int regist(String account) throws Exception {

//        ImmutableMap<String, String> params = null;
        Map<String, String> params = new HashMap<>(1);

        boolean isEmail = false;
        //校验account,要符合邮箱或者手机号格式
        if (RegexUtils.isMobileExact(account)) {

            params.put("tel", account);

        } else if (RegexUtils.isEmail(account)) {
            params.put("email", account);
            isEmail = true;
            
        } else {
            throw new Exception("不合法账号");
        }

        final int count = businessMapper.selectCountByParams(params);

        if (count != 0) {
            throw new Exception("该账号已经被注册");
        }

        //发邮件
        if (isEmail) {
            final String code = RandomCodeUtils.getSixValidationCode();

            emailService.sendMailSimple(account, "注册验证", "验证码为：" + code);
        }else { //发短信

        }

        return 0;
    }
}
