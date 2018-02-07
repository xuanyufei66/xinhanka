package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TUserMessage;
import com.payease.wallet.entity.pojo.TUserMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserMessageMapper {
    int countByExample(TUserMessageExample example);

    int deleteByExample(TUserMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TUserMessage record);

    int insertSelective(TUserMessage record);

    List<TUserMessage> selectByExample(TUserMessageExample example);

    TUserMessage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TUserMessage record, @Param("example") TUserMessageExample example);

    int updateByExample(@Param("record") TUserMessage record, @Param("example") TUserMessageExample example);

    int updateByPrimaryKeySelective(TUserMessage record);

    int updateByPrimaryKey(TUserMessage record);
}