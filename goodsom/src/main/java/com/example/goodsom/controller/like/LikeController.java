package com.example.goodsom.controller.like;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LikeController {
	
	private static final String AUCTION_DETAIL = "auction/auction_detail";
	
	public ModelAndView clickLike() {
		ModelAndView mav = new ModelAndView(AUCTION_DETAIL);
		
		
		return mav;
	}
}
