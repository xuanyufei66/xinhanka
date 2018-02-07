package com.payease.wallet.gateway.impl.serviceImpl;

import bo.ReqRealNameBO;
import bo.RspResultBO;
import com.payease.wallet.gateway.api.service.IAuthenticationService;
import com.payease.wallet.gateway.impl.utils.HttpClients;
import com.payease.wallet.gateway.impl.utils.Md5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ljp on 2017/11/7.
 */
@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    /**
     * 实名认证
     * @param reqRealNameBO
     * @return
     */
    @Override
    public RspResultBO RealName(ReqRealNameBO reqRealNameBO) {

        RspResultBO rspResultBo = new RspResultBO();
        try {
            Map<String,String> map = new HashMap<>();
            map.put("v_mid",reqRealNameBO.getMid());
            map.put("v_idtype",reqRealNameBO.getIdtype());
            map.put("v_idnumber",reqRealNameBO.getIdnumber());
            map.put("v_idname",URLEncoder.encode(reqRealNameBO.getIdname(),"UTF-8"));
            String mac1 = reqRealNameBO.getMid() + reqRealNameBO.getIdtype() + reqRealNameBO.getIdnumber() + URLEncoder.encode(reqRealNameBO.getIdname(),"UTF-8");
            String mac = new Md5().getMd5(mac1,reqRealNameBO.getKey());
            map.put("v_mac",mac);
            log.info("实名认证的入参={}",map);
            Map<String,String> resultMap;
            resultMap = HttpClients.xmlPutMap(HttpClients.InvokeHttpPost(reqRealNameBO.getUrl(), map));
            log.info("请求实名认证接口返回的结果={}",resultMap);
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("mid",resultMap.get("mid"));
            dataMap.put("idtype",resultMap.get("idtype"));
            dataMap.put("idnumber",resultMap.get("idnumber"));
            dataMap.put("idname",resultMap.get("idname"));
            dataMap.put("verifystatus",resultMap.get("verifystatus"));
            rspResultBo.deal(resultMap,dataMap);
            log.info("函数返回的结果={}",resultMap);
        }catch (Exception e){
            e.printStackTrace();
            rspResultBo.error(e.getMessage());
        }
        return rspResultBo;
    }
}
