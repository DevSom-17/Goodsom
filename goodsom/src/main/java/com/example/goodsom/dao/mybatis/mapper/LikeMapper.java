package com.example.goodsom.dao.mybatis.mapper;

import org.springframework.dao.DataAccessException;

public interface LikeMapper {

	int getLikeCountOfAuction(int auctionId) throws DataAccessException;
	
	int getLikeCountOfGroupBuy(int groupBuyId) throws DataAccessException;

	int likeCheckOfAuctionByUserId(int userId, int auctionId) throws DataAccessException;
	
	int likeCheckOfGroupBuyByUserId(int userId, int auctionId) throws DataAccessException;

	void likeAuction(int userId, int auctionId) throws DataAccessException;
	
	void likeGroupBuy(int userId, int groupBuyId) throws DataAccessException;

	void unlikeAuction(int userId, int auctionId) throws DataAccessException;
	
	void unlikeGroupBuy(int userId, int groupBuyId) throws DataAccessException;
	
}
