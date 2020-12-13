package com.example.goodsom.service;

import java.util.List;

public interface LikeService {

	int getLikeCountOfAuction(int auctionId);
	
	int getLikeCountOfGroupBuy(int groupBuyId);

	int likeCheckOfAuctionByUserId(int userId, int auctionId);
	
	int likeCheckOfGroupBuyByUserId(int userId, int auctionId);

	void likeAuction(int userId, int auctionId);
	
	void likeGroupBuy(int userId, int groupBuyId);

	void unlikeAuction(int userId, int auctionId);
	
	void unlikeGroupBuy(int userId, int groupBuyId);
	
	List<Integer> getLikedAuctionListByUserId(int userId);
	
	List<Integer> getLikedGroupBuyListByUserId(int userId);

}
