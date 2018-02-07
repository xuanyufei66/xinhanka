package com.payease.wallet.app.api.service;

import com.payease.wallet.dto.bean.RequestBo;
import com.payease.wallet.dto.bean.ResultBo;

/**
 * @Author : zhangwen
 * @Data : 2017/11/15
 * @Description :新韩卡业务接口
 */
public interface IkoreaCardService {

    /**
     * @Author zhangwen
     * @MethodName 申请新韩卡
     * @Params 
     * @Return 
     * @Date 2017/11/15 上午10:31 
     * @Desp 
     */
    public ResultBo applyKoreaCard(RequestBo requestBo) throws Exception;

    /**
     * @Author zhangwen
     * @MethodName 获取新韩卡详情
     * @Params 
     * @Return 
     * @Date 2017/11/15 上午10:31 
     * @Desp 
     */
    public ResultBo getKoreaCardDetail(RequestBo requestBo) throws Exception;

    /**
     * @Author zhangwen
     * @MethodName 新韩卡充值
     * @Params 
     * @Return 
     * @Date 2017/11/15 上午10:32 
     * @Desp 
     */
    public ResultBo chargeKoreaCard(RequestBo requestBo) throws Exception;

    /**
     * @Author zhangwen
     * @MethodName 激活挂失新韩卡
     * @Params 
     * @Return 
     * @Date 2017/11/15 上午10:32 
     * @Desp 
     */
    public ResultBo activateOrLossKoreaCard(RequestBo requestBo) throws Exception;

    /**
     * @Author zhangwen
     * @MethodName 新韩卡退款
     * @Params 
     * @Return 
     * @Date 2017/11/15 上午10:32 
     * @Desp 
     */
    public ResultBo refundKoreCard(RequestBo requestBo) throws Exception;

    /**
     * @Author zhangwen
     * @MethodName 获取新韩卡账单流水
     * @Params 
     * @Return 
     * @Date 2017/11/15 下午1:45
     * @Desp 
     */
    public ResultBo getKoreaCardBill(RequestBo requestBo) throws Exception;
}
