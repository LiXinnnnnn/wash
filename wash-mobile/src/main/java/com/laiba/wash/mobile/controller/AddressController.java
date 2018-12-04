package com.laiba.wash.mobile.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laiba.wash.core.po.User;
import com.laiba.wash.core.po.UserAddress;
import com.laiba.wash.core.service.UserAddressService;
import com.laiba.wash.core.util.JsonUtil;
import com.laiba.wash.mobile.constant.FrontContant;
import com.laiba.wash.mobile.front.dto.ResponseResult;

@Controller
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private UserAddressService userAddressService;
	
	@RequestMapping("/list.htm")
	public String addressList(HttpSession session,ModelMap map){
		//用户
		User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
		//查询地址列表
		List<UserAddress> addressList = userAddressService.addressList(user.getId());
		map.addAttribute("addressList", addressList);
		return "/index/addressList";
	}
	
	@ResponseBody
	@RequestMapping("/ajax/deleteAddress.htm")
	public Object deleteAddress(HttpSession session,Integer addressId){
		//用户
		User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
		userAddressService.deleteAddress(user.getId(),addressId);
		
		ResponseResult responseResult = new ResponseResult();
		responseResult.setSuccess(true);
		
		String mapJson = JsonUtil.toJson(responseResult);
		
		return mapJson;
	}
	
	@ResponseBody
	@RequestMapping("/ajax/setDefaultAddress.htm")
	public Object setDefaultAddress(HttpSession session,Integer addressId){
		//用户
		User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
		userAddressService.setDefaultAddress(user.getId(),addressId);
		
		ResponseResult responseResult = new ResponseResult();
		responseResult.setSuccess(true);
		
		String mapJson = JsonUtil.toJson(responseResult);
		
		return mapJson;
	}
	
	@RequestMapping("/add.htm")
	public String addAddress(HttpSession session,ModelMap map){
	
		return "/index/addAddress";
	}
	
	@ResponseBody
	@RequestMapping("/ajax/add.htm")
	public Object addAddressAjax(HttpSession session,ModelMap map,UserAddress userAddress){
		//用户
		User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
		userAddress.setUserId(user.getId());
		userAddressService.addAddress(userAddress);
		
		ResponseResult responseResult = new ResponseResult();
		responseResult.setSuccess(true);
		
		String mapJson = JsonUtil.toJson(responseResult);
		return mapJson;
	}
	
	@RequestMapping("/selectAddress.htm")
	public String selectAddress(HttpSession session,ModelMap map,Integer addressId,String url){
		//用户
		User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
		//查询地址列表
		List<UserAddress> addressList = userAddressService.addressList(user.getId());
		map.addAttribute("addressList", addressList);
		
		url = url.replace("addressId", "beforeAddressId");
		//
		map.addAttribute("addressId", addressId);
		map.addAttribute("url", url);
		return "/index/selectAddress";
	}

}
