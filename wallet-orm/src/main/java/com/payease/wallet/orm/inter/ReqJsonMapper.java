package com.payease.wallet.orm.inter;

import com.payease.wallet.entity.pojo.ReqJson;
import com.payease.wallet.entity.pojo.ReqJsonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReqJsonMapper {
    int countByExample(ReqJsonExample example);

    int deleteByExample(ReqJsonExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReqJson record);

    int insertSelective(ReqJson record);

    List<ReqJson> selectByExample(ReqJsonExample example);

    ReqJson selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReqJson record, @Param("example") ReqJsonExample example);

    int updateByExample(@Param("record") ReqJson record, @Param("example") ReqJsonExample example);

    int updateByPrimaryKeySelective(ReqJson record);

    int updateByPrimaryKey(ReqJson record);
}