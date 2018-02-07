package com.payease.wallet.gateway.api.service;

import bo.ReqRealNameBO;
import bo.RspResultBO;

/**
 * 客户身份认证接口
 * Created by ljp on 2017/11/7.
 */
public interface IAuthenticationService {
    RspResultBO RealName(ReqRealNameBO reqRealNameBO);
}
