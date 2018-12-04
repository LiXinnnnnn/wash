package com.laiba.wash.core.service;

import java.util.List;

import com.laiba.wash.core.po.UserAddress;

public interface UserAddressService {

	public UserAddress getDefaultAddress(Integer userId,Integer addressId);

	public List<UserAddress> addressList(Integer userId);

	public void deleteAddress(Integer userId, Integer addressId);

	public void setDefaultAddress(Integer userId, Integer addressId);

	public void addAddress(UserAddress userAddress);

	public UserAddress getAddressById(Integer addressId);

}
