package com.example.goodsom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.goodsom.dao.LikeDao;
import com.example.goodsom.service.LikeService;

@Service
@Component
public class LikeServiceImpl implements LikeService {

	@Autowired
	LikeDao likeDao;
	
	@Override
	public int getLikeCountOfAuction(int auctionId) {
		return likeDao.getLikeCountOfAuction(auctionId);
	}

	@Override
	public int getLikeCountOfGroupBuy(int groupBuyId) {
		return likeDao.getLikeCountOfGroupBuy(groupBuyId);
	}
	
	@Override
	public int likeCheckOfAuctionByUserId(int userId, int auctionId) {
		return likeDao.likeCheckOfAuctionByUserId(userId, auctionId);
	}
	
	@Override
	public int likeCheckOfGroupBuyByUserId(int userId, int auctionId) {
		return likeDao.likeCheckOfGroupBuyByUserId(userId, auctionId);
	}

	@Override
	public void likeAuction(int userId, int auctionId) {
		likeDao.likeAuction(userId, auctionId);
	}

	@Override
	public void likeGroupBuy(int userId, int groupBuyId) {
		likeDao.likeGroupBuy(userId, groupBuyId);
	}

	@Override
	public void unlikeAuction(int userId, int auctionId) {
		likeDao.unlikeAuction(userId, auctionId);
	}

	@Override
	public void unlikeGroupBuy(int userId, int groupBuyId) {
		likeDao.unlikeGroupBuy(userId, groupBuyId);
	}

}
