package com.example.goodsom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodsom.dao.GroupBuyDao;
import com.example.goodsom.dao.LikeDao;
import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.service.SearchService;
import com.example.goodsom.dao.AuctionDao;

/**
 * @author Yejin Lee
 * @since 2020.12.16
 */

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private GroupBuyDao groupBuyDao;
	@Autowired
	private AuctionDao auctionDao;	
	@Autowired
	private LikeDao likeDao;
	
	@Override
	public List<GroupBuy> getGroupBuyListByKeyword(String keyword, int userId) {
		List<GroupBuy> groupBuys = groupBuyDao.getGroupBuyListByKeyword(keyword);
		List<Integer> likedGroupBuyIds = likeDao.getLikedGroupBuyIdList(userId);
		for (GroupBuy groupBuy : groupBuys) {
			int id = groupBuy.getGroupBuyId();
			groupBuy.setLiked(0);
			for (int likedGroupBuyId : likedGroupBuyIds) {
				if (id == likedGroupBuyId) {
					groupBuy.setLiked(1);
					break;
				}
			}
		}
		return groupBuys;
	}

	@Override
	public List<Auction> getAuctionListByKeyword(String keyword, int userId) {
		List<Auction> auctions = auctionDao.getAuctionListByKeyword(keyword);
		List<Integer> likedAuctionIds = likeDao.getLikedAuctionIdList(userId);
		for (Auction auction : auctions) {
			int id = auction.getAuctionId();
			auction.setLiked(0);
			for (int likedAuctionId : likedAuctionIds) {
				if (id == likedAuctionId) {
					auction.setLiked(1);
					break;
				}
			}
		}
		return auctions;
	}
	
}
