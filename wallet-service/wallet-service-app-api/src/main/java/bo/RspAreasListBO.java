package bo;

import java.io.Serializable;
import java.util.List;

/**
 * 返回前台的地区id和地区name
 * Created by Administrator on 2017/11/19.
 */
public class RspAreasListBO implements Serializable {

    private static final long serialVersionUID = -5009124455975945348L;
    private String provinceCode;
    private String provinceName;
    private List<RspCitysBO> citys;

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<RspCitysBO> getCitys() {
        return citys;
    }

    public void setCitys(List<RspCitysBO> citys) {
        this.citys = citys;
    }
}
