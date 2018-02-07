package com.payease.wallet.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class TBankBase implements Serializable {
    private Long id;

    private String bankName;

    private String bankLogoUrl;

    private String bankLogoNotColorUrl;

    private String bankBgColor;

    private String bankCode;

    private String bankLangCode;

    private String singleLimit;

    private String dayLimit;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankLogoUrl() {
        return bankLogoUrl;
    }

    public void setBankLogoUrl(String bankLogoUrl) {
        this.bankLogoUrl = bankLogoUrl;
    }

    public String getBankLogoNotColorUrl() {
        return bankLogoNotColorUrl;
    }

    public void setBankLogoNotColorUrl(String bankLogoNotColorUrl) {
        this.bankLogoNotColorUrl = bankLogoNotColorUrl;
    }

    public String getBankBgColor() {
        return bankBgColor;
    }

    public void setBankBgColor(String bankBgColor) {
        this.bankBgColor = bankBgColor;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankLangCode() {
        return bankLangCode;
    }

    public void setBankLangCode(String bankLangCode) {
        this.bankLangCode = bankLangCode;
    }

    public String getSingleLimit() {
        return singleLimit;
    }

    public void setSingleLimit(String singleLimit) {
        this.singleLimit = singleLimit;
    }

    public String getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(String dayLimit) {
        this.dayLimit = dayLimit;
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