package com.laiba.wash.core.service.wechat;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WechatPayConfig {

	private static Log log = LogFactory.getLog(WechatPayConfig.class);
	private static Configuration configs;

	private static String url; // 微信接口地址
	private static String key; // 微信密钥
	private static String appid; // 公众账号ID
	private static String mch_id; // 商户号
	private static String notify_url;

	private WechatPayConfig() {
		// No Constructor
	}

	// 根据文件名读取配置文件，文件后缀名必须为.properties
	public synchronized static void init(String filePath) {
		if (configs != null) {
			return;
		}

		try {
			configs = new PropertiesConfiguration(filePath);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		if (configs == null) {
			throw new IllegalStateException("can`t find file by path:"
					+ filePath);
		}

		url = configs.getString("url");
		key = configs.getString("key");

		appid = configs.getString("appid");
		mch_id = configs.getString("mch_id");

		notify_url = configs.getString("notify_url");

		log.info("配置文件名: " + filePath);
		log.info(description());
	}

	public static String description() {
		StringBuilder sb = new StringBuilder("Wechat Pay Configs{");
		sb.append("微信扫码支付url: ").append(url).append("\n");
		sb.append(", 微信扫码支付密钥: ").append(getKeyDescription(key)).append("\n");

		sb.append(", appid: ").append(getKeyDescription(appid)).append("\n");
		sb.append(", mch_id: ").append(getKeyDescription(mch_id)).append("\n");

		return sb.toString();
	}

	private static String getKeyDescription(String key) {
		int showLength = 4;
		if (StringUtils.isNotEmpty(key) && key.length() > showLength) {
			return new StringBuilder(key.substring(0, showLength))
					.append("******")
					.append(key.substring(key.length() - showLength))
					.toString();
		}
		return null;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		WechatPayConfig.url = url;
	}

	public static String getKey() {
		return key;
	}

	public static void setKey(String key) {
		WechatPayConfig.key = key;
	}

	public static String getAppid() {
		return appid;
	}

	public static void setAppid(String appid) {
		WechatPayConfig.appid = appid;
	}

	public static String getMch_id() {
		return mch_id;
	}

	public static void setMch_id(String mch_id) {
		WechatPayConfig.mch_id = mch_id;
	}

	public static String getNotify_url() {
		return notify_url;
	}

	public static void setNotify_url(String notify_url) {
		WechatPayConfig.notify_url = notify_url;
	}

}
