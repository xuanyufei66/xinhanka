package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TAccountBankHistory;
import com.payease.wallet.entity.pojo.TAccountBankHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAccountBankHistoryMapper {
    int countByExample(TAccountBankHistoryExample example);

    int deleteByExample(TAccountBankHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TAccountBankHistory record);

    int insertSelective(TAccountBankHistory record);

    List<TAccountBankHistory> selectByExample(TAccountBankHistoryExample example);

    TAccountBankHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TAccountBankHistory record, @Param("example") TAccountBankHistoryExample example);

    int updateByExample(@Param("record") TAccountBankHistory record, @Param("example") TAccountBankHistoryExample example);

    int updateByPrimaryKeySelective(TAccountBankHistory record);

    int updateByPrimaryKey(TAccountBankHistory record);
}