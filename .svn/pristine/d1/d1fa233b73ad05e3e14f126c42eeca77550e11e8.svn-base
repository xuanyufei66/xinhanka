package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TPasswordInfo;
import com.payease.wallet.entity.pojo.TPasswordInfoExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import javax.xml.crypto.Data;

public interface TPasswordInfoMapper {
    int countByExample(TPasswordInfoExample example);

    int deleteByExample(TPasswordInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TPasswordInfo record);

    int insertSelective(TPasswordInfo record);

    List<TPasswordInfo> selectByExample(TPasswordInfoExample example);

    TPasswordInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TPasswordInfo record, @Param("example") TPasswordInfoExample example);

    int updateByExample(@Param("record") TPasswordInfo record, @Param("example") TPasswordInfoExample example);

    int updateByPrimaryKeySelective(TPasswordInfo record);

    int updateByPrimaryKey(TPasswordInfo record);


    ///////////////////////////////////base end////////////////////////////////////
    /**
     * @Author zhangwen
     * @MethodName getPasswordInfo
     * @Params
     * @Return
     * @Date 2017/11/14 下午2:18
     * @Desp  获取用户登录密码信息-登录时验证密码使用
     */
    Map<String,String> getPasswordInfo(@Param("acctno")String acctno);

    /**
     * @Author zqw
     * @MethodName upPass
     * @Params
     * @Return
     * @Date 2017/11/15下午16.00
     * @Desp  修改登陆密码
     */
    int upPass(@Param("pass")String Pass,@Param("userId")Long tUserInId,@Param("ranDom")String ranDom,@Param("upData")Date upData);

    /**
     * @Author zqw
     * @MethodName updateGesture
     * @Params
     * @Return
     * @Date 2017/11/15下午16.00
     * @Desp  修改手势密码
     */
    int updateGesture(@Param("pass")String Pass,@Param("userId")Long tUserInId,@Param("ranDom")String ranDom,@Param("upData")Date upData);

    /**
     * @Author zqw
     * @MethodName upPass
     * @Params
     * @Return
     * @Date 2017/11/15下午16.00
     * @Desp  修改支付密码
     */
    int updatePayPass(@Param("pass")String Pass,@Param("userId")Long tUserInId,@Param("ranDom")String ranDom,@Param("upData")Date upData);

}