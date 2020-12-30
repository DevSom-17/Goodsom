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
import org.springframework.web.util.WebUtils;

import com.example.goodsom.service.UserService;
import com.example.goodsom.validator.UserUpdateValidator;

/**
 * @author Yejin Lee | Seonmi Hwang
 * @since 2020.05.07 | 2020.06.21
 */

@Controller
@RequestMapping("/user/update.do")
public class UpdateUserFormController {

	@Value("user/user_update")
	private String formViewName;
	@Value("user/user_detail")
	private String successViewName;
	
	private static final int FAIL = 0;

	
	@Autowired
	private UserService userService;
	
	
	@ModelAttribute("userForm")
	public UserForm formBackingObject(HttpServletRequest request) throws Exception {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		return new UserForm(userService.getUserByEmail(userSession.getUser().getEmail()));
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
			@ModelAttribute("userForm") UserForm userForm, Model model, BindingResult bindingResult) throws Exception {
		
		if (!userForm.isOriginPasswordMatched(userForm.getRepeatedOriginPassword())) {
			bindingResult.reject("notMatchOriginPassword", new Object[] {}, null);
			return formViewName; 
		}
		
		new UserUpdateValidator().validate(userForm, bindingResult);
		
		// 검증 오류 발생 시 다시 form view로 이동
		if (bindingResult.hasErrors()) { 
			return formViewName; 
		}
		
		if (userForm.getNewPassword().length() > 0) {
			System.out.println("패스워드가 바뀌셨다");
			System.out.println(userForm.getNewPassword());
			userForm.getUser().setPasswd(userForm.getNewPassword());
		} else {
			userForm.getUser().setPasswd(userForm.getRepeatedOriginPassword());
		}
		
		int result = userService.updateUser(userForm.getUser());
		
		if (result == FAIL) {
			model.addAttribute("updateComplete", FAIL);
			return successViewName;
		}
		
		UserSession userSession = new UserSession(userService.getUserByEmail(userForm.getUser().getEmail()));
		session.setAttribute("userSession", userSession);
		return successViewName;
	}
}
