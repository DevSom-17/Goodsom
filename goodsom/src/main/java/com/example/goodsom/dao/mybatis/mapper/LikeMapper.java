package com.example.goodsom.dao.mybatis.mapper;

import java.util.List;

public interface LikeMapper {

	void likeAuction(int userId, int auctionId);
	
	void likeGroupBuy(int userId, int groupBuyId);

	int unlikeAuction(int userId, int auctionId);
	
	int unlikeGroupBuy(int userId, int groupBuyId);
	
	List<Integer> getLikeListOfAuction(int userId);
	
	List<Integer> getLikeListOfGroupBuy(int userId);
}
