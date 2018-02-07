package com.payease.wallet.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.payease.wallet.app.api.service.IRouterService;
import com.payease.wallet.app.common.utils.LoginUtil;
import com.payease.wallet.app.common.utils.SignUtil;
import com.payease.wallet.app.properties.LoginProperties;
import com.payease.wallet.dto.bean.PacketHead;
import com.payease.wallet.dto.bean.RequestBo;
import com.payease.wallet.dto.bean.ResultBo;
import com.payease.wallet.dto.exception.AccountRuntimeException;
import com.payease.wallet.dto.exception.ConvertException;
import com.payease.wallet.dto.exception.SignException;
import com.payease.wallet.dto.utils.ErrorResult;
import com.payease.wallet.entity.pojo.TRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by zhangzhili on 2017/11/2.
 */

@RestController
public class GatewayController implements ApplicationContextAware {

    private final static Logger log = LoggerFactory.getLogger(GatewayController.class);

    private ApplicationContext applicationContext;

    @Autowired
    LoginProperties loginProperties;

    @Autowired
    private IRouterService routerService;

    private Map<String, Object> serviceMap = new HashMap<String, Object>();

    /**
     * 方法map
     */
    private Map<String, Method> methodMap = new HashMap<String, Method>();


    private Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();


    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    @RequestMapping("gateway")
    public ResultBo response(@RequestBody String requestBoJson, HttpServletRequest request) {
        ResultBo resultBo = null;

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        log.debug("\n通用控制层处理begin");
        //入参打印
        log.info("\n入参打印uuid(" + uuid + "):======" + requestBoJson);
        RequestBo requestBo = null;
        try {
            requestBo = JsonToBean(requestBoJson, request);
        } catch (ConvertException e) {
            log.error("入参格式错误", e);
            log.info("回参格式错误打印uuid(" + uuid + ")");
            return ResultBo.build().error(ErrorResult.CONVERT_PARAMTERS);
        }
        log.debug("\n对象转换结束");
        try {
            if (loginProperties.isCheckSign()) {
                //校验签名
                if (!SignUtil.checkSign(requestBoJson)) {
                    return ResultBo.build().signFail();
                }
            }
        } catch (SignException e) {
            log.error("签名错误", e);
            log.info("回参签名错误打印uuid(" + uuid + ")");
            return ResultBo.build().signFail();
        }
        log.debug("\n签名校验通过");
        try {
            if (loginProperties.isCheckInterceptor()) {
                //登录拦截器
                if (!LoginUtil.check(Arrays.asList(loginProperties.getExclude().split(",")),
                    requestBo.getPacketHead().getServiceCode(), stringRedisTemplate, requestBo
                        .getPacketHead().getToken())) {
                    return ResultBo.build().tokenTimeOut();
                }
            }
            log.debug("\n登录拦截校验通过，start调用业务逻辑");
            resultBo = invokeService(requestBo, request);
            log.info("\n回参打印uuid(" + uuid + "):======" + JSON.toJSONString(resultBo));
        } catch (Exception e) {
            if (e instanceof AccountRuntimeException) {
                log.error(((AccountRuntimeException) e).getMsg(), e);
                resultBo = ResultBo.build().setResultHead(((AccountRuntimeException) e).getMsg());
                log.info("\n回参异常AccountRuntimeException打印uuid(" + uuid + "):======"
                    + JSON.toJSONString(resultBo));
                return resultBo;
            } else {
                log.error("service调用失败", e);
                resultBo = ResultBo.build().fail();
                log.info(
                    "\n回参service调用失败打印uuid(" + uuid + "):======" + JSON.toJSONString(resultBo));
                return resultBo;
            }
        }
        return resultBo;
    }



    private ResultBo invokeService(RequestBo requestBo, HttpServletRequest request) throws
        InvocationTargetException, IllegalAccessException {
        log.debug("接口调用 invokeService begin");
        TRouter router = this.getRouterByServiceCode(requestBo.getPacketHead().getServiceCode());
        if (router == null) {
            log.debug("\n未配置请求跳转接口！");
            return ResultBo.build().notInterface();
        }
        log.debug("router==" + JSON.toJSONString(router));
        // 服务名字（接口类名字com.payease.**.IRouter）
        String serviceName = router.getServiceClass();
        // 请求方法(接口方法名字getRouterByServiceCode)
        String method = router.getServiceMethod();
        // 执行对应的方法
        ResultBo returnObj = this.invokeMethod(serviceName, method, requestBo, request);
        log.debug("\n返回对象 = " + returnObj);
        log.debug("接口调用 invokeService end");
        return returnObj;
    }


    /**
     * 获取路由对象
     *
     * @param serviceCode 业务操作码
     * @return 返回路由对象
     */
    private TRouter getRouterByServiceCode(String serviceCode) {
        log.info("controller调用路由getRouterByServiceCode接口");
        return routerService.getRouterByServiceCode(serviceCode);
    }



    private ResultBo invokeMethod(String serviceType, String methodName, RequestBo requestBo,
                                  HttpServletRequest request) throws InvocationTargetException,
        IllegalAccessException {
        log.debug("\n接口名：" + serviceType + "\n方法名：" + methodName + "\n入参：" + requestBo.toString());

        Method method = methodMap.get(serviceType + methodName);
        Object service = serviceMap.get(serviceType);//获取Servcie实例对象
        if (service == null) {
            //为了获取service对象
            Class<?> c = classMap.get(serviceType);
            if (c == null) {
                try {
                    c = getClass(serviceType);
                } catch (ClassNotFoundException e) {
                    log.error("类初始化配置失败", e);
                    return ResultBo.build().fail();
                }

            }
            service = applicationContext.getBean(c);
            log.debug("service add...");
            serviceMap.put(serviceType, service);
        }

        if (method == null) {
            Class<?> c = classMap.get(serviceType);
            for (Method m : c.getDeclaredMethods()) {
                if (methodName.equals(m.getName())) {
                    method = m;
                    log.debug("method add...");
                    methodMap.put(serviceType + methodName, m);
                    break;
                }
            }
        }


        log.debug("准备执行方法");
        assert method != null;
        ResultBo returnObj = (ResultBo) method.invoke(service, requestBo);
        log.debug("执行方法结束");

        return returnObj;

    }


    private Class<?> getClass(String serviceType) throws ClassNotFoundException {
        Class<?> c = Class.forName(serviceType);
        classMap.put(serviceType, c);
        return c;
    }


    private RequestBo JsonToBean(String requestBoJson, HttpServletRequest request) throws
        ConvertException {
        JSONObject json = JSON.parseObject(requestBoJson);
        PacketHead packetHead = json.getObject("packetHead", PacketHead.class);
        RequestBo requestBo = new RequestBo();
        requestBo.setPacketHead(packetHead);
        requestBo.setPacketBody(json.getJSONObject("packetBody").toJSONString());
        requestBo.setIpAddress(LoginUtil.getIpAddr(request));
        return requestBo;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }



}
