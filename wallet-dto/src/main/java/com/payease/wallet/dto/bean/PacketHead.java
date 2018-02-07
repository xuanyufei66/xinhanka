package com.payease.wallet.dto.bean;

import java.io.Serializable;

/**
 * Created by zhangzhili on 2017/11/2.
 */
public class PacketHead implements Serializable {

    private String serviceCode;

    private String token;

    private String sign;


    public PacketHead(String serviceCode,String token,String sign){
        this.serviceCode = serviceCode;
        this.token = token;
        this.sign = sign;
    }

    public PacketHead(){}


    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
