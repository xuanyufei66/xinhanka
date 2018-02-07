package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TAccountBank;
import com.payease.wallet.entity.pojo.TAccountBankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAccountBankMapper {
    int countByExample(TAccountBankExample example);

    int deleteByExample(TAccountBankExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TAccountBank record);

    int insertSelective(TAccountBank record);

    List<TAccountBank> selectByExample(TAccountBankExample example);

    TAccountBank selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TAccountBank record, @Param("example") TAccountBankExample example);

    int updateByExample(@Param("record") TAccountBank record, @Param("example") TAccountBankExample example);

    int updateByPrimaryKeySelective(TAccountBank record);

    int updateByPrimaryKey(TAccountBank record);
}