package com.payease.wallet.gateway.impl.serviceImpl;

import bo.ReqPaymentStatusBO;
import bo.ReqPaymentSubmitBO;
import bo.RspResultBO;
import com.payease.wallet.gateway.api.service.IPaymentService;
import com.payease.wallet.gateway.impl.utils.HttpClients;
import com.payease.wallet.gateway.impl.utils.Md5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 代付接口实现
 * <p>
 * Created by lch on 2017/11/8.
 */
@Service
public class PaymentServiceImpl implements IPaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Override
    public RspResultBO paymentSubmit(ReqPaymentSubmitBO reqPaymentSubmitBO) {
        RspResultBO rspResultBo = new RspResultBO();
        //封装接口请求参数
        Map<String, String> map = new HashMap<>();
        Map<String, String> resultMap;
        map.put("v_mid", reqPaymentSubmitBO.getMid());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(date);
        //汇总行（格式：分帐信息总行数|分帐总金额|批次号）
        //批次号格式=商户编号-日期(yyyymmdd)-顺序号 例=888-20140709-000001 保证批次号的唯一性
        String totalRow = "1|" + reqPaymentSubmitBO.getMoney() + "|" + reqPaymentSubmitBO.getMid() + "-" + dateString + "-" + System.currentTimeMillis();
        log.info("totalRow={}", totalRow);
        //明细行（格式：收方帐号|收方帐户名|收方开户行|收方省份|收方城市|付款金额|客户标识|联行号）
        //客户标识为该笔交易在商户端的交易流水号，流水号唯一且不能重复，长度不超过 120 位字符。
        String detailsRow = "$" + reqPaymentSubmitBO.getAccountNo() + "|" + reqPaymentSubmitBO.getAccountName() + "|" +
                reqPaymentSubmitBO.getBankName() + "|" + reqPaymentSubmitBO.getProvince() + "|" + reqPaymentSubmitBO.getCity() + "|" +
                reqPaymentSubmitBO.getMoney() + "|" + reqPaymentSubmitBO.getUserID() + "|" + reqPaymentSubmitBO.getBankCode();
        log.info("detailsRow={}", detailsRow);
        map.put("v_data", totalRow + detailsRow);
        log.info("v_data={}", map.get("v_data"));
        String v_md5info = map.get("v_mid") + map.get("v_data");
        try {
            v_md5info = new Md5("").getMd5(URLEncoder.encode(v_md5info, "UTF-8"), reqPaymentSubmitBO.getKey());
            map.put("v_mac", v_md5info);
            map.put("v_version", reqPaymentSubmitBO.getVersion());
            log.info("map={}", map);
            log.info("url={}", reqPaymentSubmitBO.getUrl());
            resultMap = HttpClients.xmlPutMap(HttpClients.InvokeHttpPost(reqPaymentSubmitBO.getUrl(), map));
            log.info("resultMap：{}", resultMap);
            rspResultBo.deal(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            rspResultBo.error(e.getMessage());
        }
        return rspResultBo;
    }

    @Override
    public RspResultBO paymentStatus(ReqPaymentStatusBO reqPaymentStatusBO) {
        RspResultBO rspResultBo = new RspResultBO();
        Map<String, String> map = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, String> resultMap;
        map.put("v_mid", reqPaymentStatusBO.getMid());
        map.put("v_data", reqPaymentStatusBO.getRequestId());
        String v_md5info = map.get("v_mid") + map.get("v_data");
        v_md5info = new Md5("").getMd5(v_md5info, reqPaymentStatusBO.getKey());
        map.put("v_mac", v_md5info);
        map.put("v_version", reqPaymentStatusBO.getVersion());
        log.info("map={}", map);
        log.info("url={}", reqPaymentStatusBO.getUrl());
        try {
            resultMap = HttpClients.xmlPutMap(HttpClients.InvokeHttpPost(reqPaymentStatusBO.getUrl(), map));
            log.info("resultMap={}", resultMap);
            dataMap.put("fee", resultMap.get("v_fee"));
            rspResultBo.deal(resultMap, dataMap);
        } catch (Exception e) {
            e.printStackTrace();
            rspResultBo.error(e.getMessage());
        }
        return rspResultBo;
    }


}
