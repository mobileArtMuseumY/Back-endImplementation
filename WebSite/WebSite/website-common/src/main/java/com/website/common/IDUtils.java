package com.website.common;

import java.util.Random;
/**
 * 各种id生成策略
 * Title: IDUtils
 * @author	ikook
 */
public class IDUtils {

    //随机生成ProjectID
    public static long generateId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return id;
    }
}
