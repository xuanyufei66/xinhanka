package com.payease.wallet.dto.utils;

/**
 * @Author : zhangwen
 * @Data : 2017/11/23
 * @Description :
 */
public enum ErrorResult {
    CONVERT_PARAMTERS("3", "参数转换错误"),
    //登录相关
    LOGIN_NOTEXIST("2001","用户不存在"),LOGIN_PWDERROR("5002","密码验证失败"),
    APPLYKOREACARD_FAIL("5007","申请新韩卡失败"),GETKOREAINFO_FAIL("5008","获取新韩卡详细信息失败"),
    GETSWITCHINFO_FAIL("5009","获取用户开关状态失败"),SETSWITCHINFO_FAIL("5010","设置用户开关状态失败"),
    //获取用户详细信息 2003
    GET_USERDETAILINFO_ERROR("20031","获取用户详细信息请求异常"),
    //获取用户公告信息 2004
    GET_USERSETTING_EMPTY("20041","获取用户设置表信息为空"),
    GET_NOTICEINFO("20042","获取用户公告信息请求异常"),
    //获取页面展示图片
    PAGETYPEISNULL("10011","获取字符串为空"),
    GETBANNERRESULTBO_ISNULL("10013","获取标题为空"),
    GETTYPEFAILURE("10015","获取类型失败"),
    //新韩卡充值
    CHARGETYPE_ERROR("50041","充值类型错误"),
    CHARGE_ERROR("50042","充值失败"),
    ACCOUNTBANK_NOTEXIST("50043","绑定银行卡不存在"),
    BANKCARDTYPE_CREDIT("50044","信用卡不能用于新韩卡充值"),
    BASEBANK_NOTEXIST("50045","银行基础表查询为空"),
    CHARGEAMOUNT_UPPER_SINGLELIMIT("50046","充值金额不能大于单笔限额"),
    CHARGEAMOUNT_UPPER_DAYLIMIT("50047","充值已超过单日限额"),
    ACCOUNTINFO_NOTEXIST("50048","账户信息不存在"),
    CHARGEAMOUNT_UPPER_FREEAMOUNT("50049","充值金额大于可用余额"),
    KOREAINFO_NOTEXIST("50050","新韩卡用户信息不存在"),
    KOREA_CARD_STATUS_ERROR("50051","该新韩卡状态充值错误"),
    REFUND_FAILED("556565","退款失败"),
   LNCOMING_DATE_ERROR("666666","获取数据参数失败"),
    //新韩卡激活挂失
    FOREIGNCARDTYPEISNULLKOREACARD("50052","新韩卡返回卡类型不符返回失败"),
    TKOREAINFOISNULL("50053","新韩卡基本信息表是空"),
    FOREIGNCARDIDISNULL("50555","卡id获取失败"),
    //用户注册页面
    CHECK_SUCCESSANDFAILURE("10021","短信验证码验证失败"),//注册-短信验证码失败
    REGISTEREDINFOFAILURE("10022","注册用户信息失败"),//注册-注册用户信息失败
    //用户设置
    INCOMINGPASSWORDISBALNK("50066","传入密码为空"),
    SAME_PASSWORD("50067","新密码与原密码重复"),
    EMPTY_PASSWORD("50068","用户尚未设置密码"),
    //申请新韩卡
    ENTERTHECORRECTPASSPORTNUMBER("50021","请输入正确的护照编号"),
    ENTERTHECORRECTDATEOFISSUE("50022","请输入正确的签发日期"),
    ENTERTHECORRECTEXPIRATIONDATE("50023","请输入正确的失效日期"),
    INPUTTHECORRECTDATETOKOREA("50024","请输入正确的来韩日期"),
    INPUTTHECORRECTDATEOFDEPARTUREFROMKOREA("50025","请输入正确的离韩日期"),
    SWITCHOPTIONCANNOTBEEMPTY("20051","开关选项不能为空"),
    LOGIN_PASSWORD_USER_LOCKED("50069","由于30分钟内连续输入错误密码超过5次,导致被锁住,请过30分钟后再次尝试登录或者可以立即找回密码"),
    LOGIN_GESTURE_ERROR_LOCKED("50070","手势登录错误次数5次，应跳转常规密码登录页"),
    THEUSERKOREACARDALREADYEXISTS("50028","该用户新韩卡已存在")
    ;




    private String code;
    private String msg;

    private ErrorResult( String code ,String msg){
        this.code = code ;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
