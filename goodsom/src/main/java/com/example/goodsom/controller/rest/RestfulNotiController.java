package com.example.goodsom.controller.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.service.AuctionService;
import com.example.goodsom.service.GroupBuyService;
import com.example.goodsom.service.NotiService;

/**
 * @author HK
 * @since  2020.6.30
 */

@Controller
public class RestfulNotiController {
	
	@Autowired
	private NotiService notiService;
	
	@Autowired
	private AuctionService auctionService;
	
	@Autowired
	private GroupBuyService groupBuyService;
	
	@RequestMapping(value="/auction/{notiId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Auction getAuctionInfo(@PathVariable("notiId") int notiId, HttpServletResponse response)
			throws IOException {
		System.out.println("/rest/auction/{notiId} request accepted: {notiId} = " + notiId);
		
		int auctionId = notiService.getAuctionNoti(notiId).getAuctionId();
		Auction auction = auctionService.getAuctionById(auctionId);
		if (auction == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return auction; 
	}
	
	@RequestMapping(value="/groupBuy/{notiId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public GroupBuy getGroupBuyInfo(@PathVariable("notiId") int notiId, HttpServletResponse response)
			throws IOException {
		System.out.println("/rest/groupBuy/{notiId} request accepted: {notiId} = " + notiId);
		
		int groupBuyId = notiService.getGroupBuyNoti(notiId).getGroupBuyId();
		GroupBuy groupBuy = groupBuyService.getGroupBuy(groupBuyId);
		if (groupBuy == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		return groupBuy; 
	}
}
