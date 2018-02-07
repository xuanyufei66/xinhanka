package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TLoginHistory;
import com.payease.wallet.entity.pojo.TLoginHistoryExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TLoginHistoryMapper {
    int countByExample(TLoginHistoryExample example);

    int deleteByExample(TLoginHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TLoginHistory record);

    int insertSelective(TLoginHistory record);

    List<TLoginHistory> selectByExample(TLoginHistoryExample example);

    TLoginHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TLoginHistory record, @Param("example") TLoginHistoryExample example);

    int updateByExample(@Param("record") TLoginHistory record, @Param("example") TLoginHistoryExample example);

    int updateByPrimaryKeySelective(TLoginHistory record);

    int updateByPrimaryKey(TLoginHistory record);

    //------------------------------------------------------------------------------------------------------------

    /**
     * @Author zqw
     * @MethodName upPass
     * @Params
     * @Return
     * @Date 2017/11/15下午16.00
     * @Desp 查询当前用户ID上一次登陆数据和登陆次数（第一次登陆）
     */
    Map<String,String> selectRecent(@Param("userId")Long userInFoId);

    /**
     * @Author zqw
     * @MethodName upPass
     * @Params
     * @Return
     * @Date 2017/11/15下午16.00
     * @Desp 查询当前用户ID上一次登陆数据和登陆次数(非第一次)
     */
    Map<String,String> selectRecent1(@Param("userId")Long userInFoId);

}