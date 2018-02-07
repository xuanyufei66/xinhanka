package com.payease.wallet.app.api.service;

import com.payease.wallet.dto.bean.RequestBo;
import com.payease.wallet.dto.bean.ResultBo;

/**
 * Created by ljp on 2017/11/12.
 */
public interface IAccountBankCardService {
    //获取地区(省市)
    ResultBo getAreas(RequestBo requestBo) throws Exception;
    //实名认证
    ResultBo authentication(RequestBo requestBo) throws Exception;
    //添加银行卡
    ResultBo bindCard(RequestBo requestBo) throws Exception;
    //获取银行卡列表
    ResultBo cardList(RequestBo requestBo) throws Exception;
    //删除银行卡
    ResultBo cardUnbind(RequestBo requestBo) throws Exception;
    //综合验证接口
    ResultBo verification(RequestBo requestBo) throws Exception;
    //会员账户充值
    ResultBo recharge(RequestBo requestBo) throws Exception;
    //获取会员账户可提现金额
    ResultBo getFreeAmount(RequestBo requestBo) throws Exception;
    //会员账户提现
    ResultBo withdrawal(RequestBo requestBo) throws Exception;
    //获取会员帐户账单列表
    ResultBo getBillList(RequestBo requestBo) throws Exception;
}
