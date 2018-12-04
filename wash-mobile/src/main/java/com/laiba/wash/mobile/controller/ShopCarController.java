package com.laiba.wash.mobile.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laiba.wash.core.model.ShopCarCommodity;
import com.laiba.wash.core.po.ShopCar;
import com.laiba.wash.core.po.User;
import com.laiba.wash.core.service.ShopCarService;
import com.laiba.wash.core.util.JsonUtil;
import com.laiba.wash.core.util.WashPropertyUtil;
import com.laiba.wash.mobile.constant.FrontContant;
import com.laiba.wash.mobile.front.dto.ResponseResult;

@Controller
@RequestMapping("/shopCar")
public class ShopCarController {
	
	private static Logger logger = LoggerFactory.getLogger(ShopCarController.class);
	
	@Autowired
	private ShopCarService shopCarService;
	
	@Autowired
	private WashPropertyUtil washPropertyUtil;
	
	@ResponseBody
	@RequestMapping("/ajax/addCommodity.htm")
	public String addCommodity(Integer commodityId,HttpSession session){
		try {
			//用户
			User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
			
			ShopCar shopCar = shopCarService.selectByUserAndCommodity(user.getId(),commodityId);
			if (shopCar == null) {
				//新增
				shopCar = new ShopCar();
				shopCar.setCommodityId(commodityId);
				shopCar.setCreateTime(new Date());
				shopCar.setNumber(1);
				shopCar.setStatus(1);
				shopCar.setUserId(user.getId());
				shopCarService.insert(shopCar);
			}else {
				//修改
				shopCar.setNumber(shopCar.getNumber() + 1);
				shopCarService.update(shopCar);
			}
			int shopCarCount = shopCarService.countShopCar(user.getId());
			String result = "{'success':'true','shopCarCount':'"+shopCarCount+"'}";
			return result;
		} catch (Exception e) {
			logger.error("/ajax/addCommodity.htm"+e);
			String result = "{'success':'false'}";
			return result;
		}
		
	}
	
	@RequestMapping("/list.htm")
	public String shopCarList(ModelMap map,HttpSession session){
		//用户
		User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
		
		//购物车件数
		int shopCarCount = shopCarService.countShopCar(user.getId());
		map.addAttribute("shopCarCount", shopCarCount);
		
		//购物车商品
		List<ShopCarCommodity> commodityList = shopCarService.shopCarCommodityList(user.getId());
		
		//购物车总金额
		double totalCount = 0;
		for(ShopCarCommodity commodity : commodityList){
			totalCount += commodity.getPrice()*commodity.getNumber();
			commodity.setImg(washPropertyUtil.getProperty("sever_path") + commodity.getImg());
		}
		map.addAttribute("commodityList", commodityList);
		String commodityListJson = JsonUtil.toJson(commodityList);
		map.addAttribute("commodityListJson", commodityListJson);
		map.addAttribute("totalCount", totalCount);
		
		return "/index/shoppingCar";
	}
	
	@ResponseBody
	@RequestMapping("/ajax/changeNumber.htm")
	public Object changeNumber(HttpSession session,Integer addOrminus,Integer commodityId){
		//用户
		User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
		
		Map<String, Object> map = new HashMap<String, Object>();
		//更新购物车商品数量
		ShopCar shopCar = shopCarService.selectByShopCarId(user.getId(), commodityId);
		shopCar.setNumber(shopCar.getNumber() + addOrminus);
		shopCarService.update(shopCar);
		map.put("commodityNumber", shopCar.getNumber());
		//购物车商品
		List<ShopCarCommodity> commodityList = shopCarService.shopCarCommodityList(user.getId());
		//购物车总金额
		Double totalCount = 0.0;
		for(ShopCarCommodity commodity : commodityList){
			totalCount += commodity.getPrice()*commodity.getNumber();
			if (commodity.getId().equals(commodityId)) {
				Double commodityPrice = commodity.getNumber() * commodity.getPrice();
				commodityPrice = new BigDecimal(commodityPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				map.put("commodityPrice", commodityPrice);
			}
		}
		totalCount = new BigDecimal(totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		map.put("totalCount", totalCount);
		
		ResponseResult<Map<String, Object>> responseResult = new ResponseResult<Map<String,Object>>();
		responseResult.setSuccess(true);
		responseResult.setResult(map);
		
		String mapJson = JsonUtil.toJson(responseResult);
		
		return mapJson;
	}
	
	@ResponseBody
	@RequestMapping("/ajax/deleteCommodity.htm")
	public Object deleteCommodity(HttpSession session,Integer commodityId){
		//用户
		User user = (User) session.getAttribute(FrontContant.SESSION_OPERATOR);
		
		shopCarService.deleteCommodity(user.getId(), commodityId);
		
		ResponseResult<Map<String, Object>> responseResult = new ResponseResult<Map<String,Object>>();
		responseResult.setSuccess(true);
		
		String mapJson = JsonUtil.toJson(responseResult);
		
		return mapJson;
	}


}
