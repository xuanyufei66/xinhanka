package com.payease.wallet.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class TWithdrawalHistory implements Serializable {
    private Long id;

    private Long tUserInfoId;

    private Long tAccountTransactId;

    private String amount;

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

    public Long gettAccountTransactId() {
        return tAccountTransactId;
    }

    public void settAccountTransactId(Long tAccountTransactId) {
        this.tAccountTransactId = tAccountTransactId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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