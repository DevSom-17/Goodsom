package com.example.goodsom.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.goodsom.dao.BidDao;
import com.example.goodsom.domain.Bid;
import com.example.goodsom.dao.mybatis.mapper.BidMapper;

/**
 * @author kimdahyee | Yejin Lee
 * @since 2020.06.24 | 2020.06.29
 */

@Repository
public class MybatisBidDao implements BidDao {

	@Autowired
	private BidMapper bidMapper;

	public Bid getBid(int bidId) throws DataAccessException {
		return bidMapper.getBid(bidId);
	}

	public Bid getBidByAuctionId(int bidId, int auctionId) throws DataAccessException {
		return bidMapper.getBidByAuctionId(bidId, auctionId);
	}

	public String getMaxPrice(int auctionId) throws DataAccessException {
		return bidMapper.getMaxPrice(auctionId);
	}

	public Bid getBidByMaxPrice(int bidMaxPrice, int auctionId) throws DataAccessException {
		return bidMapper.getBidByMaxPrice(bidMaxPrice, auctionId);
	}

	public void createBid(Bid bid) throws DataAccessException {
		bidMapper.createBid(bid);
	}

	public List<Bid> getBidByUserId(int userId) throws DataAccessException {
		return bidMapper.getBidByUserId(userId);
	}

	public Bid getSuccessBidByAuctionId(int auctionId) throws DataAccessException{
		return bidMapper.getSuccessBidByAuctionId(auctionId);
	}

	public void updateBid(Bid bid) throws DataAccessException {
		bidMapper.updateBid(bid);
	}

	public Bid getBidByUserIdAndAuctionId(int userId, int auctionId) {
		return bidMapper.getBidByUserIdAndAuctionId(userId, auctionId);
	}

}
