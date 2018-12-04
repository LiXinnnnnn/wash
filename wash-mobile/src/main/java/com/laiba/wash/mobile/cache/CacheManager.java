package com.laiba.wash.mobile.cache;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;



/**
 * 虚拟机内缓存实现
 * @author patrick
 *
 */
@Component("cacheManager")
public class CacheManager implements InitializingBean{

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/*@Autowired
	private PropertiesService propertiesService;
		
	private static HashMap<String,Object> map = new HashMap<>();

	@Override
	public void afterPropertiesSet() throws Exception {
		freshPropertiesList();
		
	}
	
	*//**
	 * 克隆对象
	 * @param obj
	 * @return
	 *//*
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
		List<PropertiesModel> propertiesList = propertiesService.getList();
		map.put("propertiesList", propertiesList);
	}
	
	*//**
	 * 得到配置列表
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public List<PropertiesModel> getPropertiesList(){
		Object obj = map.get("propertiesList");
		if(obj == null){
			return null;
		}
		List<PropertiesModel> propertiesList = (List<PropertiesModel>) obj;
		return (List<PropertiesModel>)deepClone(propertiesList); 
	}
	
	*//**
	 * 根据名称得到值
	 * @param name
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public String getPropertiesByName(String name){
		Object obj = map.get("propertiesList");
		if(obj == null){
			return null;
		}
		List<PropertiesModel> propertiesList = (List<PropertiesModel>) obj;
		for(PropertiesModel propertiesModel : propertiesList){
			if (name.equals(propertiesModel.getName())) {
				return (String) deepClone(propertiesModel.getValue());
			}
		}
		return null;
	}
	
	*//**
	 * 根据id得到对象
	 * @param id
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public PropertiesModel getPropertiesById(Integer id){
		Object obj = map.get("propertiesList");
		if(obj == null){
			return null;
		}
		List<PropertiesModel> propertiesList = (List<PropertiesModel>) obj;
		for(PropertiesModel propertiesModel : propertiesList){
			if (id.equals(propertiesModel.getId())) {
				return (PropertiesModel) deepClone(propertiesModel);
			}
		}
		return null;
	}
*/
	
}
