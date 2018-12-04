package com.laiba.wash.core.service;

import java.util.List;

import com.laiba.wash.core.model.ShopCarCommodity;
import com.laiba.wash.core.po.OrderCommodity;
import com.laiba.wash.core.po.UserOrder;
import com.laiba.wash.core.service.exception.WxPrepayException;
import com.laiba.wash.core.util.PageInfo;


public interface UserOrderService {

	public UserOrder createOrder(Integer userId, Integer userAddressId,  Integer[] shopCarId,
			String comment,String openId) throws WxPrepayException;
		
	public int insertOrderCommodity(OrderCommodity orderCommodity);
	
	public int insertOrder(UserOrder userOrder);
	
	public int updateOrder(UserOrder userOrder);

	public void payOrder(String outTradeNo, String transactionId);
	
	public UserOrder selectOrderByOutTradeNo(String outTradeNo);

	public List<UserOrder> getList(Integer userId);

	public List<ShopCarCommodity> getOrderCommodity(String orderNo);

	public PageInfo<UserOrder> getList(Integer status, String orderNo,
			PageInfo<UserOrder> pageInfo);

	public void cancleOrder(Integer userId, Integer orderId);

	public UserOrder getOrder(Integer userId, Integer orderId);

	public UserOrder createRepayOrder(Integer id, Integer orderId,String openId)throws WxPrepayException;

}
