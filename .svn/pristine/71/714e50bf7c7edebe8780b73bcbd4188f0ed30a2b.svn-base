package com.payease.wallet.app.impl.serviceImpl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.payease.wallet.app.api.service.IRegistryService;
import com.payease.wallet.dto.bean.RequestBo;
import com.payease.wallet.dto.bean.ResultBo;
import com.payease.wallet.dto.utils.ErrorResult;
import com.payease.wallet.dto.utils.RandomUtil;
import com.payease.wallet.entity.pojo.*;
import com.payease.wallet.orm.inter.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @Author : zhoushijie
 * @Data : 2017/11/13
 * @Description :
 */
@Service
public class RegistryServiceImpl implements IRegistryService {

    private static final Logger log = LoggerFactory.getLogger(RegistryServiceImpl.class);
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private TPasswordInfoMapper tPasswordInfoMapper;
    @Autowired
    private TLoginInfoMapper tLoginInfoMapper;
    @Autowired
    private StringRedisTemplate stringRedis;
    @Autowired
    private TUserSettingMapper tUserSettingMapper;
    @Autowired
    private TAccountInfoMapper tAccountInfoMapper;


    /**
     * @param requestBo
     * @Author zhoushijie
     * @MethodName 注册接口
     * @Params
     * @Return
     * @Date 2017/11/13 上午11:17
     * @Desp
     */
    @Override
    @Transactional
    public ResultBo registory(RequestBo requestBo) throws Exception {
        ResultBo resultBo = ResultBo.build();
        try {
            String packetBody = requestBo.getPacketBody();//获取json串
            JSONObject jsonObject = JSON.parseObject(packetBody);
            String acctNoKey = "registe" + jsonObject.getString("acctNo");//电话号码 key
            if(!check(jsonObject,acctNoKey)){
                return resultBo.error(ErrorResult.CHECK_SUCCESSANDFAILURE);//注册-短信验证码失败
            }
            //判断手机号(账号)是否已注册   得到页面登录账号
            if(getTUserByMobilePhone(jsonObject.getString("acctNo"))){
                return resultBo.Registered();//短信验证码失败   账号已注册
            }

            saveTUsernfo(jsonObject);//保存客户信息
            Long userInfoId = getUserIdByMobilePhone(jsonObject.getString("acctNo"));
            if (null != userInfoId) {//用户信息id不为空
                insertTPasswordInfo(jsonObject,userInfoId); //存入用户密码表
                saveTLoginInfo(jsonObject,userInfoId);//用户登录信息
            }
            //新增usersetting表 设置默认值
            getUserset(resultBo,userInfoId);
            //新增加用户账户表
            setTaccountInfo(resultBo,userInfoId);
            stringRedis.delete(acctNoKey); //判断成功后删除Redis中的短信验证码
        }catch (Exception e){
            resultBo.fail().error(ErrorResult.REGISTEREDINFOFAILURE);//注册用户信息失败
            log.error("registory",e);
            throw e;
        }
            return resultBo;

    }



    /**
     * 判断手机号是否已注册
     * @param mobilePhone
     * @return
     * @throws Exception
     */
    private boolean getTUserByMobilePhone(String mobilePhone) throws Exception{
        TUserInfoExample example = new TUserInfoExample();
        example.createCriteria().andMobilePhoneEqualTo(mobilePhone);
        int i = tUserInfoMapper.countByExample(example);//查询数据库中的账号
        //判断手机号是否已注册
        if(0 < i){
            return true;//已注册
        }
            return false;//没有注册
    }

    /**
     * 获取用户id
     * @param mobilePhone
     * @return
     * @throws Exception
     */
    private Long getUserIdByMobilePhone(String mobilePhone) throws Exception{
        TUserInfoExample example = new TUserInfoExample();
        example.createCriteria().andMobilePhoneEqualTo(mobilePhone);//获取登录密码
        List<TUserInfo> list = tUserInfoMapper.selectByExample(example);
        if(null!=list && list.size()>0){
            TUserInfo tUserInfo = list.get(0);
            return tUserInfo.getId();
        }
        return null;
    }
    /**
     * 新增一个密码
     * @param jsonObject
     * @param userInfoId
     * @throws Exception
     */
    private void insertTPasswordInfo(JSONObject jsonObject,Long userInfoId) throws Exception{
        TPasswordInfo tPasswordInfo = new TPasswordInfo();
        tPasswordInfo.settUserInfoId(userInfoId);//保存客户id到密码表中
        String random = RandomUtil.RandomNumber(11);
        tPasswordInfo.setLoginPasswordRandomCode(random);//将生成的随机数保存到密码表中
        String passwodText = jsonObject.getString("password") + random;//加盐
        String password = DigestUtils.md5Hex(passwodText).toUpperCase();
        tPasswordInfo.setLoginPassword(password);//加密后保存到密码表中
        tPasswordInfoMapper.insertSelective(tPasswordInfo);
    }

    /**
     * 保存客户信息表
     * @param jsonObject
     * @throws Exception
     */
    private void saveTUsernfo(JSONObject jsonObject) throws Exception{
        TUserInfo tUserInfo = new TUserInfo();
        saveTUserInfo(jsonObject,tUserInfo);//存入用户基础信息表
        String usrref = this.getRandomNum(tUserInfo);//得生成唯一标识11位
        tUserInfo.setUserref(usrref);//将明文密码存入用户基础信息表中
        tUserInfoMapper.insertSelective(tUserInfo);//保存信息

    }

    /**
     * 判断短信验证码是否发送成功
     * @param jsonObject
     * @return
     * @throws Exception
     */
    private Boolean check(JSONObject jsonObject,String acctNoKey) throws Exception{
       // stringRedis.setKeySerializer(new StringRedisSerializer());//序列化号
        String redisSms = stringRedis.opsForValue().get(acctNoKey);//获得Redis中存储的值
        String smsCode = jsonObject.getString("smsCode");//解析短信验证码
         if (StringUtils.isBlank(redisSms)
                   || !redisSms.equals(smsCode)) {//验证码先不做判断默认为提供的,
                return false;
           }
        return true;
    }

    /**
     * 存入用户基础信息表
     * @param jsonObject
     * @param tUserInfo
     * @throws Exception
     */
    private void saveTUserInfo(JSONObject jsonObject,TUserInfo tUserInfo) throws Exception{
        tUserInfo.setSex(jsonObject.getString("sex"));//性别
        tUserInfo.setRegionLocation(jsonObject.getString("location"));//地址
        tUserInfo.setMobilePhone(jsonObject.getString("acctNo"));//电话号码
    }

    /**
     * 生成唯一标识11位随机数
     * @param tUserInfo
     * @return
     * @throws Exception
     */
     private String getRandomNum(TUserInfo tUserInfo) throws Exception{
        String result ;//定义空
         while (true) {
             String usrref = RandomUtil.RandomNumber(11);
             //检查数据库是否存在唯一标识
             TUserInfoExample example = new TUserInfoExample();
             TUserInfoExample.Criteria criteria = example.createCriteria();
             criteria.andUserrefEqualTo(usrref);
             int count = tUserInfoMapper.countByExample(example);
             if (count > 0) {
             } else {
                 result = usrref;
                 break;
             }
         }
         return result;
     }

    /**
     * 存入用户登录信息
     * @param jsonObject
     * @param userInfoId
     * @throws Exception
     */
     private void saveTLoginInfo(JSONObject jsonObject,Long userInfoId) throws Exception{
         TLoginInfo tLoginInfo = new TLoginInfo();
         tLoginInfo.settUserInfoId(userInfoId);
         tLoginInfo.setAcctNo(jsonObject.getString("acctNo"));
         tLoginInfo.setLoginType("system");
         tLoginInfoMapper.insertSelective(tLoginInfo);
     }

    /**
     * 新增usersetting表 设置默认值 "0"
     * @param resultBo
     * @param userInfoId
     * @throws Exception
     */
    private void getUserset(ResultBo resultBo,Long userInfoId) throws Exception{
        TUserSetting tUserSetting = new TUserSetting();
        tUserSetting.settUserInfoId(userInfoId);//`t_user_info_id` 用户主键id',
        tUserSetting.setRealNameFlag(Short.valueOf("0"));//`real_name_flag` 是否实名认证 ,
        tUserSetting.setGestureLoginFlag(Short.valueOf("0")); //`gesture_login_flag` 是否设置登录手势,
        tUserSetting.setGestureWayFlag(Short.valueOf("0")); // `gesture_way_flag` 是否显示手势轨迹,
        tUserSetting.setFingerFlag(Short.valueOf("0"));//`finger_flag` 是否设置指纹支付,
        tUserSetting.setAvoidPwdFlag(Short.valueOf("0")); //`avoid_pwd_flag`是否设置免密支付
        tUserSetting.setAvoidPayAmount("0");// `avoid_pay_amount`免密金额,
        tUserSetting.setNoticeFlag(Short.valueOf("0")); //`notice_flag` 公告开关,
        tUserSettingMapper.insertSelective(tUserSetting);//插入数据库
    }

    /**
     * 新增加用户账户表 ,设置默认值为"0.00"
     * @param resultBo
     * @param userInfoId
     * @throws Exception
     */
    private void setTaccountInfo(ResultBo resultBo,Long userInfoId) throws Exception{
        TAccountInfo tAccountInfo=new TAccountInfo();
        tAccountInfo.settUserInfoId(userInfoId);//t_user_info_id用户id
        tAccountInfo.setAmount("0.00");//amount账户总额
        tAccountInfo.setFreeAmount("0.00");//free_amount可用余额
        tAccountInfo.setFrozenAmount("0.00");//frozen_amount冻结金额
        tAccountInfoMapper.insertSelective(tAccountInfo);//插入数据库
    }
}
