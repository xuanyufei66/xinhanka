package com.payease.wallet.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class TUserMessage implements Serializable {
    private Long id;

    private Long tUserInfoId;

    private String messagHead;

    private String messageContent;

    private String messageFrom;

    private Date messageTime;

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

    public String getMessagHead() {
        return messagHead;
    }

    public void setMessagHead(String messagHead) {
        this.messagHead = messagHead;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
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