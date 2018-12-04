package com.laiba.wash.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laiba.wash.core.dao.mapper.CommodityCategoryMapper;
import com.laiba.wash.core.dao.mapper.CommodityMapper;
import com.laiba.wash.core.po.Commodity;
import com.laiba.wash.core.po.CommodityCategory;
import com.laiba.wash.core.po.CommodityExample;
import com.laiba.wash.core.service.CommodityService;
import com.laiba.wash.core.util.PageInfo;

@Service("commodityService")
public class CommodityServiceImpl implements CommodityService{
	
	@Autowired
	private CommodityMapper commodityMapper;
	@Autowired
	private CommodityCategoryMapper commodityCategoryMapper;

	@Override
	public PageInfo<Commodity> getCommodityList(Integer categoryId,
			PageInfo<Commodity> pageInfo) {
		CommodityExample example = new CommodityExample();
		CommodityExample.Criteria criteria =  example.createCriteria();
		if (categoryId != null) {
			criteria.andCategoryIdEqualTo(categoryId);
		}
		criteria.andStatusEqualTo(1);
		int count = commodityMapper.countByExample(example);
		pageInfo.setTotalCount(count);
		
		example.setOffset(pageInfo.getStartRow());
		example.setLimit(pageInfo.getPageSize());
		example.setOrderByClause(" create_time desc ");
		List<Commodity> ppList = commodityMapper.selectByExample(example);
		
		pageInfo.setResult(ppList);
		return pageInfo;
	}

	@Override
	public List<Commodity> getCommodityList(Integer categoryId) {
		CommodityExample example = new CommodityExample();
		CommodityExample.Criteria criteria =  example.createCriteria();
		if (categoryId != null) {
			criteria.andCategoryIdEqualTo(categoryId);
		}
		criteria.andStatusEqualTo(1);
		List<Commodity> ppList = commodityMapper.selectByExample(example);
		return ppList;
	}



	@Override
	public CommodityCategory getCategoryById(Integer categoryId) {
		return commodityCategoryMapper.selectByPrimaryKey(categoryId);
	}

	@Override
	public List<CommodityCategory> getCategoryList() {
		List<CommodityCategory> list = commodityCategoryMapper.selectByExample(null);
		return list;
	}

	@Override
	public int delete(Integer id) {
		Commodity commodity = new Commodity();
		commodity.setId(id);
		commodity.setStatus(0);
		commodity.setUpdateTime(new Date());
		int result = commodityMapper.updateByPrimaryKeySelective(commodity);
		return result;
	}

	@Override
	public int insertCommodity(Commodity commodity) {
		commodity.setStatus(1);
		commodity.setCreateTime(new Date());
		int result = commodityMapper.insertSelective(commodity);
		return result;
	}
	
	
}
