package com.laiba.wash.mobile.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MailUtil {
	
	private static Logger logger = LoggerFactory.getLogger(MailUtil.class); 
	  
    public static boolean send(Mail mail) {  
        // 发送email  
        HtmlEmail email = new HtmlEmail();  
        try {  
            // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"  
            email.setHostName(mail.getHost());  
            // 字符编码集的设置  
            email.setCharset(Mail.ENCODEING);  
            // 收件人的邮箱  
            email.addTo(mail.getReceiver()); 
            // 发送人的邮箱  
            email.setFrom(mail.getSender(), mail.getName());  
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码  
            email.setAuthentication(mail.getUsername(), mail.getPassword());  
            // 要发送的邮件主题  
            email.setSubject(mail.getSubject());  
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签  
            email.setMsg(mail.getMessage());  
            // 发送  
            email.send();  
            
            return true;  
        } catch (EmailException e) {  
            logger.error(mail.getSender() + " 发送邮件失败" , e);  
            return false;  
        }  
    }    
    
    public static void main(String[] args) {
		Mail mail = new Mail();
		mail.setHost("smtp.163.com");
		mail.setReceiver(new String[]{"823523036@qq.com"});
		mail.setSender("18036122391@163.com");
		mail.setName("订单通知");
		mail.setUsername("18036122391@163.com");
		mail.setPassword("liuning159753");
		mail.setSubject("主题");
		mail.setMessage("test");
		MailUtil.send(mail);
	}

}
