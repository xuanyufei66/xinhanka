package com.payease.wallet.app.properties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by zhangzhili on 2017/11/17.
 */
@ConfigurationProperties(prefix = "login")
public class LoginProperties {


    /**
     * 登录拦截器开关   true 开启登录拦截
     */
    private boolean checkInterceptor = false;


    /**
     * 不包含的业务编码id   例如  30001,20003,30000.......
     */
    private String exclude = "";


    /**
     * 是否开启签名校验  是否需要签名 true 需要  默认不需要false
     */
    private boolean checkSign = false;


    public boolean isCheckInterceptor() {
        return checkInterceptor;
    }

    public void setCheckInterceptor(boolean checkInterceptor) {
        this.checkInterceptor = checkInterceptor;
    }

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public boolean isCheckSign() {
        return checkSign;
    }

    public void setCheckSign(boolean checkSign) {
        this.checkSign = checkSign;
    }
}
