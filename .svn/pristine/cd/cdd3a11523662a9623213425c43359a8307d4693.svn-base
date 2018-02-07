package com.payease.wallet.dto.bean;


import com.payease.wallet.dto.utils.ErrorResult;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangzhili on 2017/11/2.
 */
public class ResultBo implements Serializable {

    private ResultHead resultHead;

    private ResultBody resultBody;

    public ResultHead getResultHead() {
        return resultHead;
    }

    public void setResultHead(ResultHead resultHead) {
        this.resultHead = resultHead;
    }

    public ResultBody getResultBody() {
        return resultBody;
    }

    public void setResultBody(ResultBody resultBody) {
        this.resultBody = resultBody;
    }

    public ResultBo(ResultHead resultHead, ResultBody resultBody) {
        this.resultHead = resultHead;
        this.resultBody = resultBody;
    }

    public void setResultBody(Object object) {

        Method[] methods = object.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            String name = method.getName();
            if (name.startsWith("get")) {
                try {
                    name = name.replaceAll("get", "");
                    name =
                            name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toLowerCase());
                    this.getResultBody().put(name, method.invoke(object));
                } catch (IllegalAccessException | IllegalArgumentException |
                        InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public enum Status {
        SUCCESS("0", "OK"), FAIL("1", "fail"), NOINTERFACE("2", "not find 对应的接口"), SIGNFAIL("1001", "签名失败"),
        SMSTIMEOUT("1002", "短信验证码验证失败"), TOKENTIMEOUT("1003", "登录状态已失效"), REGISTERED("1004", "账号已注册"),
        REQUEST_NOT_FULL("1005", "请求参数不完整"),
        BANK_CARD_EXIST("3001", "该卡已经被绑定！"),ID_NUMBER_ERROR("3002","身份证号码不存在！"),NAME_ERROR("3003","姓名与身份证号不匹配！"),
        USER_ID_EXIST("3004","该身份证号已存在"),BANK_CARD_NUMBER("3005","最多绑定5张卡"),DATA_UPDATE_FAIL("3006","更新数据库失败"),
        BANK_NOT_YOURS("3007","不能删除别人的银行卡"),SMS_CODE("3008","短信验证码不正确"),SMS_TIMEOUT("3009","短信验证码过期"),
        SINGLELIMIT("4001", "充值金额超过单笔限额"), DAYLIMIT("4002", "已超过单日限额"), ACCOUNTCHANGED("4003", "账户金额有变化"),
        BANKCARDTYPEERROR("4004","信用卡不能进行充值"),PAY_PASSWORD_LOCkED("4005","支付密码输入错误五次");
        public final String code;
        public final String msg;
        private final static Map<String, Status> data = new HashMap<>();

        Status(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        static {
            for (Status status : Status.values()) {
                data.put(status.code, status);
            }
        }

        public static Status getStatusByCode(String code) {
            Status status = data.get(code);
            if (status == null) {
                throw new IllegalArgumentException("不存存文档所定义的状态值");
            }
            return status;
        }
    }


    public static ResultBo build(ResultBody resultBody) {
        ResultHead resultHead = new ResultHead(Status.SUCCESS.code, Status.SUCCESS.msg);
        ResultBo resultBo = new ResultBo(resultHead, resultBody);
        return resultBo;
    }


    public static ResultBo build() {
        return build(new ResultBody());
    }


    public ResultBo setStatus(Status status) {
        setResultHead(status);
        return this;
    }


    public ResultBo fail() {
        setResultHead(Status.FAIL);
        return this;
    }


    public ResultBo notInterface() {
        setResultHead(Status.NOINTERFACE);
        return this;
    }

    public ResultBo smsTimeOut() {
        setResultHead(Status.SMSTIMEOUT);
        return this;
    }

    public ResultBo tokenTimeOut() {
        setResultHead(Status.TOKENTIMEOUT);
        return this;
    }

    public ResultBo Registered() {
        setResultHead(Status.REGISTERED);
        return this;
    }

    public ResultBo reqNotFull() {
        setResultHead(Status.REQUEST_NOT_FULL);
        return this;
    }

    public ResultBo error(ErrorResult errorResult) {
        setResultHead(errorResult.getCode(), errorResult.getMsg());
        return this;
    }


    public void setResultHead(String code, String msg) {
        this.getResultHead().setResultCode(code);
        this.getResultHead().setResultDesc(msg);
    }

    public ResultBo signFail() {
        setResultHead(Status.SIGNFAIL);
        return this;
    }


    public ResultBo bankExist() {
        setResultHead(Status.BANK_CARD_EXIST);
        return this;
    }

    public ResultBo interError(String msg){
        setResultHead(msg);
        return this;
    }

    //调用北京接口返回错误通用返回码
    public ResultBo setResultHead(String msg) {
        this.getResultHead().setResultCode("9001");
        this.getResultHead().setResultDesc(msg);
        return this;
    }

    public void setResultHead(Status status) {
        setResultHead(status.code, status.msg);
    }


}
