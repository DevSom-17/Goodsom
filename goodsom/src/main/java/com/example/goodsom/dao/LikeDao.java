package com.example.goodsom.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface LikeDao {

	void likeAuction(int userId, int auctionId) throws DataAccessException;
	
	void likeGroupBuy(int userId, int groupBuyId) throws DataAccessException;

	void unlikeAuction(int userId, int auctionId) throws DataAccessException;
	
	void unlikeGroupBuy(int userId, int groupBuyId) throws DataAccessException;
	
	List<Integer> getLikeListOfAuction(int userId) throws DataAccessException;
	
	List<Integer> getLikeListOfGroupBuy(int userId) throws DataAccessException;
	
}
