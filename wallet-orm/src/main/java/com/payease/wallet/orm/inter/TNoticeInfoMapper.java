package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TNoticeInfo;
import com.payease.wallet.entity.pojo.TNoticeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TNoticeInfoMapper {
    int countByExample(TNoticeInfoExample example);

    int deleteByExample(TNoticeInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TNoticeInfo record);

    int insertSelective(TNoticeInfo record);

    List<TNoticeInfo> selectByExample(TNoticeInfoExample example);

    TNoticeInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TNoticeInfo record, @Param("example") TNoticeInfoExample example);

    int updateByExample(@Param("record") TNoticeInfo record, @Param("example") TNoticeInfoExample example);

    int updateByPrimaryKeySelective(TNoticeInfo record);

    int updateByPrimaryKey(TNoticeInfo record);
}