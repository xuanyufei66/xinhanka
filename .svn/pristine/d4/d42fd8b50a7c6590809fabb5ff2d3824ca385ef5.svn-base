package com.payease.wallet.gateway.impl.serviceImpl;

import bo.*;
import com.payease.wallet.gateway.api.service.IFastPaymentService;
import com.payease.wallet.gateway.impl.utils.AES;
import com.payease.wallet.gateway.impl.utils.HttpClients;
import com.payease.wallet.gateway.impl.utils.Md5;
import com.payease.wallet.gateway.impl.utils.xmlToObject.NewsItem;
import com.payease.wallet.gateway.impl.utils.xmlToObject.OutNews;
import com.payease.wallet.gateway.impl.utils.xmlToObject.XmlOrObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by ljp on 2017/11/8.
 */
@Service
public class FastPaymentServiceImpl implements IFastPaymentService{

    private static final Logger log = LoggerFactory.getLogger(FastPaymentServiceImpl.class);
    //银行卡绑定接口
    @Override
    public RspResultBO cardBind(ReqCardBindBO reqCardBindBO) {
        Map<String,String> map = new HashMap<>();
        RspResultBO rspResultBo = new RspResultBO();
        try {
            map.put("v_mid", reqCardBindBO.getMid());
            map.put("v_idname",URLEncoder.encode(AES.aesEncrypt(URLEncoder.encode(reqCardBindBO.getIdName(),"utf-8"),"MTIzNDU2NzgxMjM0"),"utf-8"));
            map.put("v_cardno",URLEncoder.encode(AES.aesEncrypt(reqCardBindBO.getCardNo(),"MTIzNDU2NzgxMjM0"),"utf-8"));
            map.put("v_idtype","01");
            map.put("v_idnumber",URLEncoder.encode(AES.aesEncrypt(reqCardBindBO.getIdNumber(),"MTIzNDU2NzgxMjM0"),"utf-8"));
            map.put("v_phone",URLEncoder.encode(AES.aesEncrypt(reqCardBindBO.getPhone(),"MTIzNDU2NzgxMjM0"),"utf-8"));
            map.put("v_userref",reqCardBindBO.getUserRef());
            map.put("v_tokenpass",reqCardBindBO.getTokenPass());
            String mac1 = map.get("v_mid") + map.get("v_userref") + map.get("v_cardno") + map.get("v_idtype") + map.get("v_phone")+ map.get("v_idnumber") + map.get("v_idname") + map.get("v_tokenpass");
            String mac = new Md5().getMd5(mac1, reqCardBindBO.getKey());
            map.put("v_mac",mac);
            log.info("银行卡绑定的入参={}",map);
            Map<String,String> resultMap = HttpClients.xmlPutMap(HttpClients.InvokeHttpPost(reqCardBindBO.getUrl(), map));
            log.info("请求接口返回的参数={}",resultMap);
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("mid",resultMap.get("mid"));
            dataMap.put("userref",resultMap.get("userref"));
            dataMap.put("tokenid",resultMap.get("tokenid"));
            dataMap.put("cardtype",resultMap.get("cardtype"));
            dataMap.put("cardmask",resultMap.get("cardmask"));
            dataMap.put("bankname",resultMap.get("bankname"));
            dataMap.put("sign",resultMap.get("sign"));
            rspResultBo.deal(resultMap,dataMap);
            log.info("函数返回的参数={}",rspResultBo);
        } catch (Exception e) {
            e.printStackTrace();
            rspResultBo.error(e.getMessage());
        }
        return rspResultBo;
    }
    //短信发送接口
    @Override
    public RspResultBO cardSendMessage(ReqCardSendMessageBO reqCardSendMessageBO) {
        log.info("进入短信发送接口");
        Map<String,String> map = new HashMap<>();
        RspResultBO rspResultBo = new RspResultBO();
        try {
            map.put("v_mid", reqCardSendMessageBO.getMid());
            map.put("v_servicetype",reqCardSendMessageBO.getServiceType());
            map.put("v_userref",reqCardSendMessageBO.getUserref());
            map.put("v_mobile",reqCardSendMessageBO.getMobile());
            String mac1 = map.get("v_mid") + map.get("v_servicetype") + map.get("v_userref") + map.get("v_mobile");
            String mac = new Md5().getMd5(mac1, reqCardSendMessageBO.getKey());
            map.put("v_mac",mac);
            log.info("银行卡绑定短信发送的入参={}",map);
            Map<String,String> resultMap = HttpClients.xmlPutMap(HttpClients.InvokeHttpPost(reqCardSendMessageBO.getUrl(), map));
            log.info("请求接口返回的参数={}",resultMap);
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("mid",resultMap.get("mid"));
            dataMap.put("servicetype",resultMap.get("servicetype"));
            dataMap.put("userref",resultMap.get("v_userref"));
            dataMap.put("mobile",resultMap.get("mobile"));
            dataMap.put("sendstatus",resultMap.get("sendstatus"));
            dataMap.put("tokenpass",resultMap.get("tokenpass"));
            dataMap.put("sign",resultMap.get("sign"));
            rspResultBo.deal(resultMap,dataMap);
            log.info("函数返回的参数={}",rspResultBo);
        } catch (Exception e) {
            e.printStackTrace();
            rspResultBo.error(e.getMessage());
        }
        return rspResultBo;
    }
    //银行卡支付
    @Override
    public RspResultBO cardPay(ReqCardPayBO reqCardPayBO) {
        log.info("进入银行卡支付接口");
        Map<String,String> map = new HashMap<>();
        Map<String,String> resultMap;
        RspResultBO rspResultBo = new RspResultBO();
        map.put("v_mid", reqCardPayBO.getMid());
        map.put("v_oid",reqCardPayBO.getOid());
        map.put("v_amount",reqCardPayBO.getAmount().toString());
        map.put("v_ymd",reqCardPayBO.getYmd());
        map.put("v_moneytype",reqCardPayBO.getMoneyType().toString());
        map.put("v_tokenid",reqCardPayBO.getTokenId());
        map.put("v_userref",reqCardPayBO.getUserref());
        String mac1 = map.get("v_mid") + map.get("v_oid") + map.get("v_amount") + map.get("v_ymd")+ map.get("v_moneytype") + map.get("v_tokenid")+ map.get("v_userref");
        String mac = new Md5().getMd5(mac1, reqCardPayBO.getKey());
        map.put("v_mac",mac);
        log.info("银行卡支付的入参={}",map);
        try {
            resultMap = HttpClients.xmlPutMap(HttpClients.InvokeHttpPost(reqCardPayBO.getUrl(), map));
            log.info("请求接口返回的参数={}",resultMap);
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("mid",resultMap.get("mid"));
            dataMap.put("oid",resultMap.get("oid"));
            dataMap.put("pstatus",resultMap.get("pstatus"));
            dataMap.put("pstring",resultMap.get("pstring"));
            dataMap.put("sign",resultMap.get("sign"));
            rspResultBo.deal(resultMap,dataMap);
            log.info("函数返回的参数={}",rspResultBo);
        } catch (Exception e) {
            e.printStackTrace();
            rspResultBo.error(e.getMessage());
        }
        return rspResultBo;
    }
    //银行卡解绑接口
    @Override
    public RspResultBO cardUnbind(ReqCardUnbindBO reqCardUnbindBO) {
        log.info("进入银行卡解绑接口");
        Map<String,String> map = new HashMap<>();
        RspResultBO rspResultBo = new RspResultBO();
        map.put("v_mid", reqCardUnbindBO.getMid());
        map.put("v_tokenid",reqCardUnbindBO.getTokenId());
        map.put("v_userref",reqCardUnbindBO.getUserref());
        String mac1 = map.get("v_mid") + map.get("v_tokenid") + map.get("v_userref");
        String mac = new Md5().getMd5(mac1, reqCardUnbindBO.getKey());
        map.put("v_mac",mac);
        log.info("银行卡解绑的入参={}",map);
        try {
            Map<String,String> resultMap = HttpClients.xmlPutMap(HttpClients.InvokeHttpPost(reqCardUnbindBO.getUrl(), map));
            log.info("请求接口返回的参数={}",resultMap);
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("mid",resultMap.get("mid"));
            dataMap.put("userref",resultMap.get("v_userref"));
            dataMap.put("tokenid",resultMap.get("tokenid"));
            dataMap.put("closestatus",resultMap.get("closestatus"));
            dataMap.put("sign",resultMap.get("sign"));
            rspResultBo.deal(resultMap,dataMap);
            log.info("函数返回的参数={}",rspResultBo);
        } catch (Exception e) {
            e.printStackTrace();
            rspResultBo.error(e.getMessage());
        }
        return rspResultBo;
    }
    //银行卡列表接口
    @Override
    public RspResultBO cardListQuery(ReqCardListQueryBO reqCardListQueryBO) {
        //请求的参数存到map
        Map<String,String> map = new HashMap<>();
        map.put("v_mid",reqCardListQueryBO.getMid());
        map.put("v_userref",reqCardListQueryBO.getUserref());
        String mac1 = map.get("v_mid")+map.get("v_userref");
        String mac = new Md5().getMd5(mac1,reqCardListQueryBO.getKey());
        map.put("v_mac",mac);
        log.info("银行列表接口的入参={}",map);
        //请求接口后返回的结果存到resultMap
        RspResultBO rspResultBO = new RspResultBO();
        try {
            String s = HttpClients.InvokeHttpPost(reqCardListQueryBO.getUrl(), map);
            OutNews outNews = (OutNews)XmlOrObject.xmlStrToObject(OutNews.class, s);
            Map<String,String> resultMap = new HashMap<>();
            resultMap.put("status",outNews.getStatus());
            resultMap.put("statusdesc",outNews.getStatusdesc());
            Map<String,Object> dataMap = new HashMap<>();
            List<NewsItem> tokens = outNews.getTokens().getToken();
            dataMap.put("mid",outNews.getMid());
            dataMap.put("userref",outNews.getUserref());
            dataMap.put("token",tokens);
            rspResultBO.deal(resultMap,dataMap);
           /* JSONObject jsonObject = (JSONObject) JSON.toJSON(o);
            log.info("jsonObject={}",jsonObject);
            JSONObject jsonObject1 = JSON.parseObject(jsonObject.getString("tokens"));
            log.info("jsonObject1={}",jsonObject1);
            JSONArray jsonArray = JSON.parseArray(jsonObject1.getString("token"));
            log.info("jsonArray={}",jsonArray);
            log.info("jsonArray1={}",jsonArray.get(0));*/
           /* jsonObject={"userref":"1101","mid":"20027","tokens":{"token":[{"cardmask":"4071","tokenid":"8279e441f2fa2946cda8f24ce9670a1a","sign":"3e72f25b23f301ba1bb90548c21078d1","bankname":"icbc","cardtype":"14","v_seq":"1"},{"cardmask":"7482","tokenid":"44d8f42993707d7b44b521399f48f295","sign":"23cd6df34eeedfeace8f40ab4da564c3","bankname":"spdb","cardtype":"14","v_seq":"2"}]},"statusdesc":"Success","status":"0"}
             jsonObject1={"token":[{"cardmask":"4071","tokenid":"8279e441f2fa2946cda8f24ce9670a1a","sign":"3e72f25b23f301ba1bb90548c21078d1","bankname":"icbc","cardtype":"14","v_seq":"1"},{"cardmask":"7482","tokenid":"44d8f42993707d7b44b521399f48f295","sign":"23cd6df34eeedfeace8f40ab4da564c3","bankname":"spdb","cardtype":"14","v_seq":"2"}]}
             jsonArray=[{"cardmask":"4071","tokenid":"8279e441f2fa2946cda8f24ce9670a1a","sign":"3e72f25b23f301ba1bb90548c21078d1","bankname":"icbc","cardtype":"14","v_seq":"1"},{"cardmask":"7482","tokenid":"44d8f42993707d7b44b521399f48f295","sign":"23cd6df34eeedfeace8f40ab4da564c3","bankname":"spdb","cardtype":"14","v_seq":"2"}]
             jsonArray1={"cardmask":"4071","tokenid":"8279e441f2fa2946cda8f24ce9670a1a","sign":"3e72f25b23f301ba1bb90548c21078d1","bankname":"icbc","cardtype":"14","v_seq":"1"}*/
        } catch (Exception e) {
            e.printStackTrace();
            rspResultBO.error(e.getMessage());
        }
        return rspResultBO;
    }
}
