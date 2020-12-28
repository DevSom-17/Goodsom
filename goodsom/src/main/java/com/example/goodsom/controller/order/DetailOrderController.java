package com.example.goodsom.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.controller.user.UserSession;
import com.example.goodsom.domain.Order;
import com.example.goodsom.service.OrderService;

/**
 * @author Seonmi Hwang
 * @since 2020.05.04
 */

@Controller
@SessionAttributes("userSession")
public class DetailOrderController {
	
	@Autowired
	OrderService orderService;
	
	private static final String detailView = "order/order_detail";
	
	@RequestMapping("/order/groupBuy/detail.do")
	public ModelAndView groupBuyHandleRequest( // 공동구매
			@ModelAttribute("userSession") UserSession userSession,
			@RequestParam("orderId") int orderId) throws Exception {
		
			ModelAndView mav = new ModelAndView(detailView);
			Order order = orderService.getOrderWithLineGroupBuys(orderId);
			
			order.setGroupBuy(orderService.getGroupBuy(orderId));
			
			mav.addObject("order", order);
			return mav;
	}
	
	@RequestMapping("/order/auction/detail.do")
	public ModelAndView auctionHandleRequest( // 경매
			@ModelAttribute("userSession") UserSession userSession,
			@RequestParam("orderId") int orderId) throws Exception {
		
			ModelAndView mav = new ModelAndView(detailView);
			Order order = orderService.getOrder(orderId);

			order.setAuction(orderService.getAuction(orderId));
			
			mav.addObject("order", order);
			return mav;
	}
	
}
