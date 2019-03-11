package com.artmall.utils;

import java.util.Random;

/**
 * 随机生成盐
 *
 * @author
 * @create 2018-08-06 20:48
 **/

public class SaltUtil {

    public static String InitSalt() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        return salt;
    }
}
