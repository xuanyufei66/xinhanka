package com.payease.wallet.app.common.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.payease.wallet.dto.bean.RequestBo;
import com.payease.wallet.dto.exception.SignException;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;

/**
 * Created by zhangzhili on 2017/10/20.
 */
public class SignUtil {

    private static String KEY = "payeaseqwerty";


    /**
     * 签名防止数据被篡改
     *
     * @param parameters
     * @return
     */
    public static String createSign(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("sign=" + SignUtil.KEY);
        String sign = DigestUtils.md5Hex(sb.toString()).toUpperCase();
        return sign;
    }


    /**
     * 签名校验
     *
     * @param jsonStr 请求参数
     * @return
     */
    public static boolean checkSign(String jsonStr) throws SignException{
        JSONObject json = JSON.parseObject(jsonStr);
        String sign = (String) json.getJSONObject("packetHead").get("sign");
        json.getJSONObject("packetHead").remove("sign");
        SortedMap map = new TreeMap();
        json.getJSONObject("packetHead").entrySet().forEach(e->{
            map.put(e.getKey(),e.getValue());
        });
        json.getJSONObject("packetBody").entrySet().forEach(e->{
            map.put(e.getKey(),e.getValue());
        });
        if (sign.equals(createSign(map))){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        String str = "{\n" + "    \"packetHead\": {\n" + "        \"serviceCode\": \"3004\",\n"
//            + "        \"token\": \"fdsfdsfsfsf8f8dsfsdfsfsfsfsf0dsf0sdf\",\n"
//            + "        \"sign\":\"FHGHGFHFGHFGHGFHGFHGFHFGHF\"\n" + "    },\n"
//            + "    \"packetBody\": {\n" + "        \"bankCardNo\": \"654821456325478\",\n"
//            + "        \"realName\": \"张三\",\n" + "        \"identityCardNo\":\"5310\",\n"
//            + "        \"mobilePhone\":\"130010101010\",\n" + "        \"smsCode\":\"5656\" \n"
//            + "    }\n" + "}";
//        String sss="{\n" + "    \"packetHead\": {\n" + "        \"serviceCode\": \"2003\",\n"
//            + "        \"token\": \"fdsfdsfsfsf8f8dsfsdfsfsfsfsf0dsf0sdf\",\n"
//            + "        \"sign\":\"FHGHGFHFGHFGHGFHGFHGFHFGHF\"\n" + "    },\n"
//            + "    \"packetBody\": {}\n" + "}";
//
//       checkSign(sss);
       // System.out.println(DigestUtils.md5Hex("123456").toUpperCase());
    }

}
