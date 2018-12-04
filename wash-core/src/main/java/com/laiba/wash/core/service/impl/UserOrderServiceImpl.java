package com.laiba.wash.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laiba.wash.core.dao.mapper.OrderCommodityMapper;
import com.laiba.wash.core.dao.mapper.ShopCarMapperExtra;
import com.laiba.wash.core.dao.mapper.UserOrderMapper;
import com.laiba.wash.core.model.ShopCarCommodity;
import com.laiba.wash.core.po.OrderCommodity;
import com.laiba.wash.core.po.UserOrder;
import com.laiba.wash.core.po.UserOrderExample;
import com.laiba.wash.core.service.ShopCarService;
import com.laiba.wash.core.service.UserOrderService;
import com.laiba.wash.core.service.exception.WxPrepayException;
import com.laiba.wash.core.service.wechat.WechatPayResponse;
import com.laiba.wash.core.service.wechat.WechatPayService;
import com.laiba.wash.core.util.DateUtil;
import com.laiba.wash.core.util.PageInfo;
import com.laiba.wash.core.util.UUIDGenerator;

@Service("userOrderService")
public class UserOrderServiceImpl implements UserOrderService{
	
	@Autowired
	private UserOrderMapper userOrderMapper;
	@Autowired
	private OrderCommodityMapper orderCommodityMapper;
	@Autowired
	private WechatPayService wechatPayService;	
	@Autowired
	private ShopCarService shopCarService;	
	@Autowired
	private ShopCarMapperExtra shopCarMapperExtra;

	@Override
	public UserOrder createOrder(Integer userId, Integer userAddressId,
			 Integer[] shopCarId,String comment,String openId) throws WxPrepayException {
		
		//查询购物车数据
		List<ShopCarCommodity> commodityList = shopCarService.shopCarCommodityList(userId,shopCarId);
		//删除购物车记录
		shopCarService.deleteCommodityList(userId, shopCarId);
		//订单信息
		Date date = new Date();
		UserOrder userOrder = new UserOrder();
		userOrder.setUserId(userId);
		userOrder.setAddressId(userAddressId);
		userOrder.setComment(comment);
		userOrder.setStatus(1);
		userOrder.setCreateTime(date);
		Random random = new Random();
		String orderNo = DateUtil.parseDateToStr(new Date(), DateUtil.F_YYYYMMDD24HHMMSS) + (10 + random.nextInt(90));
		userOrder.setOrderNo(orderNo);
		//订单商品
		double totalCount = 0;
		for(ShopCarCommodity shopCarCommodity : commodityList){
			OrderCommodity orderCommodity = new OrderCommodity();
			orderCommodity.setCommodityId(shopCarCommodity.getCommodityId());
			orderCommodity.setCommodityName(shopCarCommodity.getName());
			orderCommodity.setCommodityPrice(shopCarCommodity.getPrice());
			orderCommodity.setNumber(shopCarCommodity.getNumber());
			orderCommodity.setOrderNo(orderNo);
			orderCommodity.setUserId(userId);
			orderCommodity.setCreateTime(date);
			totalCount += shopCarCommodity.getNumber() * shopCarCommodity.getPrice();
			insertOrderCommodity(orderCommodity);
		}
		userOrder.setMoney(totalCount);
		insertOrder(userOrder);
		
		try {
			String outTradeNo = "laiba"+UUIDGenerator.getUUID16();
			WechatPayResponse wxres = wechatPayService.unifiedOrder(outTradeNo,totalCount, "莱巴科技",openId);
			if (wxres != null) {
				userOrder.setOutTradeNo(outTradeNo);
				userOrder.setPrepayId(wxres.getPrepay_id());
				updateOrder(userOrder);
				return userOrder;
			}
		} catch (Exception e) {
			throw new WxPrepayException("生成订单异常！");
		}
		return null;
	}
	
	@Override
	public void payOrder(String outTradeNo, String transactionId) {
		//根据outtradeno查询order
		UserOrder userOrder = selectOrderByOutTradeNo(outTradeNo);
		userOrder.setStatus(2);
		userOrder.setTransactionId(transactionId);
		updateOrder(userOrder);
		
	}

	@Override
	public int insertOrderCommodity(OrderCommodity orderCommodity) {
		return orderCommodityMapper.insert(orderCommodity);
	}

	@Override
	public int insertOrder(UserOrder userOrder) {
		return userOrderMapper.insert(userOrder);
	}

	@Override
	public int updateOrder(UserOrder userOrder) {
		return userOrderMapper.updateByPrimaryKeySelective(userOrder);
	}

	@Override
	public UserOrder selectOrderByOutTradeNo(String outTradeNo) {
		UserOrderExample example = new UserOrderExample();
		UserOrderExample.Criteria criteria = example.createCriteria();
		criteria.andOutTradeNoEqualTo(outTradeNo);
		List<UserOrder> list = userOrderMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<UserOrder> getList(Integer userId) {
		UserOrderExample example = new UserOrderExample();
		example.createCriteria().andUserIdEqualTo(userId);
		example.setOffset(0);
		example.setLimit(10);
		example.setOrderByClause(" create_time desc ");
		List<UserOrder> list = userOrderMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<ShopCarCommodity> getOrderCommodity(String orderNo) {
		List<ShopCarCommodity> list = shopCarMapperExtra.orderCommodity(orderNo);
		return list;
	}

	@Override
	public PageInfo<UserOrder> getList(Integer status, String orderNo,
			PageInfo<UserOrder> pageInfo) {
		UserOrderExample example = new UserOrderExample();
		UserOrderExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(orderNo)) {
			criteria.andOrderNoEqualTo(orderNo);
		}
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		criteria.andStatusNotEqualTo(-1);
		int count = userOrderMapper.countByExample(example);
		pageInfo.setTotalCount(count);
		example.setOffset(pageInfo.getStartRow());
		example.setLimit(pageInfo.getPageSize());
		example.setOrderByClause(" create_time desc  ");
		List<UserOrder> list = userOrderMapper.selectByExample(example);
		pageInfo.setResult(list);
		return pageInfo;
	}

	@Override
	public void cancleOrder(Integer userId, Integer orderId) {
		UserOrderExample example = new UserOrderExample();
		UserOrderExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andIdEqualTo(orderId);
		
		UserOrder userOrder = new UserOrder();
		userOrder.setStatus(-1);
		
		userOrderMapper.updateByExampleSelective(userOrder, example);
	}

	@Override
	public UserOrder getOrder(Integer userId, Integer orderId) {
		UserOrderExample example = new UserOrderExample();
		UserOrderExample.Criteria criteria = example.createCriteria();
		if (userId != null) {
			criteria.andUserIdEqualTo(userId);
			
		}
		criteria.andIdEqualTo(orderId);
		return userOrderMapper.selectByExample(example).get(0);
	}

	@Override
	public UserOrder createRepayOrder(Integer userId, Integer orderId,String openId) throws WxPrepayException{
		UserOrder userOrder = userOrderMapper.selectByPrimaryKey(orderId);
		try {
			String outTradeNo = "laiba"+UUIDGenerator.getUUID16();
			WechatPayResponse wxres = wechatPayService.unifiedOrder(outTradeNo,userOrder.getMoney(), "莱巴科技",openId);
			if (wxres != null) {
				userOrder.setOutTradeNo(outTradeNo);
				userOrder.setPrepayId(wxres.getPrepay_id());
				updateOrder(userOrder);
				return userOrder;
			}
		} catch (Exception e) {
			throw new WxPrepayException("生成订单异常！");
		}
		return null;
	}
	
	
}
