package com.payease.wallet.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class TAccountBank implements Serializable {
    private Long id;

    private Long tUserInfoId;

    private Long tBankBaseId;

    private String bankCardNo;

    private String bankCardType;

    private String tokenid;

    private String mobilePhone;

    private String provinces;

    private String city;

    private Date useTime;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long gettUserInfoId() {
        return tUserInfoId;
    }

    public void settUserInfoId(Long tUserInfoId) {
        this.tUserInfoId = tUserInfoId;
    }

    public Long gettBankBaseId() {
        return tBankBaseId;
    }

    public void settBankBaseId(Long tBankBaseId) {
        this.tBankBaseId = tBankBaseId;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(String bankCardType) {
        this.bankCardType = bankCardType;
    }

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}