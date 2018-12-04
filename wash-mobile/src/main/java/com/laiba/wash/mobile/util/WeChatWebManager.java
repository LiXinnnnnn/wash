package com.laiba.wash.mobile.util;

import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.laiba.wash.core.po.WxAccessToken;
import com.laiba.wash.mobile.front.dto.SNSUserInfo;

/**
 * Created by admin on 2016/8/29.
 */
public class WeChatWebManager {

    static Logger logger = Logger.getLogger(WeChatWebManager.class.getName());
    
    /**
     * 1.获取微信返回code的 url
     *
     * @param snsapi_userinfo
     * @param url
     * @param query
     * @return code
     * @throws Exception
     */
    public static String getWeChatCodeUrl(String snsapi_userinfo, String url, String query, String state) throws Exception {
        String callBackUrl = url;
        if (!StringUtils.isEmpty(query)) {
            callBackUrl += "?" + query;
        }
        callBackUrl = callBackUrl.replace(":8080", "");
        callBackUrl = URLEncoder.encode(callBackUrl, "UTF-8");
        logger.info("calllBackUrl: " + callBackUrl);
        /**第一步：获取微信返回code*/
        String redirectUrl = WeiXinConfig.WEIXIN_OAUTH2_CODE + "?appid="
                + WeiXinConfig.WEIXIN_APPID + "&redirect_uri="
                + callBackUrl + "&response_type=code&scope=" + snsapi_userinfo
                + "&state=" + state + "#wechat_redirect";

        return redirectUrl;
    }

    /**
     * 获取微信openid
     * @param code
     * @return
     */
    public static String getOpenIdByCode(String code) {
		try {
			String appid = WeiXinConfig.WEIXIN_APPID;
			String secret = WeiXinConfig.WEIXIN_APPSECRET;
			String getAccesstokenUrl = WeiXinConfig.WEIXIN_ACCESS_TOKEN_OAUTH2
					+ "?appid=" + appid + "&secret=" + secret + "&code=" + code
					+ "&grant_type=authorization_code";
			HttpResult httpResult = HttpClientUtils.doGet(getAccesstokenUrl);
			logger.info("----获取微信openId------: "+httpResult.getBody());
			if (httpResult.isSuccess()) {
				Map<String, String> resultMap = JsonUtil
						.jsonToStringMap(httpResult.getBody());
//				Integer expiresIn = Integer.parseInt(resultMap
//						.get("expires_in"));
//				Date startDate = new Date();
//				Calendar calendar = Calendar.getInstance();
//				calendar.setTime(startDate);
//				calendar.add(Calendar.SECOND, expiresIn);
//				wxAccessToken.setStartTime(startDate);
//				wxAccessToken.setAccessToken(resultMap.get("access_token"));
				String openId = resultMap.get("openid");
				return openId;
			}
		} catch (Exception e) {
			logger.error("getAccessTokenOAuth error!", e);
		}
		return null;
	}
    
    public static WxAccessToken getAccessToken() {
		try {
			String appid = WeiXinConfig.WEIXIN_APPID;
			String secret = WeiXinConfig.WEIXIN_APPSECRET;
			String getAccesstokenUrl = WeiXinConfig.WEIXIN_ACCESSTOKEN_URL
					+ "&appid=" + appid + "&secret=" + secret;
			HttpResult httpResult = HttpClientUtils.doGet(getAccesstokenUrl);
			logger.info("----获取微信accesstoken------: "+httpResult.getBody());
			if (httpResult.isSuccess()) {
				Map<String, String> resultMap = JsonUtil
						.jsonToStringMap(httpResult.getBody());
				WxAccessToken wxAccessToken = new WxAccessToken();
				Integer expiresIn = Integer.parseInt(resultMap
						.get("expires_in"));
				Date startDate = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(startDate);
				calendar.add(Calendar.SECOND, expiresIn);
				Date endTime = calendar.getTime();
				wxAccessToken.setStartTime(startDate);
				wxAccessToken.setEndTime(endTime);
				wxAccessToken.setAccessToken(resultMap.get("access_token"));
				return wxAccessToken;
			}
		} catch (Exception e) {
			logger.error("getAccessTokenOAuth error!", e);
		}
		return null;
	}

    /**
     * 微信网页授权 获取用户信息
     *
     * @param access_token
     * @return
     */
    public static SNSUserInfo getWeiXinUserInfo(String access_token, String openId) throws Exception {
        String url = WeiXinConfig.WEIXIN_USERINFO + "?access_token=" + access_token + "&openid=" + openId + "&lang=zh_CN";
        HttpResult httpResult = HttpClientUtils.doGet(url);
        SNSUserInfo snsUserInfo = new SNSUserInfo();
        logger.info("----获取微信用户资料------: "+httpResult.getBody());
        if (httpResult.isSuccess()) {
            String result = new String(httpResult.getBody().getBytes("ISO_8859_1"), "utf-8");
            Map<String, Object> resultMap = JsonUtil.jsonToMap(result);
            if (resultMap.get("errcode") != null) {
                return null;
            }
            String nickname = (String) resultMap.get("nickname");
            String openid = (String) resultMap.get("openid");
            double sex = (double) resultMap.get("sex");
            String province = (String) resultMap.get("province");
            String city = (String) resultMap.get("city");
            String headimgurl = (String) resultMap.get("headimgurl");
//            List privilege = (List) resultMap.get("privilege");
            String unionid = (String) resultMap.get("unionid");
            snsUserInfo.setNickname(nickname);
            snsUserInfo.setCity(city);
            snsUserInfo.setOpenId(openid);
            snsUserInfo.setSex((int) sex);
            snsUserInfo.setProvince(province);
            snsUserInfo.setHeadImgUrl(headimgurl);
            snsUserInfo.setUnionid(unionid);
//            snsUserInfo.setPrivilegeList(privilege);
        }
        return snsUserInfo;
    }


}
