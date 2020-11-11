package com.example.goodsom.service;

import java.util.Date;
import java.util.List;

import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.SuccessBidder;


/**
 * @author Hyekyung Kim | Yejin Lee  | kimdahyee  | Seonmi Hwang
 * @since 2020.05.05	| 2020.06.13 | 2020.06.25 | 2020.06.29
 */

public interface AuctionService {
	
	Auction getAuction(int auctionId);
	
	int createAuction(Auction auction);
	
	int updateAuction(Auction auction);
	
	int updateAuctionMaxPrice(int maxPrice, int auctionId);
	
	List<Auction> deleteAuction(int auctionId);
	
	List<Auction> getAuctionList();
	
	boolean isAuctionClosed(int auctionId, Date endDate);
	
	void increaseCount(Auction auction);
	
	List<Auction> getRecentAuctionList();
	
	public void deadLineScheduler(Date endDate, int auctionId);
	
	public Integer getSuccessBidderUserId(int auctionId);
	
	public SuccessBidder getSuccessBidderByAuctionId(int auctionId);
	
}
