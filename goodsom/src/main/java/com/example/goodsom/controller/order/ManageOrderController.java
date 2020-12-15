package com.example.goodsom.controller.order;

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

@Controller
@SessionAttributes("userSession")
public class ManageOrderController {
	
	@Autowired
	OrderService orderService;
	@Autowired
	GroupBuyService groupBuyService;
	@Autowired
	AuctionService auctionService;
	
	
	@RequestMapping("/order/groupBuy/manage.do")
	public ModelAndView groupBuyHandleRequest( // 공동구매
			@ModelAttribute("userSession") UserSession userSession,
			@RequestParam("groupBuyId") int groupBuyId) throws Exception {
			
			System.out.println("/order/groupbuy/manage.do 실행");
		
			ModelAndView mav = new ModelAndView("order/order_manage");
			List<Order> orderList = orderService.getOrdersByGroupBuyId(groupBuyId);
			
			mav.addObject("groupBuy", groupBuyService.getGroupBuy(groupBuyId));
			mav.addObject("orderList", orderList);
			return mav;
	}
	
	@RequestMapping("/order/auction/manage.do")
	public ModelAndView auctionHandleRequest( // 경매
			@ModelAttribute("userSession") UserSession userSession,
			@RequestParam("auctionId") int auctionId) throws Exception {
		
		ModelAndView mav = new ModelAndView("order/order_manage");
		List<Order> orderList = orderService.getOrdersByAuctionId(auctionId);
		
		mav.addObject("auction", auctionService.getAuction(auctionId));
		mav.addObject("orderList", orderList);
		return mav;
	}

}
