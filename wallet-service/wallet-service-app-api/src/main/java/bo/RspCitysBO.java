package bo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/5.
 */
public class RspCitysBO implements Serializable{

    private static final long serialVersionUID = 8935401955429574431L;
    private String cityCode;
    private String cityName;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
