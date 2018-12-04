package com.laiba.wash.front.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.laiba.wash.core.po.Commodity;
import com.laiba.wash.core.po.CommodityCategory;
import com.laiba.wash.core.service.CommodityService;
import com.laiba.wash.core.util.PageInfo;
import com.laiba.wash.core.util.WashPropertyUtil;
import com.laiba.wash.front.constant.FrontContant;
import com.laiba.wash.front.dto.CommodityDto;
import com.laiba.wash.front.util.UUIDGenerator;

@Controller
@RequestMapping("/commodity")
public class CommodityController {
	
	@Autowired
	private CommodityService commodityService;
	@Autowired
	private WashPropertyUtil washPropertyUtil;
	
	private static Logger logger = LoggerFactory.getLogger(CommodityController.class);
	
	@RequestMapping("/list")
	public String commodityList(Integer categoryId,Integer currentPage, Integer pageSize,ModelMap map){
		//分页
		PageInfo<Commodity> pageInfo = new PageInfo<Commodity>();
		pageInfo.setCurrentPage(currentPage == null ? 1:currentPage);
		pageInfo.setPageSize(pageSize == null ? FrontContant.PAGE_SIZE:pageSize);
		//查询条件保持
		map.addAttribute("categoryId", categoryId);
		//查询条件
		List<CommodityCategory> categoryList = commodityService.getCategoryList();
		map.addAttribute("categoryList", categoryList);
		//查询结果
		pageInfo = commodityService.getCommodityList(categoryId,pageInfo);
		List<CommodityDto> commodityList = new ArrayList<CommodityDto>();
		for(Commodity commodity : pageInfo.getResult()){
			//
			CommodityDto commodityDto = new CommodityDto();
			BeanUtils.copyProperties(commodity, commodityDto);
			//分类名称
			CommodityCategory category = commodityService.getCategoryById(commodity.getCategoryId());
			if (category != null) {
				commodityDto.setCategory(category.getName());
			}
			commodityDto.setImg(washPropertyUtil.getProperty("sever_path") + commodityDto.getImg());
			commodityList.add(commodityDto);
		}
		//
		map.addAttribute("commodityList", commodityList);
		map.addAttribute("pageInfo", pageInfo);
		return "/commodity/list";
	}
	
	@RequestMapping("/ajax/delete.htm")
	public String delete(Integer id,ModelMap map){
		//删除
		int result = commodityService.delete(id);
		if (result > 0) {
			map.addAttribute("result", "{'success':'true','msg':'删除成功！'}");
			return "/control/blank";
		}else {
			map.addAttribute("result", "{'success':'false','msg':'删除失败！'}");
			return "/control/blank";
		}
	}
	
	@RequestMapping("/add")
	public String add(ModelMap map){
		
		List<CommodityCategory> categoryList = commodityService.getCategoryList();
		map.addAttribute("categoryList", categoryList);
		map.addAttribute(FrontContant.NAVIGATOR_LAST_MENU_KEY, "新增商品");
		return "/commodity/add";		
	}
	
	/**
	 * 上传方法
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("/ajax/uploadify.htm")
	public String uploadify(HttpServletRequest request) throws IOException {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("Filedata");
		
		String fileNameLast = multipartFile.getOriginalFilename().substring(
				multipartFile.getOriginalFilename().lastIndexOf("."),
				multipartFile.getOriginalFilename().length());
		String uuid = UUIDGenerator.getUUID();
		String fileName = uuid + fileNameLast;
		String filePath = washPropertyUtil.getProperty("file_path");
		File file = new File(filePath + fileName);
		// 保存
		try {
			multipartFile.transferTo(file);
		} catch (Exception e) {
			logger.error("文件保存错误", e);
			return "";
		}
		return fileName;
	}

	@RequestMapping("/ajax/add")
	public String ajaxAdd(ModelMap map,Commodity commodity){
		int result = commodityService.insertCommodity(commodity);
		if (result > 0) {
			map.addAttribute("result", "{'success':'true','msg':'新增成功！'}");
			return "/control/blank";
		}else {
			map.addAttribute("result", "{'success':'false','msg':'新增失败！'}");
			return "/control/blank";
		}
	}
}
