package com.website.common;

/**
 * @program: WebSite
 * @description: 获取path工具类
 * @author: smallsoup
 * @create: 2018-06-30 19:58
 **/
public class PathUtils {

    public static String separator = System.getProperty("file.separator");

    public static String getImageBasePath(){

        String os = System.getProperty("os.name");

        String basePath = "";

        if (os.toLowerCase().startsWith("win")) {

            basePath = "D:/tmp/dev/";
        }else {
            basePath = "/home/dev/";
        }

        //替换/为操作系统的分隔符
        basePath = basePath.replace("/", separator);

        return basePath;
    }

    public static String getImagePath(long shopId){

        String path = "D:/tmp/dev/upload/dev/" + shopId + "/";

        return path.replace("/", separator);
    }

}
