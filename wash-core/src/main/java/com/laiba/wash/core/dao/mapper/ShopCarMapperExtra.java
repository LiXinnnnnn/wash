package com.laiba.wash.core.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.laiba.wash.core.model.ShopCarCommodity;

public interface ShopCarMapperExtra extends ShopCarMapper{

	List<ShopCarCommodity> shopCarCommodity(Map<String, Object> map);

	List<ShopCarCommodity> orderCommodity(@Param("orderNo")String orderNo);

}
