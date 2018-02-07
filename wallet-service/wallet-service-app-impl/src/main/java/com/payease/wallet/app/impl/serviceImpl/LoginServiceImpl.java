package com.payease.wallet.app.impl.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.payease.wallet.app.api.service.ILoginService;
import com.payease.wallet.dto.bean.LoginUserBo;
import com.payease.wallet.dto.bean.RequestBo;
import com.payease.wallet.dto.bean.ResultBo;
import com.payease.wallet.dto.bean.ResultBody;
import com.payease.wallet.dto.utils.ErrorResult;
import com.payease.wallet.dto.utils.RandomUtil;
import com.payease.wallet.entity.pojo.*;
import com.payease.wallet.orm.inter.TLoginHistoryMapper;
import com.payease.wallet.orm.inter.TLoginInfoMapper;
import com.payease.wallet.orm.inter.TPasswordInfoMapper;
import com.payease.wallet.orm.inter.TUserInfoMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author : zhangwen
 * @Data : 2017/11/13
 * @Description :用户登录
 */
@Service
public class LoginServiceImpl implements ILoginService {
    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    StringRedisTemplate stringRedis;
    @Autowired
    TPasswordInfoMapper tPasswordInfoMapper;
    @Autowired
    TLoginHistoryMapper tLoginHistoryMapper;
    @Autowired
    TUserInfoMapper tUserInfoMapper;
    @Autowired
    TLoginInfoMapper tLoginInfoMapper;
    /**
     * @param requestBo
     * @Author zhangwen
     * @MethodName 用户主动登录接口
     * @Params
     * @Return
     * @Date 2017/11/13 上午11:14
     * @Desp
     *
     * 事物配置说明：
     * Propagation.REQUIRED 事务传播行为，如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值
     * Isolation.DEFAULT 事务隔离级别，表示使用底层数据库的默认隔离级别。对大部分数据库而言，通常这值就是TransactionDefinition.ISOLATION_READ_COMMITTED。
     * timeout 事务超时时间设置
     * rollbackFor 导致事务回滚的异常类
     * 全部采用默认配置
     */
    @Override
    @Transactional
    public ResultBo initiativeLogin(RequestBo requestBo) throws Exception{
        ResultBo bo = ResultBo.build();
        try {
            String packageBody = requestBo.getPacketBody();
            JSONObject bodyJson = JSON.parseObject(packageBody);
            //1.验证参数合法性
            if(checkRequestBody(bodyJson)){
                //增加逻辑 判断用户是否注册
                if(!isUserRegistry(bodyJson.getString("acctNo"))){
                    return bo.error(ErrorResult.LOGIN_NOTEXIST);
                }
                //判断用户是否被锁定
                if (!isUserLock(bodyJson.getString("acctNo"))) {
                    return bo.error(ErrorResult.LOGIN_PASSWORD_USER_LOCKED);
                }
                //2.获取用户密码信息
                Map<String,String> passwordMap = new HashMap<String, String>();
                passwordMap = this.tPasswordInfoMapper.getPasswordInfo(bodyJson.getString("acctNo"));
               //3.判断登录方式
                String loginType = bodyJson.getString("loginType");
                //密码验证结果
                boolean checkResult = false;
                //4.密码登录验证
                if("password".equals(loginType)){
                    checkResult = this.passwordLogin(bodyJson,passwordMap);
                }else if("gesture".equals(loginType)){
                    //手势登录验证
                    checkResult = this.gestureLogin(bodyJson,passwordMap);
                }
                if(checkResult){
                    String ipAddr = requestBo.getIpAddress();
//                    String ipAddr="127.0.0.1";
                    //插入登录历史信息表
                    this.saveLoginHistory(bodyJson,ipAddr,String.valueOf(passwordMap.get("userId")),"initiative");
                    String token =this.createToken(bodyJson);
                    //新增token
                    this.saveUserToken(String.valueOf(passwordMap.get("userId")),token);
                    ResultBody body = new ResultBody();
                    body.put("token",token);
                    bo.setResultBody(body);
                    //如果已经存在错误输入次数 则进行删除
                    String countKey="loginCount-"+bodyJson.getString("acctNo");
                    if(stringRedis.hasKey(countKey)){
                        stringRedis.delete(countKey);
                    }

                }else{
                    //手势登录密码错误 则进行
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("pwdType",loginType);
                    params.put("acctNo",bodyJson.getString("acctNo"));
                    if("password".equals(loginType)){
                        int surplusCount = this.getLockCount(params);
                        if(0==surplusCount){
                            return bo.error(ErrorResult.LOGIN_PASSWORD_USER_LOCKED);
                        }else{
//                            String.format(ErrorResult.LOGIN_ERROR_SURPLUS_COUNT.getMsg(),surplusCount);
                            bo.setResultHead("50071",String.format("密码错误，剩余次数为：%d",surplusCount));
                            return bo;
                        }
                    }else if("gesture".equals(loginType)){
                        int surplusCount = this.getLockCount(params);
                        if(0==surplusCount){
                            return bo.error(ErrorResult.LOGIN_GESTURE_ERROR_LOCKED);
                        }else {
                            bo.setResultHead("50071",String.format("密码错误，剩余次数为：%d",surplusCount));
                            return bo;
                        }
                    }
//                    bo.error(ErrorResult.LOGIN_PWDERROR);
                }
            }else{
                bo.reqNotFull();
            }
        } catch (Exception e) {
            e.printStackTrace();
            bo.fail();
            log.error("initiativeLogin",e);
            throw e;
        }
        return bo;




    }

    /**
     * @param requestBo
     * @Author zhangwen
     * @MethodName 用户自动登录接口
     * @Params
     * @Return
     * @Date 2017/11/13 上午11:14
     * @Desp
     */
    @Override
    @Transactional
    public ResultBo autoLogin(RequestBo requestBo) throws Exception{
        ResultBo bo = ResultBo.build();
        try {
            String packageBody = requestBo.getPacketBody();
            JSONObject bodyJson = JSON.parseObject(packageBody);
            //1.判断token是否存在
            String reqToken =requestBo.getPacketHead().getToken();
            LoginUserBo loginUserBo = this.getUserInRedis(reqToken);
            //1.用户正常登录 延长token有效期 并插入新的登录历史记录
            if(null!=loginUserBo){
                //保存用户登录历史信息
//                String ipaddr = this.getIpAddr();
                String ipaddr =requestBo.getIpAddress();
                this.saveLoginHistory(bodyJson,ipaddr,String.valueOf(loginUserBo.gettUserInfoId()),"auto");
                //延长token
                this.updateRedisToken(bodyJson,loginUserBo);
            }else {
                //2.token失效 返回异常
                bo.tokenTimeOut();
            }
        } catch (Exception e) {
            e.printStackTrace();
            bo.fail();
            log.error("autoLogin",e);
            throw e;
        }
        return bo;

    }

    /**
     * @Author zhangwen
     * @MethodName updateRedisToken
     * @Params [bodyJson, loginUserBo]
     * @Return void
     * @Date 2017/11/15 下午2:44
     * @Desp 更新token有效期
     */
    private void updateRedisToken(JSONObject bodyJson,LoginUserBo loginUserBo){
        String key = "user-"+loginUserBo.gettUserInfoId();
        String updateToken = stringRedis.opsForValue().get(key);
        //1.删除acctno与token的redis 然后插入新的acctno与token的redis
        stringRedis.delete(key);
        stringRedis.opsForValue().set(key,updateToken,15, TimeUnit.DAYS);
        //2.删除token与user的redis 然后插入新的token与user的redis
        stringRedis.delete(updateToken);
        stringRedis.opsForValue().set(updateToken,JSON.toJSONString(loginUserBo),15, TimeUnit.DAYS);

    }

    /**
     * @param token
     * @Author zhangwen
     * @MethodName 根据token获取redis中的用户信息
     * @Params
     * @Return
     * @Date 2017/11/14 下午5:11
     * @Desp
     */
    @Override
    public LoginUserBo getUserInRedis(String token) throws Exception {
        LoginUserBo bo = null;
        try {
            String userJson = String.valueOf(stringRedis.opsForValue().get(token));
            bo = JSON.parseObject(userJson,LoginUserBo.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getUserInRedis",e);
            throw e;
        }finally {
            return bo;
        }

    }

    /**
     * @param token
     * @Author zhangwen
     * @MethodName 刷新用户token
     * @Params
     * @Return
     * @Date 2017/11/22 上午10:07
     * @Desp
     */
    @Override
    public void refreshUserInRedis(String token) throws Exception {
        try {
            String userJson = String.valueOf(stringRedis.opsForValue().get(token));
            LoginUserBo bo = JSON.parseObject(userJson,LoginUserBo.class);
            LoginUserBo updateBo = this.getUserInfo(String.valueOf(bo.gettUserInfoId()));
            stringRedis.opsForValue().set(token,JSON.toJSONString(updateBo),stringRedis.getExpire(token),TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("refreshUserInRedis",e);
            throw e;
        }
    }

    /**
     * @param requestBo
     * @Author zhangwen
     * @MethodName 忘记登录密码
     * @Params
     * @Return
     * @Date 2017/11/24 下午4:46
     * @Desp
     */
    @Override
    public ResultBo forgetLoginPwd(RequestBo requestBo) throws Exception {
        ResultBo bo = ResultBo.build();
        try {
            String packetBody = requestBo.getPacketBody();
            JSONObject bodyJson = JSON.parseObject(packetBody);
            if(null==bodyJson||!bodyJson.containsKey("mobilePhone")
                    ||!bodyJson.containsKey("smsCode")||!bodyJson.containsKey("password")){
                return bo.reqNotFull();
            }
            String smsKey = "forgetpwd"+bodyJson.getString("mobilePhone");
            String reqCode = bodyJson.getString("smsCode");
            //1.验证短信验证码是否正确
            if(checkSmsCode(smsKey,reqCode)){
                //2.验证新密码是否和老密码一致（注意随机码）
                String reqPwd = bodyJson.getString("password");
                Map<String,String> passwordMap = new HashMap<String, String>();
                passwordMap = this.tPasswordInfoMapper.getPasswordInfo(bodyJson.getString("mobilePhone"));
                if(null!=passwordMap){
                  if(!isSamePassword(reqPwd,passwordMap)){
                      //3.修改密码
                      this.updatePassword(passwordMap,bodyJson);
                      //4.如果用户被锁定 则解除锁定
                      String countKey="loginCount-"+bodyJson.getString("mobilePhone");
                      if(stringRedis.hasKey(countKey)){
                          stringRedis.delete(countKey);
                      }
                      String loginKey = "userLock-"+bodyJson.getString("mobilePhone");
                      if(stringRedis.hasKey(loginKey)){
                          stringRedis.delete(loginKey);
                      }
                      String gestureKey="gestureCount-"+bodyJson.getString("mobilePhone");
                      if (stringRedis.hasKey(gestureKey)) {
                          stringRedis.delete(gestureKey);
                      }
                  }else{
                      bo.error(ErrorResult.SAME_PASSWORD);
                  }
                }else{
                     bo.error(ErrorResult.EMPTY_PASSWORD);
                }
            }else{
                 bo.smsTimeOut();
            }
        } catch (Exception e) {
            log.error("forgetLoginPwd",e);
            e.printStackTrace();
            bo.fail();
            throw e;
        } finally {
            return bo;
        }

    }


    /**
     * @Author zhangwen
     * @MethodName updatePassword
     * @Params [passwordMap, bodyJson]
     * @Return void
     * @Date 2017/11/24 下午6:40
     * @Desp 修改密码
     */
    private void updatePassword(Map<String,String> passwordMap,JSONObject bodyJson) throws Exception{
        String random = RandomUtil.getSixRandom();
        String password = DigestUtils.md5Hex(bodyJson.getString("password") + random).toUpperCase();
        TPasswordInfoExample example = new TPasswordInfoExample();
        TPasswordInfoExample.Criteria c = example.createCriteria();
        c.andTUserInfoIdEqualTo(Long.parseLong(String.valueOf(passwordMap.get("userId"))));
        TPasswordInfo info = new TPasswordInfo();
        info.setLoginPassword(password);
        info.setLoginPasswordRandomCode(random);
        info.setUpdatetime(new Date());
        tPasswordInfoMapper.updateByExampleSelective(info,example);
    }

    /**
     * @Author zhangwen
     * @MethodName isSamePassword
     * @Params [reqPwd, passwordMap]
     * @Return boolean
     * @Date 2017/11/24 下午6:10
     * @Desp 检测密码是否重复
     */
    public boolean isSamePassword(String reqPwd,Map<String,String> passwordMap ) throws Exception{
        String pwdCode = passwordMap.get("pwdCode");
        String loginPwd = passwordMap.get("loginPwd");
        if(checkPwd(reqPwd,pwdCode,loginPwd)){
            return true;
        }
        return false;
    }


    /**
     * @Author zhangwen
     * @MethodName checkSmsCode
     * @Params [key, reqCode]
     * @Return boolean
     * @Date 2017/11/24 下午4:50
     * @Desp 验证短信验证码是否正确 适用于找回密码
     */
    private boolean checkSmsCode(String key,String reqCode) throws Exception{
        String redisCode = stringRedis.opsForValue().get(key);
        if(StringUtils.isNotBlank(redisCode)
                &&redisCode.equals(reqCode)){
            return true;
        }
        return false;
    }




    /**
     * @Author zhangwen
     * @MethodName passwordLogin
     * @Params []
     * @Return com.payease.wallet.dto.bean.ResultBo
     * @Date 2017/11/14 下午1:51
     * @Desp 密码登录
     */
    private boolean passwordLogin(JSONObject bodyJson,Map<String,String> passwordMap) throws Exception{
        String reqPwd = bodyJson.getString("password");
        String pwdCode = passwordMap.get("pwdCode");
        String loginPwd = passwordMap.get("loginPwd");
        return checkPwd(reqPwd,pwdCode,loginPwd);
    }

    /**
     * @Author zhangwen
     * @MethodName gestureLogin
     * @Params []
     * @Return com.payease.wallet.dto.bean.ResultBo
     * @Date 2017/11/14 下午2:13
     * @Desp 手势登录
     */
    private boolean gestureLogin(JSONObject bodyJson,Map<String,String> passwordMap) throws Exception{
        String reqPwd = bodyJson.getString("password");
        String gestureCode = passwordMap.get("gestureCode");
        String gesturePwd = passwordMap.get("gesturePwd");
        return checkPwd(reqPwd,gestureCode,gesturePwd);
    }

    /**
     * @Author zhangwen
     * @MethodName checkRequestBody
     * @Params [bodyJson]
     * @Return boolean
     * @Date 2017/11/14 下午1:57
     * @Desp 检查参数合法性
     */
    private boolean checkRequestBody(JSONObject bodyJson) throws Exception{
        if(null!=bodyJson){
            if(bodyJson.containsKey("loginType")
                    && StringUtils.isNotBlank(bodyJson.getString("loginType"))
                    && bodyJson.containsKey("acctNo")
                    && StringUtils.isNotBlank(bodyJson.getString("acctNo"))
                    && bodyJson.containsKey("password")
                    && StringUtils.isNotBlank(bodyJson.getString("password"))
                    ){
                return true;
            }

        }
        return false;
    }

    /**
     * @Author zhangwen
     * @MethodName checkPwd
     * @Params [reqPwd, pwdCode, loginPwd]
     * @Return boolean
     * @Date 2017/11/14 下午2:32
     * @Desp 验证密码是否正确
     */
    private boolean checkPwd(String reqPwd,String pwdCode,String loginPwd) throws Exception{
        if(StringUtils.isBlank(pwdCode)||StringUtils.isBlank(loginPwd)){
            return false;
        }
        String checkPwd = reqPwd+pwdCode;
        if(loginPwd.equals(DigestUtils.md5Hex(checkPwd).toUpperCase())){
            return true;
        }
        return false;
    }


    /**
     * @Author zhangwen
     * @MethodName getIpAddr
     * @Params
     * @Return java.lang.String
     * @Date 2017/11/14 下午2:54
     * @Desp 获取请求ip地址
     */
    private String getIpAddr(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
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


    /**
     * @Author zhangwen
     * @MethodName saveLoginHistory
     * @Params [bodyJson, ipAddr]
     * @Return void
     * @Date 2017/11/14 下午2:59
     * @Desp 保存登录历史信息
     */
    private void saveLoginHistory(JSONObject bodyJson,String ipAddr,String userId,String loginNature) throws Exception{
        TLoginHistory history = new TLoginHistory();
        history.setDeviceFactory(bodyJson.getString("deviceFactory"));
        history.setDeviceId(bodyJson.getString("deviceId"));
        history.setDeviceModel(bodyJson.getString("deviceModel"));
        history.setIp(ipAddr);
        history.setLoginNature(loginNature);
        history.setSystemVersion(bodyJson.getString("systemVersion"));
        history.settUserInfoId(Long.parseLong(userId));
        this.tLoginHistoryMapper.insertSelective(history);
    }


    /**
     * @Author zhangwen
     * @MethodName saveUserToken
     * @Params [bodyJson]
     * @Return void
     * @Date 2017/11/14 下午3:27
     * @Desp 保存用户token 包括acctno-token token-用户信息2部分
     */
    private void saveUserToken(String userId,String token) throws Exception{
        try {
            String key = "user-"+userId;
            String delToken = stringRedis.opsForValue().get(key);
            //1.删除acctno与token的redis 然后插入新的acctno与token的redis
            if(StringUtils.isNotBlank(delToken)){
                stringRedis.delete(key);
                stringRedis.delete(delToken);
            }
            stringRedis.opsForValue().set(key,token,15, TimeUnit.DAYS);
            //2.删除token与user的redis 然后插入新的token与user的redis
            LoginUserBo bo = this.getUserInfo(userId);
            stringRedis.opsForValue().set(token,JSON.toJSONString(bo),15, TimeUnit.DAYS);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * @Author zhangwen
     * @MethodName createToken
     * @Params [bodyJson]
     * @Return java.lang.String
     * @Date 2017/11/14 下午3:35
     * @Desp 创建token
     */
    private String createToken(JSONObject bodyJson) throws  Exception{
        String tokenText = bodyJson.getString("acctNo")+"-"+bodyJson.getString("deviceId")+"-"+ RandomUtil.getSixRandom();
        return DigestUtils.md5Hex(tokenText).toUpperCase();
    }


    /**
     * @Author zhangwen
     * @MethodName getUserInfo
     * @Params [userId]
     * @Return com.payease.wallet.dto.bean.LoginUserBo
     * @Date 2017/11/14 下午3:53
     * @Desp 获取redis中的user对象
     */
    private LoginUserBo getUserInfo(String userId) throws Exception{
        LoginUserBo bo = new LoginUserBo();
        TUserInfoExample example = new TUserInfoExample();
        TUserInfoExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(Long.parseLong(userId));
        List<TUserInfo> list = tUserInfoMapper.selectByExample(example);
        if(null!=list&&list.size()>0){
            bo.setEmail(list.get(0).getEmail());
            bo.setIdentityCardNo(list.get(0).getIdentityCardNo());
            bo.setMobilePhone(list.get(0).getMobilePhone());
            bo.setRealName(list.get(0).getRealName());
            bo.setSex(list.get(0).getSex());
            bo.settUserInfoId(list.get(0).getId());
            bo.setUserref(list.get(0).getUserref());
            bo.setLocation(list.get(0).getRegionLocation());
        }
        return bo;
    }

    /**
     * @Author zhangwen
     * @MethodName isUserRegistry
     * @Params [acctno]
     * @Return boolean
     * @Date 2017/11/23 下午3:49
     * @Desp 用户是否注册
     */
    public boolean isUserRegistry(String acctno) throws Exception{
        TLoginInfoExample example = new TLoginInfoExample();
        TLoginInfoExample.Criteria criteria = example.createCriteria();
        criteria.andAcctNoEqualTo(acctno);
        criteria.andLoginTypeEqualTo("system");
        int c = tLoginInfoMapper.countByExample(example);
        if(c>=1){
            return true;
        }else{
            return false;
        }
    }



    /**
     * @Author zhangwen
     * @MethodName isUserLock
     * @Params [acctno]
     * @Return boolean
     * @Date 2017/11/28 上午10:00
     * @Desp 判断用户是否被锁定
     */
    public boolean isUserLock(String acctno) throws Exception{
        //增加逻辑 判断用户是否已被锁定从redis中取值
        String key = "userLock-"+acctno;
        if( stringRedis.hasKey(key)){
            return false;
        }else{
            return true;
        }

    }


    /**
     * @Author zhangwen
     * @MethodName getLockCount
     * @Params [params]
     * @Return 返回剩余的可尝试次数 5-n
     * @Date 2017/11/29 下午1:49
     * @Desp 判断用户锁定状态 false-需要被锁定或返回特殊提示 true-暂时不需要锁定
     */
    @Override
    public int getLockCount(Map<String,String> params){
        //常规密码登录
        if("password".equals(params.get("pwdType").toString())){
            String countKey="loginCount-"+params.get("acctNo").toString();
            int surplusCount = setCountRedis(countKey);
            if(0==surplusCount){
                String lockKey = "userLock-"+params.get("acctNo").toString();
                stringRedis.opsForValue().set(lockKey,"lock",30,TimeUnit.MINUTES);
                stringRedis.delete(countKey);
                //删除手势密码登录的错误次数
                if(stringRedis.hasKey("gestureCount-"+params.get("acctNo").toString())){
                    stringRedis.delete("gestureCount-"+params.get("acctNo").toString());
                }
            }
            return surplusCount;
        //手势密码登录
        }else if("gesture".equals(params.get("pwdType").toString())){
            String gestureKey="gestureCount-"+params.get("acctNo").toString();
            return this.setCountRedis(gestureKey);
        }else if("pay".equals(params.get("pwdType").toString())){
            String payKey = "payCount-"+params.get("userId").toString();
            return this.setCountRedis(payKey);
        }
        return 0;

    }


    /**
     * @Author zhangwen
     * @MethodName setCountRedis
     * @Params [key]
     * @Return boolean
     * @Date 2017/11/29 下午2:01
     * @Desp 设置用户redis中的锁定次数
     */
    private int setCountRedis(String key){
        if(stringRedis.hasKey(key)){
            String val = stringRedis.opsForValue().get(key);
            if(Integer.parseInt(val) >= 4){
                return 0;
            }else{
                //计算新的值
                String newVal = String.valueOf(Integer.parseInt(val)+1);
                stringRedis.opsForValue().set(key,newVal,stringRedis.getExpire(key),TimeUnit.SECONDS);
                int surplusCount = 5-Integer.parseInt(val)-1;
                return surplusCount;
            }
        }else{
            //不存在则插入新的数据
            stringRedis.opsForValue().set(key,"1",30,TimeUnit.MINUTES);
            return 4;
        }
    }



}
