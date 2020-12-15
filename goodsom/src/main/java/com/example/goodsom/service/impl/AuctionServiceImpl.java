package com.example.goodsom.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.goodsom.dao.AuctionDao;
import com.example.goodsom.dao.BidDao;
import com.example.goodsom.dao.FileDao;
import com.example.goodsom.dao.NotificationDao;
import com.example.goodsom.dao.UserDao;
import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.Bid;
import com.example.goodsom.domain.Image_a;
import com.example.goodsom.domain.SuccessBidder;
import com.example.goodsom.domain.User;
import com.example.goodsom.service.AuctionService;
import com.example.goodsom.service.NotiMailService;


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
	
	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private NotiMailService notiMailService;
	
	@Autowired
	private UserDao userDao;
	
	public Auction getAuction(int auctionId) throws DataAccessException {
		Auction auction = auctionDao.getAuction(auctionId);
//		auction.setImgs_a(fileDao.getAuctionImgs(auctionId));
		return auction;
	}
	
	public List<Auction> getAuctionList() {
		return auctionDao.getAuctionList();
	}
	
	@Override
	public List<Auction> getAuctionListByUserId(int userId) {
		return auctionDao.getAuctionListByUserId(userId);
	}

	@Transactional
	public int createAuction(Auction auction, List<Image_a> auctionImgs) {
		int auctionId = auctionDao.createAuction(auction);
		System.out.println("경매 생성 후 바로 받아온 auctionId: " + auctionId);
		for (Image_a auctionImg : auctionImgs) {
			auctionImg.setAuctionId(auctionId);
			System.out.println("사진 저장 전 auctionId=" + auctionImg.getAuctionId() + "의 fileNo: " + auctionImg.getFileNo() + " url:" + auctionImg.getUrl());
		}
		auction.setImgs_a(auctionImgs);
		fileDao.saveAuctionImgs(auctionImgs);
		return auctionId;
	}

	@Transactional
	public int updateAuction(Auction auction, List<Image_a> auctionImgs) {
		fileDao.deleteAuctionImgs(auction.getAuctionId());
		int auctionId = auctionDao.updateAuction(auction);
		for (Image_a auctionImg : auctionImgs) {
			auctionImg.setAuctionId(auctionId);
			System.out.println("사진 저장 전 auctionId=" + auctionImg.getAuctionId() + "의 fileNo: " + auctionImg.getFileNo() + " url:" + auctionImg.getUrl());
		}
		auction.setImgs_a(auctionImgs);
		fileDao.saveAuctionImgs(auctionImgs);
		return auctionId;
	}
	
	public int updateAuctionMaxPrice(int maxPrice, int auctionId) {
		return auctionDao.updateAuctionMaxPrice(maxPrice, auctionId);
	}

	@Transactional
	public List<Auction> deleteAuction(int auctionId) {
		fileDao.deleteAuctionImgs(auctionId);
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
			Auction auction = auctionDao.getAuction(auctionId[i]);
			
			if(auction.getState().equals(CLOSED)) { // 마감일때 알림 보내기
				Bid bid = bidDao.getSuccessBidByAuctionId(auctionId[i]); // 낙찰자
				
				if(bid != null) {
					bid.setAuctionTitle(auctionDao.getAuction(auctionId[i]).getTitle());
					notiDao.createNoti_a(bid); // 알림 생성
					
					// 메일 보내기 - 마감이면서 알림을 보내지 않았을 때
					if(auction.getSendNoti() == 0) {
						try {
							// 메일 전송
							User successUser = userDao.getUserByUserId(bid.getUserId());
							notiMailService.sendAuctionMessage(successUser.getEmail());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					auctionDao.updateAuctionNoti(auctionId[i]); // 알림 보냄으로 상태변경
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

	@Override
	public List<Auction> getLikedAuctionListByUserId(int userId) {
		return auctionDao.getLikedAuctionListByUserId(userId);
	}
	
}
