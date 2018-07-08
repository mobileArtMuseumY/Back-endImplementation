package com.website.common;

import java.util.Random;

/**
 * @program: WebSite
 * @description: 随机数生成工具类
 * @author: smallsoup
 * @create: 2018-06-30 19:58
 **/
public final class RandomCodeUtils {

    /**
     * 获取4位随机验证码
     * @return
     */
    public static String getFourValidationCode(){
        return String.valueOf((new Random().nextInt(8999) + 1000));
    }

    /**
     * 获取6位随机验证码
     * @return
     */
    public static String getSixValidationCode(){
        return String.valueOf((new Random().nextInt(899999) + 100000));
    }
}
