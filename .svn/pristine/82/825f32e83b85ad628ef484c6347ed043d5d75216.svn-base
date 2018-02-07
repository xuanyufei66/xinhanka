package com.payease.wallet.scheduling.service;

import bo.ReqPaymentStatusBO;
import bo.RspResultBO;
import com.alibaba.fastjson.JSON;
import com.payease.wallet.entity.pojo.TAccountInfo;
import com.payease.wallet.entity.pojo.TAccountInfoExample;
import com.payease.wallet.entity.pojo.TAccountTransact;
import com.payease.wallet.entity.pojo.TWithdrawalHistory;
import com.payease.wallet.gateway.api.service.IPaymentService;
import com.payease.wallet.orm.inter.TAccountInfoMapper;
import com.payease.wallet.orm.inter.TAccountTransactMapper;
import com.payease.wallet.orm.inter.TLoginInfoMapper;
import com.payease.wallet.orm.inter.TWithdrawalHistoryMapper;
import com.payease.wallet.scheduling.utils.MoneyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * Created by zhangzhili on 2017/11/30.
 */
@Service
public class CheckWithdrawalServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(CheckWithdrawalServiceImpl.class);

    @Value("${own.url.paymentStatus}")
    String paymentStatusUrl;//代付状态查询接口地址
    @Value("${own.mid}")
    String mid;//商户号
    @Value("${own.key}")
    String key;//md5的key
    @Value("${own.version}")
    String version;//版本号

    @Autowired
    IPaymentService iPaymentService;
    @Autowired
    TLoginInfoMapper tLoginInfoMapper;
    @Autowired
    TWithdrawalHistoryMapper tWithdrawalHistoryMapper;
    @Autowired
    TAccountTransactMapper tAccountTransactMapper;
    @Autowired
    TAccountInfoMapper tAccountInfoMapper;


    @Transactional
    public void handler(TWithdrawalHistory tWithdrawalHistory) {
        String uuid = UUID.randomUUID().toString();
        TAccountTransact tAccountTransact;
        log.info("提现记录uuid={}，提现参数={}", uuid, JSON.toJSONString(tWithdrawalHistory));
        //封装对象，调用查询提现状态接口
        ReqPaymentStatusBO reqPaymentStatusBO = new ReqPaymentStatusBO();
        tAccountTransact =
            tAccountTransactMapper.selectByPrimaryKey(tWithdrawalHistory.gettAccountTransactId());
        reqPaymentStatusBO.setRequestId(tAccountTransact.getTransactNo());
        //流水号（对应代付提交的用户唯一标识）
        reqPaymentStatusBO.setMid(mid);//商户号
        reqPaymentStatusBO.setKey(key);//md5的key
        reqPaymentStatusBO.setUrl(paymentStatusUrl);//代付状态查询的请求地址
        reqPaymentStatusBO.setVersion(version);//版本号
        log.info("提现uuid={}，状态查询接口入参为={}", uuid, JSON.toJSONString(reqPaymentStatusBO));
        RspResultBO rspResultBO = iPaymentService.paymentStatus(reqPaymentStatusBO);
        log.info("提现uuid={}，状态查询返回结果是={}", uuid, rspResultBO);
        //如果返回1，已成功，删除该条提现记录的数据，更新交易记录表中的deal_status为success
        if ("1".equals(rspResultBO.getCode())) {
            int delete = tWithdrawalHistoryMapper.deleteByPrimaryKey(tWithdrawalHistory.getId());
            tAccountTransact.setDealStatus("success");
            int update = tAccountTransactMapper.updateByPrimaryKeySelective(tAccountTransact);
            log.info("成功返回1时uuid={}，删除返回值={},更新返回值={}", uuid, delete, update);
        }
        //如果返回2，处理中，更新该条提现记录的数据的updatetime为当前时间
        else if ("2".equals(rspResultBO.getCode())) {
            tWithdrawalHistory.setUpdatetime(new Date());
            int update = tWithdrawalHistoryMapper.updateByPrimaryKeySelective(tWithdrawalHistory);
            log.info("成功返回2时uuid={}，更新返回值={}", uuid, update);
        }
        //如果返回3，已失败，删除该条提现记录的数据，更新交易记录表中的deal_status为fail,将可用余额和账户总额改回原金额
        else if ("3".equals(rspResultBO.getCode())) {
            int delete = tWithdrawalHistoryMapper.deleteByPrimaryKey(tWithdrawalHistory.getId());
            tAccountTransact.setDealStatus("fail");
            int updatetAccountTransact =
                tAccountTransactMapper.updateByPrimaryKeySelective(tAccountTransact);

            TAccountInfoExample tAccountInfoExample = new TAccountInfoExample();
            TAccountInfoExample.Criteria c = tAccountInfoExample.createCriteria();
            c.andTUserInfoIdEqualTo(tWithdrawalHistory.gettUserInfoId());
            TAccountInfo tAccountInfo =
                tAccountInfoMapper.selectByExample(tAccountInfoExample).get(0);
            c.andFreeAmountEqualTo(tAccountInfo.getFreeAmount());//保证更新时可用余额与之前查询的一样，防止值有变化
            tAccountInfo.setAmount(MoneyUtil.moneyAdd(tWithdrawalHistory.getAmount(),
                tAccountInfo.getAmount()));
            tAccountInfo.setFreeAmount(MoneyUtil.moneyAdd(tWithdrawalHistory.getAmount(),
                tAccountInfo.getFreeAmount()));
            int result =
                tAccountInfoMapper.updateByExampleSelective(tAccountInfo, tAccountInfoExample);
            log.info("成功返回3时uuid={}，删除返回值={},更新返回值={},账户更新返回值={}", uuid, delete,
                updatetAccountTransact, result);

            if (result == 0) {
                log.error("返回3时uuid={}更新失败记录回滚", uuid);
                throw new RuntimeException();
            }
        } else {
            tWithdrawalHistory.setUpdatetime(new Date());
            int update = tWithdrawalHistoryMapper.updateByPrimaryKeySelective(tWithdrawalHistory);
            log.info("状态不匹配时状态码是={}时,uuid={}，更新返回值={}", rspResultBO.getCode(), uuid, update);
        }
    }
}
