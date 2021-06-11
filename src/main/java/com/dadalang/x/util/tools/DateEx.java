package com.dadalang.x.util.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/28 11:51 上午
 * @desc
 */
public class DateEx {

    static public Long now() {
        Date date = new Date();
        return date.getTime();
    }
    // 2020-06-01
    public static String yyyymmdd(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    // 2020-06-01 13:25:53
    public static String yyyyMMddHHmmss(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    // Mon June 26 00:00:00 CST 2021
    public static Date timestamp() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        try{
            return format.parse(format.format(now));
        }catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
