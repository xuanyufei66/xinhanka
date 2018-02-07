package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TUserInfo;
import com.payease.wallet.entity.pojo.TUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserInfoMapper {
    int countByExample(TUserInfoExample example);

    int deleteByExample(TUserInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TUserInfo record);

    int insertSelective(TUserInfo record);

    List<TUserInfo> selectByExample(TUserInfoExample example);

    TUserInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TUserInfo record, @Param("example") TUserInfoExample example);

    int updateByExample(@Param("record") TUserInfo record, @Param("example") TUserInfoExample example);

    int updateByPrimaryKeySelective(TUserInfo record);

    int updateByPrimaryKey(TUserInfo record);


    //----------------------------------------------------------------------------
    /**
     * @Author zqw
     * @MethodName 获取用户名
     * @Params
     * @Return
     * @Date 2017/11/13 上午10:37
     * @Desp
     */
      String selectRealNamebyid(@Param("userId") long userInfoId);
}