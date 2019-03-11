package com.artmall.utils;

/**
 * excel解析工具类
 *
 * @author mllove
 * @create 2018-11-12 10:09
 **/

public class ExcelUtils {


    public static boolean isExcel2003(String path) {
        return path.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String path) {
        return path.matches("^.+\\.(?i)(xlsx)$");
    }
}
