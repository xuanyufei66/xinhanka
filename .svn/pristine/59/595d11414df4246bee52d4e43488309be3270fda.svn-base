package com.payease.wallet.app.impl.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.payease.wallet.app.api.service.ILoginService;
import com.payease.wallet.app.api.service.IndexService;
import com.payease.wallet.dto.bean.LoginUserBo;
import com.payease.wallet.dto.bean.RequestBo;
import com.payease.wallet.dto.bean.ResultBo;
import com.payease.wallet.dto.bean.ResultBody;
import com.payease.wallet.dto.utils.DateUtil;
import com.payease.wallet.dto.utils.ErrorResult;
import com.payease.wallet.dto.utils.MaskCodeUtil;
import com.payease.wallet.entity.pojo.*;
import com.payease.wallet.orm.inter.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @Author : zhangwen
 * @Data : 2017/11/13
 * @Description : 首页service
 */
@Service
public class IndexServiceImpl implements IndexService {

    private static final Logger log = LoggerFactory.getLogger(IndexServiceImpl.class);
    @Autowired
    private ILoginService iLoginService;
    @Autowired
    private TUserSettingMapper tUserSettingMapper;
    @Autowired
    private TNoticeInfoMapper tNoticeInfoMapper;
    @Autowired
    private TAccountInfoMapper tAccountInfoMapper;
    @Autowired
    private TAccountBankMapper tAccountBankMapper;
    @Autowired
    private TKoreaInfoMapper tKoreaInfoMapper;
    @Autowired
    private TKoreaTransactMapper tKoreaTransactMapper;
    @Autowired
    private TCurrencyRateMapper tCurrencyRateMapper;

    @Autowired
    private TPageImageInfoMapper tPageImageInfoMapper;
// ========================================= zhoushijie  start 获取首页展示图片=================================================
    /**
     * @param requestBo
     * @Author zhoushijie
     * @MethodName 获取首页展示图片
     * @Params
     * @Return
     * @Date 2017/11/13 上午11:08
     * @Desp
     */
    @Override
    public ResultBo getIndexImages(RequestBo requestBo) throws Exception {
        ResultBo resultBo=ResultBo.build();
        try {
            String packetBody = requestBo.getPacketBody();
            JSONObject jsonObject = JSON.parseObject( packetBody);//获取字符串
            if(StringUtils.isBlank(jsonObject.getString("pageType"))){//查看解析的字符串是否空
                return resultBo.reqNotFull().error(ErrorResult.PAGETYPEISNULL);//首页从Josn串获取类型为空
            }
            resultBo = getBannerResultBo(jsonObject.getString("pageType"),resultBo);//根据pageType判断类型
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getIndexImages",e);
            resultBo.fail().error(ErrorResult.GETTYPEFAILURE);//获取判断类型失败
            throw e;
        } finally {
            return resultBo;
        }
    }

    /**
     * 根据pageType判断类型 :portal-首页 guidance-韩国指南
     * @param pageType
     * @param resultBo
     * @return
     * @throws Exception
     */
    private ResultBo getBannerResultBo(String pageType,ResultBo resultBo) throws Exception{
        //必填；portal-首页 guidance-韩国指南
        switch (pageType){
            case "portal" :
                resultBo = getBanner(pageType);
                break;
            case "guidance" :
                resultBo = getBanner(pageType);
                break;
            default :
                resultBo.error(ErrorResult.GETBANNERRESULTBO_ISNULL);//首页获取Banner结果集为空
                break;
        }
        return resultBo;
    }

    /**
     *判断是不是Banner
     * 返回Banner结果集  1  页面banner下的部分  0
     * @param pageType
     * @return
     * @throws Exception
     */
    private ResultBo getBanner(String pageType) throws Exception{
        ResultBo resultBo = ResultBo.build();
        ResultBody resultBody = new ResultBody();
        resultBody.put("bannerList",BannerListToMap(pageType,"1"));//返回Banner结果集
        resultBody.put("bodyParts",BannerListToMap(pageType,"0"));

        resultBo.setResultBody(resultBody);
        return  resultBo;
    }

    /**
     * 判断是否是banner头图 0-否 1-是'
     * @param pageType
     * @param isBanner
     * @return
     * @throws Exception
     */
    //  `isbanner` smallint(6) NOT NULL DEFAULT '0' COMMENT '是不是banner头图 0-否 1-是',
    private List<TPageImageInfo> getIsBanner(String pageType,String isBanner) throws Exception{
        TPageImageInfoExample example = new TPageImageInfoExample();
        TPageImageInfoExample.Criteria criteria = example.createCriteria();
        criteria.andPageTypeEqualTo(pageType);//查询类型
        if(isBanner.equals("1")){
            criteria.andIsbannerEqualTo(Short.valueOf("1"));
        }else{
            criteria.andIsbannerEqualTo(Short.valueOf("0"));
        }
        return tPageImageInfoMapper.selectByExample(example);
    }

    /**
     * TNoticeInfoList 转成 map
     * @param pageType
     * @return
     */
    private List<Map<String, Object>> BannerListToMap(String pageType,String isBanner) throws Exception {
        List<TPageImageInfo> list = getIsBanner(pageType, isBanner);
        if(null == list || list.isEmpty()){
            return null;
        }
        //实体类result : mapList
        Map<String, Object> mapList = new LinkedHashMap<String,Object>();
        for (TPageImageInfo entity : list) {
            Map<String, Object> map = new LinkedHashMap<String,Object>();
            map.put("orderNum", entity.getOrderNum() + "");
            map.put("title", entity.getTitle());
            map.put("imgUrl", entity.getImgUrl());
            map.put("turnUrl",entity.getTurnUrl());
            mapList.putAll(map);
        }
        List<Map<String, Object>> listMap = new ArrayList<>();
        listMap.add(mapList);
        return listMap;
    }
// ========================================= zhoushijie  end 获取首页展示图片=================================================

// ========================================= liuxiaoming  start 获取用户详细信息=================================================
    /**
     * @param requestBo
     * @Author liuxiaoming
     * @MethodName 获取用户详细信息(OK)
     * @Params
     * @Return
     * @Date 2017/11/13 上午11:09
     * @Desp
     */
    @Override
    public ResultBo getUserDetailInfo(RequestBo requestBo) throws Exception {
        ResultBo resultBo = ResultBo.build();
        try {
            //去Redis中取用户信息 :取用户ID
            LoginUserBo userInRedis = iLoginService.getUserInRedis(requestBo.getPacketHead().getToken());
            Long userId = Long.valueOf(userInRedis.gettUserInfoId());
            //验证用户是否实名认证 是：realName为Redis值 否：realName为““
            String realName = "";
            if(null !=  userInRedis.getRealName() ||StringUtils.isNotBlank(userInRedis.getRealName())){
                realName = userInRedis.getRealName();
            }

            String mobilePhone = userInRedis.getMobilePhone();
            String sex = userInRedis.getSex();

            //根据用户ID查询 用户账户表  t_account_info  :  amount free_amount frozen_amount (判断参数值是否为null)
            TAccountInfo tAccountInfo = getTAccountInfoByUserId(userId);

            //根据用户ID查询 用户设置表 t_user_setting : real_name_flag
            TUserSetting tUserSetting = getTUserSettingByUserId(userId);

            //根据用户ID查询 账户银行卡信息  t_account_bank  : isHasBank
            //boolean hasBank = isHasBank(userId);

            //根据用户ID查询 新韩卡基本信息 t_korea_info  : isHasKorea koreaCardId koreaCardNo
            TKoreaInfo tKoreaInfo = getTKoreaInfoByUserId(userId);
            //新韩卡剩余可用余额 ： koreaCardFreeAmount == 用户账户表 t_account_info  : frozen_amount

            //根据用户ID查询 新韩卡交易记录 t_korea_transact
            //	 koreaCardLatelyAmount	新韩卡最近消费支出  koreaCardLatelyAmount(userId)
            //	 koreaCardTotalAmount	新韩卡总支出    tKoreaTransactMapper.sumKoreaCardPayAmount()


            //返回数据拼串
            ResultBody resultBody = new ResultBody();

            if(StringUtils.isBlank(realName)){
                resultBody.put("realName", "");
            }else{
                resultBody.put("realName", MaskCodeUtil.maskRealName(realName));
            }

            resultBody.put("mobilePhone", MaskCodeUtil.maskPhone(mobilePhone));
            resultBody.put("sex", sex);

            if (tAccountInfo != null) {
                resultBody.put("amount", tAccountInfo.getAmount());
                resultBody.put("freeAmount", tAccountInfo.getFreeAmount());
                resultBody.put("frozenAmount", tAccountInfo.getFrozenAmount());
                //新韩卡剩余可用余额 ： koreaCardFreeAmount == 用户账户表 t_account_info  : frozen_amount
                resultBody.put("koreaCardFreeAmount", tAccountInfo.getFrozenAmount());
            }
            if (tUserSetting != null) {
                resultBody.put("isRealName", tUserSetting.getRealNameFlag() + "");
            }
            resultBody.put("isHasBank", (isHasBank(userId)) ? "1" : "0");
            resultBody.put("isHasKorea", (tKoreaInfo!= null) ? "1" : "0");
            if (tKoreaInfo != null) {
                resultBody.put("koreaCardId", tKoreaInfo.getId() + "");
                resultBody.put("koreaCardNo", MaskCodeUtil.maskBankCard(tKoreaInfo.getKoreaCardNo()));
            }

            resultBody.put("koreaCardLatelyAmount", koreaCardLatelyAmount(userId));
            resultBody.put("koreaCardTotalAmount", tKoreaTransactMapper.sumKoreaCardPayAmount(userId) + "");
            resultBo.setResultBody(resultBody);
        } catch (Exception e) {
            resultBo.error(ErrorResult.GET_USERDETAILINFO_ERROR);
            log.error("getUserDetailInfo",e);
            throw e;
        } finally {
            return resultBo;
        }
    }
// ========================================= liuxiaoming  end 获取用户详细信息=================================================

// ========================================= liuxiaoming  start 获取用户公告信息=================================================
    /**
     * @param requestBo
     * @Author liuxiaoming
     * @MethodName 获取用户公告信息 (ok)
     * @Params
     * @Return
     * @Date 2017/11/13 上午11:10
     * @Desp
     */
    @Override
    public ResultBo getNoticeInfo(RequestBo requestBo) throws Exception {
        ResultBo resultBo = ResultBo.build();
        try {
            //去Redis中取用户信息 :取用户ID
            LoginUserBo userInRedis = iLoginService.getUserInRedis(requestBo.getPacketHead().getToken());
            Long userId = Long.valueOf(userInRedis.gettUserInfoId());
//             Long userId = Long.valueOf("1");
            //根据用户ID（t_user_info_id）查询用户设置表 （t_user_setting）里面的 公告开关（notice_flag）
            TUserSetting tUserSetting = this.getTUserSettingByUserId(userId);//用户ID
            if (null == tUserSetting) {
                //获取用户设置表信息为空
                return  resultBo.error(ErrorResult.GET_USERSETTING_EMPTY);
            }

            tUserSetting.setNoticeFlag(tUserSetting.getNoticeFlag());
            //获取公告信息表list
            List<TNoticeInfo> tNoticeInfoList = tNoticeInfoMapper.selectByExample(null);
            //返回数据拼串
            ResultBody resultBody = new ResultBody();
            resultBody.put("noticeFlag", tUserSetting.getNoticeFlag() + "");
            resultBody.put("noticeList", listParseTNoticeInfoMap(tNoticeInfoList));
            resultBo.setResultBody(resultBody);
        } catch (Exception e) {
            //获取用户公告信息请求异常
            resultBo.error(ErrorResult.GET_NOTICEINFO);
            log.error("getNoticeInfo",e);
            e.printStackTrace();
            throw e;
        } finally {
            return resultBo;
        }

    }


    /**
     * 通过用户ID获取 用户设置表 t_user_setting
     * liuxiaoming
     *
     * @param userId
     * @return
     */
    public TUserSetting getTUserSettingByUserId(Long userId) throws Exception {
        TUserSettingExample tUserSettingExample = new TUserSettingExample();
        tUserSettingExample.createCriteria().andTUserInfoIdEqualTo(userId);//用户ID
        List<TUserSetting> tUserSettingList = tUserSettingMapper.selectByExample(tUserSettingExample);
        TUserSetting tUserSetting = new TUserSetting();
        if (null != tUserSettingList && 0 < tUserSettingList.size()) {
            //获取用户设置表信息
            tUserSetting = tUserSettingList.get(0);
        }
        return tUserSetting;

    }

    /**
     * 通过用户ID获取 用户账户表 t_account_info
     * liuxiaoming
     *
     * @param userId
     * @return
     */
    public TAccountInfo getTAccountInfoByUserId(Long userId) throws Exception {
        TAccountInfoExample example = new TAccountInfoExample();
        example.createCriteria().andTUserInfoIdEqualTo(userId);
        List<TAccountInfo> list = tAccountInfoMapper.selectByExample(example);
        if (null != list && 0 < list.size()) {
            //获取用户设置表信息
            return list.get(0);
        }
        return null;
    }


    /**
     * 通过用户ID 账户银行卡信息 t_account_bank
     * 是否有银行卡: 0 ：否 1 ： 是
     * liuxiaoming
     *
     * @param userId
     * @return
     */
    public boolean isHasBank(Long userId) throws Exception {
        TAccountBankExample example = new TAccountBankExample();
        example.createCriteria().andTUserInfoIdEqualTo(userId);
        int i = tAccountBankMapper.countByExample(example);
        if (i > 0) {
            return true;
        }
        return false;
    }

    /**
     * 通过用户ID 新韩卡基本信息 t_korea_info
     * 是否有新韩卡: 0 ：否 1 ： 是
     * liuxiaoming
     * @param userId
     * @return
     */
//    private boolean isHasKorea(Long userId){
//        TKoreaInfoExample example = new TKoreaInfoExample();
//        example.createCriteria().andTUserInfoIdEqualTo(userId);
//        int i = tKoreaInfoMapper.countByExample(example);
//        if(i > 0){
//            return true;
//        }
//        return false;
//    }

    /**
     * 通过用户ID 新韩卡基本信息 t_korea_info
     * <p>
     * ??? `korea_card_type` varchar(50) NOT NULL COMMENT 'prepaid-预付卡',
     * liuxiaoming
     *
     * @param userId
     * @return
     */
    public TKoreaInfo getTKoreaInfoByUserId(Long userId) throws Exception {
        TKoreaInfoExample example = new TKoreaInfoExample();
        example.createCriteria().andTUserInfoIdEqualTo(userId);
        List<TKoreaInfo> list = tKoreaInfoMapper.selectByExample(example);
        if (null != list && 0 < list.size()) {
            //获取用户设置表信息
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过用户ID 新韩卡交易记录  t_korea_transact
     * liuxiaoming
     * 新韩卡最近消费支出
     * pay_type 'koreacharge-新韩卡充值 korearefund-新韩卡退款 koreascan-新韩卡扫码支付 koreacard-新韩卡刷卡支付',
     *
     * @param userId
     * @return
     */
    public String koreaCardLatelyAmount(Long userId) throws Exception {
        TKoreaTransactExample example = new TKoreaTransactExample();
        List<String> payTypeIn = new ArrayList<>();
        payTypeIn.add("koreascan");
        payTypeIn.add("koreacard");
        example.createCriteria().andTUserInfoIdEqualTo(userId).andPayTypeIn(payTypeIn);
        example.setOrderByClause("ordertime desc");
        List<TKoreaTransact> list = tKoreaTransactMapper.selectByExample(example);
        String billAmount = "";
        if (null != list && 0 < list.size()) {
            billAmount = list.get(0).getBillAmount();
        }
        return billAmount;
    }

    /**
     * 通过用户ID 账户银行卡信息 t_account_bank
     * liuxiaoming
     *
     * @param userId
     * @return
     */
    public TAccountBank getTAccountBankByUserIdAndBankCardId(Long userId, String bankCardId) throws Exception {
        TAccountBankExample example = new TAccountBankExample();
        example.createCriteria().andTUserInfoIdEqualTo(userId).andIdEqualTo(Long.valueOf(bankCardId));
        List<TAccountBank> list = tAccountBankMapper.selectByExample(example);
        if (null != list && 0 < list.size()) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过币种(currency_type)  汇率表 t_currency_rate
     * currency_type : korea-韩元
     * liuxiaoming
     */
    public TCurrencyRate getTCurrencyRateBy(String currencyType) throws Exception {
        TCurrencyRateExample example = new TCurrencyRateExample();
        example.createCriteria().andCurrencyTypeEqualTo(currencyType);
        List<TCurrencyRate> list = tCurrencyRateMapper.selectByExample(example);
        if (null != list && 0 < list.size()) {
            return  list.get(0);
        }
        return null;
    }

    /**
     * TNoticeInfoList 转成 map
     * @param list
     * @return
     */
    public List<Map<String, Object>> listParseTNoticeInfoMap (List<TNoticeInfo> list) throws Exception{
        if(null == list || list.isEmpty()){
            return null;
        }
        //实体类result : mapList
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (TNoticeInfo entity : list) {
            Map<String, Object> map =new LinkedHashMap<String,Object>();
            map.put("noticeTitle", entity.getNoticeTitle());
            map.put("noticeTime", DateUtil.formatNewDatetoString(entity.getNoticeTime()));
            map.put("noticeUrl", entity.getNoticeUrl());
            mapList.add(map);
        }

        return mapList;
    }

// ========================================= liuxiaoming  end 获取用户公告信息=================================================

}
