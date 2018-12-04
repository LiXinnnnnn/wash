package com.laiba.wash.mobile.util;

public class MailThread implements Runnable{
	
	private Mail mail;
	
	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	@Override
	public void run() {
		
		MailUtil.send(mail);
		
		
	}

}
