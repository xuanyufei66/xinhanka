package com.payease.wallet.dto.utils;

/**
 * @Author : zhangwen
 * @Data : 2017/11/14
 * @Description :随机数工具类
 */
public class RandomUtil {


    public static String getSixRandom(){
        int randNum = 1 + (int)(Math.random() * ((999999 - 1) + 1));
        return String.valueOf(randNum);
    }

    public static String getEightRandom(){
        int randNum = 1 + (int)(Math.random() * ((99999999 - 1) + 1));
        return String.valueOf(randNum);
    }

    /**
     * 十一位随机数
     * @return
     */
    public static String RandomNumber(Integer num){

        int a[] = new int[num];
        String ram ="";
        for(int i=0;i<a.length;i++ ) {
            a[i] = (int)(10*(Math.random()));
            System.out.print(a[i]);
            ram += a[i];
        }
        return ram;
    }


}
