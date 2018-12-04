package com.laiba.wash.core.model;

import java.util.List;

import com.laiba.wash.core.po.User;
import com.laiba.wash.core.po.UserAddress;
import com.laiba.wash.core.po.UserOrder;

public class OrderDetail extends UserOrder{

	private static final long serialVersionUID = 1L;
	
	private UserAddress userAddress;
	
	private List<ShopCarCommodity> commodityList;
	
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public List<ShopCarCommodity> getCommodityList() {
		return commodityList;
	}

	public void setCommodityList(List<ShopCarCommodity> commodityList) {
		this.commodityList = commodityList;
	}
	
}
