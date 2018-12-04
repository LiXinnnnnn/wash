package com.laiba.wash.core.service;

import com.laiba.wash.core.po.Operator;


/**
 * 操作员service接口
 * @author jerry
 *
 */
public interface OperatorService {
	/**
	 * 登录判断
	 * @param loginName
	 * @param password
	 * @return
	 */
	public Operator login(String loginName,String password);
	/**
	 * 修改密码
	 * @param id
	 * @param newPassword
	 */
	public int updatePassword(Integer id, String newPassword);
	
	
	
}
