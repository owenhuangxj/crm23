package com.ss.crm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 获取学生编号
     * @return 学生编号
     */
    public static String getStuNmuber(){
        String stuNumber = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        return "ss"+stuNumber;
    }

    /**
     * 获取当前时间
     * @return 当前时间
     */
    public static String getDateTime(String format){
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 获取当前时间
     * @return 当前时间
     */
    public static String getDateTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String changeDateTime(String date){
        String year = date.substring(date.lastIndexOf("/") + 1, date.length());
        String day = date.substring(date.indexOf("/") + 1, date.lastIndexOf("/"));
        String month = date.substring(0, date.indexOf("/"));
        return year + "-" + month + "-" + day;
    }
}
