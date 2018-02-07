package bo;

import java.io.Serializable;

/**
 * 通用的入参类
 * Created by ljp on 2017/11/7.
 */
public class ReqBaseBO implements Serializable{

    private static final long serialVersionUID = 2779047898738307057L;

    String mid;                     //商户编号
    String key;                     //MD5密钥
    String url;                     //接口地址
    String version;                 //版本号

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
