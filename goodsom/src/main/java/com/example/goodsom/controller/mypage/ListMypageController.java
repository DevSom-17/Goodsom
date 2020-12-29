 package com.example.goodsom.controller.mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.controller.user.UserSession;
import com.example.goodsom.domain.Order;
import com.example.goodsom.service.AuctionService;
import com.example.goodsom.service.GroupBuyService;
import com.example.goodsom.service.OrderService;
import com.example.goodsom.service.UserService;

/**
 * @author Seonmi-Hwang	| Yejin Lee
 * @since  2020.06.14	| 2020.12.15
 */


@Controller
@SessionAttributes("userSession")
public class ListMypageController {
	
	@Autowired
	UserService userService;
	@Autowired
	OrderService orderService;
	@Autowired
	AuctionService auctionService;
	@Autowired
	GroupBuyService groupBuyService;
	
	@RequestMapping("/mypage/list.do")
	public ModelAndView handleRequest(
			@ModelAttribute("userSession") UserSession userSession,
			@RequestParam(required = false) Integer listType) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/mypage_list");
		int userId = userSession.getUser().getUserId();
		mav.addObject("loginUserId", userId);	// 좋아요를 위해 필요한 변수

		if (listType == null) {
			listType = 1;
		}
		mav.addObject("listType", listType); // 1 - 등록목록 / 2 - 결제목록 / 3 - 좋아요한 목록
		
		if (listType == 1) {
//		경매 등록 목록
			mav.addObject("auctionList", auctionService.getAuctionListByUserId(userId));
//		공동구매 등록 목록
			mav.addObject("groupBuyList", groupBuyService.getGroupBuyListByUserId(userId));
		} else if (listType == 2) {
			List<Order> auctionOrderList =  userService.getAuctionOrderList(userId);
			List<Order> groupBuyOrderList =  userService.getGroupBuyOrderList(userId);
			
			mav.addObject("auctionOrderList", orderService.setAuctionInfo(auctionOrderList));
			mav.addObject("groupBuyOrderList", orderService.setGroupBuyInfo(groupBuyOrderList));
		} else { 
//		좋아요를 누른 공동구매/경매 목록 가져오기 (listType == 3)
			mav.addObject("likedAuctionList", auctionService.getLikedAuctionListByUserId(userId));
			mav.addObject("likedGroupBuyList", groupBuyService.getLikedGroupBuyListByUserId(userId));
		}
		return mav;
	}
	
}
