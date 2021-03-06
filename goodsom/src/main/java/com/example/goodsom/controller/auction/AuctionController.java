package com.example.goodsom.controller.auction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import com.example.goodsom.controller.user.UserSession;
import com.example.goodsom.domain.Auction;
import com.example.goodsom.service.AuctionService;
import com.example.goodsom.service.UserService;
import com.example.goodsom.domain.Bid;
import com.example.goodsom.domain.SuccessBidder;
import com.example.goodsom.domain.User;
import com.example.goodsom.service.BidService;
import com.example.goodsom.service.LikeService;

/**
 * @author Yejin Lee   
 * @since  2020.06.14 
 */


@Controller
@SessionAttributes("auctionForm")
public class AuctionController {
	
	private static final String AUCTION_LIST = "auction/auction_list";
	private static final String AUCTION_DETAIL = "auction/auction_detail";
	
	@Autowired
	AuctionService auctionService;
	@Autowired
	UserService userService;
	@Autowired
	BidService bidService;
	@Autowired
	LikeService likeService;
	
	@RequestMapping(value="/auction/list.do", method=RequestMethod.GET)
	public ModelAndView auctionList(HttpServletRequest request, SessionStatus sessionStatus, HttpSession session){
		ModelAndView mav = new ModelAndView(AUCTION_LIST);
		List<Auction> auctionList = null;
		
		UserSession user  = (UserSession)request.getSession().getAttribute("userSession");
		int loginUserId = user.getUser().getUserId();
		mav.addObject("loginUserId", loginUserId);

		auctionList = auctionService.getAuctionList(loginUserId);
		if (auctionList == null) {
			System.out.println("[DetailAuctionController] auctionList가 null");
		} else {
			mav.addObject("auctionList", auctionList);			
		}
		session.removeAttribute("bidForm");
		sessionStatus.setComplete();
		return mav;
	}
	
	@RequestMapping(value="/auction/detail.do", method=RequestMethod.GET)
	public ModelAndView auctionDetail(HttpServletRequest request, 
			@RequestParam("auctionId") int auctionId, HttpSession session) {

		ModelAndView mav = new ModelAndView(AUCTION_DETAIL);

//		auction 정보 가져오기
		Auction auction = auctionService.getAuction(auctionId); 
		
// 		낙찰자 정보 가져오기 (낙찰자의 userId) -> 낙찰자에게만 '결제하기'버튼이 보이게 하기 위해
		mav.addObject("successBidderUserId", auctionService.getSuccessBidderUserId(auctionId));

// 		낙찰자가 결제까지 완료한 경우
		SuccessBidder successBidder = auctionService.getSuccessBidderByAuctionId(auctionId);
		if (successBidder != null) {
			mav.addObject("completeOrder", 1);
		} else {
			mav.addObject("completeOrder", 0);
		}
		
//		로그인 한 사용자가 작성자가 아닐 경우 조회수 증가시킨 후 해당 정보 넘겨줌
		UserSession user  = (UserSession)request.getSession().getAttribute("userSession");
		int loginUserId = user.getUser().getUserId();
		if (loginUserId == auction.getUserId()) {
			mav.addObject("isWriter", true);
		} else {
			auction.setCount(auction.getCount()+1);
			auctionService.increaseCount(auction);
			mav.addObject("isWriter", false);
		}

//		해당 경매의 좋아요 수
		auction.setLikeCount(likeService.getLikeCountOfAuction(auctionId));
//		목록에서의 좋아요 기능을 위한 파라미터: loginUserId
		mav.addObject("loginUserId", loginUserId);
		
		int result = likeService.likeCheckOfAuctionByUserId(loginUserId, auctionId);
		if (result == 1) {
			mav.addObject("like", true);
		} else if (result == 0) {
			mav.addObject("like", false);
		} else {
			System.out.println("[AuctionDetail]likeService.likeCheckOfAuctionByUserId()오류!");
			mav.addObject("like", false);
		}

		if (auction.getMaxPrice() == 0) { // 아무도 입찰 안 했을 때
			mav.addObject("date_maxBid", "");
			mav.addObject("user_maxBid", "아직 입찰자가 없습니다.");
		} else {
//			auction의 최고 금액에 해당하는 bid 정보 가져오기
			Bid maxPriceBid = bidService.getBidByMaxPrice(auction.getMaxPrice(), auctionId); 
			mav.addObject("date_maxBid", maxPriceBid.getBidDate());
			User user_maxBid = userService.getUserByUserId(maxPriceBid.getUserId());
			mav.addObject("user_maxBid", user_maxBid.getNickname());
		}

		//		현재 최고 금액을 입찰한 사람의 정보
		session.setAttribute("bidForm", new BidForm());
		session.setAttribute("auctionId", auctionId);
		mav.addObject("auction", auction);
		mav.addObject("bidForm", session.getAttribute("bidForm"));
		mav.addObject("writer", userService.getUserByUserId(auction.getUserId()).getNickname());
		mav.addObject("dDay", auction.getDday(auction.getEndDate().getTime()));
		return mav;
	}
	
}
