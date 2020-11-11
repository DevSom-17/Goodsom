package com.example.goodsom.controller.scrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.controller.user.UserSession;
import com.example.goodsom.service.ScrapService;

/**
 * @author kimdahyee
 * @since 05.08.2020
 */

//@Controller
public class CreateScrapController {
	/*
	@Autowired
	private ScrapService scrapService;
	
	@RequestMapping("/scrap_a/create.do")
	public ModelAndView handleRequest(
			@RequestParam("auctionId") int auctionId,
			@ModelAttribute("userSession") UserSession userSession) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("aution/auction_detail");
		mav.addObject("scrap_a", scrapService.createScrap_a(auctionId));
		return mav;
	}
	
	@RequestMapping("/scrap_g/create.do")
	public ModelAndView handleRequest2(
			@RequestParam("groupBuyId") int groupBuyId,
			@ModelAttribute("userSession") UserSession userSession) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("groupBuy/groupBuy_detail");
		mav.addObject("scrap_g", scrapService.createScrap_g(groupBuyId));
		return mav;
	}
	*/
}
