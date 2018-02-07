package com.payease.wallet.gateway.impl.utils.xmlToObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by zhaohebing on 2017-04-06.
 */
@XmlRootElement(name = "bindmessage")
public class OutNews {
    @XmlElement(name = "status")
    private String status;
    @XmlElement(name = "statusdesc")
    private String statusdesc;
    @XmlElement(name = "mid")
    private String mid;
    @XmlElement(name = "userref")
    private String userref;
    @XmlElement(name = "tokens")
    private ItemList tokens;

    @XmlTransient
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @XmlTransient
    public String getStatusdesc() {
        return statusdesc;
    }

    public void setStatusdesc(String statusdesc) {
        this.statusdesc = statusdesc;
    }
    @XmlTransient
    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
    @XmlTransient
    public String getUserref() {
        return userref;
    }

    public void setUserref(String userref) {
        this.userref = userref;
    }
    @XmlTransient
    public ItemList getTokens() {
        return tokens;
    }

    public void setTokens(ItemList tokens) {
        this.tokens = tokens;
    }

    @Override
    public String toString() {
        return "OutNews{" +
                "status='" + status + '\'' +
                ", statusdesc='" + statusdesc + '\'' +
                ", mid='" + mid + '\'' +
                ", userref='" + userref + '\'' +
                ", tokens=" + tokens +
                '}';
    }
}
