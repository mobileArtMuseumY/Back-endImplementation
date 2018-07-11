package com.website.common;

import com.google.code.kaptcha.Constants;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: WebSite
 * @description: 验证码验证工具类
 * @author: smallsoup
 * @create: 2018-07-07 14:06
 **/

public class CodeUtils {

    public static boolean checkVerifyCode(HttpServletRequest request) {

        String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

        String verifyCodeActual = HttpServletRequestUtils.getString(request, "verifyCodeActual");

        if (null != verifyCodeExpected && StringUtils.equalsIgnoreCase(verifyCodeActual, verifyCodeExpected)) {
            return true;
        }

        return false;
    }


}
