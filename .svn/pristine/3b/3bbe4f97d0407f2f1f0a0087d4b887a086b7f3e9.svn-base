package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TAreas;
import com.payease.wallet.entity.pojo.TAreasExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAreasMapper {
    int countByExample(TAreasExample example);

    int deleteByExample(TAreasExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAreas record);

    int insertSelective(TAreas record);

    List<TAreas> selectByExample(TAreasExample example);

    TAreas selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAreas record, @Param("example") TAreasExample example);

    int updateByExample(@Param("record") TAreas record, @Param("example") TAreasExample example);

    int updateByPrimaryKeySelective(TAreas record);

    int updateByPrimaryKey(TAreas record);
}