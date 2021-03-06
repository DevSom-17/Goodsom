
package com.example.goodsom.controller.user;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.User;
import com.example.goodsom.service.AuctionService;
import com.example.goodsom.service.GroupBuyService;
import com.example.goodsom.service.UserService;
import com.example.goodsom.validator.LoginFormValidator;


/**
 * @author kimdahyee | Seonmi Hwang | HK
 * @since 2020.06.12 | 2020.06.28   | 2020.06.28
 */

@Controller
@SessionAttributes("userSession")
@RequestMapping("/user/login.do")
public class LoginController {

	@Value("user/login")
	private String formViewName;

	@Autowired
	private UserService userService;
	@Autowired
	private GroupBuyService groupBuyService;
	@Autowired
	private AuctionService auctionService;
	
	@Autowired
	private Authenticator authenticator;

	
	@ModelAttribute("loginForm")
	public LoginForm formBacking(HttpServletRequest request) throws Exception {
		return new LoginForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String form() {
		return formViewName;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView handleRequest(HttpServletRequest request, HttpSession session,
			@ModelAttribute("loginForm") LoginForm loginForm, Model model, BindingResult bindingResult) throws Exception {
		new LoginFormValidator().validate(loginForm, bindingResult);

		// 검증 오류 발생 시 다시 form view로 이동
		if (bindingResult.hasErrors()) {
			return new ModelAndView(formViewName);
		}

		User user = userService.getUserByEmail(loginForm.getEmail());
		
//		System.out.println("LoginForm : " + loginForm);
		
		try {
			authenticator.authenticate(loginForm); // email과 password가 맞는지 검증
			UserSession userSession = new UserSession(user);
			session.setAttribute("userSession", userSession);
			int loginUserId = userSession.getUser().getUserId();
			
			List<GroupBuy> recentGroupBuy = groupBuyService.getBestGroupBuyList(loginUserId);
			List<Auction> recentAuction = auctionService.getBestAuctionList(loginUserId);

			model.addAttribute("recentGroupBuy", recentGroupBuy);
			model.addAttribute("recentAuction", recentAuction);
			model.addAttribute("loginUserId", loginUserId);
			
			return new ModelAndView("redirect:../index.do");
		} catch (AuthenticationException e) { // 검증 실패 시
			
			ModelAndView mav = new ModelAndView();
			bindingResult.reject(e.getMessage(), new Object[] { loginForm.getEmail() }, null); // error message
			mav.addObject("loginForm", loginForm);
			mav.setViewName(formViewName); // login form 이동
			return mav;
		}
		
	}
}
