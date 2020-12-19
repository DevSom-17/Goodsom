package com.example.goodsom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.goodsom.controller.mypage.ReportForm;
import com.example.goodsom.controller.user.CreateReportForm;
import com.example.goodsom.dao.AuctionDao;
import com.example.goodsom.dao.BidDao;
import com.example.goodsom.dao.GroupBuyDao;
import com.example.goodsom.dao.LikeDao;
import com.example.goodsom.dao.NotificationDao;
import com.example.goodsom.dao.ReportDao;
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
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Autowired 
	private ReportDao reportDao;
	
	@Autowired
	private GroupBuyDao groupBuyDao;
	
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
	public int deleteUser(User user) { // 알림, 좋아요, 베팅, 신고 목록 삭제
		try {
//		    DAO method들을 호출 (트랜잭션으로 실행)
			userDao.deleteUser(user);
		  } catch (DataAccessException ex) {
		     // 트랜잭션에서 오류 발생 시 실행해야 할 코드 작성
		      ex.getMessage();
		      return 0;
		  }
		  return 1;
	}

	@Override
	public List<Order> getAuctionOrderList(int userId) { // 마이페이지 결제 목록 보기
		return userDao.getAuctionOrderList(userId);
	}
	
	@Override
	public List<Order> getGroupBuyOrderList(int userId) { // 마이페이지 결제 목록 보기
		return userDao.getGroupBuyOrderList(userId);
	}
	
//	@Override	
//	public List<GroupBuy> getGroupBuyList(int userId) { // 마이페이지 공동구매 등록 목록 보기
//		return userDao.getGroupBuyList(userId);
//	}
	
//	@Override	
//	public List<Auction> getAuctionList(int userId) { // 마이페이지 경매 등록 목록 보기
//		return userDao.getAuctionList(userId);
//	}
	
	public ReportForm getReportList(int userId) throws DataAccessException {
		List<String> reportList = reportDao.getReportList(userId);
		
		ReportForm reportForm = new ReportForm();
		int abuse = 0;
		int destroy = 0;
		
		for (String val : reportList) {
			if (val.equals("욕설 및 비방")) { // 욕설 및 비방
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
		List<GroupBuy> groupBuys = groupBuyDao.getGroupBuyListByUserId(userId);
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
	
	public void createReport_a(CreateReportForm reportForm) throws DataAccessException { // 신고 현황 상세 페이지 
		reportDao.createReport_a(reportForm);
	}
	
	public void createReport_g(CreateReportForm reportForm) throws DataAccessException { // 신고 현황 상세 페이지 
		reportDao.createReport_g(reportForm);
	}
	
	public void updateReport(int userId) throws DataAccessException {
		reportDao.updateReport(userId);
	}
}
