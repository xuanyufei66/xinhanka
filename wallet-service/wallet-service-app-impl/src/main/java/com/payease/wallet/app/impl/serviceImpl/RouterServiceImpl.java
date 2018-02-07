package com.payease.wallet.app.impl.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.payease.wallet.app.api.service.IRouterService;
import com.payease.wallet.entity.pojo.TRouter;
import com.payease.wallet.entity.pojo.TRouterExample;
import com.payease.wallet.orm.inter.TRouterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangzhili on 2017/11/6.
 */
@Service
public class RouterServiceImpl implements IRouterService {

    private final static Logger log = LoggerFactory.getLogger(RouterServiceImpl.class);
    @Autowired
    TRouterMapper tRouterMapper;



    @Override
    public TRouter getRouterByServiceCode(String serviceCode) {
        log.info("获取路由serviceCode：="+serviceCode);
        TRouterExample example = new TRouterExample();

        example.createCriteria().andServiceCodeEqualTo(serviceCode);
        List<TRouter> list = tRouterMapper.selectByExample(example);
        if(list.isEmpty()){
            log.info("无对应路由");
            return null;
        }
        TRouter trouter = list.get(0);
        log.info("路由返回值：="+ JSON.toJSONString(trouter));
        return trouter;
    }

    @Override
    public String getCode(String str) {
        System.out.println(str);
        return str;
    }
}
