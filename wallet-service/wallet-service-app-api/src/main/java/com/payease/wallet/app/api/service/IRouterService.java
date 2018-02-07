package com.payease.wallet.app.api.service;

import com.payease.wallet.entity.pojo.TRouter;

/**
 * Created by zhangzhili on 2017/11/6.
 */
public interface IRouterService {

    /**
     * 获取路由
     * @param serviceCode  接口业务码
     * @return   返回路由对象
     */
    TRouter getRouterByServiceCode(String serviceCode);


    String getCode(String str);
}
