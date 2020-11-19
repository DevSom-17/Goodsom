package  com.example.goodsom.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.goodsom.domain.Bid;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Notification;

public interface NotificationDao {
	
	List<Notification> getAuctionNotiByUserId(int userId) throws DataAccessException; //Do sql operations twice.
	
	Notification getAuctionNoti(int notiId) throws DataAccessException; // Two tables share one noti_sequence.
	
	Notification getGroupBuyNoti(int notiId) throws DataAccessException;
//	void createNoti_g(int groupBuyId) throws DataAccessException; // lineGroupBuys table
	
	void createNoti_a(Bid bid) throws DataAccessException; // successBidders table
	
	
	void createNoti_g(Notification noti) throws DataAccessException;
	
	List<Notification> getGroupBuyNotiByUserId(int userId) throws DataAccessException;
	
	void deleteAuctionNoti(int notiId) throws DataAccessException;
	
	void deleteGroupBuyNoti(int notiId) throws DataAccessException;
	
	void notiUserUpdate(int groupBuyId) throws DataAccessException;
	
	int[] getUserIdByGroupBuyId(int groupBuyId) throws DataAccessException;
	
//	List<Bid> getBidByUserId(int userId) throws DataAccessException;
}
