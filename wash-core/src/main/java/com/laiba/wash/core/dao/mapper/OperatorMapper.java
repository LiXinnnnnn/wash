package com.laiba.wash.core.dao.mapper;

import com.laiba.wash.core.po.Operator;
import com.laiba.wash.core.po.OperatorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperatorMapper {
    int countByExample(OperatorExample example);

    int deleteByExample(OperatorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Operator record);

    int insertSelective(Operator record);

    List<Operator> selectByExample(OperatorExample example);

    Operator selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Operator record, @Param("example") OperatorExample example);

    int updateByExample(@Param("record") Operator record, @Param("example") OperatorExample example);

    int updateByPrimaryKeySelective(Operator record);

    int updateByPrimaryKey(Operator record);
}