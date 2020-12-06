package com.example.goodsom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.goodsom.controller.mypage.ReportForm;
import com.example.goodsom.dao.AuctionDao;
import com.example.goodsom.dao.UserDao;
import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Order;
import com.example.goodsom.domain.User;
import com.example.goodsom.service.UserService;

/**
 * @author Yejin Lee | kimdahyee  | Seonmi-Hwang
 * @since 2020.05.03 | 2020.06.12 | 2020.06.13
 */

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AuctionDao auctionDao;
	
	public User getUser(String email, String password) {
		return userDao.getUser(email, password);
	}
	
	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public User getUserByUserId(int userId) throws DataAccessException {
		return userDao.getUserByUserId(userId);
	}

	@Override
	public void createUser(User user) {
		userDao.createUser(user);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public int deleteUser(User user) {
		return userDao.deleteUser(user);
	}

	@Override
	public List<Order> getAuctionOrderList(int userId) { // 마이페이지 결제 목록 보기
		return userDao.getAuctionOrderList(userId);
	}
	
	@Override
	public List<Order> getGroupBuyOrderList(int userId) { // 마이페이지 결제 목록 보기
		return userDao.getGroupBuyOrderList(userId);
	}
	
	@Override	
	public List<GroupBuy> getGroupBuyList(int userId) { // 마이페이지 공동구매 등록 목록 보기
		return userDao.getGroupBuyList(userId);
	}
	
//	@Override	
//	public List<Auction> getAuctionList(int userId) { // 마이페이지 경매 등록 목록 보기
//		return userDao.getAuctionList(userId);
//	}
	
	public ReportForm getReportList(int userId) {
		List<String> reportList = userDao.getReportList(userId);
		
		ReportForm reportForm = new ReportForm();
		int abuse = 0;
		int destroy = 0;
		
		for (String val : reportList) {
			if (val.equals('0')) { // 욕설 및 비방
				abuse++;
			} else { // 거래 파기 '1'
				destroy++;
			}
		}
		reportForm.setAbuse(abuse);
		reportForm.setDestroy(destroy);
		
		return reportForm;
	}
	
	public boolean isUnClosedExist(int userId) {
		List<GroupBuy> groupBuys = userDao.getGroupBuyList(userId);
		List<Auction> auctions = auctionDao.getAuctionListByUserId(userId);
		
		if (groupBuys != null && auctions != null) {
			for (GroupBuy groupBuy : groupBuys) {
				if (!groupBuy.getState().equals("closed")) {
					return true;
				}
			}
			
			for (Auction auction : auctions) {
				if (!auction.getState().equals("closed")) {
					return true;
				}
			}
		}
		return false;
	}

}
