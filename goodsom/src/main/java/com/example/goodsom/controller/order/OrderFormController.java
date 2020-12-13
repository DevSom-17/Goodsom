package com.example.goodsom.controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.example.goodsom.controller.groupBuy.LineGroupBuyForm;
import com.example.goodsom.controller.user.UserSession;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.User;
import com.example.goodsom.service.AuctionService;
import com.example.goodsom.service.NotiService;
import com.example.goodsom.service.OrderService;
import com.example.goodsom.validator.OrderFormValidator;

/**
 * @author Seonmi Hwang	| HK
 * @since 2020.05.04	| 2020.06.26
 */

@Controller
@SessionAttributes({"lineGroupBuyForm", "orderForm"})
public class OrderFormController {
	
	private static final String orderFormView = "order/order_create";
	private static final String detailView = "order/payment_detail";

	private static final int FAIL = 0; // 결제가 실패했을 경우
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private AuctionService auctionService;
	@Autowired
	private NotiService notiService;

	
	@ModelAttribute("orderForm")
	public OrderForm formBacking(HttpServletRequest request) {
		return new OrderForm();
	}
	
	@ModelAttribute("cardBanks")
	public List<String> cardBanksData() {
		ArrayList<String> cardBanks = new ArrayList<String>();
		cardBanks.add("신한");
		cardBanks.add("하나");
		cardBanks.add("우리");
		cardBanks.add("농협");
		cardBanks.add("국민"); 
		// add more
		return cardBanks;			
	}
	
	@RequestMapping(value = "/order/groupBuy/create.do", method = RequestMethod.GET) // form 출력
	public ModelAndView groupBuyOrderForm(HttpServletRequest request,
			@ModelAttribute("lineGroupBuyForm") LineGroupBuyForm lineGroupBuyForm,
			@ModelAttribute("orderForm") OrderForm orderForm) throws ModelAndViewDefiningException {

		ModelAndView mav = new ModelAndView(orderFormView);
		
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		User user = userSession.getUser();

		orderForm.getOrder().initOrder(user, lineGroupBuyForm, null);
		return mav;
	}
	
	@RequestMapping(value = "/order/groupBuy/create.do", method = RequestMethod.POST) // 결과 출력
	protected ModelAndView groupBuyOrderSubmit(
			@ModelAttribute("orderForm") OrderForm orderForm,
			BindingResult bindingResult, SessionStatus status) {
		
		System.out.println("command 객체: " + orderForm);
		new OrderFormValidator().validate(orderForm, bindingResult);
		
		// 검증 오류 발생 시 다시 form view로 이동
		if (bindingResult.hasErrors()) {
			return new ModelAndView(orderFormView);
		}
		
		// 참여인원 update
		int totalQuantity = orderForm.getOrder().getTotalQuantity();
		orderForm.getOrder().getGroupBuy().orderSet(totalQuantity);
	
		int result = orderService.createOrder(orderForm.getOrder());

		System.out.println("create order success? : " + result);
		
		ModelAndView mav = new ModelAndView(detailView);
		mav.addObject("order", orderForm.getOrder());
		
		if (result == FAIL) {
			mav.addObject("message", "결제에 실패했습니다.");
		} else {
			mav.addObject("message", "결제가 성공적으로 완료되었습니다.");
		}
		status.setComplete();  // remove session lineGroupBuyForm and orderForm from session
		return mav;
	}
	
	
	@RequestMapping(value = "/order/auction/create.do", method = RequestMethod.GET) // form 출력
	public ModelAndView auctionOrderForm(HttpServletRequest request,
			@RequestParam("auctionId") int auctionId,
			@ModelAttribute("orderForm") OrderForm orderForm
			) throws ModelAndViewDefiningException {
		ModelAndView mav = new ModelAndView(orderFormView);
		
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		User user = userSession.getUser();

		orderForm.getOrder().initOrder(user, null, auctionService.getAuction(auctionId));
		return mav;
	}
	
	@RequestMapping(value = "/order/auction/create.do", method = RequestMethod.POST) // 결과 출력
	protected ModelAndView auctionOrderSubmit(
			@ModelAttribute("orderForm") OrderForm orderForm, 
			SessionStatus status, BindingResult bindingResult) {
		
		System.out.println("command 객체: " + orderForm);
		new OrderFormValidator().validate(orderForm, bindingResult);
		
		// 검증 오류 발생 시 다시 form view로 이동
		if (bindingResult.hasErrors()) {
			return new ModelAndView(orderFormView);
		}
		
		int result = orderService.createOrder(orderForm.getOrder());
		
		ModelAndView mav = new ModelAndView(detailView);
		mav.addObject("order", orderForm.getOrder());
		
		if (result == FAIL) {
			mav.addObject("message", "결제에 실패했습니다.");
		} else {
			mav.addObject("message", "결제가 성공적으로 완료되었습니다.");
		}
		status.setComplete();  // remove session lineGroupBuyForm and orderForm from session
		return mav;
	}
	
}
