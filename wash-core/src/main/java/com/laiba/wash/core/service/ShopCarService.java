package com.laiba.wash.core.service;

import java.util.List;

import com.laiba.wash.core.model.ShopCarCommodity;
import com.laiba.wash.core.po.ShopCar;

public interface ShopCarService {
	
	public int countShopCar(int userId);

	public ShopCar selectByUserAndCommodity(Integer userId, Integer commodityId);

	public void insert(ShopCar shopCar);

	public void update(ShopCar shopCar);
	
	public List<ShopCarCommodity> shopCarCommodityList(Integer userId);

	public List<ShopCarCommodity> shopCarCommodityList(Integer userId,
			Integer[] shopCarId);
	
	public void deleteCommodityList(Integer userId,Integer[] shopCarId);

	public ShopCar selectByShopCarId(Integer userId, Integer shopCarId);

	public void deleteCommodity(Integer userId, Integer commodityId);

}
