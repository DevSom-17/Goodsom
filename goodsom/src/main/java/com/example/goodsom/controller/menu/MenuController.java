package com.example.goodsom.controller.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public ModelAndView goHome(Model model) throws Exception{
		List<GroupBuy> recentGroupBuy = groupBuyService.getBestGroupBuyList();
		List<Auction> recentAuction = auctionService.getBestAuctionList();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("recentGroupBuy", recentGroupBuy);
		mav.addObject("recentAuction", recentAuction);
		
		mav.setViewName(formViewName); // home 이동
		
		return mav;
	}
}
