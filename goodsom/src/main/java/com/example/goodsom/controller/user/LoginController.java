package com.example.goodsom.controller.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index.do")
public class LoginController {
	@Value("index")
	private String formViewName;

	@RequestMapping(method = RequestMethod.GET)
	public String form() {
		return formViewName;
	}
}
