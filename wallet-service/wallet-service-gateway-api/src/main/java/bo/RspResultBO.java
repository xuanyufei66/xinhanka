package bo;

import java.io.Serializable;
import java.util.Map;

/**
 * 通用返回类
 * Created by ljp on 2017/11/7.
 */
public class RspResultBO implements Serializable {

    private static final long serialVersionUID = 5260204903955780791L;
    String code;                       //返回码
    String desc;                       //返回描述
    Map<String, Object> data;    //返回数据（可为空）

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public RspResultBO deal(Map<String, String> resultMap) {
        this.code = resultMap.get("status");
        this.desc = resultMap.get("statusdesc");
        return this;
    }

    public RspResultBO deal(Map<String, String> resultMap, Map<String, Object> dataMap) {
        this.code = resultMap.get("status");
        this.desc = resultMap.get("statusdesc");
        this.data = dataMap;
        return this;
    }

    public RspResultBO error(String desc) {
        this.code = "-1";
        this.desc = desc;
        return this;
    }

    @Override
    public String toString() {
        return "RspResultBo{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }
}
