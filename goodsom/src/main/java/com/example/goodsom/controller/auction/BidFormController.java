package com.example.goodsom.controller.auction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.example.goodsom.domain.Bid;
import com.example.goodsom.service.BidService;
import com.example.goodsom.service.LikeService;
import com.example.goodsom.domain.User;
import com.example.goodsom.service.UserService;
import com.example.goodsom.controller.user.UserSession;
import com.example.goodsom.domain.Auction;
import com.example.goodsom.service.AuctionService;

/**
 * @author Hyekyung Kim | kimdahyee		| Yejin Lee
 * @since 2020.05.08 	| 2020.06.25	| 2020.06.29
 */


@Controller
@SessionAttributes("bidForm")
@RequestMapping("/auction/bidCreate.do")
public class BidFormController {

	private static final String AUCTION_DETAIL = "auction/auction_detail";

	@Autowired
	BidService bidService;

	public void setBidService(BidService bidService) {
		this.bidService = bidService;
	}

	@Autowired
	UserService userService;
	@Autowired
	AuctionService auctionService;
	@Autowired
	LikeService likeService;
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(HttpServletRequest request,
			@Valid @ModelAttribute("bidForm") BidForm bidForm, BindingResult result, 
			HttpServletResponse response, HttpSession session, Model model, SessionStatus sessionStatus) throws Exception {
		
		int auctionId = bidForm.getBid().getAuctionId();
//		bid 생성 위해 현재 로그인한 User객체 필요
		UserSession userSession = (UserSession)session.getAttribute("userSession");
		int userId = (int) userSession.getUser().getUserId();
//		BidForm객체 validation
		Auction auction = auctionService.getAuction(auctionId);
		model.addAttribute("writer", userService.getUserByUserId(auction.getUserId()).getNickname());
		model.addAttribute("isWriter", false);
		model.addAttribute("dDay", auction.getDday(auction.getEndDate().getTime()));
		
		if (auction.getBids().isEmpty()) {
			if (bidForm.getBid().getBidPrice() < auction.getStartPrice()) {
				result.rejectValue("bid.bidPrice", "smallerThanStartPrice");
			}
		} else {
			if (bidForm.getBid().getBidPrice() <= auction.getMaxPrice()) {
				result.rejectValue("bid.bidPrice", "smallerThanMaxPrice");
			}
		}

//		해당 경매의 좋아요 수
		auction.setLikeCount(likeService.getLikeCountOfAuction(auctionId));
//		사용자가 like했는지 안 했는지
		model.addAttribute("loginUserId", userId);
		int likeCheck = likeService.likeCheckOfAuctionByUserId(userId, auctionId);
		if (likeCheck == 1) {
			model.addAttribute("like", true);
		} else if (likeCheck == 0) {
			model.addAttribute("like", false);
		} else {
			System.out.println("[AuctionDetail]likeService.likeCheckOfAuctionByUserId()오류!");
			model.addAttribute("like", false);
		}

//		BidForm객체 validation
		if (result.hasErrors()) {
			Bid maxPriceBid = bidService.getBidByMaxPrice(auction.getMaxPrice(), auctionId);
			if (maxPriceBid == null) {
				model.addAttribute("date_maxBid", "");
				model.addAttribute("user_maxBid", "아직 입찰자가 없습니다.");
			} else {
				model.addAttribute("date_maxBid", maxPriceBid.getBidDate());
				User user_maxBid = userService.getUserByUserId(maxPriceBid.getUserId());
				model.addAttribute("user_maxBid", user_maxBid.getNickname());
			}
			model.addAttribute("auction", auction);
			return AUCTION_DETAIL;
		}
		
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date bidDate = new java.sql.Date(utilDate.getTime());

//		해당 경매에 한 번이라도 입찰한 적이 있으면 updateBide(), 없으면 createBid()를 해준다.
		Bid bid = bidService.getBidByUserIdAndAuctionId(userId, auctionId);
		if (bid == null) {
			bid = new Bid(userId, auctionId, bidForm.getBid().getBidPrice(), bidDate);
			bidService.createBid(bid);
		} else {
			bid.setBidPrice(bidForm.getBid().getBidPrice());
			bidService.updateBid(bid);
		}

//		Auction객체의 최고 금액 변경 후 Auction객체 다시 가져와 넘겨주기
		int updatedAutionId = auctionService.updateAuctionMaxPrice(bidForm.getBid().getBidPrice(), auctionId); // auction table maxPrice update
		Auction updatedAuction = auctionService.getAuction(updatedAutionId);
//		해당 경매의 좋아요 수
		updatedAuction.setLikeCount(likeService.getLikeCountOfAuction(updatedAutionId));
		model.addAttribute("auction", updatedAuction);
		
//		auction_detail.jsp에 넘겨줄 model 값 설정
		model.addAttribute("date_maxBid", bid.getBidDate());
		User user_maxBid = userService.getUserByUserId(bid.getUserId());
		model.addAttribute("user_maxBid", user_maxBid.getNickname());
		session.setAttribute("bidForm", new BidForm());
		model.addAttribute("bidForm", session.getAttribute("bidForm"));
		return AUCTION_DETAIL;
	}

}
