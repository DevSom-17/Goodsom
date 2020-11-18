package com.example.goodsom.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.goodsom.dao.NotificationDao;
import com.example.goodsom.dao.mybatis.mapper.NotiMapper;
import com.example.goodsom.domain.Bid;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Notification;

/**
 * @author HK
 * @since  2020.06.29
 */

@Repository
public class MybatisNotiDao implements NotificationDao {
	
	@Autowired
	private NotiMapper notiMapper;
	
	public void createNoti_a(Bid bid) throws DataAccessException{
		notiMapper.createNoti_a(bid);
	}
	
	public Notification getAuctionNoti(int notiId) throws DataAccessException{
		return notiMapper.getAuctionNoti(notiId);
	}
	
	public Notification getGroupBuyNoti(int notiId) throws DataAccessException{
		return notiMapper.getGroupBuyNoti(notiId);
	}
	
//	public List<Bid> getBidByUserId(int userId)throws DataAccessException{
//		return notiMapper.getBidByUserId(userId);
//	}
	
	public List<Notification> getAuctionNotiByUserId(int userId) throws DataAccessException{
		return notiMapper.getAuctionNotiByUserId(userId);
	}
	
	public List<Notification> getGroupBuyNotiByUserId(int userId) throws DataAccessException{
		return notiMapper.getGroupBuyNotiByUserId(userId);
	}
	
	public void createNoti_g(Notification noti) throws DataAccessException{
		notiMapper.createNoti_g(noti);
	}
	
	public int[] getUserIdByGroupBuyId(int groupBuyId) throws DataAccessException{
		return notiMapper.getUserIdByGroupBuyId(groupBuyId);
	}
	
	public void deleteAuctionNoti(int notiId) throws DataAccessException{
		notiMapper.deleteAuctionNoti(notiId);
	}
	
	public void deleteGroupBuyNoti(int notiId) throws DataAccessException{
		notiMapper.deleteGroupBuyNoti(notiId);
	}

	@Override
	public void notiUserUpdate(int groupBuyId) throws DataAccessException {
		notiMapper.notiUserUpdate(groupBuyId);
	}
}