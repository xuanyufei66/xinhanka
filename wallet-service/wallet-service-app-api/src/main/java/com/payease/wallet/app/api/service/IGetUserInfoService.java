package com.payease.wallet.app.api.service;

import com.payease.wallet.dto.bean.RequestBo;
import com.payease.wallet.dto.bean.ResultBo;

/**
 * Created by YHF on 2017/11/15.
 */
public interface IGetUserInfoService {
    //获取用户实名信息  3003
    ResultBo getUserInfo(RequestBo requestBo) throws Exception;
    //获取快捷支付协议 3006  Quick payment protocol
    ResultBo getPaymentProtocol(RequestBo requestBo);
    //发送短信验证码  4001
    ResultBo sendMessage(RequestBo requestBo);
    //获取汇率接口  5008
    ResultBo geteExchangeRate(RequestBo requestBo);
    //获取图片服务器地址 5009
    ResultBo getPicUrl(RequestBo requestBo);

}
