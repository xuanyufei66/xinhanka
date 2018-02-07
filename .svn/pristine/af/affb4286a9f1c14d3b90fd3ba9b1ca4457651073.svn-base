package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TPlAddress;
import com.payease.wallet.entity.pojo.TPlAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPlAddressMapper {
    int countByExample(TPlAddressExample example);

    int deleteByExample(TPlAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TPlAddress record);

    int insertSelective(TPlAddress record);

    List<TPlAddress> selectByExample(TPlAddressExample example);

    TPlAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TPlAddress record, @Param("example") TPlAddressExample example);

    int updateByExample(@Param("record") TPlAddress record, @Param("example") TPlAddressExample example);

    int updateByPrimaryKeySelective(TPlAddress record);

    int updateByPrimaryKey(TPlAddress record);
}