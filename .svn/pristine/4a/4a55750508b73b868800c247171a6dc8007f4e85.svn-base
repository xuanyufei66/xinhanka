package com.payease.wallet.dto.exception;

import com.payease.wallet.dto.bean.ResultBo;

/**
 * Created by zhangzhili on 2017/11/15.
 */
public class AccountRuntimeException extends RuntimeException {

    private String code;

    private String msg;



    public AccountRuntimeException(){

    }

    public AccountRuntimeException(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public AccountRuntimeException(ResultBo.Status status){
        this.code = status.code;
        this.msg = status.msg;
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
