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

import com.example.goodsom.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
	@Autowired
	JavaMailSender emailSender;
	
	@Value("${AdminMail.id}")
	private String emailId;
	
	public static String ePw; // 8자리 키 생성
	
	public MimeMessage createMessage(String to) throws Exception{
		 System.out.println("보내는 대상 : "+ to);
		 ePw = createKey();
		 System.out.println("인증 번호 : "+ePw);
		 MimeMessage  message = emailSender.createMimeMessage();
		 	
		 	message.addRecipients(RecipientType.TO, to); //보내는 대상
	        message.setSubject("Goodsom 회원가입 인증번호가 도착했습니다."); //제목
	        
	        String msgg="";
	        msgg+= "<div style='margin:100px;'>";
	       	msgg+= "<h1> 안녕하세요 Goodsom입니다. </h1>";
	        msgg+= "<br>";
	        msgg+= "<p>아래 코드를 회원가입 창에 입력해주세요<p>";
	        msgg+= "<br>";
	        msgg+= "<p>감사합니다!<p>";
	        msgg+= "<br>";
			msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
			msgg+= "<h3 style='color:blue;'>회원가입 코드입니다.</h3>";
			msgg+= "<div style='font-size:130%'>";
			msgg+= "CODE : <strong>";
			msgg+= ePw+"</strong><div><br/> ";
			msgg+= "</div>";
	        message.setText(msgg, "utf-8", "html");//내용
	        message.setFrom(new InternetAddress(emailId, "Goodsom"));//보내는 사람
	        
	        return message;
	    }
//		인증코드 만들기
		public static String createKey() {
			StringBuffer key = new StringBuffer();
			Random rnd = new Random();

			for (int i = 0; i < 8; i++) { // 인증코드 8자리
				int index = rnd.nextInt(3); // 0~2 까지 랜덤

				switch (index) {
				case 0: //  a~z  (ex. 1+97=98 => (char)98 = 'b')
					key.append((char) ((int) (rnd.nextInt(26)) + 97));
					break;
				case 1: //  A~Z 
					key.append((char) ((int) (rnd.nextInt(26)) + 65));
					break;
				case 2: //  0~9
					key.append((rnd.nextInt(10)));
					break;
				}
			}

			return key.toString();
		}

		

	@Override
	public void sendSimpleMessage(String to) throws Exception {
		MimeMessage message = createMessage(to);
        try{//예외처리
        	emailSender.send(message);
        }catch(MailException es){
        	es.printStackTrace();
            throw new IllegalArgumentException();
        }

	}
}
