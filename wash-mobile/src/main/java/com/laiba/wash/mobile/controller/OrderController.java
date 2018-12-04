package com.laiba.wash.mobile.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laiba.wash.core.model.OrderDetail;
import com.laiba.wash.core.model.ShopCarCommodity;
import com.laiba.wash.core.po.User;
import com.laiba.wash.core.po.UserAddress;
import com.laiba.wash.core.po.UserOrder;
import com.laiba.wash.core.service.ShopCarService;
import com.laiba.wash.core.service.UserAddressService;
import com.laiba.wash.core.service.UserOrderService;
import com.laiba.wash.core.service.exception.WxPrepayException;
import com.laiba.wash.core.service.wechat.WechatPayConfig;
import com.laiba.wash.core.service.wechat.js.WechatPayJs;
import com.laiba.wash.core.util.JsonUtil;
import com.laiba.wash.core.util.WashPropertyUtil;
import com.laiba.wash.mobile.constant.FrontContant;
import com.laiba.wash.mobile.front.dto.ResponseResult;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private ShopCarService shopCarService;	
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private UserOrderService userOrderService;
	
	@Autowired
	private WashPropertyUtil washPropertyUtil;
	
	private static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@RequestMapping("/confirmOrder.htm")
	public String confirmOrder(Integer[] shopCarId,HttpSession session,ModelMap map,Integer addressId){
		//用户
		User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
		//地址
		UserAddress userAddress = userAddressService.getDefaultAddress(user.getId(),addressId);
		map.addAttribute("userAddress", userAddress);
		
		//查询购物车数据
		List<ShopCarCommodity> commodityList = shopCarService.shopCarCommodityList(user.getId(),shopCarId);
		//购物车总金额
		double totalCount = 0;
		for(ShopCarCommodity commodity : commodityList){
			totalCount += commodity.getPrice()*commodity.getNumber();
			commodity.setImg(washPropertyUtil.getProperty("sever_path") + commodity.getImg());
		}
		map.addAttribute("commodityList", commodityList);
		totalCount = new BigDecimal(totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		map.addAttribute("totalCount", totalCount);
		
		return "/index/confirmOrder";
	}
	
	@ResponseBody
	@RequestMapping("/ajax/submitOrder.htm")
	public Object submitOrder(Integer[] shopCarId,Integer userAddressId,String comment,HttpSession session,
			ModelMap map){
		ResponseResult<WechatPayJs> responseResult = new ResponseResult<WechatPayJs>();
		//用户
		User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
		UserOrder userOrder;
		try {
			userOrder = userOrderService.createOrder(user.getId(),userAddressId,shopCarId,comment,user.getOpenId());
		} catch (WxPrepayException e) {
			logger.error("创建订单失败！",e);
			responseResult.setSuccess(false);
			return JsonUtil.toJson(responseResult);
		}
		if (userOrder == null) {
			responseResult.setSuccess(false);
			return JsonUtil.toJson(responseResult);
		}
		WechatPayJs wechatPayJs = new WechatPayJs();
		wechatPayJs.setAppId(WechatPayConfig.getAppid());
		wechatPayJs.setSignType("MD5");
		wechatPayJs.setTimeStamp(new String(Calendar.getInstance().getTimeInMillis() + "").substring(0, 10));
		wechatPayJs.setPackageString("prepay_id=" + userOrder.getPrepayId());
		wechatPayJs.setNonceStr(30);
		wechatPayJs.setPaySign(WechatPayConfig.getKey(),"MD5");
		logger.info("微信js参数" + wechatPayJs.toString());
		responseResult.setSuccess(true);
		responseResult.setResult(wechatPayJs);
		//支付
		return JsonUtil.toJson(responseResult);
	}
	
	@RequestMapping("/list.htm")
	public String orderList(HttpSession session,ModelMap map){
		//用户
		User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
		//订单列表
		List<UserOrder> userOrders = userOrderService.getList(user.getId());
		List<OrderDetail> orderList = new ArrayList<OrderDetail>();
		for(UserOrder order : userOrders){
			OrderDetail orderDetail = new OrderDetail();
			BeanUtils.copyProperties(order, orderDetail);
			List<ShopCarCommodity> commodityList = userOrderService.getOrderCommodity(order.getOrderNo());
			for(ShopCarCommodity shopCarCommodity : commodityList){
				shopCarCommodity.setImg(washPropertyUtil.getProperty("sever_path") + shopCarCommodity.getImg());
			}
			orderDetail.setCommodityList(commodityList);
			orderList.add(orderDetail);
		}
		map.addAttribute("orderList", orderList);
		return "/index/orderList";
	}
	
	@ResponseBody
	@RequestMapping("/ajax/cancleOrder.htm")
	public Object cancleOrder(HttpSession session,Integer id){
		//用户
		User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
		
		userOrderService.cancleOrder(user.getId(),id);
		
		ResponseResult<Map<String, Object>> responseResult = new ResponseResult<Map<String,Object>>();
		responseResult.setSuccess(true);
		
		String mapJson = JsonUtil.toJson(responseResult);
		
		return mapJson;
	}
	
	@RequestMapping("/orderDetail.htm")
	public String orderDetail(HttpSession session,ModelMap map,Integer orderId){
		//用户
		User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
		//订单详情
		UserOrder userOrder = userOrderService.getOrder(user.getId(),orderId);		
		OrderDetail orderDetail = new OrderDetail();
		BeanUtils.copyProperties(userOrder, orderDetail);
		List<ShopCarCommodity> commodityList = userOrderService.getOrderCommodity(userOrder.getOrderNo());
		for(ShopCarCommodity shopCarCommodity : commodityList){
			shopCarCommodity.setImg(washPropertyUtil.getProperty("sever_path") + shopCarCommodity.getImg());
		}
		orderDetail.setCommodityList(commodityList);
		//地址
		UserAddress userAddress = userAddressService.getAddressById(orderDetail.getAddressId());
		map.addAttribute("userAddress", userAddress);
		map.addAttribute("orderDetail", orderDetail);
		return "/index/orderDetail";
	}
	
	@ResponseBody
	@RequestMapping("/ajax/repayOrder.htm")
	public Object repayOrder(Integer orderId, HttpSession session,
			ModelMap map){
		ResponseResult<WechatPayJs> responseResult = new ResponseResult<WechatPayJs>();
		//用户
		User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
		UserOrder userOrder;
		try {
			userOrder = userOrderService.createRepayOrder(user.getId(),orderId,user.getOpenId());
		} catch (WxPrepayException e) {
			logger.error("创建订单失败！",e);
			responseResult.setSuccess(false);
			return JsonUtil.toJson(responseResult);
		}
		if (userOrder == null) {
			responseResult.setSuccess(false);
			return JsonUtil.toJson(responseResult);
		}
		WechatPayJs wechatPayJs = new WechatPayJs();
		wechatPayJs.setAppId(WechatPayConfig.getAppid());
		wechatPayJs.setSignType("MD5");
		wechatPayJs.setTimeStamp(new String(Calendar.getInstance().getTimeInMillis() + "").substring(0, 10));
		wechatPayJs.setPackageString("prepay_id=" + userOrder.getPrepayId());
		wechatPayJs.setNonceStr(30);
		wechatPayJs.setPaySign(WechatPayConfig.getKey(),"MD5");
		logger.info("微信js参数" + wechatPayJs.toString());
		responseResult.setSuccess(true);
		responseResult.setResult(wechatPayJs);
		//支付
		return JsonUtil.toJson(responseResult);
	}
}
