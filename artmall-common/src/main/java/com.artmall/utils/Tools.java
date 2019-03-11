package com.artmall.utils;

import com.artmall.response.Const;

import java.util.Random;

public class Tools {
    /**
     * 随机生成验证码
     */
    public static String getRandomNum() {
        String sources = "0123456789";
        Random ran = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j = 0; j < 6; j++)
            flag.append(sources.charAt(ran.nextInt(9)) + "");
        return (flag.toString());
    }

    /**
     * 检验key是否正确
     */
    public static boolean checkKey(String paraname, String key) {
        paraname = (null == paraname) ? "" : paraname;
        return MD5.md5(paraname + DateUtil.getDays() + ",fh,").equals(key);
    }

    public static byte setSex(String sex) throws Exception {
        if (sex.equals("男")){
            return Const.MAN;
        }else if (sex.equals("女")){
            return Const.WOMAN;
        }else {
            throw new Exception("性别输入有误");
        }
    }

    public static Long initId(){
        int num1 = new Random().nextInt(10)+1;
        int num2 = new Random().nextInt(10)+1;
        int num3 = new Random().nextInt(10)+1;
        Long id = new IDUtils(num1,num2).nextId()+num3;
        return id;
    }
}
