package com.example.goodsom.service.impl;

import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.goodsom.service.NotiMailService;

@Service
public class NotiMailServiceImpl implements NotiMailService{
	@Autowired
	JavaMailSender emailSender;
	
	@Value("${AdminMail.id}")
	private String emailId;
	
	public MimeMessage auctionMessage(String to) throws Exception{
		System.out.println("경매 낙찰 : "+ to);
		MimeMessage  message = emailSender.createMimeMessage();
		 	
	 	message.addRecipients(RecipientType.TO, to); //보내는 대상
        message.setSubject("Goodsom 참여하신 경매가 낙찰되었습니다."); //제목
        
        String msgg="";
        msgg+= "<div style='margin:100px;'>";
       	msgg+= "<h1> 안녕하세요 Goodsom입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>축하드립니다!<p>";
        msgg+= "<p>참여하신 경매가 낙찰되었습니다. 결제를 진행해주세요!<p>";
        msgg+= "<p style='color:red;'>(결제를 진행하지 않을 시, 경고가 누적됩니다.)<p>";
        // 바로가기
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress(emailId, "Goodsom"));//보내는 사람
        
        return message;
	}

	@Override
	public void sendAuctionMessage(String to) throws Exception {
		MimeMessage message = auctionMessage(to);
        try{//예외처리
        	emailSender.send(message);
        }catch(MailException es){
        	es.printStackTrace();
            throw new IllegalArgumentException();
        }

	}
	
	public MimeMessage groupbuyCloseMessage(String to) throws Exception{
		System.out.println("공동구매 마감 : "+ to);
		MimeMessage  message = emailSender.createMimeMessage();
		 	
	 	message.addRecipients(RecipientType.TO, to); //보내는 대상
        message.setSubject("Goodsom 참여하신 공동구매가 마감되었습니다."); //제목
        
        String msgg="";
        msgg+= "<div style='margin:100px;'>";
       	msgg+= "<h1> 안녕하세요 Goodsom입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>참여하신 공동구매가 마감되었습니다.<p>";
        msgg+= "<p>달성 여부를 확인한 뒤, 결제를 진행해주세요!<p>";
        msgg+= "<p style='color:red;'>(성사된 공동구매에 대해 결제를 진행하지 않을 시, 경고가 누적됩니다.)<p>";
        // 바로가기
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress(emailId, "Goodsom"));//보내는 사람
        
        return message;
	}
	
	public MimeMessage groupbuyAchieveMessage(String to) throws Exception{
		System.out.println("공동구매 성사 : "+ to);
		MimeMessage  message = emailSender.createMimeMessage();
		 	
	 	message.addRecipients(RecipientType.TO, to); //보내는 대상
        message.setSubject("Goodsom 참여하신 공동구매가 성사되었습니다."); //제목
        
        String msgg="";
        msgg+= "<div style='margin:100px;'>";
       	msgg+= "<h1> 안녕하세요 Goodsom입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>축하드립니다!<p>";
        msgg+= "<p>참여하신 공동구매가 성사되었습니다. 결제를 진행해주세요!<p>";
        msgg+= "<p style='color:red;'>(결제를 진행하지 않을 시, 경고가 누적됩니다.)<p>";
        // 바로가기
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress(emailId, "Goodsom"));//보내는 사람
        
        return message;
	}
	
	@Override
	public void sendGroupBuyCloseMessage(String to) throws Exception {
		MimeMessage message = groupbuyCloseMessage(to);
        try{//예외처리
        	emailSender.send(message);
        }catch(MailException es){
        	es.printStackTrace();
            throw new IllegalArgumentException();
        }

	}
	
	@Override
	public void sendGroupBuyAchieveMessage(String to) throws Exception {
		MimeMessage message = groupbuyAchieveMessage(to);
        try{//예외처리
        	emailSender.send(message);
        }catch(MailException es){
        	es.printStackTrace();
            throw new IllegalArgumentException();
        }

	}
}
