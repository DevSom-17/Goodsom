package com.example.goodsom.dao.mybatis.mapper;

import java.util.List;


import com.example.goodsom.domain.Bid;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Notification;

public interface NotiMapper {
	void createNoti_a(Bid bid); // successBidders table

//	List<Bid> getBidByUserId(int userId);
	Notification getAuctionNoti(int notiId);
	
	Notification getGroupBuyNoti(int notiId);
	
	List<Notification> getAuctionNotiByUserId(int userId);
	
	List<Notification> getGroupBuyNotiByUserId(int userId);
	
	void deleteAuctionNoti(int notiId);
	
	void createNoti_g(Notification noti);
	
	int[] getUserIdByGroupBuyId(int groupBuyId);
	
	void deleteGroupBuyNoti(int notiId);
	
	void notiUserUpdate(int groupBuyId);
	
	void deleteNotisByUserId_a(int userId);
	
	void deleteNotisByUserId_g(int userId);
}
