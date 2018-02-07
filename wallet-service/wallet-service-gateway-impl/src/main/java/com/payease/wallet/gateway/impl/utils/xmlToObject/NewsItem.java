package com.payease.wallet.gateway.impl.utils.xmlToObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by zhaohebing on 2017-04-06.
 */
@XmlRootElement(name = "123")
public class NewsItem {
    @XmlElement(name = "v_seq")
    private String v_seq;
    @XmlElement(name = "tokenid")
    private String tokenid;
    @XmlElement(name = "cardtype")
    private String cardtype;
    @XmlElement(name = "cardmask")
    private String cardmask;
    @XmlElement(name = "bankname")
    private String bankname;
    @XmlElement(name = "sign")
    private String sign;

    @XmlTransient
    public String getV_seq() {
        return v_seq;
    }

    public void setV_seq(String v_seq) {
        this.v_seq = v_seq;
    }
    @XmlTransient
    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }
    @XmlTransient
    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }
    @XmlTransient
    public String getCardmask() {
        return cardmask;
    }

    public void setCardmask(String cardmask) {
        this.cardmask = cardmask;
    }
    @XmlTransient
    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
    @XmlTransient
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "v_seq='" + v_seq + '\'' +
                ", tokenid='" + tokenid + '\'' +
                ", cardtype='" + cardtype + '\'' +
                ", cardmask='" + cardmask + '\'' +
                ", bankname='" + bankname + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
