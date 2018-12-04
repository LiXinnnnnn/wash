package com.laiba.wash.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laiba.wash.core.dao.mapper.UserMapper;
import com.laiba.wash.core.dao.mapper.WxAccessTokenMapper;
import com.laiba.wash.core.po.User;
import com.laiba.wash.core.po.UserExample;
import com.laiba.wash.core.po.WxAccessToken;
import com.laiba.wash.core.po.WxAccessTokenExample;
import com.laiba.wash.core.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private WxAccessTokenMapper wxAccessTokenMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public WxAccessToken getWxAccessToken() {
		Date date = new Date();
		WxAccessTokenExample example = new WxAccessTokenExample();
		WxAccessTokenExample.Criteria criteria = example.createCriteria();
		criteria.andStartTimeLessThanOrEqualTo(date);
		criteria.andEndTimeGreaterThanOrEqualTo(date);
		List<WxAccessToken> list = wxAccessTokenMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public User getUserByOpenId(String openId) {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andOpenIdEqualTo(openId);
		List<User> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public void insertUser(User user) {
		userMapper.insertSelective(user);
	}

	@Override
	public void updateAccessToken(WxAccessToken accessToken) {
		List<WxAccessToken> list = wxAccessTokenMapper.selectByExample(null);
		WxAccessToken wxAccessToken = list.get(0);
		accessToken.setId(wxAccessToken.getId());
		wxAccessTokenMapper.updateByPrimaryKey(accessToken);
		
	}

	@Override
	public User getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(userId);
	}
	
	
}
