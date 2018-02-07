package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TLoginInfo;
import com.payease.wallet.entity.pojo.TLoginInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TLoginInfoMapper {
    int countByExample(TLoginInfoExample example);

    int deleteByExample(TLoginInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TLoginInfo record);

    int insertSelective(TLoginInfo record);

    List<TLoginInfo> selectByExample(TLoginInfoExample example);

    TLoginInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TLoginInfo record, @Param("example") TLoginInfoExample example);

    int updateByExample(@Param("record") TLoginInfo record, @Param("example") TLoginInfoExample example);

    int updateByPrimaryKeySelective(TLoginInfo record);

    int updateByPrimaryKey(TLoginInfo record);
}