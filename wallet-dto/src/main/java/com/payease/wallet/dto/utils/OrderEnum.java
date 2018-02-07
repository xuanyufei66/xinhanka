package com.payease.wallet.dto.utils;

/**
 * @Author : zhangwen
 * @Data : 2017/11/23
 * @Description :
 */
public enum OrderEnum {

//banktransfer-帐户充值 bankinto-帐户提现 walletfrozen-新韩卡充值 walletfree-新韩卡提现 koreascan-新韩卡扫码支付 koreacard-新韩卡刷卡支付

    BANTRANSTER("BANTRANSTER","100001"),BANKINTO("BANKINTO","100002"),WALLETFROZEN("WALLETFROZEN","200001"),WALLETFREE("WALLETFREE","200002");

    private String name;
    private String code;

    private OrderEnum( String name , String code ){
        this.name = name ;
        this.code = code ;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
