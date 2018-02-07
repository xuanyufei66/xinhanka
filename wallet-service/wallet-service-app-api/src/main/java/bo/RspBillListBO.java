package bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 账单列表对象
 * Created by lch on 2017/11/20.
 */
public class RspBillListBO implements Serializable{

    private static final long serialVersionUID = 2416548437539262188L;

    private String singleBillType;//账单类型
    private String billTime;//账单支付时间
    private String billAmount;//账单金额
    private String payMethod;//支付方式
    private String payType;//支付类型
    private String transactNo;//账单支付订单号
    private String originalAmount;//账单原金额
    private String saleAmount;//优惠金额
    private String logoUrl;//对应logo的Url
    private String logoNotColorUrl;//对应没有颜色logo的Url
    private String billStatus;//账单状态

    public String getSingleBillType() {
        return singleBillType;
    }

    public void setSingleBillType(String singleBillType) {
        this.singleBillType = singleBillType;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getTransactNo() {
        return transactNo;
    }

    public void setTransactNo(String transactNo) {
        this.transactNo = transactNo;
    }

    public String getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(String originalAmount) {
        this.originalAmount = originalAmount;
    }

    public String getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(String saleAmount) {
        this.saleAmount = saleAmount;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoNotColorUrl() {
        return logoNotColorUrl;
    }

    public void setLogoNotColorUrl(String logoNotColorUrl) {
        this.logoNotColorUrl = logoNotColorUrl;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }
}
