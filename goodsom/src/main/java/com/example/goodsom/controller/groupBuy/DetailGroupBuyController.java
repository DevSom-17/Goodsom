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
import com.example.goodsom.service.LikeService;
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
	@Autowired
	LikeService likeService;
	
	// home -> list
	// form -> list : detail에서 취소 후 왔을 때 해당정보 유지
	@RequestMapping("/groupBuy/list.do")
	public ModelAndView groupBuyDetail(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(GROUPBUY_LIST);
		List<GroupBuy> groupBuyList = null;
		
		UserSession user  = (UserSession)request.getSession().getAttribute("userSession");
		int loginUserId = user.getUser().getUserId();
		mav.addObject("loginUserId", loginUserId);
		// db
		groupBuyList = groupBuyService.getGroupBuyList(loginUserId);
		
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
		int loginUserId = user.getUser().getUserId();
		
		// db : option & groupBuy
		GroupBuy groupBuy = groupBuyService.getGroupBuy(groupBuyId);
		System.out.println("groupBuyID: " + groupBuyId);
		System.out.println("groupBuy: " + groupBuy.toString());
		System.out.println("user: " + loginUserId);
		
		// 작성자가 아닐때 조회수 증가
		if(loginUserId != groupBuy.getUserId()) {
			groupBuyService.increaseCount(groupBuy);
		}		
		
		// 작성자인지 여부 
		if (loginUserId == groupBuy.getUserId()) {
			model.addAttribute("isWriter", true);
		} else {
			model.addAttribute("isWriter", false);
		}
		
//		해당 경매의 좋아요 수
		groupBuy.setLikeCount(likeService.getLikeCountOfGroupBuy(groupBuyId));
//		사용자가 like했는지 안 했는지
		mav.addObject("loginUserId", loginUserId);
		int result = likeService.likeCheckOfGroupBuyByUserId(loginUserId, groupBuyId);
		if (result == 1) {
			mav.addObject("like", true);
		} else if (result == 0) {
			mav.addObject("like", false);
		} else {
			System.out.println("[GroupBuyDetail]likeService.likeCheckOfGroupBuyByUserId()오류!");
			mav.addObject("like", false);
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
