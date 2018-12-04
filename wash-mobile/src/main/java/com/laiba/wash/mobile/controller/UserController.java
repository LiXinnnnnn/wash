package com.laiba.wash.mobile.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laiba.wash.core.po.User;
import com.laiba.wash.core.service.ShopCarService;
import com.laiba.wash.mobile.constant.FrontContant;

@Controller
@RequestMapping("/user")
public class UserController {	
	
	@Autowired
	private ShopCarService shopCarService;
	
	@RequestMapping("/userIndex.htm")
	public String userIndex(HttpSession session,ModelMap map){
		
		//用户
		User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
		
		//购物车件数
		int shopCarCount = shopCarService.countShopCar(user.getId());
		map.addAttribute("shopCarCount", shopCarCount);
		return "/index/userIndex";
	}

}
