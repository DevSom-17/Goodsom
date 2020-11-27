package com.example.goodsom.service;

import javax.mail.internet.MimeMessage;

public interface EmailService {

	public MimeMessage createMessage(String to) throws Exception;
	
	public void sendSimpleMessage(String to) throws Exception;
}
