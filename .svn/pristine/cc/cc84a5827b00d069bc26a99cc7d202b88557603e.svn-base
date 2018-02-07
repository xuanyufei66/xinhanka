package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.TRouter;
import com.payease.wallet.entity.pojo.TRouterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRouterMapper {
    int countByExample(TRouterExample example);

    int deleteByExample(TRouterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRouter record);

    int insertSelective(TRouter record);

    List<TRouter> selectByExample(TRouterExample example);

    TRouter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRouter record, @Param("example") TRouterExample example);

    int updateByExample(@Param("record") TRouter record, @Param("example") TRouterExample example);

    int updateByPrimaryKeySelective(TRouter record);

    int updateByPrimaryKey(TRouter record);
}