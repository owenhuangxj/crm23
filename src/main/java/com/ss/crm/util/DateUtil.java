package com.ss.crm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 获取学生编号
     *
     * @return 学生编号
     */
    public static String getStuNumber() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static String getDateTime(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static String getDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 改变时间格式
     *
     * @param date
     * @return 时间
     */
    public static String changeDateTime(String date) {
        String date1 = null;
        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z").parse(date.replace("Z", " UTC"));
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    public static void main(String args[]) {

        String date = "2019-01-16T06:28:27.000Z";
        String dd = "2018-01-22T09:12:43.083Z";

//        String s = DateUtil.changeDateTime(date);
//        System.out.println(s);
        String s = DateUtil.getStuNumber();
        System.out.println(s);

    }


}
