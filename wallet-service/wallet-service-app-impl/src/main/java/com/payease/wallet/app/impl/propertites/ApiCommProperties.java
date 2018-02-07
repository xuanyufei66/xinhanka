package com.payease.wallet.app.impl.propertites;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author : zhangwen
 * @Data : 2017/11/22
 * @Description :
 */
@ConfigurationProperties(prefix = "comm")
public class ApiCommProperties {

    //在银行卡基础信息表中维护的新韩银行code
    private String koreaBankCode="xhyh";

    public String getKoreaBankCode() {
        return koreaBankCode;
    }

    public void setKoreaBankCode(String koreaBankCode) {
        this.koreaBankCode = koreaBankCode;
    }
}
