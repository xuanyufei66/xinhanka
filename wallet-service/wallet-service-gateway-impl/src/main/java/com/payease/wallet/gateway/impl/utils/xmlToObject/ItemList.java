package com.payease.wallet.gateway.impl.utils.xmlToObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Created by zhaohebing on 2017-04-06.
 */
@XmlRootElement(name = "lists")
public class ItemList {
    @XmlElement(name = "token")
    private List<NewsItem> token;

    @XmlTransient
    public List<NewsItem> getToken() {
        return token;
    }

    public void setToken(List<NewsItem> token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ItemList{" +
                "token=" + token +
                '}';
    }
}
