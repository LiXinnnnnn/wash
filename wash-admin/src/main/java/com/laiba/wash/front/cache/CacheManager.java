package com.laiba.wash.front.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;



/**
 * 虚拟机内缓存实现
 * @author patrick
 *
 */
@Component("cacheManager")
public class CacheManager implements InitializingBean{
	
		
	private static HashMap<String,Object> map = new HashMap<>();

	@Override
	public void afterPropertiesSet() throws Exception {
		freshPropertiesList();
		
	}
	
	/**
	 * 克隆对象
	 * @param obj
	 * @return
	 */
	private Object deepClone(Object obj){
		try{
			//将对象写到流里 
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			//从流里读出来
			ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bis);
			Object ret = ois.readObject();
			return ret;
		}catch(Exception ex){
			return null;
		}
	}
	
	public synchronized void freshPropertiesList(){
		/*List<PropertiesModel> propertiesList = propertiesService.getList();
		map.put("propertiesList", propertiesList);*/
	}	
	


	
}
