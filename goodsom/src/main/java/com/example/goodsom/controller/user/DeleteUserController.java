package com.example.goodsom.controller.user;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.domain.User;
import com.example.goodsom.service.UserService;

/**
 * @author Yejin Lee | Seonmi-Hwang
 * @since 2020.05.07 | 2020.06.21
 */


@Controller
public class DeleteUserController {
	
	private static final String formViewName = "user/login";
	private static final String detailViewName = "user/user_detail";
	
	private static final int NOT_ALLOWED = -1;
	private static final int FAIL = 0;
	private static final int SUCCESS = 1;

	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/user/delete.do")
	public ModelAndView handleRequest(HttpSession session,
			HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		User user = userSession.getUser();
		
		// 작성한 경매, 공동구매 중에 마감되지 않은 것이 있다면 회원탈퇴 불가
		if (userService.isUnClosedExist(user.getUserId())) {
			mav.addObject("deleteComplete", NOT_ALLOWED);
			mav.addObject("userForm", new UserForm(userService.getUserByEmail(userSession.getUser().getEmail())));
			mav.setViewName(detailViewName);
			return mav;
		} 
		
		// User 삭제
		int result = userService.deleteUser(userSession.getUser());
		
		if (result != SUCCESS) {
			mav.addObject("deleteComplete", FAIL);
			mav.setViewName(detailViewName);
		} else {
			mav.setViewName(formViewName);
			mav.addObject("loginForm", new LoginForm());
			mav.addObject("deleteComplete", SUCCESS);
			session.removeAttribute("userSession");
			session.invalidate();
		}
		return mav;
	}
	
}
