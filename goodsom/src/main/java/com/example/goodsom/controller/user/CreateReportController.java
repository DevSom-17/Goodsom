package com.example.goodsom.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.goodsom.domain.User;
import com.example.goodsom.service.UserService;

@Controller
@RequestMapping("/report/create.do")
public class CreateReportController {
	@Value("user/report_create")
	private String formViewName;
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("contents")
	public List<String> contentsData() {
		ArrayList<String> contents = new ArrayList<String>();
		contents.add("욕설 및 비방");
		contents.add("거래파기");
		return contents;			
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm(HttpServletRequest request,
			@RequestParam(value="auctionId", required=false) Integer auctionId,
			@RequestParam(value="groupBuyId", required=false) Integer groupBuyId,
			@RequestParam("writerId") int writerId) {
		
		System.out.println("/report/create.do controller 실행");
		
		ModelAndView mav = new ModelAndView(formViewName);

		CreateReportForm reportForm = new CreateReportForm();
		
		if (auctionId != null) {
			reportForm.setId(auctionId);
			mav.addObject("type", "auction");
		} else {
			reportForm.setId(groupBuyId);
			mav.addObject("type", "groupBuy");
		}
		
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		reportForm.setReporterId(userSession.getUser().getUserId());
		reportForm.setUserId(writerId);
		
		mav.addObject("reportForm", reportForm);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(HttpServletRequest request,
			@ModelAttribute("reportForm") CreateReportForm reportForm,
			@RequestParam("type") String type) throws Exception {
		
		if (type.equals("auction")) {
			userService.createReport_a(reportForm);
		} else {
			userService.createReport_g(reportForm);
		}
		userService.updateReport(reportForm.getUserId());
		
		User user = userService.getUserByUserId(reportForm.getUserId()); // 신고 받은 회원의 정보
		if (user.getWarning() >= 3) { // 경고 3회 이상이면 자동 탈퇴
			userService.deleteUser(user);
		}
		
		System.out.println(reportForm);
		
		return new ModelAndView("redirect:/" + type + "/detail.do?" + type + "Id=" + reportForm.getId());
	}
}
