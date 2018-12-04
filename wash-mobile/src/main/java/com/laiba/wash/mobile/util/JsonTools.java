/*
 * ProjectName:  wot-core
 * ApiJsonTools.java
 * cn.wsn.wot.core.util
 *
 * Function： TODO 
 *
 *   ver     date      		author       email
 * ──────────────────────────────────────────────────────
 *   		 2013年9月12日 	guoqiang     guoqiang@wsn.cn
 *
 * Copyright (c) 2013, WSN All Rights Reserved.
*/
package com.laiba.wash.mobile.util;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

/**
 * 
 * @author patrick
 *
 */
public class JsonTools {

	/**
	 * 把json字符串转换成List
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static List<?> convertJson2List(String json, Class<?> clazz) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		JavaType valueType = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
		return mapper.readValue(json, valueType);
	}
	
	/**
	 * 把json转换成Object对象
	 * convertJson2Obj
	 * @param json
	 * @param clazz
	 * @return
	 * @throws Exception 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	public static Object convertJson2Obj(String json, Class<?> clazz) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, clazz);
	}
	
	/**
	 * 把Object对象转换成json并写入输出流
	 * writeObj2Json
	 * @param os
	 * @param obj
	 * @throws Exception 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public static void writeObj2Json(OutputStream os, Object obj) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(os, obj);
		return;
	}
	
	/**
	 * 把各种Object转换成json字符串
	 * Object可以是POJO、Map、List
	 * @param value
	 * @return
	 */
	public static String convertObject2String(Object value) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(value);
	}
	
}
