package  com.example.goodsom.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.goodsom.domain.Bid;


/**
 * @author Hyekyung Kim | Yejin Lee
 * @since  2020.05.08   | 2020.06.29
 */

public interface BidDao {
	
	Bid getBid(int bidId) throws DataAccessException;
	
	Bid getBidByAuctionId(int bidId, int auctionId) throws DataAccessException;
	
	String getMaxPrice(int auctionId);
	
	Bid getBidByMaxPrice(int bidMaxPrice, int auctionId);
	
	void createBid(Bid bid) throws DataAccessException;	
	
	List<Bid> getBidByUserId(int userId) throws DataAccessException;
	
	Bid getSuccessBidByAuctionId(int auctionId) throws DataAccessException;
	
	Bid getBidByUserIdAndAuctionId(int userId, int auctionId);
	
	void updateBid(Bid bid) throws DataAccessException;
	
}
