package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TCurrencyRate;
import com.payease.wallet.entity.pojo.TCurrencyRateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCurrencyRateMapper {
    int countByExample(TCurrencyRateExample example);

    int deleteByExample(TCurrencyRateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TCurrencyRate record);

    int insertSelective(TCurrencyRate record);

    List<TCurrencyRate> selectByExample(TCurrencyRateExample example);

    TCurrencyRate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TCurrencyRate record, @Param("example") TCurrencyRateExample example);

    int updateByExample(@Param("record") TCurrencyRate record, @Param("example") TCurrencyRateExample example);

    int updateByPrimaryKeySelective(TCurrencyRate record);

    int updateByPrimaryKey(TCurrencyRate record);
}