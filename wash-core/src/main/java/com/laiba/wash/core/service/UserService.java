package com.laiba.wash.core.service;

import com.laiba.wash.core.po.User;
import com.laiba.wash.core.po.WxAccessToken;

public interface UserService {
	
	public WxAccessToken getWxAccessToken();
	
	public User getUserByOpenId(String openId);

	public void insertUser(User user);

	public void updateAccessToken(WxAccessToken accessToken);

	public User getUserById(Integer userId);

}
//{"access_token":"GEk_L90Gxm5OTi6RYQppck7iGT1yLRlfvtp9ivFHpdUm9a7m3wYmxDag9PW4D7XGxkK-CV2_0XXnikcR4cTjgIHAOz1f17c-2wbQp0vVy8LiemPya_SOpR46rFLHH5-HJMJaAHANQN","expires_in":7200}