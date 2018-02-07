package com.payease.wallet.dto.utils;

/**
 * Created by zhangzhili on 2017/11/23.
 */
public class MaskCodeUtil {

    /**
     * 掩码手机号
     *
     * @param phone 15122144444
     * @return 151****4444
     */
    public static String maskPhone(String phone) {
        String phoneNumber = phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
        return phoneNumber;
    }

    /**
     * 掩码身份证号
     *
     * @param idCard 120 101 1990 0514  0016
     * @return 120 101 **** 0016
     */
    public static String maskIdCard(String idCard) {

        return idCard.substring(0, 6) + "****" + idCard.substring(
            idCard.length() - 4, idCard.length());
    }

    /**
     * 掩码名字
     *
     * @param realName 张三
     * @return *三
     */
    public static String maskRealName(String realName) {
        return "*" + realName.substring(1, realName.length());
    }

    /**
     * @param bankCard 622202 000 000 00 45678
     * @return 5678  银行卡最后四位
     */
    public static String maskBankCard(String bankCard) {
        if (bankCard.length() < 4) {
            return "0";
        }
        return bankCard.substring(bankCard.length() - 4, bankCard.length());
    }


}
