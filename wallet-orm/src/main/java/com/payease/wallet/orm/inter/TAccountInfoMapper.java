package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TAccountInfo;
import com.payease.wallet.entity.pojo.TAccountInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAccountInfoMapper {
    int countByExample(TAccountInfoExample example);

    int deleteByExample(TAccountInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TAccountInfo record);

    int insertSelective(TAccountInfo record);

    List<TAccountInfo> selectByExample(TAccountInfoExample example);

    TAccountInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TAccountInfo record, @Param("example") TAccountInfoExample example);

    int updateByExample(@Param("record") TAccountInfo record, @Param("example") TAccountInfoExample example);

    int updateByPrimaryKeySelective(TAccountInfo record);

    int updateByPrimaryKey(TAccountInfo record);
}