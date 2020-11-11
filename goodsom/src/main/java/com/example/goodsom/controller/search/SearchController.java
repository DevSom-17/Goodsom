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
import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.service.SearchService;

/**
 * @author Hyekyung Kim  | kimdahyee
 * @since  2020.05.05    | 2020.06.30
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
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="choice") int choice) throws Exception {
		
		System.out.println("나 들어오기는해");
		
		ModelAndView mav = new ModelAndView();
				
		if (keyword != null) {	
			
			if (!StringUtils.hasLength(keyword)) {	// no input
				return new ModelAndView("Error", "message", "검색어를 입력해주세요.");
			}
			
			if (choice == 1) { //groupBuy
				mav = new ModelAndView(GROUPBUY_LIST);
				List<GroupBuy> groupBuyList = searchService.groupBuyListByKeyword(keyword.toLowerCase());
				mav.addObject("groupBuyList", groupBuyList);
			} else {
				mav = new ModelAndView(AUCTION_LIST);
				List<Auction> auctionList = searchService.auctionListByKeyword(keyword.toLowerCase());
				mav.addObject("auctionList", auctionList);
			}
		}
		return mav;
	}
}
