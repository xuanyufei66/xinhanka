package com.payease.wallet.dto.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * 数字格式化工具类
 * liuxiaoming
 * 2017/9/15
 * 字符串类型，必须是数字，否则会报错  java.lang.NumberFormatException 异常
 * */
public class DecimalFormatUtil {

	/**
	 * 初始化数据
	 */
	public static final BigDecimal ZERO = new BigDecimal("0");

	//String 转 BigDecimal
	public static BigDecimal toBigDecimal(String str){
		return new BigDecimal(str);
	}

	/**
	 * 比较两个字段大小  a.compareTo(b)
	 * //结果是:       -1 小于,0 等于,1 大于
	 * @param a
	 * @Param b
	 * @return
	 */
	public static int compareTo(String a,String b){
		if(b == null){
			return 1;
		}
		return  (toBigDecimal(a)).compareTo(toBigDecimal(b));

	}



	// 金额
	public static BigDecimal amountFormat(String str) {
		BigDecimal number = toBigDecimal(str);
		number = number.setScale(BidConst.STORE_SCALE, RoundingMode.HALF_UP);
		return number;
	}

	// 折现率
	public static BigDecimal rateFormat(String str) {
		BigDecimal number = toBigDecimal(str);
		number = number.setScale(BidConst.STORE_SCALE, RoundingMode.HALF_UP);
		return number;
	}
	//加减乘除计算
	/**
	 * 提供精确的加法运算。
	 * @param b1 被加数
	 * @param b2 加数
	 * @return 两个参数的和
	 */
	public static BigDecimal add(BigDecimal b1,BigDecimal b2){
		return b1.add(b2).setScale(BidConst.STORE_SCALE, RoundingMode.HALF_UP);
	}
	public static BigDecimal addStr(String b1,String b2){
		return toBigDecimal(b1).add(toBigDecimal(b2)).setScale(BidConst.STORE_SCALE, RoundingMode.HALF_UP);
	}

	/**
	 * 提供精确的减法运算。
	 * @param b1 被减数
	 * @param b2 减数
	 * @return 两个参数的差
	 */
	public static BigDecimal sub(BigDecimal b1,BigDecimal b2){
		return b1.subtract(b2).setScale(BidConst.STORE_SCALE, RoundingMode.HALF_UP);
	}
	public static BigDecimal subStr(String b1,String b2){
		return (toBigDecimal(b1)).subtract(toBigDecimal(b2)).setScale(BidConst.STORE_SCALE, RoundingMode.HALF_UP);
	}
	/**
	 * 提供精确的乘法运算。
	 * @param b1 被乘数
	 * @param b2 乘数
	 * @return 两个参数的积
	 */
	public static BigDecimal mul(BigDecimal b1,BigDecimal b2){
		return b1.multiply(b2).setScale(BidConst.STORE_SCALE, RoundingMode.HALF_UP);
	}
	public static BigDecimal mulStr(String b1,String b2){
		return toBigDecimal(b1).multiply(toBigDecimal(b2)).setScale(BidConst.STORE_SCALE, RoundingMode.HALF_UP);
	}
	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
	 * 小数点以后10位，以后的数字四舍五入。
	 * @param b1 被除数
	 * @param b2 除数
	 * @return 两个参数的商
	 */
	public static BigDecimal div(BigDecimal b1,BigDecimal b2){
		MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);
		if((b2+"").equals("0.00")){
			return b2;
		}
		return (b1).divide(b2,mc);
	}



















	public static BigDecimal decimalRateFormat(String str) {
		BigDecimal number = toBigDecimal(str);
		return number.multiply(BigDecimal.valueOf(100));
	}

	// 月利率
	public static BigDecimal monthRateFormat(BigDecimal number) {
		return number.multiply(BigDecimal.valueOf(100)).divide(
				BigDecimal.valueOf(12), BidConst.CAL_SCALE,
				RoundingMode.HALF_UP);
	}

	public static BigDecimal formatBigDecimal(BigDecimal data, int scal) {
		if (null == data)
			return new BigDecimal(0.00);
		return data.setScale(scal, BigDecimal.ROUND_HALF_UP);
	}
}
