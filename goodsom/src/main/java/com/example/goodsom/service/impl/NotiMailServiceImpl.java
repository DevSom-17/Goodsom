package com.example.goodsom.service.impl;

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
	
	// 경매
	// 경매 참여자
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
	
	// 경매 작성자
	public MimeMessage auctionWriterMessage(String to) throws Exception{
		System.out.println("경매 작성자 : "+ to);
		MimeMessage  message = emailSender.createMimeMessage();
		 	
	 	message.addRecipients(RecipientType.TO, to); //보내는 대상
        message.setSubject("Goodsom 등록하신 경매가 마감되었습니다."); //제목
        
        String msgg="";
        msgg+= "<div style='margin:100px;'>";
       	msgg+= "<h1> 안녕하세요 Goodsom입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>등록하신 경매가 마감되었습니다.<p>";
        msgg+= "<p>낙찰 여부를 확인한 뒤, 거래를 진행해주세요.<p>";
        msgg+= "<p style='color:red;'>(결제된 물품에 대해 거래를 진행하지 않을 시, 경고가 누적됩니다.)<p>";
        // 바로가기
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress(emailId, "Goodsom"));//보내는 사람
        
        return message;
	}

	@Override
	public void sendAuctionWriterMessage(String to) throws Exception {
		MimeMessage message = auctionWriterMessage(to);
        try{ //예외처리
        	emailSender.send(message);
        }catch(MailException es){
        	es.printStackTrace();
            throw new IllegalArgumentException();
        }

	}
	
	// 공동구매
	// 공동구매 참여자 마감
	public MimeMessage groupBuyCloseMessage(String to) throws Exception{
		System.out.println("공동구매 마감 : "+ to);
		MimeMessage  message = emailSender.createMimeMessage();
		 	
	 	message.addRecipients(RecipientType.TO, to); //보내는 대상
        message.setSubject("Goodsom 참여하신 공동구매가 마감되었습니다."); //제목
        
        String msgg="";
        msgg+= "<div style='margin:100px;'>";
       	msgg+= "<h1> 안녕하세요 Goodsom입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>참여하신 공동구매가 마감되었습니다.<p>";
        msgg+= "<p>달성 여부를 확인해주세요!<p>";
        // 바로가기
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress(emailId, "Goodsom"));//보내는 사람
        
        return message;
	}
	
	@Override
	public void sendGroupBuyCloseMessage(String to) throws Exception {
		MimeMessage message = groupBuyCloseMessage(to);
        try{//예외처리
        	emailSender.send(message);
        }catch(MailException es){
        	es.printStackTrace();
            throw new IllegalArgumentException();
        }

	}
	
	// 공동구매 참여자 성사
	public MimeMessage groupBuyAchieveMessage(String to) throws Exception{
		System.out.println("공동구매 성사 : "+ to);
		MimeMessage  message = emailSender.createMimeMessage();
		 	
	 	message.addRecipients(RecipientType.TO, to); //보내는 대상
        message.setSubject("Goodsom 참여하신 공동구매가 성사되었습니다."); //제목
        
        String msgg="";
        msgg+= "<div style='margin:100px;'>";
       	msgg+= "<h1> 안녕하세요 Goodsom입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>축하드립니다!<p>";
        msgg+= "<p>참여하신 공동구매가 성사되었습니다. 진행 상태를 확인해주세요!<p>";
        // 바로가기
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress(emailId, "Goodsom"));//보내는 사람
        
        return message;
	}

	
	@Override
	public void sendGroupBuyAchieveMessage(String to) throws Exception {
		MimeMessage message = groupBuyAchieveMessage(to);
        try{//예외처리
        	emailSender.send(message);
        }catch(MailException es){
        	es.printStackTrace();
            throw new IllegalArgumentException();
        }

	}

	// 공동구매 작성자 마감
	public MimeMessage groupBuyWriterCloseMessage(String to) throws Exception{
		System.out.println("공동구매 작성자 마감 : "+ to);
		MimeMessage  message = emailSender.createMimeMessage();
		 	
		message.addRecipients(RecipientType.TO, to); //보내는 대상
        message.setSubject("Goodsom 등록하신 공동구매가 마감되었습니다."); //제목
        
        String msgg="";
        msgg+= "<div style='margin:100px;'>";
       	msgg+= "<h1> 안녕하세요 Goodsom입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>등록하신 공동구매가 마감되었습니다.<p>";
        msgg+= "<p>달성 여부를 확인한 뒤, 거래를 진행해주세요!<p>";
        msgg+= "<p style='color:red;'>(성사된 공동구매에 대해 거래를 진행하지 않을 시, 경고가 누적됩니다.)<p>";
        // 바로가기
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress(emailId, "Goodsom"));//보내는 사람
        
        return message;
	}

	
	@Override
	public void sendGroupBuyWriterCloseMessage(String to) throws Exception {
		MimeMessage message = groupBuyWriterCloseMessage(to);
        try{//예외처리
        	emailSender.send(message);
        }catch(MailException es){
        	es.printStackTrace();
            throw new IllegalArgumentException();
        }
	}
	
	// 공동구매 작성자 성사
	public MimeMessage groupBuyWriterAchieveMessage(String to) throws Exception{
		System.out.println("공동구매 작성자 성사 : "+ to);
		MimeMessage  message = emailSender.createMimeMessage();
		 	
	 	message.addRecipients(RecipientType.TO, to); //보내는 대상
        message.setSubject("Goodsom 등록하신 공동구매가 성사되었습니다."); //제목
        
        String msgg="";
        msgg+= "<div style='margin:100px;'>";
       	msgg+= "<h1> 안녕하세요 Goodsom입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>축하드립니다!<p>";
        msgg+= "<p>등록하신 공동구매가 성사되었습니다. 거래를 진행해주세요!<p>";
        msgg+= "<p style='color:red;'>(성사된 공동구매에 대해 거래를 진행하지 않을 시, 경고가 누적됩니다.)<p>";
        // 바로가기
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress(emailId, "Goodsom"));//보내는 사람
        
        return message;
	}

	
	@Override
	public void sendGroupBuyWriterAchieveMessage(String to) throws Exception {
		MimeMessage message = groupBuyWriterAchieveMessage(to);
        try{//예외처리
        	emailSender.send(message);
        }catch(MailException es){
        	es.printStackTrace();
            throw new IllegalArgumentException();
        }

	}
}
