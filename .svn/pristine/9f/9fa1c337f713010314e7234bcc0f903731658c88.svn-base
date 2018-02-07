package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TAccountTransact;
import com.payease.wallet.entity.pojo.TAccountTransactExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TAccountTransactMapper {
    int countByExample(TAccountTransactExample example);

    int deleteByExample(TAccountTransactExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TAccountTransact record);

    int insertSelective(TAccountTransact record);

    List<TAccountTransact> selectByExample(TAccountTransactExample example);

    TAccountTransact selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TAccountTransact record, @Param("example") TAccountTransactExample example);

    int updateByExample(@Param("record") TAccountTransact record, @Param("example") TAccountTransactExample example);

    int updateByPrimaryKeySelective(TAccountTransact record);

    int updateByPrimaryKey(TAccountTransact record);

    /**
     *  /////////////////////////////////////////// liuxiaoming base end///////////////////////////////////
     * 根据 用户ID 和 银行卡卡号 计算新韩卡总支出   此银行卡：1. pay_type： 'banktransfer-银行转出
     *                                                  2. pay_type： walletfrozen-账户冻结  `charge_type` ：'bankcard-银行卡充值 -->
     */
    BigDecimal sumTAccountTransactPayAmountCurrentDay(@Param("userId") Long userId,@Param("bankCardNo") String bankCardNo);

    //    查询转入总额
    String selectIntoTotalAmount(Long userInfoId);

    //    查询转出总额
    String selectOutTotalAmount(Long userInfoId);
}