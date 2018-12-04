package com.laiba.wash.core.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laiba.wash.core.dao.mapper.UserAddressMapper;
import com.laiba.wash.core.po.UserAddress;
import com.laiba.wash.core.po.UserAddressExample;
import com.laiba.wash.core.service.UserAddressService;

@Service("userAddressService")
public class UserAddressServiceImpl implements UserAddressService{
	
	@Autowired
	private UserAddressMapper userAddressMapper;

	@Override
	public UserAddress getDefaultAddress(Integer userId,Integer addressId) {
		UserAddressExample example = new UserAddressExample();
		UserAddressExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		if (addressId != null) {
			criteria.andIdEqualTo(addressId);
		}
		criteria.andStatusEqualTo(1);
		example.setOrderByClause(" is_default desc, create_time desc  ");
		List<UserAddress> list = userAddressMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<UserAddress> addressList(Integer userId) {
		UserAddressExample example = new UserAddressExample();
		UserAddressExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		
		criteria.andStatusEqualTo(1);
		example.setOrderByClause(" is_default desc, create_time desc  ");
		List<UserAddress> list = userAddressMapper.selectByExample(example);
		return list;
	}

	@Override
	public void deleteAddress(Integer userId, Integer addressId) {
		UserAddressExample example = new UserAddressExample();
		UserAddressExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andIdEqualTo(addressId);
		criteria.andStatusEqualTo(1);
		
		UserAddress userAddress = new UserAddress();
		userAddress.setStatus(0);
		userAddress.setUpdateTime(new Date());
		
		userAddressMapper.updateByExampleSelective(userAddress, example);
	}

	@Override
	public void setDefaultAddress(Integer userId, Integer addressId) {
		UserAddressExample example = new UserAddressExample();
		UserAddressExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andStatusEqualTo(1);
		
		UserAddress userAddress = new UserAddress();
		userAddress.setIsDefault(0);
		userAddress.setUpdateTime(new Date());
		userAddressMapper.updateByExampleSelective(userAddress, example);
		
		userAddress.setIsDefault(1);
		userAddress.setId(addressId);
		userAddressMapper.updateByPrimaryKeySelective(userAddress);
		
	}

	@Override
	public void addAddress(UserAddress userAddress) {
		userAddress.setCreateTime(new Date());
		userAddress.setStatus(1);
		userAddress.setIsDefault(0);
		userAddressMapper.insert(userAddress);
	}

	@Override
	public UserAddress getAddressById(Integer addressId) {
		
		return userAddressMapper.selectByPrimaryKey(addressId);
	}
	
	
	
}
