package com.payease.wallet.gateway.impl.serviceImpl;

import bo.ReqMessageBO;
import bo.RspResultBO;
import com.payease.wallet.gateway.api.service.IMessageCallService;
import com.payease.wallet.gateway.impl.utils.HttpClients;
import com.payease.wallet.gateway.impl.utils.Md5;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.payease.wallet.gateway.impl.utils.HttpClients.sendPost;

/**
 * Created by YHF on 2017/11/7.
 * 短信发送 业务编码:0019   md5秘钥:1 payeaseshopPWD
 * v_mac=hmac_md5(text , key)；其中 text 是上述参数 值按如下顺序拼串的结果： v_servicecode + v_mobile + v_content，key 为双方约定 的密钥。
 * 是
 */

@Service
public class MessageCallServiceImpl implements IMessageCallService {
    @Value("messageSendUrl")
    String messageSendUrl;

    @Override
    public RspResultBO sendMessage(ReqMessageBO reqMessageBo) throws UnsupportedEncodingException {
        Md5 md5 = new Md5("");
        RspResultBO rspResultBo = new RspResultBO();
        String param;
        String serviceCode = reqMessageBo.getV_servicecode();
        String mobile = reqMessageBo.getV_mobile();
        String content = reqMessageBo.getV_content();
        String key = reqMessageBo.getKey();
        String contentGbk = URLEncoder.encode(content,"GBK");
        String contentUTF8 = URLEncoder.encode(contentGbk,"utf-8");
        String b = serviceCode + mobile + contentGbk; // 拼接 v_mac
        if (StringUtils.isNotBlank(serviceCode)) {
            reqMessageBo.setV_servicecode("0019");
            reqMessageBo.setV_mac(md5.getMd5(b, key));
            System.out.println("MD5密码：" + md5.getMd5(b, key));//payeaseshopPWD
            param = "v_servicecode=" + reqMessageBo.getV_servicecode() + "&v_mobile=" + reqMessageBo.getV_mobile() + "&v_content=" +
                    contentUTF8 + "&v_mac=" + reqMessageBo.getV_mac();
            if(StringUtils.isNotBlank(reqMessageBo.getUrl())){
                String sr = sendPost(reqMessageBo.getUrl(), param);       //发送 POST 请求
                rspResultBo.deal(HttpClients.xmlPutMap(sr));
            }else{
                String sr = sendPost(messageSendUrl, param);       //发送 POST 请求
                rspResultBo.deal(HttpClients.xmlPutMap(sr));
            }

            return rspResultBo;
        } else {
            rspResultBo.setCode("1");
            rspResultBo.setDesc("业务码错误");
            return rspResultBo;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        ReqMessageBO reqMessageBo = new ReqMessageBO();
        reqMessageBo.setV_servicecode("0019");
        reqMessageBo.setV_mobile("15122144426");
        reqMessageBo.setKey("payeaseshopPWD");
        String s = "啊哈哈哈哈";
        reqMessageBo.setV_content(s);
        //reqMessageBo.setUrl("https://api.yizhifubj.com/merchant/ack/serviceSmsApi.jsp");
        reqMessageBo.setUrl("http://210.73.90.235/merchant/ack/serviceSmsApi.jsp");
        MessageCallServiceImpl iMessageCallService = new MessageCallServiceImpl();
        RspResultBO rspResultBo = iMessageCallService.sendMessage(reqMessageBo);
        System.out.println(rspResultBo.getCode());
    }
}
