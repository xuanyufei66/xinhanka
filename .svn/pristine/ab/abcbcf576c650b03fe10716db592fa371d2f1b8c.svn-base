package com.payease.wallet.orm.inter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.payease.wallet.entity.pojo.TKoreaTransact;
import com.payease.wallet.entity.pojo.TKoreaTransactExample;
import org.apache.ibatis.annotations.Param;

public interface TKoreaTransactMapper {
    int countByExample(TKoreaTransactExample example);

    int deleteByExample(TKoreaTransactExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TKoreaTransact record);

    int insertSelective(TKoreaTransact record);

    List<TKoreaTransact> selectByExample(TKoreaTransactExample example);

    TKoreaTransact selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TKoreaTransact record, @Param("example") TKoreaTransactExample example);

    int updateByExample(@Param("record") TKoreaTransact record, @Param("example") TKoreaTransactExample example);

    int updateByPrimaryKeySelective(TKoreaTransact record);

    int updateByPrimaryKey(TKoreaTransact record);

    /**
     * 根据用户ID计算新韩卡总支出
     * liuxiaoming
     * 2017/10/14
     */
    BigDecimal sumKoreaCardPayAmount(Long userId);

    List<Map<String,String>> listTKoreaTransact(Map<String,String> reqMap);
}