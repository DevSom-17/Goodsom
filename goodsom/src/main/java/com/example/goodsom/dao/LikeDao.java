package com.example.goodsom.dao;

import org.springframework.dao.DataAccessException;

public interface LikeDao {

	int getLikeCountOfAuction(int auctionId) throws DataAccessException;
	
	int getLikeCountOfGroupBuy(int groupBuyId) throws DataAccessException;

	int likeCheckOfAuctionByUserId(int userId, int auctionId) throws DataAccessException;
	
	int likeCheckOfGroupBuyByUserId(int userId, int auctionId) throws DataAccessException;
	
	void likeAuction(int userId, int auctionId) throws DataAccessException;
	
	void likeGroupBuy(int userId, int groupBuyId) throws DataAccessException;

	void unlikeAuction(int userId, int auctionId) throws DataAccessException;
	
	void unlikeGroupBuy(int userId, int groupBuyId) throws DataAccessException;
	
}
