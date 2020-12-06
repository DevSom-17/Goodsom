package com.example.goodsom.dao.mybatis.mapper;

import java.util.List;

import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Order;
import com.example.goodsom.domain.User;

/**
 * @author kimdahyee | Seonmi-Hwang
 * @since 2020.06.12 | 2020.06.13
 */

public interface UserMapper {

	User getUser(String email, String passwd);
	
	User getUserByEmail(String email);
	
	User getUserByUserId(int userId);
	
	void createUser(User user);

	int updateUser(User user);

	int deleteUser(User user);
	
	List<Order> getAuctionOrderList(int userId);
	
	List<Order> getGroupBuyOrderList(int userId);
	
	List<GroupBuy> getGroupBuyList(int userId); // 마이페이지 공동구매 등록 목록 보기
	
//	List<Auction> getAuctionList(int userId); // 마이페이지 경매 등록 목록 보기  -> AuctionMapper.xml의 getAuctionListByUserId(int userId)가 대신 함!
	
	List<String> getReportList(int userId); // 신고 현황 상세 페이지
}
