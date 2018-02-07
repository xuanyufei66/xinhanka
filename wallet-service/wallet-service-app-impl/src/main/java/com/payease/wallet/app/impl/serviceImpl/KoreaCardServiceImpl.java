package com.payease.wallet.app.impl.serviceImpl;

import bo.ReqCardPayBO;
import bo.RspResultBO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.payease.wallet.app.api.service.ILoginService;
import com.payease.wallet.app.api.service.IkoreaCardService;
import com.payease.wallet.app.impl.propertites.ApiCommProperties;
import com.payease.wallet.app.impl.utils.DateUtil;
import com.payease.wallet.app.impl.utils.MoneyUtil;
import com.payease.wallet.dto.bean.*;
import com.payease.wallet.dto.utils.*;
import com.payease.wallet.entity.pojo.*;
import com.payease.wallet.gateway.api.service.IFastPaymentService;
import com.payease.wallet.orm.inter.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * @Author : zhangwen
 * @Data : 2017/11/15
 * @Description :新韩卡业务
 */
@Service
public class KoreaCardServiceImpl implements IkoreaCardService {

    private static final Logger log = LoggerFactory.getLogger(KoreaCardServiceImpl.class);

    @Autowired
    private ILoginService iLoginService;
    @Autowired
    private IndexServiceImpl indexService;
    @Autowired
    private TAccountInfoMapper tAccountInfoMapper;
    @Autowired
    private TAccountTransactMapper tAccountTransactMapper;
    @Autowired
    private TKoreaTransactMapper tKoreaTransactMapper;
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private IFastPaymentService iFastPaymentService;
    @Autowired
    private TKoreaInfoMapper tKoreaInfoMapper;
    @Autowired
    private TBankBaseMapper tBankBaseMapper;
    @Autowired
    private TKoreaApplyMapper tKoreaApplyMapper;
    @Autowired
    private TCurrencyRateMapper tCurrencyRateMapper;
    @Autowired
    ApiCommProperties apiCommProperties;
    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> valOpts;
    @Value("${own.mid}")
    String mid;//商户号
    @Value("${own.key}")
    String key;//md5的key
    @Value("${own.url.cardPay}")
    String cardPayUrl;//银行卡支付地址接口地址

    // ========================================= shihaikun start 申请新韩卡=================================================
    /**
     * @param requestBo
     * @Author shihaikun
     * @MethodName 申请新韩卡
     * @Params
     * @Return
     * @Date 2017/11/15 上午10:31
     * @Desp
     */
    @Override
    public ResultBo applyKoreaCard(RequestBo requestBo) throws Exception {

        //去redis中取用户登陆后填写的申请新韩卡数据
        ResultBo resultBo = ResultBo.build();
        try {
            LoginUserBo userInRedis = iLoginService.getUserInRedis(requestBo.getPacketHead().getToken());

            Long tUserInfoId = Long.valueOf(userInRedis.gettUserInfoId());
            TKoreaInfoExample example = new TKoreaInfoExample();
            TKoreaInfoExample.Criteria c = example.createCriteria();
            c.andTUserInfoIdEqualTo(tUserInfoId);
            c.andIsDeleteEqualTo((short)0);
            int count = tKoreaInfoMapper.countByExample(example);
            if(count>0) {
                return resultBo.error(ErrorResult.THEUSERKOREACARDALREADYEXISTS);
            }
//            Long tUserInfoId = Long.valueOf("2");
            String packetBody = requestBo.getPacketBody();//获取json串
            JSONObject jsonObject = JSON.parseObject(packetBody);
            if(jsonObject == null){
                return resultBo.reqNotFull();
            }
            //存入新韩卡申请表
            TKoreaApply tKoreaApply = new TKoreaApply();
            if(!jsonObject.containsKey("passportNo")|| StringUtils.isBlank(jsonObject.getString("passportNo"))){
                return resultBo.error(ErrorResult.ENTERTHECORRECTPASSPORTNUMBER);
            }
            if(!jsonObject.containsKey("lssueDate")|| StringUtils.isBlank(jsonObject.getString("lssueDate"))){
                return resultBo.error(ErrorResult.ENTERTHECORRECTDATEOFISSUE);
            }
            if(!jsonObject.containsKey("expirationDate")||StringUtils.isBlank(jsonObject.getString("expirationDate"))){
                return resultBo.error(ErrorResult.ENTERTHECORRECTEXPIRATIONDATE);
            }
            if(!jsonObject.containsKey("toKoreaDate")||StringUtils.isBlank(jsonObject.getString("toKoreaDate"))){
                return resultBo.error(ErrorResult.INPUTTHECORRECTDATETOKOREA);
            }
            if(!jsonObject.containsKey("outKoreaDate")||StringUtils.isBlank(jsonObject.getString("outKoreaDate"))){
                return resultBo.error(ErrorResult.INPUTTHECORRECTDATEOFDEPARTUREFROMKOREA);
            }
            if(!jsonObject.containsKey("imgFileUrl")||StringUtils.isBlank(jsonObject.getString("imgFileUrl"))){
                return resultBo.reqNotFull();
            }

            tKoreaApply.settUserInfoId(tUserInfoId);// passportNo	护照编号
            tKoreaApply.setPassportNo(String.valueOf(jsonObject.getString("passportNo")));// passportNo	护照编号
            tKoreaApply.setLssueDate(String.valueOf(jsonObject.getString("lssueDate")));// lssueDate	护照签发日期
            tKoreaApply.setExpirationDate(String.valueOf(jsonObject.getString("expirationDate")));// expirationDate	护照失效日期
            tKoreaApply.setToKoreaDate(java.sql.Date.valueOf(jsonObject.getString("toKoreaDate"))); // toKoreaDate	来韩日期
            tKoreaApply.setOutKoreaDate(java.sql.Date.valueOf(jsonObject.getString("outKoreaDate")));  // outKoreaDate 离韩日期
            tKoreaApply.setImgFileUrl(String.valueOf(jsonObject.getString("imgFileUrl")));   // imgFileUrl	护照url
//        tKoreaApply.setCreatetime(Date.valueOf(jsonObject.getString("createtime")));// createtime	申请时间
//            tKoreaApply.setApplyState(Short.valueOf(jsonObject.getString("applyState")));// applyState	申请状态
            tKoreaApplyMapper.insertSelective(tKoreaApply);

            //TODO 调用北京接口时再去做判断，如果申请新韩卡成功再把护照信息存入
            //==============把护照信息存入到用户基本信息表====================
            Long UserInfoId = tKoreaApply.gettUserInfoId();
            TUserInfo tUserInfo = getTUserInfoByUserId(UserInfoId);
            tUserInfo.setPassportNo(tKoreaApply.getPassportNo());
            tUserInfo.setLssueDate(tKoreaApply.getLssueDate());
            tUserInfo.setExpirationDate(tKoreaApply.getExpirationDate());
            tUserInfo.setImgFileUrl(tKoreaApply.getImgFileUrl());
            tUserInfoMapper.updateByPrimaryKeySelective(tUserInfo);

            //================存入到新韩卡基本信息表============================
            TBankBase tBankBase = new TBankBase();
            String bankCode = apiCommProperties.getKoreaBankCode();
            TBankBaseExample tBankBaseExample = new TBankBaseExample();
            tBankBaseExample.createCriteria().andBankCodeEqualTo(bankCode);
            List<TBankBase> tBankBaseList = tBankBaseMapper.selectByExample(tBankBaseExample);
            if(tBankBaseList == null) {
                return resultBo.reqNotFull();
            }

            Long baseId = tBankBaseList.get(0).getId();
//            TKoreaInfo tKoreaInfo = getTKoreaInfoByUserId(tUserInfoId);
            TKoreaInfo tKoreaInfo = new TKoreaInfo();
            tKoreaInfo.settUserInfoId(tUserInfoId);
            tKoreaInfo.settBankBaseId(baseId);
            //TODO 调用北京接口返回新韩卡卡号 暂时使用随机数模拟
//=================调北京接口，返回新韩卡卡号=====================
            tKoreaInfo.setKoreaCardNo(RandomUtil.RandomNumber(11));
            tKoreaInfo.setKoreaCardType("prepaid");
            tKoreaInfo.setKoreaCardTime(new Date());
            tKoreaInfo.setKoreaCardTotalAmount("0.00");
            tKoreaInfo.setKoreaCardUsedAmount("0.00");
            tKoreaInfo.setKoreaCardFreeAmount("0.00");
            tKoreaInfo.setKoreaCardRetreatKoreaAmount("0.00");
            tKoreaInfo.setKoreaCardRetreatChAmount("0.00");
            //待激活-beforeactivate ；已激活-afteractivate ；已挂失-afterloss
            tKoreaInfo.setKoreaCardStatus("beforeactivate");
            tKoreaInfo.setUpdatetime(new Date());
            //0-未删除 1-已删除
            tKoreaInfo.setIsDelete(Short.valueOf("0"));
            tKoreaInfoMapper.insertSelective(tKoreaInfo);
            ResultBody resultBody = new ResultBody();
//            resultBody.put("koreaCardId", tKoreaInfo.gettUserInfoId());
//            resultBody.put("tBankBaseId", baseId);
            resultBody.put("koreaCardNo", MaskCodeUtil.maskBankCard(tKoreaInfo.getKoreaCardNo()));
//            resultBody.put("cardType", tKoreaInfo.getKoreaCardType());
//            resultBody.put("cardTime", tKoreaInfo.getKoreaCardTime());
//            resultBody.put("totalAmount", tKoreaInfo.getKoreaCardTotalAmount());
//            resultBody.put("usedAmount", tKoreaInfo.getKoreaCardUsedAmount());
//            resultBody.put("freeAmount", tKoreaInfo.getKoreaCardFreeAmount());
//            resultBody.put("retreatKoreaAmount", tKoreaInfo.getKoreaCardRetreatKoreaAmount());
//            resultBody.put("retreatChAmount", tKoreaInfo.getKoreaCardRetreatChAmount());
//            resultBody.put("koreaCardState", tKoreaInfo.getKoreaCardStatus());
//            resultBody.put("updateTime", tKoreaInfo.getUpdatetime());
//            resultBody.put("isDelete", tKoreaInfo.getIsDelete());
            resultBo.setResultBody(resultBody);
        }catch(Exception e){
            e.printStackTrace();
            resultBo.error(ErrorResult.APPLYKOREACARD_FAIL);
            log.error("applyKoreaCard",e);
            throw e;
        }finally{
            return resultBo;
        }

    }
    /**
     * 通过用户ID获取 用户基本信息 t_User_Info
     * shihaikun
     * @param UserInfoId
     * @return
     */
    private TUserInfo getTUserInfoByUserId(Long UserInfoId){
        TUserInfoExample tUserInfoExample = new TUserInfoExample();
        tUserInfoExample.createCriteria().andIdEqualTo(UserInfoId);//用户ID
        List<TUserInfo> tUserInfoList = tUserInfoMapper.selectByExample(tUserInfoExample);
        TUserInfo tUserInfo = new TUserInfo();
        if(null != tUserInfoList &&  0 < tUserInfoList.size() ){
            tUserInfo = tUserInfoList.get(0);
        }
        return tUserInfo;

    }

    /**
     * 通过用户ID获取 新韩卡基本信息 t_korea_info
     * shihaikun
     * @param
     * @return
     */
    private TKoreaInfo getTKoreaInfoById(Long tUserInfoId,Long koreaCardId) throws Exception{
        TKoreaInfoExample example = new TKoreaInfoExample();
        example.createCriteria().andIdEqualTo(Long.valueOf(koreaCardId)).andTUserInfoIdEqualTo(tUserInfoId);
        List<TKoreaInfo> list = tKoreaInfoMapper.selectByExample(example);
        if(null != list && 0 < list.size()){
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过用户ID获取 银行基础信息 t_bank_base
     * shihaikun
     * @param
     * @return
     */
    private TBankBase getTBankBaseById(Long id){
        return tBankBaseMapper.selectByPrimaryKey(id);
    }
// ========================================= shihaikun end 申请新韩卡=================================================
// ========================================= shihaikun start 获取新韩卡详情=================================================
    /**
     * @param requestBo
     * @Author shihaikun
     * @MethodName 获取新韩卡详情
     * @Params
     * @Return
     * @Date 2017/11/15 上午10:31
     * @Desp
     */
    @Override
    public ResultBo getKoreaCardDetail(RequestBo requestBo) throws Exception {
        //去redis中取用户登陆后填写的申请新韩卡数据
        ResultBo resultBo = ResultBo.build();
        try {
            LoginUserBo userInRedis = iLoginService.getUserInRedis(requestBo.getPacketHead().getToken());

            Long tUserInfoId = Long.valueOf(userInRedis.gettUserInfoId());
            //           Long tUserInfoId = Long.valueOf("1");
            String packetBody = requestBo.getPacketBody();//获取json串
            JSONObject jsonObject = JSON.parseObject(packetBody);

            if(jsonObject == null){
                return resultBo.reqNotFull();
            }
            Long koreaCardId =Long.valueOf(jsonObject.getString("koreaCardId"));//获取id

            if (jsonObject.containsKey("koreaCardId")&&(jsonObject.getString("koreaCardId").equals("") || StringUtils.isBlank(jsonObject.getString("koreaCardId")))) {
                return resultBo.reqNotFull();
            }
            //通过用户id查询新韩卡详细信息
            TKoreaInfo tKoreaInfo = getTKoreaInfoById(tUserInfoId,koreaCardId);
            if (null == tKoreaInfo) {
                return resultBo.reqNotFull();
            }
//            resultBo.setResultHead(ResultBo.Status.SUCCESS);
            ResultBody resultBody = new ResultBody();
            resultBody.put("koreaCardId", tKoreaInfo.getId() + "");
            resultBody.put("tBankBaseId", tKoreaInfo.gettBankBaseId() + "");
            resultBody.put("koreaCardno", MaskCodeUtil.maskBankCard(tKoreaInfo.getKoreaCardNo()));
            resultBody.put("koreaCardType", tKoreaInfo.getKoreaCardType());
            resultBody.put("koreaCardUsedAmount", tKoreaInfo.getKoreaCardUsedAmount());
            resultBody.put("koreaCardFreeAmount", tKoreaInfo.getKoreaCardFreeAmount());
            resultBody.put("koreaCardRetreatKoreaAmount", tKoreaInfo.getKoreaCardRetreatKoreaAmount());
            resultBody.put("koreaCardTotalAmount", tKoreaInfo.getKoreaCardTotalAmount());
            resultBody.put("koreaCardRetreatChAmount", tKoreaInfo.getKoreaCardRetreatChAmount());
            resultBody.put("koreaCardState", tKoreaInfo.getKoreaCardStatus());
            //logo URL
            resultBody.put("koreaCardLogoUrl", getTBankBaseById(tKoreaInfo.gettBankBaseId()).getBankLogoUrl());

            resultBo.setResultBody(resultBody);
        }catch(Exception e){
            e.printStackTrace();
            resultBo.error(ErrorResult.GETKOREAINFO_FAIL);
            log.error("getKoreaCardDetail",e);
            throw e;
        }finally{
            return resultBo;
        }

    }



// ========================================= shihaikun  end 获取新韩卡详情=====================================================

// ========================================= liuxiaoming  start 新韩卡充值========================================
    /**
     * @param requestBo
     * @Author liuxiaoming
     * @MethodName 新韩卡充值
     * bankcard-银行卡充值：先账户充值（考虑银行单笔限额 和 单日限额），再走账户充值
     *
     * acctcharge-账户充值： 向新韩卡充值
     *
     * `pay_type`  walletfrozen-账户冻结
     * `charge_type` 'bankcard-银行卡充值 acctcharge-账户余额充值'
     * `t_korea_transact`
     * `pay_type` 'koreacharge-新韩卡充值
     *
     *  账户银行卡信息 t_account_bank

    用户 t_user_info_id
    银行卡号 bank_card_no
    单日限额 day_limit         与 账户交易记录      t_account_transact
    单笔限额 single_limit      与 账户交易记录      t_account_transact
    账户信息表 （修改）
    新韩卡信息表 （修改）
    新增 账户交易记录      t_account_transact
    新增 新韩卡交易流水表   t_korea_transact
     * @Params
     * @Return
     * @Date 2017/11/15 上午10:32
     * @Desp
     */
    @Override
    @Transactional
    public ResultBo chargeKoreaCard(RequestBo requestBo) throws Exception {
        log.info("新韩卡充值："+requestBo.toString());
        ResultBo resultBo =ResultBo.build();
        try{
            //去Redis中取用户信息 :取用户ID
            LoginUserBo userInRedis = iLoginService.getUserInRedis(requestBo.getPacketHead().getToken());

            //解析json字符串
            String packetBody = requestBo.getPacketBody();//获取json串

            JSONObject jsonObject = JSON.parseObject(packetBody);
            //把请求入参对象转为json字符串
            String jsonRequestBo = JSON.toJSON(requestBo).toString();

            if(!(jsonObject.containsKey("chargeType")) || StringUtils.isBlank(jsonObject.getString("chargeType"))
                    || !(jsonObject.containsKey("chargeAmount")) || StringUtils.isBlank(jsonObject.getString("chargeAmount"))){
               //请求参数不完整
                return resultBo.reqNotFull();
            }


            String chargeType = jsonObject.getString("chargeType");//充值类型 必填(acctcharge-账户充值,bankcard-银行卡充值)
            String chargeAmount = jsonObject.getString("chargeAmount");// 充值金额


            switch (chargeType)
            {
                case "acctcharge":
                    //账户充值
                    resultBo =acctOrBankCardCharge(userInRedis, chargeAmount,"acctcharge", jsonRequestBo,"", resultBo);

                    break;
                case "bankcard":
                    //银行卡充值
                    String bankCardId = jsonObject.getString("bankCardId");//银行卡Id
                    resultBo = acctOrBankCardCharge(userInRedis, chargeAmount, "bankcard", jsonRequestBo,bankCardId, resultBo);

                    break;
                default:
                    resultBo.error(ErrorResult.CHARGETYPE_ERROR); //充值类型错误
            }

        }catch (Exception e){
            e.printStackTrace();
            resultBo.error(ErrorResult.CHARGE_ERROR); //充值失败
            log.error("chargeKoreaCard",e);
            throw e;
        }
            return resultBo;


    }

    /**
     * 账户充值 bankCardId="" / 银行卡充值
     * liuxiaoming
     */
    public ResultBo acctOrBankCardCharge(LoginUserBo userInRedis,String chargeAmount,String chargeType,String jsonRequestBo,String bankCardId,ResultBo resultBo) throws Exception{

        //查询汇率信息
        TCurrencyRate tCurrencyRate = indexService.getTCurrencyRateBy("korea");
        String koreaAmount = chargeAmount; //韩元
        String chAmount = String.valueOf(DecimalFormatUtil.div(DecimalFormatUtil.toBigDecimal(chargeAmount),DecimalFormatUtil.toBigDecimal(tCurrencyRate.getRate())));    //人民币

        //判断是否为银行卡充值
        TAccountBank tAccountBank = new TAccountBank();
        TBankBase baseBank = new TBankBase();
        if("bankcard".equals(chargeType)){
            //银行卡充值进行的查询
            //通过用户ID查询 账户银行卡信息 t_account_bank
            tAccountBank = indexService.getTAccountBankByUserIdAndBankCardId(userInRedis.gettUserInfoId(), bankCardId);
            if(null  == tAccountBank){
                //绑定银行卡不存在
                log.error("【新韩卡充值失败】失败原因："+ErrorResult.ACCOUNTBANK_NOTEXIST.getMsg());
                return resultBo.error(ErrorResult.ACCOUNTBANK_NOTEXIST);
            }
            //判断 t_account_bank :
            //      银行卡类型 `bank_card_type` :'savings-储值卡 credit-信用卡'
            if(tAccountBank.getBankCardType().equals("credit")){
                //信用卡 不能进行新韩卡充值
                log.error("【新韩卡充值失败】失败原因："+ErrorResult.BANKCARDTYPE_CREDIT.getMsg());
                return resultBo.error(ErrorResult.BANKCARDTYPE_CREDIT);
            }
            //银行基础表的查询
             baseBank = tBankBaseMapper.selectByPrimaryKey(tAccountBank.gettBankBaseId());
            if(null == baseBank){
                //银行基础表查询为空
                log.error("【新韩卡充值失败】失败原因："+ErrorResult.BASEBANK_NOTEXIST.getMsg());
                return resultBo.error(ErrorResult.BASEBANK_NOTEXIST);
            }


            //单笔限额
            //      单笔限额 single_limit   充值金额 <= 单笔限额
            //      单笔限额为-1 代表不限额
            if(!"-1".equals(baseBank.getSingleLimit())
                    && DecimalFormatUtil.compareTo(chAmount,baseBank.getSingleLimit()) == 1){
                //充值金额不能大于单笔限额
                log.error("【新韩卡充值失败】失败原因："+ErrorResult.CHARGEAMOUNT_UPPER_SINGLELIMIT.getMsg());
                return resultBo.error(ErrorResult.CHARGEAMOUNT_UPPER_SINGLELIMIT);
            }


            //单日限额
            String rechargedAmount = valOpts.get("day_limit-" + tAccountBank.getBankCardNo());//从redis中取出的已充值金额
            //判断充值金额是否超过单日限额
            if (StringUtils.isBlank(rechargedAmount)) {
                Date date = new Date();
                valOpts.set("day_limit-" + tAccountBank.getBankCardNo(), "0.00",
                        DateUtil.endOfDay(date).getTime() - date.getTime(), TimeUnit.MILLISECONDS);
            }
            String dayLimit = baseBank.getDayLimit();//单日限额
            //      单日限额 day_limit      充值金额 + 当天消费总金额 <= 单日限额
            //      单日限额为-1 代表不限额
            //判断充值金额是否超过单日剩余可充值金额（单日限额-已充值金额）
            if (!"-1".equals(baseBank.getDayLimit())
                    && (MoneyUtil.moneyComp(chAmount, MoneyUtil.moneySub(dayLimit,
                    valOpts.get("day_limit-" + tAccountBank.getBankCardNo()))))) {
//                resultBo.setResultHead("-1", "充值已超过单日限额！");
//                return resultBo;
                log.error("【新韩卡充值失败】失败原因："+ErrorResult.CHARGEAMOUNT_UPPER_DAYLIMIT.getMsg());
                return resultBo.error(ErrorResult.CHARGEAMOUNT_UPPER_DAYLIMIT);
            }
        }


        //t_account_info
        //根据用户ID查询 用户账户表  t_account_info
        TAccountInfo tAccountInfo = indexService.getTAccountInfoByUserId(userInRedis.gettUserInfoId());
        if(null == tAccountInfo){
            log.error("【新韩卡充值失败】失败原因："+ErrorResult.ACCOUNTINFO_NOTEXIST.getMsg());
            return resultBo.error(ErrorResult.ACCOUNTINFO_NOTEXIST);//账户信息不存在
        }
        if(!"bankcard".equals(chargeType)){
            //判断 充值金额 < = 账户 可用余额
            if(null == tAccountInfo.getFreeAmount() || DecimalFormatUtil.compareTo(chAmount,tAccountInfo.getFreeAmount()) == 1){
                log.error("【新韩卡充值失败】失败原因："+ErrorResult.CHARGEAMOUNT_UPPER_FREEAMOUNT.getMsg());
                return resultBo.error(ErrorResult.CHARGEAMOUNT_UPPER_FREEAMOUNT);//充值金额大于可用余额
            }
        }


        //根据用户ID查询 新韩卡用户信息表
        TKoreaInfo tKoreaInfo = indexService.getTKoreaInfoByUserId(userInRedis.gettUserInfoId());
        if(null == tKoreaInfo){
            log.error("【新韩卡充值失败】失败原因："+ErrorResult.KOREAINFO_NOTEXIST.getMsg());
            return resultBo.error(ErrorResult.KOREAINFO_NOTEXIST);//新韩卡用户信息不存在
        }
        //判断新韩卡状态 `korea_card_status` '待激活-beforeactivate ；已激活-afteractivate ；已挂失-afterloss',
        //判断新韩卡是否已删除 `is_delete` '0-未删除 1-已删除',
        if(!tKoreaInfo.getKoreaCardStatus().equals("afteractivate") || tKoreaInfo.getIsDelete().equals(1)){
            log.error("【新韩卡充值失败】失败原因："+ErrorResult.KOREA_CARD_STATUS_ERROR.getMsg());
            return resultBo.error(ErrorResult.KOREA_CARD_STATUS_ERROR);
        }

        TUserInfo tUserInfo = tUserInfoMapper.selectByPrimaryKey(userInRedis.gettUserInfoId());
        //订单号
        String orderNo = OrderNoUtil.getOrderNo(OrderEnum.WALLETFROZEN);//账户到新韩卡
        String orderNoBank =  OrderNoUtil.getOrderNo(OrderEnum.BANTRANSTER);//银行到账户



        //修改 用户账户表 t_account_info
        int accountInfoUpdate = this.updateTAccountInfo(tAccountInfo,chAmount,chargeType);
        if(accountInfoUpdate == 0){
            log.error("【新韩卡充值失败】订单号："+orderNo+" 新韩卡充值失败，失败原因：修改用户账户原可用金额与修改时可用金额不一致");
            throw new RuntimeException("充值失败");
        }
        //若为银行卡充值
        //  1.账户交易记录新增一条            从银行卡新增到账户的交易流水
        //  2.账户交易记录 与 新韩卡交易记录的 订单号不一致 需要调两次订单号生成的方法：OrderNoUtil.getOrderNo(OrderEnum.WALLETFROZEN) 若为账户充值则订单号一致，调一次订单号生成方法

        if("bankcard".equals(chargeType)){
          //银行卡到账户充值订单号 OrderNoUtil.getOrderNo(OrderEnum.BANTRANSTER)
            this.insertBankToAccount(chAmount, tAccountBank, tUserInfo, chargeType, userInRedis, baseBank, orderNoBank, tKoreaInfo);
        }
        //新增  账户交易记录 t_account_transact        从账户到新韩卡的交易流水
        this.insertTAccountTransact(userInRedis,"",chAmount, "walletfrozen",tAccountBank, baseBank, orderNo, tKoreaInfo);
        //修改 新韩卡基本信息 t_korea_info
        int koreaInfoUpdate= this.updateTKoreaInfo(tKoreaInfo,koreaAmount,chAmount);
        if(koreaInfoUpdate == 0){
            log.error("【新韩卡充值失败】订单号："+orderNo+" 新韩卡充值失败，失败原因：修改新韩卡账户原可用金额与修改时可用金额不一致");
            throw new RuntimeException("充值失败");
        }
        //新增 新韩卡交易记录 t_korea_transact
        //TODO 交易完成时间 ordertime 后期调新韩卡接口返回的时间   暂时为new Date()
        Date date = new Date();
        this.insertTKoreaTransact(userInRedis, tKoreaInfo, koreaAmount, tCurrencyRate, jsonRequestBo, orderNo,date);

        if("bankcard".equals(chargeType)) {
            //调用会员充值接口
            chargeToAccount(chAmount, tAccountBank, tUserInfo, orderNoBank);
        }
        return parseChargeSucessMap(resultBo, orderNo, tKoreaInfo, date, chAmount, koreaAmount);
    }

    /**
     * 修改 用户账户表 t_account_info 可用余额 减少  冻结金额 增加   更新时间
     * liuxiaoming
     */
    public int updateTAccountInfo(TAccountInfo tAccountInfo,String chAmount,String chargeType) throws Exception{

        TAccountInfoExample example = new TAccountInfoExample();
        TAccountInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(tAccountInfo.getId());
        criteria.andFreeAmountEqualTo(tAccountInfo.getFreeAmount());

        TAccountInfo tAccountInfoUpdate = new TAccountInfo();
        tAccountInfoUpdate.setFrozenAmount(DecimalFormatUtil.addStr(tAccountInfo.getFrozenAmount(),chAmount).toString());
        tAccountInfoUpdate.setUpdatetime(new Date());
        if(!"bankcard".equals(chargeType)){
            //      账户充值     可用余额 减少  冻结金额 增加   总金额 不变
            tAccountInfoUpdate.setFreeAmount(DecimalFormatUtil.subStr(tAccountInfo.getFreeAmount(),chAmount).toString());
        }else{
            //      银行卡充值   可用余额 不变   冻结金额 增加  总金额 增加
            tAccountInfoUpdate.setAmount(DecimalFormatUtil.addStr(tAccountInfo.getAmount(),chAmount).toString());
        }

        return tAccountInfoMapper.updateByExampleSelective(tAccountInfoUpdate,example);

    }

    /**
     * 调用会员充值接口
     */
    public void chargeToAccount(String chAmount, TAccountBank tAccountBank, TUserInfo tUserInfo,String orderNo){
        //封装银行卡支付接口需要的参数
        ReqCardPayBO reqCardPayBO = new ReqCardPayBO();
        reqCardPayBO.setAmount(chAmount);//订单总金额
        reqCardPayBO.setMoneyType(0);//支付币种========================
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String ymdHms = format.format(date);
        String ymd = ymdHms.substring(0, 8);//格式为：yyyyMMdd
        //订单编号(格式为订单生成日期-商户编号-商户流水号;商户流水号格式：日期年月日时分秒+8位随机数)
        reqCardPayBO.setOid(ymd + "-" + mid + "-" + ymdHms + RandomUtil.RandomNumber(8));
        reqCardPayBO.setTokenId(tAccountBank.getTokenid());//TOKEN 编号
        reqCardPayBO.setMoneyType(0);
        reqCardPayBO.setUserref(tUserInfo.getUserref());//客户唯一标识
        reqCardPayBO.setYmd(ymd);//订单产生日期
        reqCardPayBO.setMid(mid);//商户号
        reqCardPayBO.setKey(key);//md5的key
        reqCardPayBO.setUrl(cardPayUrl);//充值接口的请求地址
        //reqCardPayBO.setUrl("http://210.73.90.235/customer/shinhancard/shinhan_payment_pay_direct.jsp");//充值接口的请求地址
        RspResultBO rspResultBO = iFastPaymentService.cardPay(reqCardPayBO);
        //银行卡充值成功后向Redis中增加消费金额
        if ("0".equals(rspResultBO.getCode())) {
            //将充值成功的金额与在redis中存的充值金额相加后，再次存入redis，作为当天的已充值金额
            valOpts.set("day_limit-" + tAccountBank.getBankCardNo(), MoneyUtil.moneyAdd(valOpts.get("day_limit-" + tAccountBank.getBankCardNo()), chAmount),
                    DateUtil.endOfDay(date).getTime() - date.getTime(), TimeUnit.MILLISECONDS);
        } else {
            log.error("【新韩卡充值失败】订单号："+orderNo+" 新韩卡充值失败，失败原因：调用会员充值接口返回值中订单状态失败，返回值状态码："+rspResultBO.getCode());
            throw new RuntimeException("充值失败");
        }
    }

    /**
     * 新增账户交易记录 银行充值到账户
     */
    public void insertBankToAccount(String chAmount, TAccountBank tAccountBank, TUserInfo tUserInfo, String chargeType,LoginUserBo userInRedis,TBankBase baseBank,String orderNo,TKoreaInfo tKoreaInfo) throws Exception{

            this.insertTAccountTransact(userInRedis,chargeType,chAmount, "banktransfer",tAccountBank, baseBank, orderNo, tKoreaInfo);
    }
    /**
     * 新增  账户交易记录 t_account_transact  账户到新韩卡
     * liuxiaoming
     */
    public void insertTAccountTransact(LoginUserBo userInRedis,String chargeType,String chAmount, String payType,TAccountBank tAccountBank,TBankBase baseBank,String orderNo,TKoreaInfo tKoreaInfo) throws Exception{
        TAccountTransact tAccountTransact = new TAccountTransact();
        tAccountTransact.settUserInfoId(userInRedis.gettUserInfoId()); // 用户id t_user_info_id
        if("bankcard".equals(chargeType)){
            //银行卡充值:  银行卡卡号：银行卡号
            tAccountTransact.setBankCardNo(tAccountBank.getBankCardNo());// 银行卡号 bank_card_no
            tAccountTransact.settBankBaseId(tAccountBank.gettBankBaseId());// 银行卡基础信息id  t_bank_base_id
            tAccountTransact.setBankCardType(tAccountBank.getBankCardType());// 银行卡类型 bank_card_type
            tAccountTransact.setSingleLimit(baseBank.getSingleLimit());// 单笔限额 single_limit
            tAccountTransact.setDayLimit(baseBank.getDayLimit());// 单日限额 day_limit
            tAccountTransact.setChargeType("bankcard");// 充值类型 charge_type 'bankcard-银行卡充值
        }else{
            //账户充值： 银行卡卡号：新韩卡卡号
            tAccountTransact.setBankCardNo(tKoreaInfo.getKoreaCardNo());// 新韩卡卡号
            tAccountTransact.settBankBaseId(tKoreaInfo.gettBankBaseId());//新韩卡baseID
            tAccountTransact.setBankCardType("wallet");
            tAccountTransact.setChargeType("acctcharge");// 充值类型 charge_type acctcharge-账户余额充值'
        }
        tAccountTransact.setTransactNo(orderNo);//流水号 transact_no
        tAccountTransact.setMobilePhone(userInRedis.getMobilePhone());//手机号 mobile_phone
        if(payType.equals("banktransfer")){
            tAccountTransact.setPayType("banktransfer");//支付类型 `pay_type`  'banktransfer-银行转出
        }else{
            tAccountTransact.setPayType("walletfrozen");//支付类型 pay_type  walletfrozen-账户冻结
        }

        tAccountTransact.setBillAmount(chAmount);//账单金额 bill_amount         = original_amount
        tAccountTransact.setOriginalAmount(chAmount);//账单原金额 original_amount
        tAccountTransact.setSaleAmount("0");//优惠金额 sale_amount 0
        //商品详情 goods_info  X
        //收款商户名 merchant_name X
        //商户号 merchant_no X
        tAccountTransact.setOrdertime(new Date());//交易完成时间 ordertime 后期调新韩卡接口返回的时间   暂时为new Date()
        tAccountTransactMapper.insertSelective(tAccountTransact);
    }

    /**
     * 修改 新韩卡基本信息 t_korea_info (涉及到汇率 查询汇率表)
     * liuxiaoming
     */
    public int updateTKoreaInfo(TKoreaInfo tKoreaInfo,String koreaAmount,String chAmount) throws Exception{
        TKoreaInfoExample example = new TKoreaInfoExample();
        TKoreaInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(tKoreaInfo.getId());
        criteria.andKoreaCardFreeAmountEqualTo(tKoreaInfo.getKoreaCardFreeAmount());

        //已用额度 korea_card_used_amount X
        TKoreaInfo tKoreaInfoUpdate = new TKoreaInfo();
        tKoreaInfoUpdate.setKoreaCardFreeAmount(DecimalFormatUtil.addStr(tKoreaInfo.getKoreaCardFreeAmount(),koreaAmount).toString());//可用额度 korea_card_free_amount（汇率换算）
        tKoreaInfoUpdate.setKoreaCardTotalAmount(DecimalFormatUtil.addStr(tKoreaInfo.getKoreaCardTotalAmount(),koreaAmount).toString());//新韩卡总额度 korea_card_total_amount  已用+可用=总额度
        tKoreaInfoUpdate.setKoreaCardRetreatKoreaAmount(tKoreaInfoUpdate.getKoreaCardFreeAmount());//退款韩元额度 korea_card_retreat_korea_amount
        tKoreaInfoUpdate.setKoreaCardRetreatChAmount(DecimalFormatUtil.addStr(tKoreaInfo.getKoreaCardRetreatChAmount(),chAmount).toString());//可退款人民币额度 korea_card_retreat_ch_amount 人民币
        tKoreaInfoUpdate.setUpdatetime(new Date());//更新时间 updatetime
        return tKoreaInfoMapper.updateByExampleSelective(tKoreaInfoUpdate,example);
    }

    /**
     * 新增 新韩卡交易记录 t_korea_transact
     * liuxiaoming
     */
    public void insertTKoreaTransact(LoginUserBo userInRedis,TKoreaInfo tKoreaInfo,String koreaAmount,TCurrencyRate tCurrencyRate,String jsonRequestBo,String orderNo,Date date) throws Exception{
        TKoreaTransact tKoreaTransact = new TKoreaTransact();
        tKoreaTransact.settUserInfoId(userInRedis.gettUserInfoId());//用户id t_user_info_id
//        if("bankcard".equals(chargeType)){
//            //银行卡基础信息id t_bank_base_id  银行卡充值 ：
//            tKoreaTransact.settBankBaseId(tAccountBank.gettBankBaseId());
//        }else{
//            //                                账户充值
//            tKoreaTransact.settBankBaseId(Long.valueOf("0"));
//        }
        tKoreaTransact.settBankBaseId(tKoreaInfo.gettBankBaseId());
        tKoreaTransact.setTransactNo(orderNo);//订单号 transact_no
        tKoreaTransact.setKoreaCardNo(tKoreaInfo.getKoreaCardNo());//新韩卡号 korea_card_no
        //收款商户名 merchant_name X
        //商户号 merchant_no X
        tKoreaTransact.setPayType("koreacharge");//支付类型 pay_type 'koreacharge-新韩卡充值
        tKoreaTransact.setBillAmount(koreaAmount);//账单金额 bill_amount   = original_amount 韩元
        tKoreaTransact.setOriginalAmount(tKoreaTransact.getBillAmount());//账单原金额 original_amount
        tKoreaTransact.setSaleAmount("0");//优惠金额 sale_amount
        //商品详情 goods_info X
        tKoreaTransact.setOrdertime(date);
        tKoreaTransact.setRate(tCurrencyRate.getRate());//对应汇率 rate 查询汇率表
        tKoreaTransact.setRequestContent(jsonRequestBo);//请求入参 request_content
        tKoreaTransactMapper.insertSelective(tKoreaTransact);
    }

    /**
     * 支付成功后返回的resultbody
     * liuxiaoming
     */
    private ResultBo parseChargeSucessMap(ResultBo resultBo,String orderNo,TKoreaInfo tKoreaInfo,Date date,String chAmount,String koreaAmount) throws Exception{
        ResultBody resultBody = new ResultBody();
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("payMethod", "新韩卡(" + String.valueOf(MaskCodeUtil.maskBankCard(tKoreaInfo.getKoreaCardNo())) + ")" );// 新韩卡号(取后四位)
        map.put("payType","koreacharge");
        map.put("payTime",com.payease.wallet.dto.utils.DateUtil.formatNewDatetoString(date));// 支付时间格式 新韩卡交易记录中的订单时间
        map.put("orderNo",orderNo);// 暂设置为 （账户到新韩卡订单号）
        map.put("koreaAmount",koreaAmount);//韩元 充值金额
        map.put("chAmount",chAmount);//人民币 充值金额
        map.put("chargeStatus","success");
        resultBody.putAll(map);
        resultBo.setResultBody(resultBody);
        return resultBo;
    }

//    private Long getbaseId(){
//        TBankBase tBankBase = new TBankBase();
//        String bankCode = apiCommProperties.getKoreaBankCode();
//        TBankBaseExample tBankBaseExample = new TBankBaseExample();
//        tBankBaseExample.createCriteria().andBankCodeEqualTo(bankCode);
//        List<TBankBase> tBankBaseList = tBankBaseMapper.selectByExample(tBankBaseExample);
//        return tBankBaseList.get(0).getId();
//    }
// ========================================= liuxiaoming  end 新韩卡充值========================================





// ========================================= zhoushijie  start 激活挂失新韩卡=================================================


    /**
     * @param requestBo
     * @Author zhoushijie
     * @MethodName 激活挂失新韩卡
     * @Params
     * @Return
     * @Date 2017/11/15 上午10:32
     * @Desp
     */
    @Override
    public ResultBo activateOrLossKoreaCard(RequestBo requestBo) throws Exception {
        ResultBo resultBo=ResultBo.build();

        try {
            LoginUserBo userInRedis = getTokenId(resultBo,requestBo); //通过packetHead从tonken获取用户Id
            Long userInfoId =Long.valueOf(userInRedis.gettUserInfoId());//获取redis中的用户信息
            //获取json串中得到值
            String packetBody = requestBo.getPacketBody();//获取json串
            JSONObject jsonObject = JSON.parseObject(packetBody);
            String foreignCardId =jsonObject.getString("foreignCardId");//获取id
            String operationType = jsonObject.getString("operationType");//请求状态activate-激活 loss-挂失
            String foreignCardType = jsonObject.getString("foreignCardType");//获取卡类型
            if(StringUtils.isBlank(foreignCardId)){
                return resultBo.fail().error(ErrorResult.FOREIGNCARDIDISNULL);//卡Id获取失败
            }

            //卡类型 foreignCardType : 必填 目前只有koreacard-新韩卡
            if(!("koreacard".equals(foreignCardType))){//新韩卡不等卡类型将返回失败
                return resultBo.fail().error(ErrorResult.FOREIGNCARDTYPEISNULLKOREACARD);//新韩卡返回卡类型不符返回失败
            }
            //得到新韩卡基本信息表中的卡id和用户id
            TKoreaInfo tKoreaInfo = this.getTKoreaInfoByUserIdAndId(userInfoId, foreignCardId);
            if(tKoreaInfo == null){
                return resultBo.fail().error(ErrorResult.TKOREAINFOISNULL);//新韩卡基本信息表是空
            }
            // 判断新韩卡状态 '待激活-beforeactivate ；已激活-afteractivate ；已挂失-afterloss',
            String type = getkoreaCardStatus(operationType);

            tKoreaInfo.setKoreaCardStatus(type);//将判断返回的类型添加
            tKoreaInfo.setUpdatetime(new Date());//加时间锉
            tKoreaInfoMapper.updateByPrimaryKeySelective(tKoreaInfo);//将数据更改到数据库中
        } catch (Exception e) {
            log.error("activateOrLossKoreaCard",e);
            resultBo.fail();
            e.printStackTrace();
            throw e;
        } finally {
            return  resultBo;
        }

    }

    /**
     *得到新韩卡基本信息表中的卡id和用户id
     * @param userInfoId
     * @param foreignCardId
     * @return
     */
    private TKoreaInfo getTKoreaInfoByUserIdAndId(Long userInfoId,String foreignCardId) throws Exception{
        TKoreaInfoExample example = new TKoreaInfoExample();
        //添加卡id和用户id
        example.createCriteria().andIdEqualTo(Long.valueOf(foreignCardId)).andTUserInfoIdEqualTo(userInfoId);
        List<TKoreaInfo> list = tKoreaInfoMapper.selectByExample(example);
        if(null != list && 0 < list.size()){//list的下标大于1(有数据)
            return list.get(0);//list的中第一条数据
        }
        return null;//没有返回空
    }

    /**
     * 判断新韩卡状态     '待激活-beforeactivate ；已激活-afteractivate ；已挂失-afterloss',
     * @param operationType
     * @return
     * @throws Exception
     */
    private String getkoreaCardStatus(String operationType) throws Exception{
        String type="beforeactivate";//默认为待激活
        if("activate".equals(operationType)){
            type="afteractivate";
        }else if("loss".equals(operationType)){
            type="afterloss";
        }
        return type;//返回获得的状态
    }

    /**
     * 获取token中的用户id
     * @param requestBo
     * @return
     * @throws Exception
     */
    private LoginUserBo getTokenId(ResultBo resultBo,RequestBo requestBo) throws Exception{
        String token = requestBo.getPacketHead().getToken();//通过packetHead从tonken获取用户Id
        LoginUserBo userInRedis = iLoginService.getUserInRedis(token);//获取redis中的token
        if(null==userInRedis){
            resultBo.tokenTimeOut();//没有得到token返回失败
        }
        return userInRedis;
    }

// ========================================= zhoushijie  end 激活挂失新韩卡=================================================
// ========================================= zhengqiangwei  start 新韩卡退款=================================================
    /**
     * @param requestBo
     * @Author zhangwen
     * @MethodName 新韩卡退款主方法
     * @Params
     * @Return
     * @Date 2017/11/15 上午10:32
     * @Desp
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public ResultBo refundKoreCard(RequestBo requestBo) throws Exception {
        ResultBo result = ResultBo.build();
        Map<String,Object> myboda = this.getmybo(requestBo);
        try {
            if (myboda!=null || !myboda.isEmpty()) {
                LoginUserBo userBo = (LoginUserBo) myboda.get("userBo");
                //在账户交易记录表插入交易记录
                TAccountTransact tarccout = this.getTAccountTransactdo(myboda);
                TKoreaTransact tKoreaT = this.getTKoreaTransactdo(myboda);
                tAccountTransactMapper.insertSelective(tarccout);
                tKoreaTransactMapper.insertSelective(tKoreaT);   // 在账户交易表和新韩卡交易表中插入记录
                TAccountInfo tAccounTo = this.getTAccountInfodo(myboda);
                TAccountInfoExample example = new TAccountInfoExample();
                example.createCriteria().andIdEqualTo(tAccounTo.getId()).andAmountEqualTo(tAccounTo.getAmount());
                tAccountInfoMapper.updateByExampleSelective(tAccounTo,example);  //修改数据表实际金额
                TKoreaInfo mtkO = this.getTKoreaInfodo(myboda);
                TKoreaInfoExample example1 = new TKoreaInfoExample();
                example1.createCriteria().andTUserInfoIdEqualTo(mtkO.gettUserInfoId()).andKoreaCardUsedAmountEqualTo(mtkO.getKoreaCardUsedAmount());
                tKoreaInfoMapper.updateByExampleSelective(mtkO,example1);  //修改新韩卡基本信息中的额度信息
                String succee = "success"; //t退款成功
                if (succee.equals("success")) {//判断退款成功

                    Map<String,Object> retaBean = this.getReturnAll(myboda);
                    ResultBody  Mybody = new ResultBody();
                    Mybody.putAll(retaBean);
                    result.setResultBody(Mybody);
                } else {
                    //退款失败
                    log.error("refundKoreCard error");
                    throw new  Exception();
                }
            }else {
                result.error(ErrorResult.LNCOMING_DATE_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.fail();
            log.error("refundKoreCard",e);
            throw e;
        }
        return  result;
    }
    /**
     * @param requestBo
     * @Author zqw
     * @MethodName  封装数据源
     * @Params
     * @Return
     * @Date 2017/11/15 上午10:32
     * @Desp
     */
    private Map<String, Object> getmybo(RequestBo requestBo) throws Exception {
        JSONObject json = JSON.parseObject(requestBo.getPacketBody());
        String foreignCardType = json.getString("foreignCardType");//目前默认为koreacard-新韩卡
        String foreignCardId = json.getString("foreignCardId");//卡id
        TKoreaInfo tkr = tKoreaInfoMapper.selectByPrimaryKey(Long.valueOf(foreignCardId));//卡号
        String foreignCard = tkr.getKoreaCardNo();
        Map<Object,Object> mapperall = this.getMapperList(foreignCard,requestBo);
        if (null!=mapperall&&!mapperall.isEmpty()) {
            LoginUserBo userBo = (LoginUserBo) mapperall.get("userBo");
            TUserInfo myUser = (TUserInfo) mapperall.get("myUser");// 用户基础信息表 myUser
            TAccountInfo tacc = (TAccountInfo) mapperall.get("tacc");
            //用户账户表信息  tacc
            TKoreaInfo koreaInfo = (TKoreaInfo) mapperall.get("koreaInfo"); //新韩卡基本信息  koreaInfo
            TCurrencyRate currRate = (TCurrencyRate) mapperall.get("currRate");//汇率表 currrate
            BigDecimal bd = new BigDecimal(tacc.getFrozenAmount());//将账户冻结金额转换为BigDecimal保持精度
            BigDecimal bda = new BigDecimal(tacc.getFreeAmount());//将可用余额转换为BigDecimal保持精度
            BigDecimal rt = new BigDecimal(currRate.getRate());//将汇率转换为BigDecimal保持精度
            String ranDom = OrderNoUtil.getOrderNo(OrderEnum.WALLETFREE);//随机生成银行卡流水
            String json1 = JSON.toJSONString(requestBo);//字符串
            Date mydate = new Date();
            Map<String, Object> myDataSources = new HashMap<String, Object>();
            myDataSources.put("myUser", myUser);
            myDataSources.put("tacc", tacc);
            myDataSources.put("koreaInfo", koreaInfo);
            myDataSources.put("currRate", currRate);
            myDataSources.put("userBo", userBo);
            myDataSources.put("foreignCardType", foreignCardType);
            myDataSources.put("foreignCard", foreignCard);
            myDataSources.put("bd", bd);
            myDataSources.put("bda", bda);
            myDataSources.put("rt", rt);
            myDataSources.put("ranDom", ranDom);
            myDataSources.put("json1", json1);
            myDataSources.put("mydate", mydate);
            return myDataSources;
        }else{
            return null;
        }
    }
    /**
     * @param
     * @Author zqw
     * @MethodName 封装表
     * @Params
     * @Return
     * @Date 2017/11/15 上午10:32
     * @Desp
     */
    private  TAccountTransact  getTAccountTransactdo(Map<String,Object> myboda)throws Exception{
        TUserInfo myUser =(TUserInfo)myboda.get("myUser");
        TAccountInfo tacc=(TAccountInfo)myboda.get("tacc");
        TKoreaInfo koreaInfo=(TKoreaInfo)myboda.get("koreaInfo");
        Date mydate=(Date)myboda.get("mydate");
        String ranDom=(String)myboda.get("ranDom");
        TAccountTransact tarccout = new TAccountTransact();
        tarccout.settUserInfoId(myUser.getId());//用户ID
        tarccout.settBankBaseId(koreaInfo.gettBankBaseId());//银行卡基础信息ID
        tarccout.setTransactNo(ranDom);//流水号
        tarccout.setBankCardNo(koreaInfo.getKoreaCardNo());//银行卡号
        tarccout.setMobilePhone(myUser.getMobilePhone());//手机号
        tarccout.setBankCardType("credit");//银行卡类型，目前写死
        //tarccout.setSingleLimit("20000");//单笔限额
        //tarccout.setDayLimit("50000");//单日限额
        tarccout.setPayType("walletfree");//支付类型，账户解冻
        tarccout.setBillAmount(tacc.getFrozenAmount());//账单金额,为冻结金额
        tarccout.setOriginalAmount(tacc.getFrozenAmount());//账单原金额
        tarccout.setSaleAmount("0");//优惠金额
        tarccout.setOrdertime(mydate);//交易完成时间
        tarccout.setCreatetime(mydate);//创建时间
        return tarccout;
    }


    /**
     * @param
     * @Author zqw
     * @MethodName 封装收据源
     * @Params
     * @Return
     * @Date 2017/11/15 上午10:32
     * @Desp
     */
    private  TKoreaTransact  getTKoreaTransactdo( Map<String,Object> myboda)throws Exception{
        TUserInfo myUser =(TUserInfo)myboda.get("myUser");
        TAccountInfo tacc=(TAccountInfo)myboda.get("tacc");
        TKoreaInfo koreaInfo=(TKoreaInfo)myboda.get("koreaInfo");
        TCurrencyRate currRate =(TCurrencyRate)myboda.get("currRate");
        Date mydate=(Date)myboda.get("mydate");
        String ranDom=(String)myboda.get("ranDom");
        String json1=(String)myboda.get("json1");
        BigDecimal bd = new BigDecimal(tacc.getFrozenAmount());//将账户冻结金额转换为BigDecimal保持精度
        BigDecimal bda = new BigDecimal(tacc.getFreeAmount());//将可用余额转换为BigDecimal保持精度
        BigDecimal rt = new BigDecimal(currRate.getRate());//将汇率转换为BigDecimal保持精度
        DecimalFormat df2 =new DecimalFormat("#.00");
        String bd1 =df2.format(bd);//切除余下位数只保留两位小数
        String rt1 =df2.format(bd.multiply(rt));//账单金额（韩元）
        TKoreaTransact tKoreaT = new TKoreaTransact();
        tKoreaT.settUserInfoId(myUser.getId());//用户ID
        tKoreaT.settBankBaseId(koreaInfo.gettBankBaseId());//银行卡基础信息ID
        tKoreaT.setTransactNo(ranDom);//流水号
        tKoreaT.setKoreaCardNo(koreaInfo.getKoreaCardNo());//银行卡号
        tKoreaT.setPayType("korearefund");//支付类型，新韩卡退款
        tKoreaT.setBillAmount(bd1);//账单金额
        tKoreaT.setOriginalAmount(bd1);//账单原金额
        tKoreaT.setSaleAmount("0");//优惠金额目前没有，存0
        tKoreaT.setOrdertime(mydate);//交易完成时间
        tKoreaT.setRate(currRate.getRate());//对应汇率
        tKoreaT.setRequestContent(json1);//请求入参
        tKoreaT.setCreatetime(mydate);//创建时间
        return tKoreaT;
    }

    /**
     * @param
     * @Author zqw
     * @MethodName 封装表
     * @Params
     * @Return
     * @Date 2017/11/15 上午10:32
     * @Desp
     */
    private  TAccountInfo  getTAccountInfodo(Map<String,Object> myboda)throws Exception{
        TAccountInfo tacc=(TAccountInfo)myboda.get("tacc");
        Date mydate=(Date)myboda.get("mydate");
        BigDecimal bd = new BigDecimal(tacc.getFrozenAmount());//将账户冻结金额转换为BigDecimal保持精度
        BigDecimal bda = new BigDecimal(tacc.getFreeAmount());//将可用余额转换为BigDecimal保持精度
        TAccountInfo tAccounTo = new TAccountInfo();
        tAccounTo.setId(tacc.getId());
        tAccounTo.settUserInfoId(tacc.gettUserInfoId());
        tAccounTo.setAmount(tacc.getAmount());
        tAccounTo.setFrozenAmount("0");//冻结金额重置为0
        tAccounTo.setFreeAmount((bda.add(bd)).toString());//可用余额=原可用余额+冻结金额
        tAccounTo.setUpdatetime(mydate);
        return tAccounTo;
    }

    /**
     * @param
     * @Author zqw
     * @MethodName 封装表
     * @Params
     * @Return
     * @Date 2017/11/15 上午10:32
     * @Desp
     */
    private  TKoreaInfo  getTKoreaInfodo(Map<String,Object> myboda)throws Exception{
        TKoreaInfo koreaInfo=(TKoreaInfo)myboda.get("koreaInfo");
        TAccountInfo tacc=(TAccountInfo)myboda.get("tacc");
        Date mydate=(Date)myboda.get("mydate");
        TKoreaInfo  mtkO= new  TKoreaInfo();
        mtkO.setId(koreaInfo.getId());
        mtkO.settUserInfoId(koreaInfo.gettUserInfoId());
        mtkO.setKoreaCardUsedAmount(koreaInfo.getKoreaCardUsedAmount());
        mtkO.setKoreaCardRetreatChAmount("0");//将可退款人民币额度调整为0
        mtkO.setKoreaCardRetreatKoreaAmount("0");//将退款韩元额度调整为0
        mtkO.setKoreaCardFreeAmount("0");//将可用额度调整为0
        mtkO.setKoreaCardTotalAmount(koreaInfo.getKoreaCardUsedAmount());//将新韩卡额度金额调整为已用额度金额
        mtkO.setUpdatetime(mydate);
        return mtkO;
    }


    /**
     * @param
     * @Author zqw
     * @MethodName 封装返回
     * @Params
     * @Return
     * @Date 2017/11/15 上午10:32
     * @Desp
     */
    private  Map<String,Object>getReturnAll(Map<String,Object> myboda)throws Exception{
        Map<String,Object> reala = new HashMap<>();
        TAccountInfo tacc=(TAccountInfo)myboda.get("tacc");
        TCurrencyRate currRate =(TCurrencyRate)myboda.get("currRate");
        BigDecimal bd = new BigDecimal(tacc.getFrozenAmount());//将账户冻结金额转换为BigDecimal保持精度
        BigDecimal rt = new BigDecimal(currRate.getRate());//将汇率转换为BigDecimal保持精度
        DecimalFormat df2 =new DecimalFormat("#0.00");//保留小数点后两位精度
        String rt1 =df2.format(bd.multiply(rt));//账单金额（韩元）
        String paymeu = (String) myboda.get("foreignCard");
        String Payaaaa = paymeu.substring(paymeu.length()-4,paymeu.length());
        Date thisDate = (Date) myboda.get("mydate");
        SimpleDateFormat SS = new SimpleDateFormat("yyyy:MM:dd  HH:mm:ss");
        String str = SS.format(thisDate);
        reala.put("payMethod","新韩卡"+"("+Payaaaa+")");//新韩卡+卡号后四位  支付方式
        reala.put("payType","korearefund");//korearefund-新韩卡退款   支付类型
        reala.put("payTime",str);//支付时间
        reala.put("orderNo",myboda.get("ranDom"));//支付订单号
        reala.put("koreaAmount",rt1);//韩元金额
        reala.put("chAmount",bd.toString());//人民币金额
        reala.put("refundStatus","success");//订单状态  先默认为success
        return reala;
    }

    /**
     * @param
     * @Author zqw
     * @MethodName mapper数据源
     * @Return
     * @Date 2017/11/15 上午10:32
     * @Desp
     */
    private  Map<Object,Object>getMapperList(String foreignCard,RequestBo requestBo)throws Exception{
        PacketHead secure = requestBo.getPacketHead();
        LoginUserBo userBo = iLoginService.getUserInRedis(secure.getToken().toString());
        if (userBo!=null) {
            TAccountInfoExample example = new TAccountInfoExample();
            example.createCriteria().andTUserInfoIdEqualTo(userBo.gettUserInfoId());
            List<TAccountInfo> tAccountInfoS = tAccountInfoMapper.selectByExample(example);
            TUserInfoExample example1 = new TUserInfoExample();
            example1.createCriteria().andIdEqualTo(userBo.gettUserInfoId());
            List<TUserInfo> userIn = tUserInfoMapper.selectByExample(example1);
            TKoreaInfoExample example2 = new TKoreaInfoExample();
            example2.createCriteria().andTUserInfoIdEqualTo(userBo.gettUserInfoId()).andKoreaCardNoEqualTo(foreignCard);
            List<TKoreaInfo> tKoreaInfos = tKoreaInfoMapper.selectByExample(example2);
            TCurrencyRateExample example3 = new TCurrencyRateExample();
            example3.createCriteria().andCurrencyTypeEqualTo("korea");
            List<TCurrencyRate> currencyRates = tCurrencyRateMapper.selectByExample(example3);
            if (null!=userIn && !userIn.isEmpty() &&null!=tAccountInfoS && !tAccountInfoS.isEmpty() &&null!=tKoreaInfos && !tKoreaInfos.isEmpty() && null!=currencyRates &&!currencyRates.isEmpty()) {
                TUserInfo myUser = userIn.get(0);// 用户基础信息表 myUser
                TAccountInfo tacc = tAccountInfoS.get(0);//用户账户表信息  tacc
                TKoreaInfo koreaInfo = tKoreaInfos.get(0); //新韩卡基本信息  koreaInfo
                TCurrencyRate currRate = currencyRates.get(0);//汇率表 currrate
                Map<Object, Object> mapList = new HashMap<>();
                mapList.put("myUser", myUser);
                mapList.put("tacc", tacc);
                mapList.put("koreaInfo", koreaInfo);
                mapList.put("currRate", currRate);
                mapList.put("userBo", userBo);
                return mapList;
            } else {
                return null;
            }
        }else {
            return null;
        }
    }
    // ========================================= zhengqiangwei end 新韩卡退款=================================================

    // ========================================= liuxiaoming  start 获取新韩卡账单流水=================================================
    /**
     * @param requestBo
     * @Author liuxiaoming
     * @MethodName 获取新韩卡账单流水(OK)
     *   解析json字符串
     *      账单类型      billType（koreacharge-新韩卡充值 korearefund-新韩卡退款，koreascan-新韩卡扫码支付，koreacard-新韩卡刷卡支付 all-全部）
     *      账单开始时间   billStartDate
     *      账单结束时间   billEndDate
     *      页码          currentPageNo
     *      每页条数       currentPageLimit
     *      卡类型         currencyCardType必填 目前只有korea-新韩卡
     *      卡id           currencyCardId必填
     * @Params
     * @Return
     * @Date 2017/11/15 上午10:32
     * @Desp
     */
    @Override
    public ResultBo getKoreaCardBill(RequestBo requestBo) throws Exception {

        ResultBo resultBo =ResultBo.build();
        try{
            //去Redis中取用户信息 :取用户ID
            LoginUserBo userInRedis = iLoginService.getUserInRedis(requestBo.getPacketHead().getToken());

            String packetBody = requestBo.getPacketBody();//获取json串
            System.out.println(packetBody);
            Map<String,String> reqMap = new HashMap<>();
            reqMap = JSON.parseObject(packetBody,Map.class);

            if(!(reqMap.containsKey("billType")) || StringUtils.isBlank(reqMap.get("billType"))
                    || !(reqMap.containsKey("currencyCardType")) || StringUtils.isBlank(reqMap.get("currencyCardType"))
                    || !(reqMap.containsKey("currencyCardId")) || StringUtils.isBlank(reqMap.get("currencyCardId"))){
                //请求参数不完整
                return resultBo.reqNotFull();
            }
            if((reqMap.containsKey("billStartDate")) && StringUtils.isNotBlank(reqMap.get("billStartDate"))){
                String billStartDate = String.valueOf(reqMap.get("billStartDate"));
                reqMap.remove("billStartDate");
                reqMap.put("billStartDate", billStartDate+" 00:00:00");
            }
            if((reqMap.containsKey("billEndDate")) && StringUtils.isNotBlank(reqMap.get("billEndDate"))){
                String billEndDate = String.valueOf(reqMap.get("billEndDate"));
                reqMap.remove("billEndDate");
                reqMap.put("billEndDate", billEndDate+" 23:59:59");
            }

            PageHelper.startPage(Integer.valueOf(reqMap.get("currentPageNo")),Integer.valueOf(reqMap.get("currentPageLimit")));
            reqMap.put("userId",String.valueOf(userInRedis.gettUserInfoId()));

            List<Map<String, String>> maps = tKoreaTransactMapper.listTKoreaTransact(reqMap);

            PageInfo<TKoreaTransact> tKoreaTransactPage = new PageInfo(maps);
            ResultBody resultBody = new ResultBody();
            Map<String,Object> resMap = new HashMap<String, Object>();

            reqMap.remove("currencyCardType");
            reqMap.remove("currencyCardId");
            reqMap.remove("userId");
            resMap.putAll(reqMap);
            resMap.put("totalPages",tKoreaTransactPage.getPages() + "");//总页数
            resMap.put("billList",maps);

            resultBody.putAll(resMap);

            resultBo.setResultBody(resultBody);
        }catch (Exception e){
            log.error("getKoreaCardBill",e);
            e.printStackTrace();
            resultBo.fail();
            throw e;
        }finally {
            return resultBo;
        }

    }

    // ========================================= liuxiaoming  end 获取新韩卡账单流水=================================================





}
