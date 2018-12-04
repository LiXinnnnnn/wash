package com.laiba.wash.core.enums;

import java.util.ArrayList;
import java.util.List;

public enum ColorEnum {
	Color010("#63b359"),Color020("#2c9f67"),Color030("#509fc9"),Color040("#5885cf"),Color050("#9062c0")
	,Color060("#d09a45"),Color070("#e4b138"),Color080("#ee903c"),Color081("#f08500"),Color082("#a9d92d")
	,Color090("#dd6549"),Color100("#cc463d"),Color101("#cf3e36"),Color102("#5E6671");
	
	// 定义变量
    private String value;

    // 构造函数，枚举类型只能为私有
    private ColorEnum(String value) {
        this.value = value;
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static List<String> getColorList(){
		List<String> colorList = new ArrayList<String>();
		ColorEnum[] colorEnums = ColorEnum.values();
		for(ColorEnum colorEnum : colorEnums){
			String color = colorEnum.getValue();
			colorList.add(color);
		}
		return colorList;
	}
	
	public static String getRgb(String color){
		int r = Integer.parseInt(color.substring(1, 3),16);
		int g = Integer.parseInt(color.substring(3, 5),16);
		int b = Integer.parseInt(color.substring(5, 7),16);
		String rgb = "RGB("+r+","+g+","+b+")";
		return rgb;
	}
	
}
