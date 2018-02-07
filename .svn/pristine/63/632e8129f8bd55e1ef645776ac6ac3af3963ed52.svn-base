package com.payease.wallet.app.impl.serviceImpl;


import bo.ReqCardSendMessageBO;
import bo.ReqMessageBO;
import bo.RspResultBO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.payease.wallet.app.api.service.IAccountBankCardService;
import com.payease.wallet.app.api.service.IGetUserInfoService;
import com.payease.wallet.app.api.service.ILoginService;
import com.payease.wallet.dto.bean.*;
import com.payease.wallet.dto.utils.MaskCodeUtil;
import com.payease.wallet.entity.pojo.*;
import com.payease.wallet.gateway.api.service.IFastPaymentService;
import com.payease.wallet.gateway.api.service.IMessageCallService;
import com.payease.wallet.orm.inter.TCurrencyRateMapper;
import com.payease.wallet.orm.inter.TImgAddressMapper;
import com.payease.wallet.orm.inter.TPlAddressMapper;
import com.payease.wallet.orm.inter.TUserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by YHF on 2017/11/15.
 */
@Service
public class GetUserInfoServiceImpl implements IGetUserInfoService {

    @Autowired
    IFastPaymentService fastPaymentService;
    @Autowired
    ILoginService loginService;
    @Autowired
    IMessageCallService messageCallService;
    @Autowired
    TUserInfoMapper tUserInfoMapper;
    @Autowired
    TPlAddressMapper tPlAddressMapper;  //支付协议mapper
    @Autowired
    TCurrencyRateMapper tCurrencyRateMapper;
    @Autowired
    TImgAddressMapper tImgAddressMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpts;
    @Value("${own.url.messageSend}")
    String messageurl;
    @Value("${own.mid}")
    String mid;
    @Value("${own.key}")
    String key;
    @Value("${own.version}")
    String version;
    @Value("${own.url.cardSendMessage}")
    String cardSendMessage;


    /**
     * 获取用户实名信息  3001
     *
     * @param requestBo
     * @return
     */
    @Override
    public ResultBo getUserInfo(RequestBo requestBo) throws Exception {
        ResultBo result = ResultBo.build();
        PacketHead packetHead = requestBo.getPacketHead();
        //获取用户
        LoginUserBo loginUserBo = loginService.getUserInRedis(packetHead.getToken());
//        TUserInfo tUserInfo = tUserInfoMapper.selectByPrimaryKey(loginUserBo.gettUserInfoId());
//GETUSERINFO("2005", "获取用户实名信息失败"),GETPAYPROTOCOL("2006", "获取快捷支付协议地址失败该用户未登录或地址为null"),
        //SENDMESSAGE("2007", "发送短信验证码失败"), GETPICURL("2010", "获取图片服务器地址失败"),
        ResultBody resultBody = new ResultBody();

        String realname=loginUserBo.getRealName();

        resultBody.put("realName", MaskCodeUtil.maskRealName(realname));
        String identity=loginUserBo.getIdentityCardNo();   //身份证号

        resultBody.put("identityCardNo", MaskCodeUtil.maskIdCard(identity));
        resultBody.put("mobilePhone", loginUserBo.getMobilePhone());
        result.setResultBody(resultBody);
        return result;

    }

    /**
     * 获取快捷支付协议地址
     *
     * @param requestBo
     * @return
     */
    @Override
    public ResultBo getPaymentProtocol(RequestBo requestBo) {
        ResultBo result = ResultBo.build();
        TPlAddressExample tPlAddressExample = new TPlAddressExample();
        List<TPlAddress> tPlAddresses = tPlAddressMapper.selectByExample(tPlAddressExample);
        if(tPlAddresses.size()!=0){
            List<Map> list = new ArrayList<>();
            tPlAddresses.forEach(e -> {
                Map<String, String> map = new HashMap<>();
                map.put("protocolUrl", e.getProtocolUrl());
                map.put("protocolType", e.getProtocolType());
                list.add(map);
            });
            ResultBody resultBody = new ResultBody();
            resultBody.put("protocolList", list);
            result.setResultBody(resultBody);
            return result;
        }else{
            return result.fail();
        }

    }

    /**
     * 发送短信验证码
     * 分登陆与未登录两种情况，其中注册与忘记密码为 未登录，重置与添加银行卡为登录状态，
     *
     * @param requestBo
     * @return
     */
    @Override
    public ResultBo sendMessage(RequestBo requestBo) {
        ResultBo result = ResultBo.build();
        PacketHead packetHead = requestBo.getPacketHead();
        JSONObject json = JSON.parseObject(requestBo.getPacketBody());
        //        if (StringUtils.isNotBlank(packetHead.getServiceCode())
        //            && "4001".equals(packetHead.getServiceCode())) {
        ReqMessageBO reqMessageBO = new ReqMessageBO();
        String authtype = json.getString("authType");   //验证码场景
        // registe-注册；forgetpwd-忘记密码；resetpaypwd-重置支付密码；addbankcard -添加银行卡
        String phone = json.getString("mobilePhone");    //手机号



        if (authtype.equals("registe")) {   //注册
            if (valOpts.get("registe" + phone) == null) {
                reqMessageBO.setUrl(messageurl);
                String code = getrandom();
                reqMessageBO.setV_content("【易智付科技】您好,您申请注册境外易购APP的验证码是:" + code+"。泄露验证码会影响您的账户安全。");
                reqMessageBO.setV_servicecode("0019");
                reqMessageBO.setV_mobile(phone);
                reqMessageBO.setKey("payeaseshopPWD");
                // reqMessageBO.setV_content(s);
                RspResultBO rspResultBO = send(reqMessageBO);   //发送短信验证
                // 返回码 success：0   fail:1
                if (rspResultBO.getCode().equals("0")) {
                    valOpts.set("registe" + phone, code, 5L, TimeUnit.MINUTES);
                    return result;
                } else {
                    result.interError(rspResultBO.getDesc());
//                    result.setResultHead(ResultBo.Status.SENDMESSAGE);
                    return result ;
                }
            } else {
                reqMessageBO.setUrl(messageurl);
                reqMessageBO.setV_content("【易智付科技】您好,您申请注册境外易购APP的验证码是:" + valOpts.get("registe" + phone)+"。泄露验证码会影响您的账户安全。");
                reqMessageBO.setV_servicecode("0019");
                reqMessageBO.setV_mobile(phone);
                reqMessageBO.setKey("payeaseshopPWD");
                // reqMessageBO.setV_content(s);
                RspResultBO rspResultBO = send(reqMessageBO);   //发送短信验证
                if (rspResultBO.getCode().equals("0")) {
                    return result;
                } else {
                    result.interError(rspResultBO.getDesc());
//                    result.setResultHead(ResultBo.Status.SENDMESSAGE);
                    return result ;
                }
            }
        }
        if (authtype.equals("forgetpwd")) {  //忘记
            if (valOpts.get("forgetpwd" + phone) == null) {
                reqMessageBO.setUrl(messageurl);
                String code = getrandom();
                reqMessageBO.setV_content("【易智付科技】您好,您申请找回登录密码的验证码是:" + code+"。泄露验证码会影响您的账户安全。");
                reqMessageBO.setV_servicecode("0019");
                reqMessageBO.setV_mobile(phone);
                reqMessageBO.setKey("payeaseshopPWD");
                // reqMessageBO.setV_content(s);
                RspResultBO rspResultBO = send(reqMessageBO);
                if (rspResultBO.getCode().equals("0")) {
                    valOpts.set("forgetpwd" + phone, code, 5L, TimeUnit.MINUTES);
                    return result;
                } else {
                    result.interError(rspResultBO.getDesc());
//                    result.setResultHead(ResultBo.Status.SENDMESSAGE);
                    return result ;
                }
            } else {
                reqMessageBO.setUrl(messageurl);
                //String code = getrandom();
                reqMessageBO.setV_content("【易智付科技】您好,您申请找回登录密码的验证码是:" + valOpts.get("forgetpwd" + phone)+"。泄露验证码会影响您的账户安全。");
                reqMessageBO.setV_servicecode("0019");
                reqMessageBO.setV_mobile(phone);
                reqMessageBO.setKey("payeaseshopPWD");
                RspResultBO rspResultBO = send(reqMessageBO);
                if (rspResultBO.getCode().equals("0")) {
                    return result;
                } else {
                    result.interError(rspResultBO.getDesc());
//                    result.setResultHead(ResultBo.Status.SENDMESSAGE);
                    return result ;
                }
            }
        }
        //暂时这么写***********
        String token = packetHead.getToken();
        LoginUserBo loginUserBo = null;
        try {
            loginUserBo = loginService.getUserInRedis(token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (loginUserBo == null) {
            return result.fail();
        }
        //后期优化*********************
        if (authtype.equals("resetpaypwd")) {   //重置
            if (valOpts.get("resetpaypwd" + phone) == null) {
                reqMessageBO.setUrl(messageurl);
                String code = getrandom();
                reqMessageBO.setV_content("【易智付科技】您好,您申请找回支付密码的验证码是:" + code+"。泄露验证码会影响您的账户安全。");
                reqMessageBO.setV_servicecode("0019");
                reqMessageBO.setV_mobile(phone);
                reqMessageBO.setKey("payeaseshopPWD");
                // reqMessageBO.setV_content(s);
                RspResultBO rspResultBO = send(reqMessageBO);
                if (rspResultBO.getCode().equals("0")) {
                    valOpts.set("resetpaypwd" + phone, code, 5L, TimeUnit.MINUTES);
                    return result;
                } else {
                    result.interError(rspResultBO.getDesc());
                 //   result.setResultHead(ResultBo.Status.SENDMESSAGE);
                    return result ;
                }

            } else {
                reqMessageBO.setUrl(messageurl);
                reqMessageBO.setV_content("【易智付科技】您好,您申请找回支付密码的验证码是:" + valOpts.get("resetpaypwd" + phone)+"。泄露验证码会影响您的账户安全。");
                reqMessageBO.setV_servicecode("0019");
                reqMessageBO.setV_mobile(phone);
                reqMessageBO.setKey("payeaseshopPWD");
                // reqMessageBO.setV_content(s);
                RspResultBO rspResultBO = send(reqMessageBO);
                if (rspResultBO.getCode().equals("0")) {
                    return result;
                } else {
                    result.interError(rspResultBO.getDesc());
                    //result.setResultHead(ResultBo.Status.SENDMESSAGE);
                    return result ;
                }
            }
        }


        if (authtype.equals("addbankcard")) {    // 添加银行卡
                ReqCardSendMessageBO reqCardSendMessageBO = new ReqCardSendMessageBO();
                reqCardSendMessageBO.setMobile(phone);
                reqCardSendMessageBO.setServiceType("0");
                reqCardSendMessageBO.setKey(key);
                reqCardSendMessageBO.setMid(mid);
                reqCardSendMessageBO.setUrl(cardSendMessage);
                reqCardSendMessageBO.setVersion(version);
                reqCardSendMessageBO.setUserref(loginUserBo.getUserref());
                RspResultBO rspResultBO = fastPaymentService.cardSendMessage(reqCardSendMessageBO);
                if (rspResultBO.getCode().equals("0")) {
                    String tokenpass = rspResultBO.getData().get("tokenpass") + "";
                    valOpts.set("addbankcard" + phone, tokenpass, 5L, TimeUnit.MINUTES);
                    return result;
                } else {
                    result.interError(rspResultBO.getDesc());
                    //result.setResultHead(ResultBo.Status.SENDMESSAGE);
                    return result ;
                }
        }

        return result;
    }

    /**
     * 获取汇率  5008
     *
     * @param requestBo
     * @return
     */
    @Override
    public ResultBo geteExchangeRate(RequestBo requestBo) {

        ResultBo result = ResultBo.build();
        JSONObject json = JSON.parseObject(requestBo.getPacketBody());
        String currencyType = json.getString("currencyType");
        TCurrencyRateExample tCurrencyRateExample = new TCurrencyRateExample();
        tCurrencyRateExample.createCriteria().andCurrencyTypeEqualTo(currencyType);
        List<TCurrencyRate> tCurrencyRates =
            tCurrencyRateMapper.selectByExample(tCurrencyRateExample);
        if (tCurrencyRates != null && tCurrencyRates.size() > 0) {
            String rate = tCurrencyRates.get(0).getRate();
            ResultBody resultBody = new ResultBody();
            resultBody.put("rate", rate);
            result.setResultBody(resultBody);
            return result;
        } else {
            // GETEXCHANGERATE("2009", "获取汇率失败"),
           // result.setResultHead(ResultBo.Status.GETEXCHANGERATE);
            return result.fail() ;
        }
    }


    /**
     * 获取图片服务器地址  5009
     *
     * @param requestBo
     * @return
     */
    @Override
    public ResultBo getPicUrl(RequestBo requestBo) {
        ResultBo result = ResultBo.build();
        ResultBody resultBody = new ResultBody();
        TImgAddressExample tImgAddressExample = new TImgAddressExample();
        List<TImgAddress> tImgAddresses = tImgAddressMapper.selectByExample(tImgAddressExample);
        if (tImgAddresses != null && tImgAddresses.size() > 0) {
            resultBody.put("imgServerUrl", tImgAddresses.get(0).getImgHost());
            result.setResultBody(resultBody);
            return result;
        }
       // result.setResultHead(ResultBo.Status.GETPICURL);
        return result.fail() ;
    }

    /**
     * 发送短信
     *
     * @param reqMessageBO
     * @return
     */

    private RspResultBO send(ReqMessageBO reqMessageBO) {
        // RspResultBO rspResultBO = new RspResultBO();
        RspResultBO rspResultBO = null;
        try {
            rspResultBO = messageCallService.sendMessage(reqMessageBO);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rspResultBO;
    }

    /**
     * 生成随机数
     *
     * @return
     */
    private String getrandom() {
        Random random = new Random();

        String result = "";

        for (int i = 0; i < 6; i++) {

            result += random.nextInt(10);
        }
        return result;
    }

    public static void main(String[] args) {
        GetUserInfoServiceImpl getUserInfoService = new GetUserInfoServiceImpl();
        RequestBo requestBo = new RequestBo();
        String packbody = "";
        //      requestBo.setPacketBody();
        //
        //
        //        getUserInfoService.getUserInfo();
        Random random = new Random();

        String result = "";

        for (int i = 0; i < 6; i++) {

            result += random.nextInt(10);
        }
        System.out.print(result);
    }
}
