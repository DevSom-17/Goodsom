package com.example.goodsom.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.goodsom.service.EmailService;
import com.example.goodsom.service.impl.EmailServiceImpl;

@Controller
@RequestMapping("/email")
public class EmailController {
	@Autowired
	EmailService emailService;
	
	@PostMapping("/send")
	@ResponseBody
	public void emailConfirm(@RequestBody String userId) throws Exception{
		System.out.println("이메일을 받은 사람: " + userId);
		emailService.sendSimpleMessage(userId);
	}
	
	@PostMapping("/verifyCode")
	@ResponseBody
	public int verifyCode(@RequestBody String code) {
		int result = 0;
		code = code.replaceAll("\"", "");
		System.out.println("code: " + code);
		System.out.println("code match: " + EmailServiceImpl.ePw.equals(code));
		if(EmailServiceImpl.ePw.equals(code)) {
			result = 1;
		}
		return result;
	}
	
}
