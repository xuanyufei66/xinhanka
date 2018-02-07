package com.payease.wallet.app.impl.utils;

import java.util.Random;

/**
 * 获得8位随机数
 * Created by lch on 2017/11/15.
 */
public class RandomUtil {

    public static StringBuilder get8Random() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            str.append(random.nextInt(10));
        }
        return str;
    }
}
