package com.payease.wallet.app.impl.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.payease.wallet.app.api.service.ILoginService;
import com.payease.wallet.app.api.service.ISettingService;
import com.payease.wallet.dto.bean.*;
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

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author : zhangwen
 * @Data : 2017/11/13
 * @Description : 设置
 */
@Service
public class SettingServiceImpl implements ISettingService {

    private static final Logger log = LoggerFactory.getLogger(SettingServiceImpl.class);
    @Autowired
    ILoginService loginService;
    @Autowired
    StringRedisTemplate stringRedis;
    @Autowired
    TPasswordInfoMapper tPasswordInfoMapper;
    @Autowired
    TLoginHistoryMapper tLoginHistoryMapper;
    @Autowired
    TUserSettingMapper tUserSettingMapper;
    @Autowired
    TUserInfoMapper tUserInfoMapper;
    @Autowired
    TAvoidAmountMapper tAvoidAmountMapper;
// ========================================= shihaikun  start 获取用户开关接口=================================================
    /**
     * @Author shihaikun
     * @MethodName 获取用户开关接口
     * @Params
     * @Return
     * @Date 2017/11/13 上午10:33
     * @Desp
     */
    @Override
    public ResultBo getSwitchInfo(RequestBo requestBo) throws Exception {
        //去redis中取用户开关状态
        ResultBo resultBo = ResultBo.build();
        try {
            LoginUserBo userInRedis = loginService.getUserInRedis(requestBo.getPacketHead().getToken());

            Long tUserInfoId = Long.valueOf(userInRedis.gettUserInfoId());


            TUserSetting tUserSetting = getTUserSettingByUserId(tUserInfoId);
            if (null != tUserSetting) {
                ResultBody resultBody = new ResultBody();
                //如果获取的免密金额为0，则把免密金额表从小到大排序，最小值设为选中值
               // Map<String ,Object> mapList = new LinkedHashMap<>();
                List<Map<String, Object>> listMap = new ArrayList<>();
                if("0".equals(tUserSetting.getAvoidPayAmount())){
                    TAvoidAmountExample example = new TAvoidAmountExample();
                    example.setOrderByClause("avoid_amount asc");
                    List<TAvoidAmount> tAvoidAmounts = tAvoidAmountMapper.selectByExample(example);

                    int i = 0;
                    for (TAvoidAmount tAvoidAmount : tAvoidAmounts){

                        Map<String ,Object> map = new LinkedHashMap<>();
                        map.put("avoidPayAmount",tAvoidAmount.getAvoidAmount());
                        if(i == 0){
                            map.put("isChecked","1");
                        }else{
                            map.put("isChecked","0");
                        }
                        i++;
                        listMap.add(map);

                    }

                    //如果获取的免密金额在免密金额表里不存在，则把它放入到表里并且设为选中值
                }else {
                    TAvoidAmountExample example = new TAvoidAmountExample();
                    example.setOrderByClause("avoid_amount asc");
                    List<TAvoidAmount> tAvoidAmounts = tAvoidAmountMapper.selectByExample(example);
                    TAvoidAmountExample example1 = new TAvoidAmountExample();
                    example1.createCriteria().andAvoidAmountEqualTo(tUserSetting.getAvoidPayAmount());

                    if(tAvoidAmountMapper.countByExample(example1) == 0){
                        Map<String ,Object> map1 = new HashMap<>();
                        map1.put("avoidPayAmount",tUserSetting.getAvoidPayAmount());
                        map1.put("isChecked","1");
                        listMap.add(map1);

                        for (TAvoidAmount tAvoidAmount : tAvoidAmounts){
                            Map<String ,Object> map = new LinkedHashMap<>();
                            map.put("avoidPayAmount",tAvoidAmount.getAvoidAmount());
                            map.put("isChecked","0");
                            listMap.add(map);
                        }

                    }else{
                        //如果获取的免密金额已经在免密金额表里存在，则把表里和获取的免密金额相同的值设为选中值
                        for (TAvoidAmount tAvoidAmount : tAvoidAmounts){
                            Map<String ,Object> map = new LinkedHashMap<>();
                            map.put("avoidPayAmount",tAvoidAmount.getAvoidAmount());
                            if(tUserSetting.getAvoidPayAmount().equals(tAvoidAmount.getAvoidAmount())){
                                map.put("isChecked","1");
                            }else {
                                map.put("isChecked","0");
                            }
                            listMap.add(map);
                        }

                    }
                }
                resultBody.put("avoidPayAmountList",listMap);
                resultBody.put("avoidPwdFlag", tUserSetting.getAvoidPwdFlag() + "");
                resultBody.put("fingerFlag", tUserSetting.getFingerFlag() + "");
                resultBody.put("realNameFlag", tUserSetting.getRealNameFlag() +"");
                resultBody.put("payPwdFlag", (isPayPwdFlag(tUserInfoId)) ? "1" : "0");
                resultBody.put("gestureLoginFlag", tUserSetting.getGestureLoginFlag() + "");
                resultBody.put("gestureWayFlag", tUserSetting.getGestureWayFlag() + "");
                resultBo.setResultBody(resultBody);
            }
        }catch(Exception e){
            e.printStackTrace();
            resultBo.error(ErrorResult.GETSWITCHINFO_FAIL);
            log.error("getSwitchInfo",e);
            throw e;
        }finally{
            return resultBo;
        }

    }



    /**
     * @Author shihaikun
     * @MethodName 判断是否设置支付密码
     * @Params
     * @Return
     * @Date 2017/11/14
     * @Desp
     */
    private Boolean isPayPwdFlag(Long userId){
        TPasswordInfoExample example = new TPasswordInfoExample();
        example.createCriteria().andTUserInfoIdEqualTo(userId);
        List<TPasswordInfo> list = tPasswordInfoMapper.selectByExample(example);
        if(null != list && 0 < list.size()){
            if(StringUtils.isNotBlank(list.get(0).getPayPassword())){
                return true;
            }
        }
        return  false;
    }
    /**
     * 通过用户ID获取 用户设置表 t_user_setting
     * shihaikun
     * @param userId
     * @return
     */
    private TUserSetting getTUserSettingByUserId(Long userId){
        TUserSettingExample tUserSettingExample = new TUserSettingExample();
        tUserSettingExample.createCriteria().andTUserInfoIdEqualTo(userId);//用户ID
        List<TUserSetting> tUserSettingList = tUserSettingMapper.selectByExample(tUserSettingExample);
        TUserSetting tUserSetting = new TUserSetting();
        if(null != tUserSettingList &&  0 < tUserSettingList.size() ){
            //获取用户设置表信息
            tUserSetting = tUserSettingList.get(0);
        }
        return tUserSetting;
    }

    // ========================================= shihaikun  end 获取用户开关=================================================

    // ========================================= shihaikun  start 设置用户开关接口=================================================
    /**
     * @Author shihaikun
     * @MethodName 设置用户开关接口
     * @Params
     * @Return
     * @Date 2017/11/13 上午10:34
     * @Desp
     */
    @Override
    public ResultBo setSwitchInfo(RequestBo requestBo) throws Exception{

        //去redis中取用户开关状态
        ResultBo resultBo = ResultBo.build();
        try {
            LoginUserBo userInRedis = loginService.getUserInRedis(requestBo.getPacketHead().getToken());

            Long tUserInfoId = Long.valueOf(userInRedis.gettUserInfoId());
            //           Long tUserInfoId = Long.valueOf("1");
            String packetBody = requestBo.getPacketBody();//获取json串
            JSONObject jsonObject = JSON.parseObject(packetBody);

            if(jsonObject == null){
                return resultBo.reqNotFull();
            }
            //存入用户设置表
            TUserSetting userSetting = getTUserSettingByUserId(tUserInfoId);
            if(null != userSetting){
//                userSetting = getTUserSettingByUserId(tUserInfoId);
                //   fingerFlag	是否开通指纹	"（0-否 1-是）
                if(jsonObject.containsKey("fingerFlag")&&(jsonObject.getString("fingerFlag").equals("0") || jsonObject.getString("fingerFlag").equals("1"))){
                    userSetting.setFingerFlag(Short.valueOf(jsonObject.getString("fingerFlag")));
                }
                // noticeFlag	是否显示公告	"（0-否 1-是）
                if(jsonObject.containsKey("noticeFlag")&&(jsonObject.getString("noticeFlag").equals("0") || jsonObject.getString("noticeFlag").equals("1"))){
                    userSetting.setNoticeFlag(Short.valueOf(jsonObject.getString("noticeFlag")));
                }
                //avoidPwdFlag	是否设置免密	（0-否 1-是）
                if(jsonObject.containsKey("avoidPwdFlag")&&(jsonObject.getString("avoidPwdFlag").equals("0") || jsonObject.getString("avoidPwdFlag").equals("1"))){
                    userSetting.setAvoidPwdFlag(Short.valueOf(jsonObject.getString("avoidPwdFlag")));
                }

                // avoidPayAmount	免密金额
                if(jsonObject.containsKey("avoidPayAmount")&& StringUtils.isNotBlank(jsonObject.getString("avoidPayAmount"))){
                    userSetting.setAvoidPayAmount(String.valueOf(jsonObject.getString("avoidPayAmount")));
                }
                // gestureLoginFlag	是否手势登录	（0-否 1-是）
                if(jsonObject.containsKey("gestureLoginFlag")&&(jsonObject.getString("gestureLoginFlag").equals("0") || jsonObject.getString("gestureLoginFlag").equals("1"))){
                    userSetting.setGestureLoginFlag(Short.valueOf(jsonObject.getString("gestureLoginFlag")));
                }
                // gestureWayFlag	是否显示手势轨迹	（0-否 1-是)
                if(jsonObject.containsKey("gestureWayFlag")&&(jsonObject.getString("gestureWayFlag").equals("0") || jsonObject.getString("gestureWayFlag").equals("1"))){
                    userSetting.setGestureWayFlag(Short.valueOf(jsonObject.getString("gestureWayFlag")));
                }
                if(null == userSetting.getFingerFlag()
                        && null == userSetting.getNoticeFlag()
                        && null == userSetting.getAvoidPwdFlag()
                        && null == userSetting.getGestureLoginFlag()
                        && null == userSetting.getGestureWayFlag()) {
                    return resultBo.error(ErrorResult.SWITCHOPTIONCANNOTBEEMPTY);
                }

                tUserSettingMapper.updateByPrimaryKeySelective(userSetting);
//                resultBo.setResultHead(ResultBo.Status.SUCCESS);
            }
        }catch(Exception e){
            e.printStackTrace();
            resultBo.error(ErrorResult.SETSWITCHINFO_FAIL);
            log.error("setSwitchInfo",e);
            throw e;
        }finally{
            return resultBo;
        }

    }
   // ========================================= shihaikun  end 设置用户开关接口=================================================
    /**
     * @Author zhengqiangwei
     * @MethodName 设置密码接口
     * @Params
     * @Return
     * @Date 2017/11/13 上午10:35
     * @Desp
     */
    @Override
    public ResultBo setPassword(RequestBo requestBo) throws Exception {
        ResultBo result = ResultBo.build();
        try {
            JSONObject json = JSON.parseObject(requestBo.getPacketBody());
            PacketHead secure = requestBo.getPacketHead();
            LoginUserBo userBo = loginService.getUserInRedis(secure.getToken().toString());
            String pwdType = json.getString("pwdType");//loginpwd-登录密码 gesturepwd-手势密码 paypwd-支付密码
            String password = json.getString("password");
            Date currentDate = new Date();
            if (password.isEmpty()) {
                result.error(ErrorResult.INCOMINGPASSWORDISBALNK);
            } else {
                String ranDom = RandomUtil.getSixRandom();//生成随机码
                String Pass = DigestUtils.md5Hex((password + ranDom).toString()).toUpperCase();//md5加密密码+随机码组合传入数据库
                if (pwdType.equals("loginpwd")) {
                    //选择为修改登陆密码，原密码已在另一接口校验，故此处只执行存新密码功能
                    tPasswordInfoMapper.upPass(Pass, userBo.gettUserInfoId(), ranDom, currentDate);
                } else if (pwdType.equals("gesturepwd")) {
                    //选择为手势密码操作,修改收手势密码
                    tPasswordInfoMapper.updateGesture(Pass, userBo.gettUserInfoId(), ranDom, currentDate);
                } else if (pwdType.equals("paypwd")) {
                    //选择为支付密码操作,修改支付密码
                    tPasswordInfoMapper.updatePayPass(Pass, userBo.gettUserInfoId(), ranDom, currentDate);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.fail();
            log.error("setPassword",e);
            throw e;
        } finally {
            return result;
        }
    }

    /**
     * @Author zhengqiangwei
     * @MethodName 获取用户上次登录信息接口
     * @Params
     * @Return
     * @Date 2017/11/13 上午10:37
     * @Desp
     */
    @Override
    public ResultBo getLastLoginInfo(RequestBo requestBo) throws Exception {
        ResultBo result = ResultBo.build();
        try {
            PacketHead secure = requestBo.getPacketHead();
            LoginUserBo userBo = loginService.getUserInRedis(secure.getToken().toString());
            int realName = tUserSettingMapper.selectRealName(userBo.gettUserInfoId());//是否实名
            String realNameAbu =tUserInfoMapper.selectRealNamebyid(userBo.gettUserInfoId());//用户真实名字
            //查出此ID最近一条登陆数据和登陆次数
            Map<String, String> tLoginHistoryS0= tLoginHistoryMapper.selectRecent(userBo.gettUserInfoId());//首次登陆
            int goSta = Integer.valueOf(String.valueOf(tLoginHistoryS0.get("loginCount")));
            Map<String,String> tLoginHistoryS = new HashMap<>();
            if (goSta==1){//首次登陆
                tLoginHistoryS.putAll(tLoginHistoryS0);
                if (realName==0) {//未实名认证
                    tLoginHistoryS.put("nickName",userBo.getMobilePhone());
                    tLoginHistoryS.put("firstTime", String.valueOf(1));//登陆次数不为0,非第一次登陆
                }else {//已实名认证
                    tLoginHistoryS.put("nickName",realNameAbu);
                    tLoginHistoryS.put("firstTime", String.valueOf(1));//登陆次数不为0,非第一次登陆
                }
            }else {//非首次登陆
                tLoginHistoryS = tLoginHistoryMapper.selectRecent1(userBo.gettUserInfoId());//非首次登陆
                if (realName==0) {//未实名认证
                    tLoginHistoryS.put("nickName",userBo.getMobilePhone());
                    tLoginHistoryS.put("firstTime", String.valueOf(0));//登陆次数不为0,非第一次登陆
                }else {//已实名认证
                    tLoginHistoryS.put("nickName",realNameAbu);
                    tLoginHistoryS.put("firstTime", String.valueOf(0));//登陆次数不为0,非第一次登陆
                }
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = simpleDateFormat.format(tLoginHistoryS.get("loginTime"));
            tLoginHistoryS.put("loginTime",str);
            tLoginHistoryS.put("isRealName",String.valueOf(realName));
            tLoginHistoryS.put("sex",userBo.getSex());
            tLoginHistoryS.remove("loginCount");
            ResultBody body = new ResultBody();
            body.putAll(tLoginHistoryS);
            result.setResultBody(body);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail();
            log.error("getLastLoginInfo",e);
            throw e;
        } finally {
            return result;
        }
    }

    /**
     * @Author zhangwen
     * @MethodName 用户登出接口
     * @Params
     * @Return
     * @Date 2017/11/13 上午10:37
     * @Desp
     */
    @Override
    public ResultBo loginOut(RequestBo requestBo) throws Exception{
        ResultBo resultBo = ResultBo.build();
        try {
            String token = requestBo.getPacketHead().getToken();
            LoginUserBo userBo = loginService.getUserInRedis(token);
            if(null!=userBo){
                String userKey = "user-"+userBo.gettUserInfoId();
                stringRedis.delete(userKey);
                stringRedis.delete(token);
            }else{
                resultBo.tokenTimeOut();
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultBo.fail();
            log.error("loginOut",e);
            throw e;
        }finally {
            return resultBo;
        }



    }
}
