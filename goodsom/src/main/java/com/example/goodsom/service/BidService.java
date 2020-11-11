package com.example.goodsom.service;

import java.util.List;

import com.example.goodsom.domain.Bid;

/**
 * @author Hyekyung Kim | kimdahyee		| Yejin Lee
 * @since  2020.05.08   | 2020.06.24	| 2020.06.29
 */

public interface BidService {
	
	Bid getBid(int bidId);
	
	Bid getBidByAuctionId(int bidId, int auctionId);
	
	String getMaxPrice(int auctionId);
	
	Bid getBidByMaxPrice(int bidPrice, int auctionId);
	
	void createBid(Bid bid);
	
	List<Bid> getBidByUserId(int userId);
	
	Bid getBidByUserIdAndAuctionId(int userId, int auctionId);
	
	void updateBid(Bid bid);
	
}
