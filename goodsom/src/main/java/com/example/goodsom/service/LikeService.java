package com.example.goodsom.service;

public interface LikeService {

	int getLikeCountOfAuction(int auctionId);
	
	int getLikeCountOfGroupBuy(int groupBuyId);

	int likeCheckOfAuctionByUserId(int userId, int auctionId);
	
	int likeCheckOfGroupBuyByUserId(int userId, int auctionId);

	void likeAuction(int userId, int auctionId);
	
	void likeGroupBuy(int userId, int groupBuyId);

	void unlikeAuction(int userId, int auctionId);
	
	void unlikeGroupBuy(int userId, int groupBuyId);

}
