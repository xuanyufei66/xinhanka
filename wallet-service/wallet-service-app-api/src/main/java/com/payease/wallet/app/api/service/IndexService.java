package com.payease.wallet.app.api.service;

import com.payease.wallet.dto.bean.RequestBo;
import com.payease.wallet.dto.bean.ResultBo;

/**
 * @Author : zhangwen
 * @Data : 2017/11/13
 * @Description : 首页service
 */
public interface IndexService {

    /**
     * @Author zhangwen
     * @MethodName 获取首页展示图片
     * @Params
     * @Return
     * @Date 2017/11/13 上午11:08
     * @Desp
     */
    public ResultBo getIndexImages(RequestBo requestBo) throws Exception;

    /**
     * @Author zhangwen
     * @MethodName 获取用户详细信息
     * @Params
     * @Return
     * @Date 2017/11/13 上午11:09
     * @Desp
     */
    public ResultBo getUserDetailInfo(RequestBo requestBo) throws Exception;

    /**
     * @Author zhangwen
     * @MethodName 获取用户公告信息
     * @Params 
     * @Return 
     * @Date 2017/11/13 上午11:10 
     * @Desp 
     */
    public ResultBo getNoticeInfo(RequestBo requestBo) throws Exception;
}
