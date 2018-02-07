package com.payease.wallet.gateway.api.service;

import bo.*;

/**
 * 快捷支付接口
 * Created by ljp on 2017/11/8.
 */
public interface IFastPaymentService {
    //银行卡绑定接口
    RspResultBO cardBind(ReqCardBindBO reqCardBindBO);
    //短信发送接口
    RspResultBO cardSendMessage(ReqCardSendMessageBO reqCardSendMessageBO);
    //银行卡支付接口
    RspResultBO cardPay(ReqCardPayBO reqCardPayBO);
    //银行卡解绑接口
    RspResultBO cardUnbind(ReqCardUnbindBO reqCardUnbindBO);
    //银行卡列表查询
    RspResultBO cardListQuery(ReqCardListQueryBO reqCardListQueryBO);
}
