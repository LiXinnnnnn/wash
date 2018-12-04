
package com.laiba.wash.api.util;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
	
	/**
	 * 反射 obj to map summer
	 * @param <T>
	 * @param <T>
	 * @param e
	 * @return
	 * @throws Exception
	 */
    public static <T> Map<String, Object> reflectDto2Map(T e) throws Exception{
    	
    	Class<? extends Object> cls = e.getClass(); 
        Field[] fields = cls.getDeclaredFields();
    	Map<String, Object> map = new HashMap<String, Object>();
        for(int i=0; i<fields.length; i++){  
            Field f = fields[i];  
            f.setAccessible(true);
            if(f.get(e) == null ||  StringUtils.isBlank(f.get(e).toString())){
            	continue;
            }
            map.put(f.getName(), f.get(e));
        }
        return map;
    } 
    
    /**
	 * 反射 obj to map summer
	 * @param <T>
	 * @param <T>
	 * @param e
	 * @return
	 * @throws Exception
	 */
    public static <T> Map<String, Object> reflectDto2Map2(T e) throws Exception{
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	Class<? extends Object> cls = e.getClass(); 
        Field[] fields = cls.getDeclaredFields();
        for(int i=0; i<fields.length; i++){  
            Field f = fields[i];  
            f.setAccessible(true);
            if(f.get(e) == null ||  StringUtils.isBlank(f.get(e).toString())){
            	continue;
            }
            map.put(f.getName(), f.get(e));
        }
        Class<? extends Object> clsSup = e.getClass().getSuperclass();
        if(clsSup == null ){
        	return map;
        }
        Field[] fieldsSup = clsSup.getDeclaredFields();
        if(fieldsSup == null || fieldsSup.length <= 0){
        	return map;
        }
        for(int i=0; i<fieldsSup.length; i++){
            Field f = fieldsSup[i];  
            f.setAccessible(true);
            if(f.get(e) == null ||  StringUtils.isBlank(f.get(e).toString())){
            	continue;
            }
            map.put(f.getName(), f.get(e));
        }
        return map;
    } 
    
    /**
   	 * 反射 obj to map summer
   	 * @param <T>
   	 * @param <T>
   	 * @param e
   	 * @return
   	 * @throws Exception
   	 */
       public static <T> Map<String, Object> reflectDto2Map3(T e) throws Exception{
       	
       	Map<String, Object> map = new HashMap<String, Object>();
       	Class<? extends Object> cls = e.getClass(); 
           Field[] fields = cls.getDeclaredFields();
           for(int i=0; i<fields.length; i++){  
               Field f = fields[i];  
               f.setAccessible(true);
               if(f.get(e) == null){
               	continue;
               }
               map.put(f.getName(), f.get(e));
           }
           Class<? extends Object> clsSup = e.getClass().getSuperclass();
           if(clsSup == null ){
           	return map;
           }
           Field[] fieldsSup = clsSup.getDeclaredFields();
           if(fieldsSup == null || fieldsSup.length <= 0){
           	return map;
           }
           for(int i=0; i<fieldsSup.length; i++){
               Field f = fieldsSup[i];  
               f.setAccessible(true);
               if(f.get(e) == null){
               		continue;
               }
               map.put(f.getName(), f.get(e));
           }
           return map;
       }
    
    public static void main(String[] args) throws Exception{
    	

		
    }
    
	
}
