package com.laiba.wash.core.service.wechat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;

import com.laiba.wash.core.util.DateUtil;

@Scope(value = "singleton")
public class WechatPayService implements InitializingBean {

	private static Log log = LogFactory.getLog(WechatPayService.class);

	private String filePath;

	/**
	 * 微信扫码付
	 * @param outTradeNo -- 微信订单号
	 * @param totalFee - 金额，分
	 * @param body - 订单详情
	 * @return
	 * @throws Exception
	 */
	public WechatPayResponse unifiedOrder(String outTradeNo, double totalFee,
			String body,String openid) throws Exception {
		
		WechatPayClient wechatPayClient = new WechatPayClient(WechatPayConfig.getKey(),
				WechatPayConfig.getUrl(), WechatPayConfig.getAppid(),
				WechatPayConfig.getMch_id(), WechatPayConfig.getNotify_url());
		// p.setSub_mch_id("");//子商户号
		wechatPayClient.setOut_trade_no(outTradeNo);// 商户订单号
		wechatPayClient.setTotal_fee((int) (totalFee * 100));// 金额
		wechatPayClient.setTrade_type("JSAPI");// 交易类型1
//		body = new String(body.getBytes(), "UTF-8");
		wechatPayClient.setBody(body);// 商品描述
		wechatPayClient.setOpenid(openid);
		wechatPayClient.setTime_start(DateUtil.parseDateToStr(new Date(), DateUtil.F_YYYYMMDD24HHMMSS));
		wechatPayClient.setTime_expire(DateUtil.parseDateToStr(new Date(), DateUtil.F_YYYYMMDD) + "235959");
		String xml = wechatPayClient.getXml("xml");
		log.info(xml);
		InputStream in = HttpClientUtil
				.sendXMLDataByPost(WechatPayConfig.getUrl(), xml).getEntity()
				.getContent();
		WechatPayResponse wxres = new WechatPayResponse(in);
		log.info(wxres.responseString());
		if (wxres.getReturn_code().equals("SUCCESS")) {
			return wxres;
		}
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// /app/webContent/ims-api/configs/
		WechatPayConfig.init(filePath);
		
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
