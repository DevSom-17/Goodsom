package com.example.goodsom.controller.scrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.service.AuctionService;
import com.example.goodsom.service.GroupBuyService;
import com.example.goodsom.service.ScrapService;

/**
 * @author kimdahyee
 * @since 05.08.2020
 */

//@Controller
public class DeleteScrapController {
	/*
	@Autowired
	private ScrapService scrapService;
	
	@Autowired
	private AuctionService auctionService;
	
	@Autowired
	private GroupBuyService groupBuyService;
	
	@RequestMapping("scrap_a/delete.do")
	public ModelAndView handleRequest (
			@RequestParam("auctionId") int auctionId) {
		scrapService.deleteScrap_a(auctionId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("auction/auction_detail");
		mav.addObject("auction", auctionService.getAuction(auctionId));
		return mav;
	}
	
	@RequestMapping("scrap_g/delete.do")
	public ModelAndView handleRequest2 (
			@RequestParam("groupBuyId") int groupBuyId) {
		scrapService.deleteScrap_g(groupBuyId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("groupBuy/groupBuy_detail");
		mav.addObject("groupBuy", groupBuyService.getGroupBuy(groupBuyId));
		return mav;
	}
	*/
}
