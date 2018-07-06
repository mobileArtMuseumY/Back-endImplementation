package com.website.common;

import java.util.Random;

/**
 * id 生成策略
 */
public class IDUtils {

    // 随机生成projectId
    public static long getProjectId() {
        // 取当前时间长整形 包含毫秒
        long millis = System.currentTimeMillis();
        // 加上两位随机数
        Random rondom = new Random();
        int end2 = rondom.nextInt(99);
        // 不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return id;
    }
}
