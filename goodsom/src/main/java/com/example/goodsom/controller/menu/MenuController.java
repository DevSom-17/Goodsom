package com.example.goodsom.controller.menu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.goodsom.controller.user.UserSession;
import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.service.AuctionService;
import com.example.goodsom.service.GroupBuyService;

/**
 * @author Hyekyung Kim
 * @since 2020.05.05
 */


@Controller
public class MenuController {
	
	@Value("index")
	private String formViewName;
	
	@Autowired
	private GroupBuyService groupBuyService;
	@Autowired
	private AuctionService auctionService;
	
	@RequestMapping("/index.do")
	public ModelAndView goHome(HttpServletRequest request, Model model) throws Exception{
		
		UserSession user  = (UserSession)request.getSession().getAttribute("userSession");
		int loginUserId = user.getUser().getUserId();
		
		List<GroupBuy> recentGroupBuy = groupBuyService.getBestGroupBuyList(loginUserId);
		List<Auction> recentAuction = auctionService.getBestAuctionList(loginUserId);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("recentGroupBuy", recentGroupBuy);
		mav.addObject("recentAuction", recentAuction);
		mav.addObject("loginUserId", loginUserId);
		
		mav.setViewName(formViewName); // home 이동
		
		return mav;
	}
}
