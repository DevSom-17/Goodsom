package com.example.goodsom.controller.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.domain.Notification;
import com.example.goodsom.domain.User;
import com.example.goodsom.service.NotiService;
import com.example.goodsom.service.UserService;

/**
 * @author Yejin Lee | HK
 * @since 2020.05.07 | 2020.06.29
 */
@Controller
public class DetailNotiController {
	
	@Value("user/noti_detail")
	private String formViewName;
	
	@Autowired
	NotiService notiService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/noti/detail.do")
	public ModelAndView handleRequest(@RequestParam("notiId") int notiId,
			@RequestParam("type") int type, 
			@RequestParam("content") String content) throws Exception {
		
		ModelAndView mov = new ModelAndView();
		Notification noti;
		int userId;
		User user;
		if(type == 1) {		// auction
			noti = notiService.getAuctionNoti(notiId);
			userId = noti.getUserId();
			user = userService.getUserByUserId(userId);
			mov.addObject("id", noti.getAuctionId());
		}else {				// groupBuy
			noti = notiService.getGroupBuyNoti(notiId);
			userId = noti.getUserId();
			user = userService.getUserByUserId(userId);
			mov.addObject("id", noti.getGroupBuyId());
		}
		System.out.println("type: " + type);
		mov.addObject("nickname", user.getNickname());
		mov.addObject("noti", noti);
		mov.addObject("content", content);
		mov.addObject("type", type);
		mov.setViewName(formViewName);
		
		return mov;
	}
}
