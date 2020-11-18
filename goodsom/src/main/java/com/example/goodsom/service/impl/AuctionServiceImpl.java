package com.example.goodsom.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.goodsom.dao.AuctionDao;
import com.example.goodsom.dao.BidDao;
import com.example.goodsom.dao.NotificationDao;
import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.Bid;
import com.example.goodsom.domain.SuccessBidder;
import com.example.goodsom.service.AuctionService;


/**
 * @author Hyekyung Kim | Yejin Lee  | kimdahyee  | Seonmi Hwang
 * @since 2020.05.05	| 2020.06.13 | 2020.06.25 | 2020.06.29
 */

@Service
@Component
public class AuctionServiceImpl implements AuctionService {
	private static final String CLOSED = "closed";
	
	@Autowired
	private AuctionDao auctionDao;
	
	@Autowired
	private BidDao bidDao;
	
	@Autowired
	private NotificationDao notiDao;
	
	public Auction getAuction(int auctionId) throws DataAccessException {
		return auctionDao.getAuction(auctionId);
	}
	
	public List<Auction> getAuctionList() {
		return auctionDao.getAuctionList();
	}

	public int createAuction(Auction auction) {
		return auctionDao.createAuction(auction);
	}

	public int updateAuction(Auction auction) {
		return auctionDao.updateAuction(auction);
	}
	
	public int updateAuctionMaxPrice(int maxPrice, int auctionId) {
		return auctionDao.updateAuctionMaxPrice(maxPrice, auctionId);
	}

	public List<Auction> deleteAuction(int auctionId) {
		auctionDao.deleteAuction(auctionId);			
		return auctionDao.getAuctionList();
	}
	
	public boolean isAuctionClosed(int auctionId, Date endDate) {
		return auctionDao.isAuctionClosed(auctionId, endDate);
	}

	public void increaseCount(Auction auction) {
		auctionDao.increaseCount(auction);
	}
	
	public List<Auction> getRecentAuctionList() {
		return auctionDao.getRecentAuctionList();
	}

	@Scheduled(fixedDelay = 1000)
	public void deadLineScheduler() {
		Date curTime = new Date();
		auctionDao.closeEvent(curTime);	// 경매 마감
		
		int[] auctionId = auctionDao.getAuctionIdForNoti(); // 동시에 여러 경매가 마감될 경우
		
		for(int i = 0; i < auctionId.length; i++) {
			
			if(auctionDao.getAuction(auctionId[i]).getState().equals(CLOSED)) {
				Bid bid = bidDao.getSuccessBidByAuctionId(auctionId[i]);
				
				if(bid != null) {
					bid.setAuctionTitle(auctionDao.getAuction(auctionId[i]).getTitle());
					notiDao.createNoti_a(bid);
					auctionDao.updateAuctionNoti(auctionId[i]);
				}
			}
		}
		
	}
	
	public int[] getAuctionIdForNoti() {
		return auctionDao.getAuctionIdForNoti();
	}
	
	public Integer getSuccessBidderUserId(int auctionId) {
		return auctionDao.getSuccessBidderUserId(auctionId);
	}
	
	public SuccessBidder getSuccessBidderByAuctionId(int auctionId) {
		return auctionDao.getSuccessBidderByAuctionId(auctionId);
	}
	
}
