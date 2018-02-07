package com.payease.wallet.app.impl.serviceImpl;

import bo.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.payease.wallet.app.api.service.IAccountBankCardService;
import com.payease.wallet.app.api.service.ILoginService;
import com.payease.wallet.app.impl.utils.DateUtil;
import com.payease.wallet.app.impl.utils.MoneyUtil;
import com.payease.wallet.app.impl.utils.RandomUtil;
import com.payease.wallet.dto.bean.LoginUserBo;
import com.payease.wallet.dto.bean.RequestBo;
import com.payease.wallet.dto.bean.ResultBo;
import com.payease.wallet.dto.bean.ResultBody;
import com.payease.wallet.dto.exception.AccountRuntimeException;
import com.payease.wallet.dto.utils.MaskCodeUtil;
import com.payease.wallet.dto.utils.OrderEnum;
import com.payease.wallet.dto.utils.OrderNoUtil;
import com.payease.wallet.entity.pojo.*;
import com.payease.wallet.gateway.api.service.IAuthenticationService;
import com.payease.wallet.gateway.api.service.IFastPaymentService;
import com.payease.wallet.gateway.api.service.IPaymentService;
import com.payease.wallet.orm.inter.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 账户
 * <p>
 * Created by ljp on 2017/11/12.
 */
@Service
public class AccountBankCardServiceImpl implements IAccountBankCardService {

    private static final Logger log = LoggerFactory.getLogger(AccountBankCardServiceImpl.class);

    @Value("${own.url.authentication}")
    String authenticationUrl;//实名认证接口地址
    @Value("${own.url.cardBind}")
    String cardBindUrl;//银行卡绑定接口地址
    @Value("${own.url.cardUnbind}")
    String cardUnbindUrl;//银行卡解绑接口地址
    @Value("${own.url.messageSend}")
    String messageSendUrl;//发送短信接口地址
    @Value("${own.url.paymentSubmit}")
    String paymentSubmitUrl;//代付提交接口地址
    @Value("${own.url.cardPay}")
    String cardPayUrl;//银行卡支付地址接口地址
    @Value("${own.mid}")
    String mid;//商户号
    @Value("${own.key}")
    String key;//md5的key
    @Value("${own.version}")
    String version;//版本号

    @Autowired
    private IAuthenticationService iAuthenticationService;
    @Autowired
    private IPaymentService iPaymentService;
    @Autowired
    private IFastPaymentService iFastPaymentService;
    @Autowired
    private ILoginService iLoginService;
    @Autowired
    private IndexServiceImpl indexService;
    @Autowired
    private TBankBaseMapper tBankBaseMapper;
    @Autowired
    private TAccountBankMapper tAccountBankMapper;
    @Autowired
    private TAccountInfoMapper tAccountInfoMapper;
    @Autowired
    private TAccountTransactMapper tAccountTransactMapper;
    @Autowired
    private TPasswordInfoMapper tPasswordInfoMapper;
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private TUserSettingMapper tUserSettingMapper;
    @Autowired
    private TAccountBankHistoryMapper tAccountBankHistoryMapper;
    @Autowired
    private TAreasMapper tAreasMapper;
    @Autowired
    private TWithdrawalHistoryMapper tWithdrawalHistoryMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> valOpts;


    //获取地区（一）
    /*@Override
    public ResultBo getAreas(RequestBo requestBo) throws Exception {
        //根据前台传来Id获取id和name
        ResultBo result = ResultBo.build();
        JSONObject jsonObject = JSON.parseObject(requestBo.getPacketBody());

        TAreasExample tAreasExample1 = new TAreasExample();
        tAreasExample1.createCriteria().andParentIdEqualTo(jsonObject.getInteger("id"));
        List<TAreas> tAreasList1 = tAreasMapper.selectByExample(tAreasExample1);
        log.info("tAreasList1={}", JSON.toJSONString(tAreasList1));
        List<RspAreasListBO> areasList = new ArrayList<>();
        if (tAreasList1 != null && !tAreasList1.isEmpty()) {
            for (TAreas tAreas : tAreasList1) {
                RspAreasListBO rspAreasListBO = new RspAreasListBO();
                rspAreasListBO.setId(tAreas.getId()+"");
                rspAreasListBO.setAreaName(tAreas.getAreaName());
                areasList.add(rspAreasListBO);
            }
        }
        ResultBody resultBody = new ResultBody();
        resultBody.put("areaList", areasList);
        result.setResultBody(resultBody);
        return result;
    } */
    //获取地区（二）
    @Override
    public ResultBo getAreas(RequestBo requestBo) throws Exception {
        ResultBo result = ResultBo.build();
        JSONObject jsonObject = JSON.parseObject(requestBo.getPacketBody());
        //判断是否存在areaVersion
        String areaVersion = null;
        if (jsonObject.containsKey("areaVersion")){
            //获取前台传来的版本号
            areaVersion = jsonObject.getString("areaVersion");
        }
        //数据库中的版本号
        TAreas tAreas = tAreasMapper.selectByPrimaryKey(1);
        String areasNumber = tAreas.getAreaName().substring(2);
        //当ereaVersion为空或者非当前版本时，返回最新数据
        List<RspAreasListBO> areasList = new ArrayList<>();

        if (!areasNumber.equals(areaVersion) || StringUtils.isBlank(areaVersion)) {
            //从数据库中查出省市数据
            TAreasExample tAreasExample1 = new TAreasExample();
            tAreasExample1.createCriteria().andParentIdEqualTo(1);
            List<TAreas> tAreasList1 = tAreasMapper.selectByExample(tAreasExample1);
            log.info("省级tAreasList1={}", JSON.toJSONString(tAreasList1));
            if (!tAreasList1.isEmpty()) {
                for (TAreas tAreas1 : tAreasList1) {
                    RspAreasListBO rspAreasListBO = new RspAreasListBO();
                    rspAreasListBO.setProvinceCode(tAreas1.getId() + "");
                    rspAreasListBO.setProvinceName(tAreas1.getAreaName());
                    //根据省级id 查出对应的市id和市名称
                    TAreasExample tAreasExample2 = new TAreasExample();
                    tAreasExample2.createCriteria().andParentIdEqualTo(tAreas1.getId());
                    List<TAreas> tAreasList2 = tAreasMapper.selectByExample(tAreasExample2);
                    List<RspCitysBO> citys = new ArrayList<>();
                    for (TAreas tAreas2 : tAreasList2) {
                        RspCitysBO rspCitysBO = new RspCitysBO();
                        rspCitysBO.setCityCode(tAreas2.getId() + "");
                        rspCitysBO.setCityName(tAreas2.getAreaName());
                        citys.add(rspCitysBO);
                    }
                    rspAreasListBO.setCitys(citys);
                    areasList.add(rspAreasListBO);
                    log.info("返回的areaList={}", areasList);
                }
            }
            ResultBody resultBody = new ResultBody();
            resultBody.put("areaVersion", areasNumber);
            resultBody.put("areaList", areasList);
            result.setResultBody(resultBody);
            return result;
        } else {
            //resultBody返回空
            ResultBody resultBody = new ResultBody();
            result.setResultBody(resultBody);
            return result;
        }
    }

    //实名认证接口的实现
    @Transactional
    @Override
    public ResultBo authentication(RequestBo requestBo) throws Exception {
        JSONObject json = JSON.parseObject(requestBo.getPacketBody());
        ReqRealNameBO reqRealNameBO = new ReqRealNameBO();
        reqRealNameBO.setIdname(json.getString("realName"));
        reqRealNameBO.setIdnumber(json.getString("identityCardNo"));
        reqRealNameBO.setIdtype("01");
        reqRealNameBO.setMid(mid);
        reqRealNameBO.setUrl(authenticationUrl);
        reqRealNameBO.setKey(key);
        //检查该身份证号是否被实名认证
        ResultBo result = ResultBo.build();
        TUserInfoExample tUserInfoExample = new TUserInfoExample();
        TUserInfoExample.Criteria c1 = tUserInfoExample.createCriteria();
        c1.andIdentityCardNoEqualTo(json.getString("identityCardNo"));
        int userCount = tUserInfoMapper.countByExample(tUserInfoExample);
        if (userCount > 0) {
            result.setResultHead(ResultBo.Status.USER_ID_EXIST);
            return result;
        }
        log.info("app-service的实名认证的入参={}", reqRealNameBO);
        RspResultBO rspResultBO = iAuthenticationService.RealName(reqRealNameBO);
        log.info("app-service请求gateway接口实名认证的返回结果={}", rspResultBO);
        if ("0".equals(rspResultBO.getCode())) {
            if ("1".equals(rspResultBO.getData().get("verifystatus"))) {
                LoginUserBo loginUserBo =
                        iLoginService.getUserInRedis(requestBo.getPacketHead().getToken());
                long id = loginUserBo.gettUserInfoId();
                TUserInfo tUserInfo = new TUserInfo();
                //从Redis中获取UserInfo对象
                tUserInfo.setId(id);
                tUserInfo.setRealName(json.getString("realName"));
                tUserInfo.setIdentityCardNo(json.getString("identityCardNo"));
                int i = tUserInfoMapper.updateByPrimaryKeySelective(tUserInfo);
                //在t_user_setting表中把RealNameFlag字段设置成“1”
                TUserSetting tUserSetting = new TUserSetting();
                tUserSetting.setRealNameFlag((short) 1);
                tUserSetting.setUpdatetime(new Date());
                TUserSettingExample tUserSettingExample = new TUserSettingExample();
                TUserSettingExample.Criteria c = tUserSettingExample.createCriteria();
                c.andTUserInfoIdEqualTo(tUserInfo.getId());
                int y = tUserSettingMapper.updateByExampleSelective(tUserSetting, tUserSettingExample);
                if (i > 0 && y > 0) {
                    log.info("更新本地数据库成功！");
                    iLoginService.refreshUserInRedis(requestBo.getPacketHead().getToken());
                    return result;
                }
                throw new AccountRuntimeException(ResultBo.Status.DATA_UPDATE_FAIL);
            } else if ("0".equals(rspResultBO.getData().get("verifystatus"))) {
                result.setResultHead(ResultBo.Status.ID_NUMBER_ERROR);
                return result;
            } else if ("2".equals(rspResultBO.getData().get("verifystatus"))) {
                result.setResultHead(ResultBo.Status.NAME_ERROR);
                return result;
            }
        } else {
            log.info("app-service的实名认证失败，参数={}", reqRealNameBO);
            return result.interError(rspResultBO.getDesc());
        }
        return result.interError(rspResultBO.getDesc());
    }

    //银行卡绑定接口的实现
    @Transactional
    @Override
    public ResultBo bindCard(RequestBo requestBo) throws Exception {
        JSONObject json = JSON.parseObject(requestBo.getPacketBody());
        ResultBo result = ResultBo.build();

        //检查银行卡是否绑定
        TAccountBankExample tAccountBankExample = new TAccountBankExample();
        TAccountBankExample.Criteria c1 = tAccountBankExample.createCriteria();
        c1.andBankCardNoEqualTo(json.getString("bankCardNo"));
        int bankCount = tAccountBankMapper.countByExample(tAccountBankExample);
        if (bankCount > 0) {
            return result.bankExist();
        }
        //检查绑定银行卡之前的短信验证码是否正确
        String smscode = valOpts.get("addbankcard" + json.getString("mobilePhone"));
        if (StringUtils.isNotBlank(smscode)) {
            if (!smscode.equals(json.getString("smsCode"))) {
                result.setResultHead(ResultBo.Status.SMS_CODE);
                return result;
            }
        } else {
            result.setResultHead(ResultBo.Status.SMS_TIMEOUT);
            return result;
        }
        //检查绑定的银行卡是否超过5张
        LoginUserBo loginUserBo =
                iLoginService.getUserInRedis(requestBo.getPacketHead().getToken());
        TAccountBankExample tAccountBankExample2 = new TAccountBankExample();
        TAccountBankExample.Criteria c2 = tAccountBankExample.createCriteria();
        c2.andTUserInfoIdEqualTo(loginUserBo.gettUserInfoId());
        int bankCount2 = tAccountBankMapper.countByExample(tAccountBankExample2);
        if (bankCount2 == 5) {
            result.setResultHead(ResultBo.Status.BANK_CARD_NUMBER);
            return result;
        }
        ReqCardBindBO reqCardBindBO = new ReqCardBindBO();
        reqCardBindBO.setUrl(cardBindUrl);
        reqCardBindBO.setMid(mid);
        reqCardBindBO.setKey(key);
        reqCardBindBO.setIdType(01);
        reqCardBindBO.setCardNo(json.getString("bankCardNo"));  //银行卡号
//        reqCardBindBO.setIdName(json.getString("realName"));    //客户姓名
//        reqCardBindBO.setIdNumber(json.getString("identityCardNo"));//身份证号
        //绑卡时不用前台传的身份证号，取redis里面的身份证号。

        reqCardBindBO.setIdNumber(loginUserBo.getIdentityCardNo());//身份证号
        reqCardBindBO.setIdName(loginUserBo.getRealName());     //客户姓名
        reqCardBindBO.setPhone(json.getString("mobilePhone"));      //手机号
        reqCardBindBO.setTokenPass(json.getString("smsCode"));      //绑卡信息验证码
        //从Redis获取出userRef.
        reqCardBindBO.setUserRef(loginUserBo.getUserref());
        log.info("app-service的银行卡绑定的入参={}", reqCardBindBO);
        RspResultBO rspResultBO = iFastPaymentService.cardBind(reqCardBindBO);
        log.info("app-service请求gateway接口银行卡绑定的返回结果={}", rspResultBO);

        if ("0".equals(rspResultBO.getCode())) {
            TAccountBank tAccountBank = new TAccountBank();

            //用户id,从Redis中的Userinfo获取
            tAccountBank.settUserInfoId(loginUserBo.gettUserInfoId());
            //通过银行卡名称在银行卡基础信息表中查出银行卡基础信息id
            TBankBaseExample tBankBaseExample = new TBankBaseExample();
            TBankBaseExample.Criteria c = tBankBaseExample.createCriteria();
            c.andBankCodeEqualTo((String) rspResultBO.getData().get("bankname"));
            List<TBankBase> tBankBases = tBankBaseMapper.selectByExample(tBankBaseExample);
            TBankBase tBankBase = tBankBases.get(0);
            tAccountBank.settBankBaseId(tBankBase.getId());                 //银行卡基础信息id,
            tAccountBank.setBankCardNo(reqCardBindBO.getCardNo());          //银行卡卡号
            //通过接口返回的cardtype字段判断银行卡类型
            //如果是cardtype=14，则bankcardtype为“savings”，如果cardtype=21，则bankcardtype为“credit”。
            if ("14".equals(rspResultBO.getData().get("cardtype"))) {
                tAccountBank.setBankCardType("savings");
            }
            if ("21".equals(rspResultBO.getData().get("cardtype"))) {
                tAccountBank.setBankCardType("credit");
            }
            tAccountBank.setTokenid((String) rspResultBO.getData().get("tokenid"));  //Token编号
            tAccountBank.setMobilePhone(reqCardBindBO.getPhone());          //手机号
            //            tAccountBank.setProvinces(json.getString("provinces"));   //银行卡所在省
            //            tAccountBank.setCity(json.getString("city"));             //银行卡所在市
            tAccountBank.setUseTime(new Date());                            //使用时间
            tAccountBank.setUpdatetime(new Date());                         //使用时间
            log.info("账户银行卡信息={}", tAccountBank);
            int i = tAccountBankMapper.insertSelective(tAccountBank);
            if (i > 0) {
                return result;
            }
            throw new AccountRuntimeException(ResultBo.Status.DATA_UPDATE_FAIL);
        } else {
            return result.interError(rspResultBO.getDesc());
        }
    }

    //银行卡列表接口的实现
    //@Transactional
    @Override
    public ResultBo cardList(RequestBo requestBo) throws Exception {
        //从Redis获取出userid
        LoginUserBo loginUserBo =
                iLoginService.getUserInRedis(requestBo.getPacketHead().getToken());
        Long tuserInfoId = loginUserBo.gettUserInfoId();

        TAccountBankExample tAccountBankExample = new TAccountBankExample();
        TAccountBankExample.Criteria c = tAccountBankExample.createCriteria();
        c.andTUserInfoIdEqualTo(tuserInfoId);
        tAccountBankExample.setOrderByClause("use_time desc");
        List<TAccountBank> tAccountBankList =
                tAccountBankMapper.selectByExample(tAccountBankExample);

        List<RspCardListBO> rspCardListBOList = new ArrayList<>();
        if (null != tAccountBankList && !tAccountBankList.isEmpty()) {
            for (int i = 0; i < tAccountBankList.size(); i++) {
                TAccountBank tAccountBank = tAccountBankList.get(i);
                TBankBase tBankBase =
                        tBankBaseMapper.selectByPrimaryKey(tAccountBank.gettBankBaseId());
                RspCardListBO rspCardListBO = new RspCardListBO();
                rspCardListBO.setOrderNum(String.valueOf(i + 1));               //序号
                rspCardListBO.setBankCardId(tAccountBank.getId() + "");          //银行卡id
                rspCardListBO.setBankCardNo(MaskCodeUtil.maskBankCard(tAccountBank.getBankCardNo()));  //银行卡号
                rspCardListBO.setBankLogoUrl(tBankBase.getBankLogoUrl());  //银行logo
                rspCardListBO.setBankLogoNotColorUrl(tBankBase.getBankLogoNotColorUrl());  //银行notColorlogo
                rspCardListBO.setBankBgColor(tBankBase.getBankBgColor());  //银行底色
                rspCardListBO.setBankName(tBankBase.getBankName());  //银行名称
                rspCardListBO.setIsCreditCard(tAccountBank.getBankCardType());  //银行卡类型（0-否 1-是 信用卡）
                rspCardListBO.setSingleLimit(tBankBase.getSingleLimit());  //单笔限额
                rspCardListBO.setDayLimit(tBankBase.getDayLimit());  //单日限额
                rspCardListBOList.add(rspCardListBO);
            }
        }
        ResultBo result = ResultBo.build();
        ResultBody resultBody = new ResultBody();
        resultBody.put("bankList", rspCardListBOList);
        result.setResultBody(resultBody);
        return result;
    }

    //银行卡解绑接口的实现
    @Transactional
    @Override
    public ResultBo cardUnbind(RequestBo requestBo) throws Exception {
        JSONObject json = JSON.parseObject(requestBo.getPacketBody());
        log.info("银行卡解绑的入参={}", json);
        TAccountBank tAccountBank =
                tAccountBankMapper.selectByPrimaryKey(json.getLong("bankCardId"));
        log.info("银行卡信息={}", tAccountBank);
        String tokenid = tAccountBank.getTokenid();
        ReqCardUnbindBO reqCardUnbindBO = new ReqCardUnbindBO();
        //userref 从Redis中获取
        LoginUserBo loginUserBo =
                iLoginService.getUserInRedis(requestBo.getPacketHead().getToken());

        ResultBo result = ResultBo.build();
        //添加校验确保删除的卡是自己的
        if (loginUserBo.gettUserInfoId() != tAccountBank.gettUserInfoId().longValue()) {
            log.info("不能删除他人的银行卡");
            result.setResultHead(ResultBo.Status.BANK_NOT_YOURS);
            return result;
        }
        reqCardUnbindBO.setUserref(loginUserBo.getUserref());
        reqCardUnbindBO.setTokenId(tokenid);
        reqCardUnbindBO.setUrl(cardUnbindUrl);
        reqCardUnbindBO.setMid(mid);
        reqCardUnbindBO.setKey(key);
        log.info("app-service的银行卡解绑的入参={}", reqCardUnbindBO);
        RspResultBO rspResultBO = iFastPaymentService.cardUnbind(reqCardUnbindBO);
        log.info("app-service的银行卡解绑接口的返回结果={}", reqCardUnbindBO);
        if ("0".equals(rspResultBO.getCode())) {
            if ("0".equals(rspResultBO.getData().get("closestatus"))) {
                log.info("解绑成功");
                TAccountBankHistory tAccountBankHistory = new TAccountBankHistory();
                BeanUtils.copyProperties(tAccountBank, tAccountBankHistory);
                TBankBase tBankBase = tBankBaseMapper.selectByPrimaryKey(tAccountBank.gettBankBaseId());
                tAccountBankHistory.setSingleLimit(tBankBase.getSingleLimit());
                tAccountBankHistory.setDayLimit(tBankBase.getDayLimit());
                int i = tAccountBankMapper.deleteByPrimaryKey(json.getLong("bankCardId"));
                int y = tAccountBankHistoryMapper.insertSelective(tAccountBankHistory);
                if (i > 0 && y > 0) {
                    log.info("更新本地数据库成功");
                    return result;
                }
                throw new AccountRuntimeException(ResultBo.Status.DATA_UPDATE_FAIL);
            } else {
                log.info("1-解绑失败");
                return result.interError(rspResultBO.getDesc());
            }
        } else {
            return result.interError(rspResultBO.getDesc());
        }
    }

    //综合验证
    @Override
    public ResultBo verification(RequestBo requestBo) throws Exception {

        //从Redis获取出user
        LoginUserBo loginUserBo =
                iLoginService.getUserInRedis(requestBo.getPacketHead().getToken());
        //根据用户id查询用户密码
        TPasswordInfoExample tPasswordInfoExample = new TPasswordInfoExample();
        TPasswordInfoExample.Criteria c = tPasswordInfoExample.createCriteria();
        c.andTUserInfoIdEqualTo(loginUserBo.gettUserInfoId());
        TPasswordInfo tPasswordInfo =
                tPasswordInfoMapper.selectByExample(tPasswordInfoExample).get(0);
        JSONObject json = JSON.parseObject(requestBo.getPacketBody());
        log.info("入参为={}", json);
        String password;
        String code;
        String sign;
        ResultBo resultBo = ResultBo.build();
        ResultBody resultBody = new ResultBody();
        boolean result;
        log.info("验证类型={}", json.getString("validateType"));
        log.info("验证内容={}", json.getString("validateContent"));
        switch (json.getString("validateType")) {
            case "gesturePwd"://手势密码(需要经过md5加密)
                password = tPasswordInfo.getGesturePassword();
                code = tPasswordInfo.getGesturePasswordRandomCode();
                sign = DigestUtils.md5Hex(json.getString("validateContent") + code).toUpperCase();
                return getResultBo(resultBo, resultBody, sign.equals(password));
            case "payPwd"://支付密码(需要经过md5加密)
                password = tPasswordInfo.getPayPassword();
                code = tPasswordInfo.getPayPasswordRandomCode();
                sign = DigestUtils.md5Hex(json.getString("validateContent") + code).toUpperCase();
                if (sign.equals(password)) {
                    log.info("支付密码正确");
                    //如果密码正确，删除redis中存入的支付密码剩余错误次数
                    stringRedisTemplate.delete("payCount-" + String.valueOf(loginUserBo.gettUserInfoId()));
                    log.info("清除redis中的key为={}", "payCount-" + String.valueOf(loginUserBo.gettUserInfoId()));
                    resultBody.put("validateResult", "correct");
                    resultBo.setResultBody(resultBody);
                    return resultBo;
                    //验证失败
                } else {
                    //调用getLockCount方法，将返回的剩余输入次数
                    Map<String, String> params = new HashMap<>();
                    params.put("pwdType", "pay");
                    params.put("userId", String.valueOf(loginUserBo.gettUserInfoId()));
                    int count = iLoginService.getLockCount(params);
                    log.info("支付密码错误，用户剩余输入次数为={}", count);
                    //如果剩余次数为0，那么提示用户超过输入限制
                    if (0 == count) {
                        return resultBo.setStatus(ResultBo.Status.PAY_PASSWORD_LOCkED);
                        //如果不为0，那么返回错误以及用户剩余输入次数
                    } else {
                        resultBody.put("validateResult", "fault");
                        resultBody.put("payCount", String.valueOf(count));
                        resultBo.setResultBody(resultBody);
                        return resultBo;
                    }
                }
            case "loginPwd"://登陆密码(需要经过md5加密)
                password = tPasswordInfo.getLoginPassword();
                code = tPasswordInfo.getLoginPasswordRandomCode();
                sign = DigestUtils.md5Hex(json.getString("validateContent") + code).toUpperCase();
                return getResultBo(resultBo, resultBody, sign.equals(password));
            case "identityCardNo"://身份证号码
                result = json.getString("validateContent").equals(loginUserBo.getIdentityCardNo());
                return getResultBo(resultBo, resultBody, result);
            case "smsPayCode"://短信验证码（用于重置支付密码）
                result = json.getString("validateContent").equals(valOpts.get(
                        "resetpaypwd" + loginUserBo.getMobilePhone()));
                return getResultBo(resultBo, resultBody, result);
            default:
                return resultBo.setStatus(ResultBo.Status.REQUEST_NOT_FULL);
        }
    }

    //根据result结果返回正确或者错误（抽出来的公用方法-综合验证用）
    private ResultBo getResultBo(ResultBo resultBo, ResultBody resultBody, boolean result) {
        log.info("验证结果为={}", result);
        //验证成功
        if (result) {
            resultBody.put("validateResult", "correct");
            resultBo.setResultBody(resultBody);
            return resultBo;
            //验证失败
        } else {
            resultBody.put("validateResult", "fault");
            resultBo.setResultBody(resultBody);
            return resultBo;
        }
    }

    //充值
    @Transactional
    @Override
    public ResultBo recharge(RequestBo requestBo) throws Exception {
        //从Redis获取出user
        LoginUserBo loginUserBo =
                iLoginService.getUserInRedis(requestBo.getPacketHead().getToken());
        JSONObject json = JSON.parseObject(requestBo.getPacketBody());
        log.info("入参为={}", json);
        //根据bankCardId查询账户银行卡信息
        TAccountBank tAccountBank =
                tAccountBankMapper.selectByPrimaryKey(json.getLong("bankCardId"));
        //查询银行卡基础信息
        TBankBase tBankBase = tBankBaseMapper.selectByPrimaryKey(tAccountBank.gettBankBaseId());
        String chargeAmount = MoneyUtil.formatMoney(json.getString("chargeAmount"));//充值金额

        ResultBo resultBo = ResultBo.build();

        //如果是信用卡，返回失败
        if ("credit".equals(tAccountBank.getBankCardType())) {
            return resultBo.setStatus(ResultBo.Status.BANKCARDTYPEERROR);
        }

        if (!"-1".equals(tBankBase.getSingleLimit())) {
            //"-1"是没有金额限制的情况
            //查询充值金额是否超过单笔限额
            if (MoneyUtil.moneyComp(chargeAmount, tBankBase.getSingleLimit())) {
                return resultBo.setStatus(ResultBo.Status.SINGLELIMIT);
            }
        }
        //从redis中取出已充值金额，判断是否为空，如果为空，赋值"0.00"，以及存活时间存入redis
        if (StringUtils.isBlank(valOpts.get("day_limit-" + tAccountBank.getBankCardNo()))) {
            valOpts.set("day_limit-" + tAccountBank.getBankCardNo(), "0.00",
                    DateUtil.endOfDay(new Date()).getTime() - new Date().getTime(), TimeUnit.MILLISECONDS);
        }
        if (!"-1".equals(tBankBase.getDayLimit())) {
            //"-1"是没有金额限制的情况
            //判断充值金额是否超过单日剩余可充值金额（单日限额-已充值金额）
            if (MoneyUtil.moneyComp(chargeAmount, MoneyUtil.moneySub(tBankBase.getDayLimit(), valOpts.get
                    ("day_limit-" + tAccountBank.getBankCardNo())))) {
                return resultBo.setStatus(ResultBo.Status.DAYLIMIT);
            }
        }

        //根据用户id查询用户账户
        TAccountInfoExample tAccountInfoExample = new TAccountInfoExample();
        TAccountInfoExample.Criteria c = tAccountInfoExample.createCriteria();
        c.andTUserInfoIdEqualTo(loginUserBo.gettUserInfoId());
        TAccountInfo tAccountInfo = tAccountInfoMapper.selectByExample(tAccountInfoExample).get(0);
        c.andAmountEqualTo(tAccountInfo.getAmount());//添加查询条件：账户总额=账户总额（防止更新时金额不一致）
        String amount = tAccountInfo.getAmount();//账户总额
        String freeAmount = tAccountInfo.getFreeAmount();//可用余额
        tAccountInfo.setAmount(MoneyUtil.moneyAdd(chargeAmount, amount));//账户总额+充值金额
        tAccountInfo.setFreeAmount(MoneyUtil.moneyAdd(chargeAmount, freeAmount));//可用余额+充值金额

        //更新用户账户表
        int count = tAccountInfoMapper.updateByExampleSelective(tAccountInfo, tAccountInfoExample);
        if (count == 0) {
            throw new AccountRuntimeException(ResultBo.Status.ACCOUNTCHANGED);
        } else {
            //更新账户银行卡使用时间
            tAccountBank.setUseTime(new Date());
            tAccountBankMapper.updateByPrimaryKeySelective(tAccountBank);
            //封装银行卡支付接口需要的参数
            ReqCardPayBO reqCardPayBO = new ReqCardPayBO();
            reqCardPayBO.setAmount(chargeAmount);//订单总金额
            reqCardPayBO.setMoneyType(json.getInteger("0"));//支付币种
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String ymdHms = format.format(date);
            String ymd = ymdHms.substring(0, 8);//格式为：yyyyMMdd
            //订单编号(格式为订单生成日期-商户编号-商户流水号;商户流水号格式：日期年月日时分秒+8位随机数)
            reqCardPayBO.setOid(ymd + "-" + mid + "-" + ymdHms + RandomUtil.get8Random());
            reqCardPayBO.setTokenId(tAccountBank.getTokenid());//TOKEN 编号
            reqCardPayBO.setMoneyType(0);
            reqCardPayBO.setUserref(loginUserBo.getUserref());//客户唯一标识
            reqCardPayBO.setYmd(ymd);//订单产生日期
            reqCardPayBO.setMid(mid);//商户号
            reqCardPayBO.setKey(key);//md5的key
            reqCardPayBO.setUrl(cardPayUrl);//充值接口的请求地址

            //新增一条账单记录
            TAccountTransact tAccountTransact = new TAccountTransact();
            tAccountTransact.settUserInfoId(loginUserBo.gettUserInfoId());//对应用户信息表id
            tAccountTransact.settBankBaseId(tAccountBank.gettBankBaseId());//对应应行卡基础信息表id
            tAccountTransact.setTransactNo(OrderNoUtil.getOrderNo(OrderEnum.BANTRANSTER));//流水号
            tAccountTransact.setBankCardNo(tAccountBank.getBankCardNo());//银行卡号
            tAccountTransact.setMobilePhone(loginUserBo.getMobilePhone());//手机号
            tAccountTransact.setBankCardType(tAccountBank.getBankCardType());//银行卡类型
            tAccountTransact.setSingleLimit(tBankBase.getSingleLimit());//单笔限额
            tAccountTransact.setDayLimit(tBankBase.getDayLimit());//单日限额
            tAccountTransact.setPayType("banktransfer");//支付类型(banktransfer-充值-银行转出)
            tAccountTransact.setBillAmount(chargeAmount);//账单金额
            tAccountTransact.setOriginalAmount(chargeAmount);//账单原金额
            tAccountTransact.setOrdertime(new Date());
            tAccountTransactMapper.insertSelective(tAccountTransact);

            log.info("充值接口入参为={}", JSON.toJSONString(reqCardPayBO));
            //调用快捷支付接口
            RspResultBO rspResultBO = iFastPaymentService.cardPay(reqCardPayBO);
            log.info("充值返回结果是={}", rspResultBO);
            if ("0".equals(rspResultBO.getCode())) {
                //将充值成功的金额与在redis中存的充值金额相加后，再次存入redis，作为当天的已充值金额
                valOpts.set("day_limit-" + tAccountBank.getBankCardNo(), MoneyUtil.moneyAdd(valOpts.get("day_limit-" + tAccountBank.getBankCardNo()), chargeAmount),
                        DateUtil.endOfDay(new Date()).getTime() - new Date().getTime(), TimeUnit.MILLISECONDS);
                ResultBody resultBody = new ResultBody();
                resultBody.put("singleBillType", "charge");//类型
                resultBody.put("billAmount", chargeAmount);//金额
                resultBody.put("payMethod", tBankBase.getBankName() + "(" + MaskCodeUtil.maskBankCard(tAccountBank.getBankCardNo()) + ")");//支付方式
                resultBody.put("payType", "banktransfer");//支付类型
                resultBody.put("billTime", DateUtil.formatNewDatetoString(tAccountTransact.getOrdertime()));//支付时间
                resultBody.put("transactNo", tAccountTransact.getTransactNo());//支付订单号
                resultBo.setResultBody(resultBody);
                return resultBo;
            } else {
                throw new AccountRuntimeException("9001", rspResultBO.getDesc());
            }
        }
    }

    //获取可提现金额
    @Override
    public ResultBo getFreeAmount(RequestBo requestBo) throws Exception {
        //从Redis获取出user
        LoginUserBo loginUserBo =
                iLoginService.getUserInRedis(requestBo.getPacketHead().getToken());
        //根据用户id查询用户账户
        TAccountInfoExample tAccountInfoExample = new TAccountInfoExample();
        TAccountInfoExample.Criteria c = tAccountInfoExample.createCriteria();
        c.andTUserInfoIdEqualTo(loginUserBo.gettUserInfoId());
        TAccountInfo tAccountInfo = tAccountInfoMapper.selectByExample(tAccountInfoExample).get(0);
        //封装返回对象
        ResultBo result = ResultBo.build();
        ResultBody resultBody = new ResultBody();
        //返回可提现金额（人民币）
        resultBody.put("freeAmount", tAccountInfo.getFreeAmount());
        log.info("可提现金额为={}", tAccountInfo.getFreeAmount());
        //返回可提现金额（韩元）
        List<Map<String, String>> freeForeignList = new ArrayList<>();
        Map<String, String> freeForeignListMap = new HashMap<>();
        freeForeignListMap.put("foreignType", "korea");
        //查询汇率信息（韩元）
        TCurrencyRate tCurrencyRate = indexService.getTCurrencyRateBy("korea");
        log.info("韩元汇率为={}", tCurrencyRate.getRate());
        String freeAmountKorea = MoneyUtil.moneyMul(tAccountInfo.getFreeAmount(), tCurrencyRate.getRate());
        log.info("可提现韩元为={}", freeAmountKorea);
        freeForeignListMap.put("freeForeignAmount", freeAmountKorea);
        freeForeignList.add(freeForeignListMap);
        resultBody.put("freeForeignList", freeForeignList);
        result.setResultBody(resultBody);
        return result;
    }

    //提现
    @Transactional
    @Override
    public ResultBo withdrawal(RequestBo requestBo) throws Exception {
        //从Redis获取出user
        LoginUserBo loginUserBo =
                iLoginService.getUserInRedis(requestBo.getPacketHead().getToken());
        JSONObject json = JSON.parseObject(requestBo.getPacketBody());
        log.info("入参为={}", json);
        String withdrawalAmount = MoneyUtil.formatMoney(json.getString("cashAmount"));//提现金额
        //根据用户id查询用户账户
        TAccountInfoExample tAccountInfoExample = new TAccountInfoExample();
        TAccountInfoExample.Criteria c = tAccountInfoExample.createCriteria();
        c.andTUserInfoIdEqualTo(loginUserBo.gettUserInfoId());
        TAccountInfo tAccountInfo = tAccountInfoMapper.selectByExample(tAccountInfoExample).get(0);
        c.andFreeAmountGreaterThanOrEqualTo(withdrawalAmount);//添加查询条件：可用余额>=提现金额（防止更新时金额不一致）
        c.andAmountEqualTo(tAccountInfo.getAmount());//添加查询条件：账户总额=账户总额（防止更新时金额不一致）
        BigDecimal amount = new BigDecimal(tAccountInfo.getAmount());//账户总额
        BigDecimal freeAmount = new BigDecimal(tAccountInfo.getFreeAmount());//可用余额
        BigDecimal cashAmount = new BigDecimal(withdrawalAmount);//提现金额
        tAccountInfo.setAmount(String.valueOf(amount.subtract(cashAmount)));//账户总额-提现金额
        tAccountInfo.setFreeAmount(String.valueOf(freeAmount.subtract(cashAmount)));//可用余额-提现金额
        //更新用户账户表
        int count = tAccountInfoMapper.updateByExampleSelective(tAccountInfo, tAccountInfoExample);
        if (count == 0) {
            throw new AccountRuntimeException(ResultBo.Status.ACCOUNTCHANGED);
        } else {
            //根据bankCardId查询账户银行卡信息
            TAccountBank tAccountBank =
                    tAccountBankMapper.selectByPrimaryKey(json.getLong("bankCardId"));
            log.info(JSON.toJSONString(tAccountBank));
            //根据账户银行卡信息中的银行卡基础信息表id查询
            TBankBase tBankBase = tBankBaseMapper.selectByPrimaryKey(tAccountBank.gettBankBaseId());
            ReqPaymentSubmitBO reqPaymentSubmitBO = new ReqPaymentSubmitBO();
            reqPaymentSubmitBO.setMoney(withdrawalAmount);//提现金额
            reqPaymentSubmitBO.setAccountName(loginUserBo.getRealName());//账户名
            reqPaymentSubmitBO.setAccountNo(tAccountBank.getBankCardNo());//银行卡号
            reqPaymentSubmitBO.setBankCode(tBankBase.getBankLangCode());//联行号
            reqPaymentSubmitBO.setBankName(tBankBase.getBankName());//银行卡名称

            //根据redis中存的location查询用户所在省市
            TAreas tAreas =
                    tAreasMapper.selectByPrimaryKey(Integer.valueOf(loginUserBo.getLocation()));
            reqPaymentSubmitBO.setCity(tAreas.getAreaName());//市
            tAreas = tAreasMapper.selectByPrimaryKey(tAreas.getParentId());
            reqPaymentSubmitBO.setProvince(tAreas.getAreaName());//省
            reqPaymentSubmitBO.setUserID(OrderNoUtil.getOrderNo(OrderEnum.BANKINTO));//用户唯一标识（流水号）
            reqPaymentSubmitBO.setMid(mid);//商户号
            reqPaymentSubmitBO.setKey(key);//md5的key
            reqPaymentSubmitBO.setUrl(paymentSubmitUrl);//代付提交接口的请求地址
            reqPaymentSubmitBO.setVersion(version);//版本号

            //新增一条账单记录
            TAccountTransact tAccountTransact = new TAccountTransact();
            tAccountTransact.settUserInfoId(loginUserBo.gettUserInfoId());//对应用户信息表id
            tAccountTransact.settBankBaseId(tAccountBank.gettBankBaseId());//对应应行卡基础信息表id
            tAccountTransact.setTransactNo(reqPaymentSubmitBO.getUserID());//流水号
            tAccountTransact.setBankCardNo(tAccountBank.getBankCardNo());//银行卡号
            tAccountTransact.setMobilePhone(loginUserBo.getMobilePhone());//手机号
            tAccountTransact.setBankCardType(tAccountBank.getBankCardType());//银行卡类型
            tAccountTransact.setSingleLimit(tBankBase.getSingleLimit());//单笔限额
            tAccountTransact.setDayLimit(tBankBase.getDayLimit());//单日限额
            tAccountTransact.setPayType("bankinto");//支付类型(bankinto-提现-银行转入)
            tAccountTransact.setBillAmount(reqPaymentSubmitBO.getMoney());//账单金额
            tAccountTransact.setOriginalAmount(reqPaymentSubmitBO.getMoney());//账单原金额
            tAccountTransact.setDealStatus("dealing");//处理状态
            tAccountTransact.setOrdertime(new Date());
            tAccountTransactMapper.insertSelective(tAccountTransact);

            //更新账户银行卡使用时间
            tAccountBank.setUseTime(new Date());
            tAccountBankMapper.updateByPrimaryKeySelective(tAccountBank);

            //新增一条提现待处理记录
            TWithdrawalHistory tWithdrawalHistory = new TWithdrawalHistory();
            tWithdrawalHistory.settUserInfoId(loginUserBo.gettUserInfoId());
            tWithdrawalHistory.settAccountTransactId(tAccountTransact.getId());
            tWithdrawalHistory.setAmount(String.valueOf(cashAmount));
            tWithdrawalHistory.setUpdatetime(new Date());
            tWithdrawalHistoryMapper.insertSelective(tWithdrawalHistory);

            log.info("提现接口入参为={}", JSON.toJSONString(reqPaymentSubmitBO));
            //调用代付提交接口
            RspResultBO rspResultBO = iPaymentService.paymentSubmit(reqPaymentSubmitBO);
            log.info("提现返回结果是={}", rspResultBO);
            ResultBo resultBo = ResultBo.build();
            //提现提交返回成功
            if ("0".equals(rspResultBO.getCode())) {
                ResultBody resultBody = new ResultBody();
                resultBody.put("singleBillType", "cash");//类型
                resultBody.put("billAmount", withdrawalAmount);//金额
                resultBody.put("payMethod", tBankBase.getBankName() + "(" + MaskCodeUtil.maskBankCard(tAccountBank.getBankCardNo()) + ")");//支付方式
                resultBody.put("payType", "bankinto");//支付类型
                resultBody.put("billTime", DateUtil.formatNewDatetoString(tAccountTransact.getOrdertime()));//支付时间
                resultBody.put("transactNo", tAccountTransact.getTransactNo());//支付订单号
                resultBo.setResultBody(resultBody);
                return resultBo;
            } else {//提现提交返回失败
                throw new AccountRuntimeException("9001", rspResultBO.getDesc());
            }
        }
    }

    //获取账单列表
    @Override
    public ResultBo getBillList(RequestBo requestBo) throws Exception {
        //从Redis获取出user
        LoginUserBo loginUserBo =
                iLoginService.getUserInRedis(requestBo.getPacketHead().getToken());
        JSONObject json = JSON.parseObject(requestBo.getPacketBody());
        log.info("入参为={}", json);
        //封装查询条件
        TAccountTransactExample tAccountTransactExample = new TAccountTransactExample();
        TAccountTransactExample.Criteria c = tAccountTransactExample.createCriteria();
        log.info("billType={}", json.getString("billType"));
        if (StringUtils.isNotBlank(json.getString("billType"))) {
            switch (json.getString("billType")) {
                case "all"://全部
                    break;
                case "charge"://账户充值
                    c.andPayTypeEqualTo("banktransfer");
                    break;
                case "cash"://账户提现
                    c.andPayTypeEqualTo("bankinto");
                    break;
                case "koreacharge"://新韩卡充值
                    c.andPayTypeEqualTo("walletfrozen");
                    break;
                case "korearefund"://新韩卡退款
                    c.andPayTypeEqualTo("walletfree");
                    break;
                case "koreascan"://新韩卡扫码支付
                    c.andPayTypeEqualTo("koreascan");
                    break;
                case "koreacard"://新韩卡刷卡支付
                    c.andPayTypeEqualTo("koreacard");
                    break;
                default:
                    c.andPayTypeEqualTo("0");
                    break;
            }
        }
        if (StringUtils.isNotBlank(json.getString("billStartDate"))) {
            c.andOrdertimeGreaterThanOrEqualTo(json.getDate("billStartDate"));
        }
        if (StringUtils.isNotBlank(json.getString("billEndDate"))) {
            c.andOrdertimeLessThanOrEqualTo(DateUtil.endOfDay(json.getDate("billEndDate")));
        }
        c.andTUserInfoIdEqualTo(loginUserBo.gettUserInfoId());
        tAccountTransactExample.setOrderByClause("ordertime desc");//按账单时间倒序排列
        PageHelper.startPage(json.getInteger("currentPageNo"), json.getInteger("currentPageLimit"));
        List<TAccountTransact> tAccountTransactList =
                tAccountTransactMapper.selectByExample(tAccountTransactExample);
        Page<TAccountTransact> tAccountTransacts = (Page<TAccountTransact>) tAccountTransactList;
        Page<RspBillListBO> rspBillListBOS = new Page<>();
        BeanUtils.copyProperties(tAccountTransacts, rspBillListBOS);
        TBankBaseExample tBankBaseExample = new TBankBaseExample();
        //查询出银行卡基础信息表的所有数据
        List<TBankBase> tBankBaseList = tBankBaseMapper.selectByExample(tBankBaseExample);
        //将list转为map
        Map<Long, TBankBase> tBankBaseMap = tBankBaseList.stream().collect(
                Collectors.toMap(TBankBase::getId, Function.identity()));
        //如果查出的账单列表不为空，封装返回参数
        if (!tAccountTransactList.isEmpty()) {
            tAccountTransactList.forEach(e -> {
                RspBillListBO rspBillListBO = new RspBillListBO();
                rspBillListBO.setBillTime(DateUtil.formatNewDatetoString(e.getOrdertime()));//账单支付时间
                rspBillListBO.setBillAmount(e.getBillAmount());//账单金额
                rspBillListBO.setPayMethod(
                        tBankBaseMap.get(e.gettBankBaseId()).getBankName() + "(" + MaskCodeUtil.maskBankCard(e.getBankCardNo()) + ")");//支付方式
//                }
                rspBillListBO.setPayType(e.getPayType());//支付类型
                String singelBillType = "";
                switch (e.getPayType()) {
                    case "banktransfer"://账户充值
                        singelBillType = "charge";
                        break;
                    case "bankinto"://账户提现
                        singelBillType = "cash";
                        break;
                    case "walletfrozen"://新韩卡充值
                        singelBillType = "koreacharge";
                        break;
                    case "walletfree"://新韩卡退款
                        singelBillType = "korearefund";
                        break;
                    case "koreascan"://新韩卡扫码支付
                        singelBillType = "koreascan";
                        break;
                    case "koreacard"://新韩卡刷卡支付
                        singelBillType = "koreacard";
                        break;
                    default:
                        break;
                }
                rspBillListBO.setSingleBillType(singelBillType);//账单类型
                rspBillListBO.setTransactNo(e.getTransactNo());//账单支付订单号
                rspBillListBO.setOriginalAmount(e.getOriginalAmount());//账单原金额
                rspBillListBO.setSaleAmount(e.getSaleAmount());//优惠金额
                rspBillListBO.setLogoUrl(tBankBaseMapper.selectByPrimaryKey(e.gettBankBaseId())
                        .getBankLogoUrl());//对应logo的url
                rspBillListBO.setLogoNotColorUrl(tBankBaseMapper.selectByPrimaryKey(e.gettBankBaseId())
                        .getBankLogoNotColorUrl());//对应没有颜色的logo的url
                rspBillListBO.setBillStatus(e.getDealStatus());//账单状态
                rspBillListBOS.add(rspBillListBO);
            });
        }
        PageInfo pageInfo = new PageInfo(rspBillListBOS);
        //封装返回对象
        ResultBody resultBody = new ResultBody();
        resultBody.put("billType", json.getString("billType"));//账单类型
        resultBody.put("currentPageNo", json.getString("currentPageNo"));//页码
        resultBody.put("currentPageLimit", json.getString("currentPageLimit"));//每页条数
        resultBody.put("billStartDate", json.getString("billStartDate"));//账单开始时间
        resultBody.put("billEndDate", json.getString("billEndDate"));//账单结束时间
        //查询转入总额（包括银行卡充值）
        String intoAmount =
                MoneyUtil.formatMoney(tAccountTransactMapper.selectIntoTotalAmount(loginUserBo
                        .gettUserInfoId()));
        log.info("转入总额={}", intoAmount);
        //查询转出总额（包括银行卡提现，刷卡支付和扫码支付）
        String outAmount =
                MoneyUtil.formatMoney(tAccountTransactMapper.selectOutTotalAmount(loginUserBo
                        .gettUserInfoId()));
        log.info("转出总额={}", outAmount);
        resultBody.put("intoAmount", intoAmount);//转入总额
        resultBody.put("outAmount", outAmount);//转出总额
        resultBody.put("totalPages", String.valueOf(pageInfo.getPages()));//总页数
        resultBody.put("billList", pageInfo.getList());//账单列表
        ResultBo resultBo = ResultBo.build();
        resultBo.setResultBody(resultBody);
        log.info("总页数={}", pageInfo.getPages());
        log.info("总条数={}", pageInfo.getTotal());
        log.info("每页显示条数={}", pageInfo.getPageSize());
        log.info("账单列表={}", JSON.toJSONString(pageInfo.getList()));
        return resultBo;
    }
}
