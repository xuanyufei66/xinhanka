package com.payease.wallet.dto.utils;

import java.util.Date;

/**
 * @Author : zhangwen
 * @Data : 2017/11/23
 * @Description :
 */
public class OrderNoUtil {

    public static String getOrderNo(OrderEnum orderEnum){
        return orderEnum.getCode()+DateUtil.formatNow()+RandomUtil.RandomNumber(8);

    }

    public static void main(String[] args) {
        System.out.println(OrderNoUtil.getOrderNo(OrderEnum.BANTRANSTER));
        System.out.println(OrderNoUtil.getOrderNo(OrderEnum.BANKINTO));
        System.out.println(OrderNoUtil.getOrderNo(OrderEnum.WALLETFROZEN));
        System.out.println(OrderNoUtil.getOrderNo(OrderEnum.WALLETFREE));

    }
}
