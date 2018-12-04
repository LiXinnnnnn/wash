package com.laiba.wash.core.service;

import java.util.List;

import com.laiba.wash.core.po.Commodity;
import com.laiba.wash.core.po.CommodityCategory;
import com.laiba.wash.core.util.PageInfo;

public interface CommodityService {

	public PageInfo<Commodity> getCommodityList(Integer categoryId,
			PageInfo<Commodity> pageInfo);
	
	public List<Commodity> getCommodityList(Integer categoryId);

	public CommodityCategory getCategoryById(Integer categoryId);

	public List<CommodityCategory> getCategoryList();

	public int delete(Integer id);

	public int insertCommodity(Commodity commodity);

}
