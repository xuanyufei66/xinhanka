package com.payease.wallet.app.impl.utils;

import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * Created by lch on 2017/11/17.
 */
public class DateUtil {

	/**
	 * 得到两个时间的间隔秒
	 * @param d1
	 * @param d2
     * @return
     */
	public static long getBetweenSecond(Date d1, Date d2){
		return Math.abs((d1.getTime() - d2.getTime())/1000);
	}

	/**
	 * 把传入的时间设置为起始时间时间，把时分秒设置成0
	 */
	public static Date getBeginDate(Date current){
		Calendar c = Calendar.getInstance();
		c.setTime(current);
		c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE),0,0,0);
		return c.getTime();
	}

	/**
	 * 把传入的时间设置为结束时间
	 *把时分秒设置为0
	 * 把天加1
	 * 把秒减1
	 */
	public static Date getEndDate(Date current){
		Calendar c = Calendar.getInstance();
		c.setTime(current);
		c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE),0,0,0);
		c.add(Calendar.DATE,1);
		c.add(Calendar.SECOND,-1);
		return c.getTime();
	}

	/**
	 * 得到一天的最后一秒钟
	 * 
	 * @param d
	 * @return
	 */
	public static Date endOfDay(Date d) {
		return DateUtils.addSeconds(
				DateUtils.addDays(DateUtils.truncate(d, Calendar.DATE), 1), -1);
	}

	/**
	 * 将date类型转化为yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String formatNewDatetoString(Date date){
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return fmt.format(date);
	}

}