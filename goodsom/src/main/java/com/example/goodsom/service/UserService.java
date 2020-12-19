package com.example.goodsom.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.goodsom.controller.mypage.ReportForm;
import com.example.goodsom.controller.user.CreateReportForm;
import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Order;
import com.example.goodsom.domain.User;

/**
 * @author Yejin Lee | kimdahyee  | Seonmi-Hwang
 * @since 2020.05.06 | 2020.06.12 | 2020.06.13
 */

public interface UserService {

	User getUser(String emailId, String password); // 로그인 시 사용
	
	User getUserByEmail(String email);
	
	User getUserByUserId(int userId) throws DataAccessException;
	
	void createUser(User user);

	int updateUser(User user);

	int deleteUser(User user);
	
	List<Order> getAuctionOrderList(int userId);
	
	List<Order> getGroupBuyOrderList(int userId);
	
//	List<GroupBuy> getGroupBuyList(int userId); // 마이페이지 공동구매 등록 목록 보기
	
//	List<Auction> getAuctionList(int userId); // 마이페이지 경매 등록 목록 보기
	
	ReportForm getReportList(int userId); // 신고 현황 상세 페이지
	
	boolean isUnClosedExist(int userId);
	
	void createReport_a(CreateReportForm reportForm);
	
	void createReport_g(CreateReportForm reportForm);
	
	void updateReport(int userId);
}
