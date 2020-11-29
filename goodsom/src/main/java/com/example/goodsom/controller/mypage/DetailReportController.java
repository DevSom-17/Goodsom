package com.example.goodsom.controller.mypage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.example.goodsom.controller.user.UserSession;
import com.example.goodsom.service.UserService;

/**
 * @author Seonmi-Hwang
 * @since 2020.11.25
 */

@Controller
public class DetailReportController {
	private static final String detailViewName = "user/report_detail";
	
	@Autowired
	UserService userService; 
	
	@ModelAttribute("reportForm")
	public ReportForm formBackingObject(HttpServletRequest request) throws Exception {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		return userService.getReportList(userSession.getUser().getUserId());
	}
	
	@RequestMapping("/mypage/report/detail.do")
	public String showForm() {
		return detailViewName;
	}
}
