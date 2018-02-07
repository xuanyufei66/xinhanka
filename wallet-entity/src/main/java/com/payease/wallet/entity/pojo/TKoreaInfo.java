package com.payease.wallet.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class TKoreaInfo implements Serializable {
    private Long id;

    private Long tUserInfoId;

    private Long tBankBaseId;

    private String koreaCardNo;

    private String koreaCardType;

    private Date koreaCardTime;

    private String koreaCardTotalAmount;

    private String koreaCardUsedAmount;

    private String koreaCardFreeAmount;

    private String koreaCardRetreatKoreaAmount;

    private String koreaCardRetreatChAmount;

    private String koreaCardStatus;

    private Date updatetime;

    private Short isDelete;

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

    public String getKoreaCardNo() {
        return koreaCardNo;
    }

    public void setKoreaCardNo(String koreaCardNo) {
        this.koreaCardNo = koreaCardNo;
    }

    public String getKoreaCardType() {
        return koreaCardType;
    }

    public void setKoreaCardType(String koreaCardType) {
        this.koreaCardType = koreaCardType;
    }

    public Date getKoreaCardTime() {
        return koreaCardTime;
    }

    public void setKoreaCardTime(Date koreaCardTime) {
        this.koreaCardTime = koreaCardTime;
    }

    public String getKoreaCardTotalAmount() {
        return koreaCardTotalAmount;
    }

    public void setKoreaCardTotalAmount(String koreaCardTotalAmount) {
        this.koreaCardTotalAmount = koreaCardTotalAmount;
    }

    public String getKoreaCardUsedAmount() {
        return koreaCardUsedAmount;
    }

    public void setKoreaCardUsedAmount(String koreaCardUsedAmount) {
        this.koreaCardUsedAmount = koreaCardUsedAmount;
    }

    public String getKoreaCardFreeAmount() {
        return koreaCardFreeAmount;
    }

    public void setKoreaCardFreeAmount(String koreaCardFreeAmount) {
        this.koreaCardFreeAmount = koreaCardFreeAmount;
    }

    public String getKoreaCardRetreatKoreaAmount() {
        return koreaCardRetreatKoreaAmount;
    }

    public void setKoreaCardRetreatKoreaAmount(String koreaCardRetreatKoreaAmount) {
        this.koreaCardRetreatKoreaAmount = koreaCardRetreatKoreaAmount;
    }

    public String getKoreaCardRetreatChAmount() {
        return koreaCardRetreatChAmount;
    }

    public void setKoreaCardRetreatChAmount(String koreaCardRetreatChAmount) {
        this.koreaCardRetreatChAmount = koreaCardRetreatChAmount;
    }

    public String getKoreaCardStatus() {
        return koreaCardStatus;
    }

    public void setKoreaCardStatus(String koreaCardStatus) {
        this.koreaCardStatus = koreaCardStatus;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Short getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Short isDelete) {
        this.isDelete = isDelete;
    }
}