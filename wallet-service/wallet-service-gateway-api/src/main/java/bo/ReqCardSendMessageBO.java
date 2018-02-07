package bo;

/**
 * 短信发送接口的入参
 * Created by ljp on 2017/11/8.
 */
public class ReqCardSendMessageBO extends ReqBaseBO{

    private String serviceType;         //短信发送业务类型
    private String userref;             //客户唯一标识
    private String mobile;              //手机号码

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getUserref() {
        return userref;
    }

    public void setUserref(String userref) {
        this.userref = userref;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
