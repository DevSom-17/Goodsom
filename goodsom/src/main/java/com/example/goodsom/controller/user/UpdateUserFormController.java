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
		cardBanks.add("신한");
		cardBanks.add("하나");
		cardBanks.add("우리");
		cardBanks.add("농협");
		cardBanks.add("국민"); 
		// add more
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
		
		userForm.getUser().setPasswd(userForm.getNewPassword());
		
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
