package com.laiba.wash.mobile.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laiba.wash.core.po.Commodity;
import com.laiba.wash.core.po.CommodityCategory;
import com.laiba.wash.core.po.User;
import com.laiba.wash.core.service.CommodityService;
import com.laiba.wash.core.service.ShopCarService;
import com.laiba.wash.core.service.UserService;
import com.laiba.wash.core.util.WashPropertyUtil;
import com.laiba.wash.mobile.constant.FrontContant;

@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ShopCarService shopCarService;
	@Autowired
	private CommodityService commodityService;
	
	@Autowired
	private WashPropertyUtil washPropertyUtil;
	
	@RequestMapping("/test.htm")
	public String test(){
		return "/control/error";
	}
	
	@RequestMapping("/index.htm")
	public String index(HttpSession session,ModelMap map)  throws Exception{
		//用户
		User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
		
		//购物车件数
		int shopCarCount = shopCarService.countShopCar(user.getId());
		map.addAttribute("shopCarCount", shopCarCount);
		
		//商品
		List<CommodityCategory> commodityCategoryList = commodityService.getCategoryList();
		Map<Integer, List<Commodity>> commodityMap = new HashMap<Integer, List<Commodity>>();
		for(CommodityCategory commodityCategory : commodityCategoryList){
			List<Commodity> commodityList = commodityService.getCommodityList(commodityCategory.getId());
			for(Commodity commodity : commodityList){
				commodity.setImg(washPropertyUtil.getProperty("sever_path") + commodity.getImg());
			}
			commodityMap.put(commodityCategory.getId(), commodityList);
		}
		map.addAttribute("commodityCategoryList", commodityCategoryList);
		map.addAttribute("commodityMap", commodityMap);
		
		return "/index/index";
	}
	
	
}
