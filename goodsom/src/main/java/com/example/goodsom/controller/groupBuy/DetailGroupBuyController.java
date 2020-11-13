package com.example.goodsom.controller.groupBuy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.controller.user.UserSession;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.service.GroupBuyService;
import com.example.goodsom.service.UserService;

/**
 * @author Seonmi Hwang	| HK
 * @since 2020.05.04	| 2020.06.27
 */

@Controller
@SessionAttributes("lineGroupBuyForm")
public class DetailGroupBuyController {
	private static final String GROUPBUY_LIST = "groupBuy/groupBuy_list";
	private static final String GROUPBUY_DETAIL = "groupBuy/groupBuy_detail";
	
	@Autowired
	GroupBuyService groupBuyService;
	@Autowired
	UserService userService;
	
	// home -> list
	// form -> list : detail에서 취소 후 왔을 때 해당정보 유지
	@RequestMapping("/groupBuy/list.do")
	public ModelAndView groupBuyDetail(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(GROUPBUY_LIST);
		List<GroupBuy> groupBuyList = null;
		
		// db
		groupBuyList = groupBuyService.getGroupBuyList();
		
		if (groupBuyList == null) {
			System.out.println("[DetailGroupBuyController] groupBuyList가 null");
		} else {
			mav.addObject("groupBuyList", groupBuyList);
		}
		return mav;
	}
	
	// list -> detail
	@RequestMapping("/groupBuy/detail.do")
	public ModelAndView groupBuyDetail(HttpServletRequest request,
										@RequestParam("groupBuyId") int groupBuyId,
										Model model)	{
		ModelAndView mav = new ModelAndView(GROUPBUY_DETAIL);
		HttpSession session = request.getSession();
		UserSession user  = (UserSession)session.getAttribute("userSession");
		
		// db : option & groupBuy
		GroupBuy groupBuy = groupBuyService.getGroupBuy(groupBuyId);
		System.out.println("groupBuy: " + groupBuy.toString());
		System.out.println("user: " + user.getUser().getUserId());
		
		// 조회수 증가
		groupBuyService.increaseCount(groupBuy);
		
		// 작성자인지 여부 
		if (user.getUser().getUserId() == groupBuy.getUserId()) {
			model.addAttribute("isWriter", true);
		} else {
			model.addAttribute("isWriter", false);
		}
		
		// LineGroupBuyForm 값 지정
		LineGroupBuyForm lineGroupBuyForm = new LineGroupBuyForm();
		lineGroupBuyForm.setGroupBuyId(groupBuyId);
		lineGroupBuyForm.setGroupBuy(groupBuy);
		session.setAttribute("lineGroupBuyForm", lineGroupBuyForm);
		mav.addObject("lineGroupBuyForm", lineGroupBuyForm);
		
		mav.addObject("groupBuy", groupBuy);
		mav.addObject("writer", userService.getUserByUserId(groupBuy.getUserId()).getNickname());
		model.addAttribute("dDay", groupBuy.getDday(groupBuy.getEndDate().getTime()));
		
		 
		return mav;
	}
	
}
