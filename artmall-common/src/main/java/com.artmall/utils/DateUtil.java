package com.artmall.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");

    public static Date getDate(){return new Date();}
    /**
     * 获取YYYY格式
     * @return
     */
    public static String getSdfTimes() {
        return sdfTimes.format(new Date());
    }

    /**
     * 获取YYYY格式
     * @return
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     * @return
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    /**
     * 获取YYYYMMDD格式
     * @return
     */
    public static String getDays(){
        return sdfDays.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     * @return
     */
    public static String getTime() {
        return sdfTime.format(new Date());

    }

    public static Date getDeadline(Date date,int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,day);
        return calendar.getTime();
    }

    public static Long getRemainDay(Date beginDate,Date endDate){
        Long a = (Long) (endDate.getTime()-beginDate.getTime())/(1000*3600*24);
        return a;
    }


    public static SimpleDateFormat getSdfYear() {
        return sdfYear;
    }

    public static SimpleDateFormat getSdfDay() {
        return sdfDay;
    }

    public static SimpleDateFormat getSdfDays() {
        return sdfDays;
    }

    public static SimpleDateFormat getSdfTime() {
        return sdfTime;
    }

    public static boolean isOutOffDate( Date finishTime) {
        Date nowTime = new Date();
        return nowTime.after(finishTime);
    }
}
