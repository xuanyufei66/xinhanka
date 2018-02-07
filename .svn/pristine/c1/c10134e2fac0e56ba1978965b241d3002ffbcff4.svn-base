package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TKoreaInfo;
import com.payease.wallet.entity.pojo.TKoreaInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TKoreaInfoMapper {
    int countByExample(TKoreaInfoExample example);

    int deleteByExample(TKoreaInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TKoreaInfo record);

    int insertSelective(TKoreaInfo record);

    List<TKoreaInfo> selectByExample(TKoreaInfoExample example);

    TKoreaInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TKoreaInfo record, @Param("example") TKoreaInfoExample example);

    int updateByExample(@Param("record") TKoreaInfo record, @Param("example") TKoreaInfoExample example);

    int updateByPrimaryKeySelective(TKoreaInfo record);

    int updateByPrimaryKey(TKoreaInfo record);
}