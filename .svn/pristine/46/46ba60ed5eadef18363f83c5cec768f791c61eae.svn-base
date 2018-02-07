package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TImgAddress;
import com.payease.wallet.entity.pojo.TImgAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TImgAddressMapper {
    int countByExample(TImgAddressExample example);

    int deleteByExample(TImgAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TImgAddress record);

    int insertSelective(TImgAddress record);

    List<TImgAddress> selectByExample(TImgAddressExample example);

    TImgAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TImgAddress record, @Param("example") TImgAddressExample example);

    int updateByExample(@Param("record") TImgAddress record, @Param("example") TImgAddressExample example);

    int updateByPrimaryKeySelective(TImgAddress record);

    int updateByPrimaryKey(TImgAddress record);
}