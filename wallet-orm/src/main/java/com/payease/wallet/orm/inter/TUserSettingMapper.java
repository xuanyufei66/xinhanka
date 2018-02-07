package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TUserSetting;
import com.payease.wallet.entity.pojo.TUserSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserSettingMapper {
    int countByExample(TUserSettingExample example);

    int deleteByExample(TUserSettingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TUserSetting record);

    int insertSelective(TUserSetting record);

    List<TUserSetting> selectByExample(TUserSettingExample example);

    TUserSetting selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TUserSetting record, @Param("example") TUserSettingExample example);

    int updateByExample(@Param("record") TUserSetting record, @Param("example") TUserSettingExample example);

    int updateByPrimaryKeySelective(TUserSetting record);

    int updateByPrimaryKey(TUserSetting record);


    //---------------------------------------------------------
    /**
     * @Author zqw
     * @MethodName 获取用户实名状态
     * @Params
     * @Return
     * @Date 2017/11/13 上午10:37
     * @Desp
     */
    int selectRealName(@Param("userId")long userInfoId );
}