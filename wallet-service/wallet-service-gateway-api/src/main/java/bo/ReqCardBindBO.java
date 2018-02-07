package bo;

/**
 * 银行卡绑定接口的入参
 * Created by ljp on 2017/11/8.
 */
public class ReqCardBindBO extends ReqBaseBO{

    private String idName;          //客户姓名
    private String cardNo;          //银行卡号
    private Integer idType;         //证件类型
    private String idNumber;        //证件号
    private String phone;           //手机号码
    private String userRef;         //客户唯一标识
    private String tokenPass;       //绑卡信息验证码

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserRef() {
        return userRef;
    }

    public void setUserRef(String userRef) {
        this.userRef = userRef;
    }

    public String getTokenPass() {
        return tokenPass;
    }

    public void setTokenPass(String tokenPass) {
        this.tokenPass = tokenPass;
    }

    @Override
    public String toString() {
        return "ReqCardBindBO{" +
                "idName='" + idName + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", idType=" + idType +
                ", mid='" + mid + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", key='" + key + '\'' +
                ", phone='" + phone + '\'' +
                ", url='" + url + '\'' +
                ", userRef='" + userRef + '\'' +
                ", tokenPass='" + tokenPass + '\'' +
                '}';
    }
}
