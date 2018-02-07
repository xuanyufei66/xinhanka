package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.BaffleInfo;
import com.payease.wallet.entity.pojo.BaffleInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaffleInfoMapper {
    int countByExample(BaffleInfoExample example);

    int deleteByExample(BaffleInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BaffleInfo record);

    int insertSelective(BaffleInfo record);

    List<BaffleInfo> selectByExample(BaffleInfoExample example);

    BaffleInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BaffleInfo record, @Param("example") BaffleInfoExample example);

    int updateByExample(@Param("record") BaffleInfo record, @Param("example") BaffleInfoExample example);

    int updateByPrimaryKeySelective(BaffleInfo record);

    int updateByPrimaryKey(BaffleInfo record);
}