package com.laiba.wash.front.dto;

import com.laiba.wash.core.po.Commodity;

public class CommodityDto extends Commodity{
	
	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
