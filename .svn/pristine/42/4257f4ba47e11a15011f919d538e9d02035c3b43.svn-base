package com.payease.wallet.dto.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static Date StringToDate(String strDate) throws ParseException {
       // string格式转化为Date对象：
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        return fmt.parse(strDate);
        // 注意：引入的是：java.text.DateFormat
    }


    public static String formatNow(){
        DateFormat fmt =new SimpleDateFormat("yyyyMMddHHmmss");
        return fmt.format(new Date());
    }

    public static String formatNewDatetoString(Date date)throws ParseException{
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(date);
    }
}
