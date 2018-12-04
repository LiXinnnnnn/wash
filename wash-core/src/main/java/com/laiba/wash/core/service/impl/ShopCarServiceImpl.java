package com.laiba.wash.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laiba.wash.core.dao.mapper.ShopCarMapperExtra;
import com.laiba.wash.core.model.ShopCarCommodity;
import com.laiba.wash.core.po.ShopCar;
import com.laiba.wash.core.po.ShopCarExample;
import com.laiba.wash.core.service.ShopCarService;

@Service("shopCarService")
public class ShopCarServiceImpl implements ShopCarService{
	
	@Autowired
	private ShopCarMapperExtra shopCarMapper;

	@Override
	public int countShopCar(int userId) {
		ShopCarExample example = new ShopCarExample();
		ShopCarExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andUserIdEqualTo(userId);
		int count = shopCarMapper.countByExample(example);
		return count;
	}

	@Override
	public ShopCar selectByUserAndCommodity(Integer userId, Integer commodityId) {
		ShopCarExample example = new ShopCarExample();
		ShopCarExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andUserIdEqualTo(userId);
		criteria.andCommodityIdEqualTo(commodityId);
		List<ShopCar> list = shopCarMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public void insert(ShopCar shopCar) {
		shopCarMapper.insert(shopCar);
	}

	@Override
	public void update(ShopCar shopCar) {
		shopCarMapper.updateByPrimaryKey(shopCar);
	}

	@Override
	public List<ShopCarCommodity> shopCarCommodityList(Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		
		List<ShopCarCommodity> commodityList = shopCarMapper.shopCarCommodity(map);
		return commodityList;
	}

	@Override
	public List<ShopCarCommodity> shopCarCommodityList(Integer userId,
			Integer[] shopCarId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("idList", shopCarId);
		List<ShopCarCommodity> commodityList = shopCarMapper.shopCarCommodity(map);
		return commodityList;
	}

	@Override
	public void deleteCommodityList(Integer userId, Integer[] shopCarId) {
		List<Integer> list = new ArrayList<Integer>();
		for(Integer id : shopCarId){
			list.add(id);
		}
		ShopCarExample example = new ShopCarExample();
		ShopCarExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andIdIn(list);
		criteria.andStatusEqualTo(1);
		ShopCar shopCar = new ShopCar();
		shopCar.setStatus(0);
		shopCarMapper.updateByExampleSelective(shopCar, example);		
	}

	@Override
	public ShopCar selectByShopCarId(Integer userId, Integer shopCarId) {
		ShopCarExample example = new ShopCarExample();
		ShopCarExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andIdEqualTo(shopCarId);
		criteria.andStatusEqualTo(1);
		List<ShopCar> list = shopCarMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public void deleteCommodity(Integer userId, Integer commodityId) {
		ShopCarExample example = new ShopCarExample();
		ShopCarExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andIdEqualTo(commodityId);
		criteria.andStatusEqualTo(1);
		ShopCar shopCar = new ShopCar();
		shopCar.setStatus(0);
		shopCarMapper.updateByExampleSelective(shopCar, example);		
		
	}
	
	
	
}
