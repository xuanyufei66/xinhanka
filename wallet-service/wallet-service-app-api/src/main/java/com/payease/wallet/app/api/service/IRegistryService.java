package com.payease.wallet.app.api.service;

import com.payease.wallet.dto.bean.RequestBo;
import com.payease.wallet.dto.bean.ResultBo;

/**
 * @Author : zhangwen
 * @Data : 2017/11/13
 * @Description : 用户注册
 */
public interface IRegistryService {

    /**
     * @Author zhangwen
     * @MethodName 注册接口
     * @Params 
     * @Return 
     * @Date 2017/11/13 上午11:32
     * @Desp 
     */
    public ResultBo registory(RequestBo requestBo) throws Exception;
}
