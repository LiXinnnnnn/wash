package com.laiba.wash.mobile.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.laiba.wash.core.service.UserOrderService;
import com.laiba.wash.core.service.wechat.report.WxPayReportResponse;
import com.laiba.wash.core.util.WashPropertyUtil;
import com.laiba.wash.mobile.util.Mail;
import com.laiba.wash.mobile.util.MailThread;


@Controller
public class WxPayController {
	
	@Autowired
	private UserOrderService userOrderService;
	@Autowired
	private WashPropertyUtil washPropertyUtil;
	
	private static Logger log = LoggerFactory.getLogger(WxPayController.class);
	
	@RequestMapping(value = "/wxpayReport.htm", method = RequestMethod.POST)
	public void reply(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 处理微信用户的消息
			InputStream inputStream = request.getInputStream();
			WxPayReportResponse wxPayReportResponse = new WxPayReportResponse(inputStream);
			log.info(wxPayReportResponse.responseString());
			if (wxPayReportResponse.getReturn_code().equals("SUCCESS") ) {
				
				String outTradeNo = wxPayReportResponse.getOut_trade_no();
				String transactionId = wxPayReportResponse.getTransaction_id();
				userOrderService.payOrder(outTradeNo,transactionId);
				
				//发送邮件
				Mail mail = new Mail();
				mail.setHost(washPropertyUtil.getProperty("mail.host"));
				mail.setReceiver(new String[]{washPropertyUtil.getProperty("mail.receiver")});
				mail.setSender(washPropertyUtil.getProperty("mail.sender"));
				mail.setName(washPropertyUtil.getProperty("mail.name"));
				mail.setUsername(washPropertyUtil.getProperty("mail.username"));
				mail.setPassword(washPropertyUtil.getProperty("mail.password"));
				mail.setSubject("订单通知");
				mail.setMessage("您又有新的订单了，快去看看吧~");
				MailThread mailThread = new MailThread();
				mailThread.setMail(mail);
				Thread thread = new Thread(mailThread);
				thread.start();
				
			}
			String xml = "<xml><return_code><![CDATA[SUCCESS]]></return_code>"
					+ "<return_msg><![CDATA[OK]]></return_msg></xml>";
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(xml.getBytes());
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			log.error("reply:" , e);
		}
	}

}
