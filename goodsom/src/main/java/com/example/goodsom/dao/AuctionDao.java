package  com.example.goodsom.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.Bid;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.SuccessBidder;

/**
 * @author Hyekyung Kim | Yejin Lee  | kimdahyee  | Seonmi Hwang
 * @since  2020.05.05   | 2020.06.12 | 2020.06.25 | 2020.06.29
 */

public interface AuctionDao {
	
	Auction getAuction(int auctionId) throws DataAccessException;
	
//	Return auctionID
	int createAuction(Auction auction) throws DataAccessException;
//	Return auctionID
	int updateAuction(Auction auction) throws DataAccessException;
	
	int updateAuctionMaxPrice(int maxPrice, int auctionId) throws DataAccessException;
	
	void deleteAuction(int auctionId) throws DataAccessException;
	
	List<Auction> getAuctionList() throws DataAccessException;
	
	List<Auction> getAuctionListByKeyword(String keyword) throws DataAccessException;
	
	boolean isAuctionClosed(int auctionId, Date currentTime) throws DataAccessException;
	
	void increaseCount(Auction auction) throws DataAccessException;
	
	List<Auction> getRecentAuctionList() throws DataAccessException;
//	스케줄러
	void closeEvent(Date curTime);
	
	public Integer getSuccessBidderUserId(int auctionId);
	
	public SuccessBidder getSuccessBidderByAuctionId(int auctionId);
	
//	keyword로 검색
	public List<Auction> auctionListByKeyword(String keyword);
}
