package com.example.goodsom.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.goodsom.service.UserService;
import com.example.goodsom.validator.RegisterValidator;

/**
 * @author kimdahyee | Seonmi Hwang
 * @since 2020.06.14 | 2020.06.28
 */

@Controller
@RequestMapping("/user/register.do")
public class RegisterUserFormController {
	
	@Value("/user/register")
	private String formViewName;
	@Value("/user/login")
	private String successViewName;
	
	@Autowired
	private UserService userService;

	
	@ModelAttribute("userForm")
	public UserForm formBackingObject(HttpServletRequest request) throws Exception {
		return new UserForm();
	}
	
	@ModelAttribute("cardBanks")
	public List<String> cardBanksData() {
		ArrayList<String> cardBanks = new ArrayList<String>();
		cardBanks.add("하나");
		cardBanks.add("국민");
		cardBanks.add("신한");
		cardBanks.add("농협"); 
		cardBanks.add("우리"); 
		cardBanks.add("기업"); 
		cardBanks.add("카카오뱅크");
		cardBanks.add("새마을금고");
		cardBanks.add("신협");
		cardBanks.add("대구");
		cardBanks.add("부산");
		cardBanks.add("경남");
		cardBanks.add("광주");
		cardBanks.add("전북");
		cardBanks.add("제주");
		cardBanks.add("산업");
		cardBanks.add("수협중앙회");
		cardBanks.add("한국씨티");
		cardBanks.add("SC제일");
		cardBanks.add("HSBC");
		cardBanks.add("도이치뱅크");
		cardBanks.add("BOA");
		cardBanks.add("JP모간");
		cardBanks.add("중국공상");
		cardBanks.add("BNP파리바");
		cardBanks.add("우체국");
		cardBanks.add("케이뱅크");
		cardBanks.add("산림조합");
		cardBanks.add("저축");
		cardBanks.add("중국");
		cardBanks.add("중국건설");
		return cardBanks;			
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return formViewName;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request, HttpSession session,
			@ModelAttribute("userForm") UserForm userForm, BindingResult result, Model model) throws Exception {
		
		new RegisterValidator().validate(userForm, result);
		
		// 같은 이메일 아이디가 이미 존재할 경우 다시 form 띄움
		if (userService.getUserByEmail(userForm.getUser().getEmail()) != null) {
			result.reject("sameEmailExist", new Object[] {}, null);
			return formViewName;
		}
		
		if (result.hasErrors()) {
			return formViewName;
		} else {
			userService.createUser(userForm.getUser());
			model.addAttribute("loginForm", new LoginForm());
			return successViewName;
		}
	}

}
