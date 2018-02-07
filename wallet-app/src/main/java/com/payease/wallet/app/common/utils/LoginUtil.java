package com.payease.wallet.app.common.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhangzhili on 2017/11/16.
 */
public class LoginUtil {



    /**
     * 登录验证器
     * @param loginExcludes     不包含的状态码集合
     * @param currentServiceCode 当前状态码
     * @param stringRedisTemplate
     * @param token            登录token
     * @return
     */
    public static boolean check(List<String> loginExcludes, String currentServiceCode,
                             StringRedisTemplate stringRedisTemplate,String token) {

        if (loginExcludes.contains(currentServiceCode)){
            //不需要登录的编码
            return true;
        }else{
            if(StringUtils.isNotBlank(token) && stringRedisTemplate.hasKey(token)){
                //redis 有token 说明用户状态有效
                return true;
            }else {
                return false;
            }
        }

    }





    /**
     * @Author zhangwen
     * @MethodName getIpAddr
     * @Params
     * @Return java.lang.String
     * @Date 2017/11/14 下午2:54
     * @Desp 获取请求ip地址
     */
    public static String getIpAddr(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
