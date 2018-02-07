package com.payease.wallet.app.api.service;

import com.payease.wallet.dto.bean.LoginUserBo;
import com.payease.wallet.dto.bean.RequestBo;
import com.payease.wallet.dto.bean.ResultBo;

import java.util.Map;

/**
 * @Author : zhangwen
 * @Data : 2017/11/13
 * @Description :
 */
public interface ILoginService {

    /**
     * @Author zhangwen
     * @MethodName 用户主动登录接口
     * @Params 
     * @Return 
     * @Date 2017/11/13 上午11:14 
     * @Desp 
     */
    public ResultBo initiativeLogin(RequestBo requestBo) throws Exception;
    
    /**
     * @Author zhangwen
     * @MethodName 用户自动登录接口
     * @Params 
     * @Return 
     * @Date 2017/11/13 上午11:14 
     * @Desp 
     */
    public ResultBo autoLogin(RequestBo requestBo) throws Exception;
    
    /**
     * @Author zhangwen
     * @MethodName 根据token获取redis中的用户信息
     * @Params 
     * @Return 
     * @Date 2017/11/15 下午1:44
     * @Desp 
     */
    public LoginUserBo getUserInRedis(String token) throws Exception;

   
    /**
     * @Author zhangwen
     * @MethodName 刷新用户token
     * @Params 
     * @Return 
     * @Date 2017/11/22 下午1:46
     * @Desp 
     */
    public void refreshUserInRedis(String token) throws Exception;
    
    /**
     * @Author zhangwen
     * @MethodName 忘记登录密码
     * @Params 
     * @Return 
     * @Date 2017/11/24 下午6:14
     * @Desp 
     */
    public ResultBo forgetLoginPwd(RequestBo requestBo) throws Exception;

    /**
     * @Author zhangwen
     * @MethodName 判断用户是否需要被锁定
     * @Params 
     * @Return 返回剩余的可尝试次数 （5-n）
     * @Date 2017/11/29 下午2:27
     * @Desp 
     */
    public int getLockCount(Map<String,String> params) throws Exception;
}
