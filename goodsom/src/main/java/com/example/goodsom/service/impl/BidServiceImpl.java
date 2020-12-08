package com.example.goodsom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodsom.dao.BidDao;
import com.example.goodsom.domain.Bid;
import com.example.goodsom.service.BidService;

/**
 * @author Hyekyung Kim | kimdahyee		| Yejin Lee
 * @since  2020.05.08   | 2020.06.24	| 2020.06.29
 */

@Service
public class BidServiceImpl implements BidService{

	@Autowired
	private BidDao bidDao;
	
	public Bid getBid(int bidId) {
		return bidDao.getBid(bidId);
	}

	public Bid getBidByAuctionId(int bidId, int auctionId) {
		return bidDao.getBidByAuctionId(bidId, auctionId);
	}
	
	public String getMaxPrice(int auctionId) {
		return bidDao.getMaxPrice(auctionId);
	}
	
	public Bid getBidByMaxPrice(int bidMaxPrice, int auctionId) {
		return bidDao.getBidByMaxPrice(bidMaxPrice, auctionId);
	}

	public void createBid(Bid bid) {
		bidDao.createBid(bid);
	}

	public List<Bid> getBidByUserId(int userId) {
		return bidDao.getBidByUserId(userId);
	}
	
	public Bid getBidByUserIdAndAuctionId(int userId, int auctionId) {
		return bidDao.getBidByUserIdAndAuctionId(userId, auctionId);
	}
	
	public void updateBid(Bid bid) {
		bidDao.updateBid(bid);
	}

}
