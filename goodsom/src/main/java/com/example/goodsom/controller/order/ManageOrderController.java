package com.example.goodsom.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.domain.Order;
import com.example.goodsom.service.AuctionService;
import com.example.goodsom.service.GroupBuyService;
import com.example.goodsom.service.OrderService;

/**
 * @author Seonmi Hwang
 * @since 2020.12.16
 */

@Controller
public class ManageOrderController {
	
	@Autowired
	OrderService orderService;
	@Autowired
	GroupBuyService groupBuyService;
	@Autowired
	AuctionService auctionService;
	
	@RequestMapping(value = "/order/groupBuy/manage.do", method = RequestMethod.GET)
	public ModelAndView groupBuyHandleRequestGet( // 공동구매
			@RequestParam("groupBuyId") int groupBuyId) throws Exception {
			
			System.out.println("/order/groupbuy/manage.do 실행");
		
			ModelAndView mav = new ModelAndView("order/order_manage");
			
			List<Order> list = orderService.getOrdersByGroupBuyId(groupBuyId);
			OrderListForm orderListForm = new OrderListForm();	
			orderListForm.setOrderList(list);
			
			mav.addObject("groupBuy", groupBuyService.getGroupBuy(groupBuyId));
			mav.addObject("orderListForm", orderListForm);
			return mav;
	}
	
	@RequestMapping(value = "/order/groupBuy/manage.do", method = RequestMethod.POST)
	public ModelAndView groupBuyHandleRequestPost( // 공동구매
			@ModelAttribute("orderListForm") OrderListForm orderListForm) throws Exception {
			
			System.out.println("/order/groupbuy/manage.do 실행");
			
			List<Order> list = orderListForm.getOrderList();
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
				orderService.updateOrder(list.get(i));
			}
			
			ModelAndView mav = new ModelAndView("order/order_manage");
			mav.addObject("groupBuy", groupBuyService.getGroupBuy(orderListForm.getGroupBuyId()));
			return mav;
	}
	
	@RequestMapping(value = "/order/auction/manage.do", method = RequestMethod.GET)
	public ModelAndView auctionHandleRequestGet( // 경매
			@RequestParam("auctionId") int auctionId) throws Exception {
		
		ModelAndView mav = new ModelAndView("order/order_manage");
		
		List<Order> list = orderService.getOrdersByAuctionId(auctionId);
		OrderListForm orderListForm = new OrderListForm();
		orderListForm.setOrderList(list);
		
		mav.addObject("auction", auctionService.getAuction(auctionId));
		mav.addObject("orderListForm", orderListForm);
		return mav;
	}

	@RequestMapping(value = "/order/auction/manage.do", method = RequestMethod.POST)
	public ModelAndView auctionHandleRequestPost( // 공동구매
			@ModelAttribute("orderListForm") OrderListForm orderListForm) throws Exception {
			
			System.out.println("/order/auction/manage.do 실행");
			
			List<Order> list = orderListForm.getOrderList();
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
				orderService.updateOrder(list.get(i));
			}
			
			ModelAndView mav = new ModelAndView("order/order_manage");
			mav.addObject("auction", auctionService.getAuction(orderListForm.getAuctionId()));
			return mav;
	}
}
