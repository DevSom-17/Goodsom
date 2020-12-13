package com.example.goodsom.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.goodsom.dao.LikeDao;
import com.example.goodsom.dao.mybatis.mapper.LikeMapper;

@Repository
public class MybatisLikeDao implements LikeDao {
	
	@Autowired
	private LikeMapper likeMapper;
	
	@Override
	public int getLikeCountOfAuction(int auctionId) throws DataAccessException {
		return likeMapper.getLikeCountOfAuction(auctionId);
	}
	
	@Override
	public int getLikeCountOfGroupBuy(int groupBuyId) throws DataAccessException {
		return likeMapper.getLikeCountOfGroupBuy(groupBuyId);
	}
	
	@Override
	public int likeCheckOfAuctionByUserId(int userId, int auctionId) throws DataAccessException {
		return likeMapper.likeCheckOfAuctionByUserId(userId, auctionId);
	}
	
	@Override
	public int likeCheckOfGroupBuyByUserId(int userId, int auctionId) throws DataAccessException {
		return likeMapper.likeCheckOfGroupBuyByUserId(userId, auctionId);
	}

	@Override
	public void likeAuction(int userId, int auctionId) throws DataAccessException {
		likeMapper.likeAuction(userId, auctionId);
	}

	@Override
	public void likeGroupBuy(int userId, int groupBuyId) throws DataAccessException {
		likeMapper.likeGroupBuy(userId, groupBuyId);
	}

	@Override
	public void unlikeAuction(int userId, int auctionId) throws DataAccessException {
		likeMapper.unlikeAuction(userId, auctionId);
	}

	@Override
	public void unlikeGroupBuy(int userId, int groupBuyId) throws DataAccessException {
		likeMapper.unlikeGroupBuy(userId, groupBuyId);
	}

	@Override
	public List<Integer> getLikedAuctionListByUserId(int userId) throws DataAccessException {
		return likeMapper.getLikedAuctionListByUserId(userId);
	}

	@Override
	public List<Integer> getLikedGroupBuyListByUserId(int userId) throws DataAccessException {
		return likeMapper.getLikedAuctionListByUserId(userId);
	}

}
