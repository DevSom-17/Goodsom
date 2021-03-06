package com.example.goodsom.controller.search;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import com.example.goodsom.controller.user.UserSession;
import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.service.SearchService;

/**
 * @author YeJin Lee
 * @since 2020.12.16
 */

@Controller
public class SearchController {

	private static final String GROUPBUY_LIST = "groupBuy/groupBuy_list";
	private static final String AUCTION_LIST = "auction/auction_list";

	private SearchService searchService;

	@Autowired
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	@RequestMapping("/list/search.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "choice") int choice) throws Exception {

		ModelAndView mav = new ModelAndView();
		
//		목록에서의 좋아요 기능을 위한 파라미터: loginUserId
		UserSession user  = (UserSession)request.getSession().getAttribute("userSession");
		int loginUserId = user.getUser().getUserId();

		if (choice == 1) { // groupBuy
			mav = new ModelAndView(GROUPBUY_LIST);
			List<GroupBuy> groupBuyList = searchService.getGroupBuyListByKeyword(keyword.toLowerCase(), loginUserId);
			mav.addObject("groupBuyList", groupBuyList);
		} else {
			mav = new ModelAndView(AUCTION_LIST);
			List<Auction> auctionList = searchService.getAuctionListByKeyword(keyword.toLowerCase(), loginUserId);
			mav.addObject("auctionList", auctionList);
		}
		mav.addObject("loginUserId", loginUserId);
		
		return mav;
	}
}
