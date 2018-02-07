package com.payease.wallet.orm.inter;

import java.util.List;

import com.payease.wallet.entity.pojo.TWithdrawalHistory;
import com.payease.wallet.entity.pojo.TWithdrawalHistoryExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TWithdrawalHistoryMapper {
    int countByExample(TWithdrawalHistoryExample example);

    int deleteByExample(TWithdrawalHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TWithdrawalHistory record);

    int insertSelective(TWithdrawalHistory record);

    List<TWithdrawalHistory> selectByExample(TWithdrawalHistoryExample example);

    TWithdrawalHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TWithdrawalHistory record, @Param("example") TWithdrawalHistoryExample example);

    int updateByExample(@Param("record") TWithdrawalHistory record, @Param("example") TWithdrawalHistoryExample example);

    int updateByPrimaryKeySelective(TWithdrawalHistory record);

    int updateByPrimaryKey(TWithdrawalHistory record);

//////////////////////////////////////lch  start//////////////////////////////////////////
//    查询表中前100条数据根据updatetime倒序排列
    List<TWithdrawalHistory> selectLimit(@Param("id") Long id);
//////////////////////////////////////lch  end//////////////////////////////////////////
}
