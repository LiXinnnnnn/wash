package com.laiba.wash.core.dao.mapper;

import com.laiba.wash.core.po.CommodityCategory;
import com.laiba.wash.core.po.CommodityCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommodityCategoryMapper {
    int countByExample(CommodityCategoryExample example);

    int deleteByExample(CommodityCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommodityCategory record);

    int insertSelective(CommodityCategory record);

    List<CommodityCategory> selectByExample(CommodityCategoryExample example);

    CommodityCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommodityCategory record, @Param("example") CommodityCategoryExample example);

    int updateByExample(@Param("record") CommodityCategory record, @Param("example") CommodityCategoryExample example);

    int updateByPrimaryKeySelective(CommodityCategory record);

    int updateByPrimaryKey(CommodityCategory record);
}