package com.payease.wallet.entity.pojo;

import java.io.Serializable;

public class TAreas implements Serializable {
    private Integer id;

    private Integer parentId;

    private String areaName;

    private Integer sort;

    private Boolean isHot;

    private Boolean isCity;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    public Boolean getIsCity() {
        return isCity;
    }

    public void setIsCity(Boolean isCity) {
        this.isCity = isCity;
    }
}