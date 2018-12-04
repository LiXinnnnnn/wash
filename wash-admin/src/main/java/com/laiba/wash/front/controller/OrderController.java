package com.laiba.wash.front.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laiba.wash.core.model.OrderDetail;
import com.laiba.wash.core.model.ShopCarCommodity;
import com.laiba.wash.core.po.User;
import com.laiba.wash.core.po.UserAddress;
import com.laiba.wash.core.po.UserOrder;
import com.laiba.wash.core.service.UserAddressService;
import com.laiba.wash.core.service.UserOrderService;
import com.laiba.wash.core.service.UserService;
import com.laiba.wash.core.util.PageInfo;
import com.laiba.wash.core.util.WashPropertyUtil;
import com.laiba.wash.front.constant.FrontContant;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private UserOrderService userOrderService;
	@Autowired
	private WashPropertyUtil washPropertyUtil;
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/list.htm")
	public String orderList(Integer status,String orderNo,Integer currentPage, Integer pageSize,ModelMap map){
		//分页
		PageInfo<UserOrder> pageInfo = new PageInfo<UserOrder>();
		pageInfo.setCurrentPage(currentPage == null ? 1:currentPage);
		pageInfo.setPageSize(pageSize == null ? FrontContant.PAGE_SIZE:pageSize);
		//查询条件保持
		map.addAttribute("status", status);
		map.addAttribute("orderNo", orderNo);
		//查询结果
		pageInfo = userOrderService.getList(status,orderNo,pageInfo);
		List<OrderDetail> orderList = new ArrayList<OrderDetail>();
		for(UserOrder userOrder : pageInfo.getResult()){
			OrderDetail orderDetail = new OrderDetail();
			BeanUtils.copyProperties(userOrder, orderDetail);
			List<ShopCarCommodity> commodityList = userOrderService.getOrderCommodity(orderDetail.getOrderNo());
			for(ShopCarCommodity shopCarCommodity : commodityList){
				shopCarCommodity.setImg(washPropertyUtil.getProperty("sever_path") + shopCarCommodity.getImg());
			}
			orderDetail.setCommodityList(commodityList);
			UserAddress userAddress = userAddressService.getAddressById(orderDetail.getAddressId());
			orderDetail.setUserAddress(userAddress);
			User orderUser = userService.getUserById(orderDetail.getUserId());
			orderDetail.setUser(orderUser);
			orderList.add(orderDetail);
		}
		
		map.addAttribute("orderList", orderList);
		map.addAttribute("pageInfo", pageInfo);
		return "/order/list";
	}
	
	@RequestMapping("/update")
	public String update(ModelMap map,Integer id){
		
		UserOrder userOrder = userOrderService.getOrder(null, id);
		OrderDetail orderDetail = new OrderDetail();
		BeanUtils.copyProperties(userOrder, orderDetail);
		List<ShopCarCommodity> commodityList = userOrderService.getOrderCommodity(orderDetail.getOrderNo());
		for(ShopCarCommodity shopCarCommodity : commodityList){
			shopCarCommodity.setImg(washPropertyUtil.getProperty("sever_path") + shopCarCommodity.getImg());
		}
		orderDetail.setCommodityList(commodityList);
		UserAddress userAddress = userAddressService.getAddressById(orderDetail.getAddressId());
		orderDetail.setUserAddress(userAddress);
		User orderUser = userService.getUserById(orderDetail.getUserId());
		orderDetail.setUser(orderUser);
		map.addAttribute(FrontContant.NAVIGATOR_LAST_MENU_KEY, "更新订单");
		map.addAttribute("orderDetail", orderDetail);
		return "/order/update";		
	}
	
	@RequestMapping("/ajax/update")
	public String ajaxUpdate(ModelMap map,UserOrder userOrder){
		
		//删除
		int result = userOrderService.updateOrder(userOrder);
		if (result > 0) {
			map.addAttribute("result", "{'success':'true','msg':'修改成功！'}");
			return "/control/blank";
		}else {
			map.addAttribute("result", "{'success':'false','msg':'修改失败！'}");
			return "/control/blank";
		}
	}
	

}
