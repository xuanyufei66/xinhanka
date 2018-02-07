package com.payease.wallet.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class TUserSetting implements Serializable {
    private Long id;

    private Long tUserInfoId;

    private Short realNameFlag;

    private Short gestureLoginFlag;

    private Short gestureWayFlag;

    private Short fingerFlag;

    private Short avoidPwdFlag;

    private String avoidPayAmount;

    private Short noticeFlag;

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

    public Short getRealNameFlag() {
        return realNameFlag;
    }

    public void setRealNameFlag(Short realNameFlag) {
        this.realNameFlag = realNameFlag;
    }

    public Short getGestureLoginFlag() {
        return gestureLoginFlag;
    }

    public void setGestureLoginFlag(Short gestureLoginFlag) {
        this.gestureLoginFlag = gestureLoginFlag;
    }

    public Short getGestureWayFlag() {
        return gestureWayFlag;
    }

    public void setGestureWayFlag(Short gestureWayFlag) {
        this.gestureWayFlag = gestureWayFlag;
    }

    public Short getFingerFlag() {
        return fingerFlag;
    }

    public void setFingerFlag(Short fingerFlag) {
        this.fingerFlag = fingerFlag;
    }

    public Short getAvoidPwdFlag() {
        return avoidPwdFlag;
    }

    public void setAvoidPwdFlag(Short avoidPwdFlag) {
        this.avoidPwdFlag = avoidPwdFlag;
    }

    public String getAvoidPayAmount() {
        return avoidPayAmount;
    }

    public void setAvoidPayAmount(String avoidPayAmount) {
        this.avoidPayAmount = avoidPayAmount;
    }

    public Short getNoticeFlag() {
        return noticeFlag;
    }

    public void setNoticeFlag(Short noticeFlag) {
        this.noticeFlag = noticeFlag;
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