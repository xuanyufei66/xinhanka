package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TBankBase;
import com.payease.wallet.entity.pojo.TBankBaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBankBaseMapper {
    int countByExample(TBankBaseExample example);

    int deleteByExample(TBankBaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TBankBase record);

    int insertSelective(TBankBase record);

    List<TBankBase> selectByExample(TBankBaseExample example);

    TBankBase selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TBankBase record, @Param("example") TBankBaseExample example);

    int updateByExample(@Param("record") TBankBase record, @Param("example") TBankBaseExample example);

    int updateByPrimaryKeySelective(TBankBase record);

    int updateByPrimaryKey(TBankBase record);
}