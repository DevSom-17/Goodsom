package com.example.goodsom.dao.mybatis;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Order;
import com.example.goodsom.domain.User;
import com.example.goodsom.controller.user.CreateReportForm;
import com.example.goodsom.dao.UserDao;
import com.example.goodsom.dao.mybatis.mapper.BidMapper;
import com.example.goodsom.dao.mybatis.mapper.LikeMapper;
import com.example.goodsom.dao.mybatis.mapper.NotiMapper;
import com.example.goodsom.dao.mybatis.mapper.ReportMapper;
import com.example.goodsom.dao.mybatis.mapper.UserMapper;

/**
 * @author kimdahyee  | Seonmi-Hwang
 * @since  2020.06.12 | 2020.06.13
 */

@Repository
public class MybatisUserDao implements UserDao {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private NotiMapper notiMapper;
	@Autowired
	private LikeMapper likeMapper;
	@Autowired
	private BidMapper bidMapper;
	@Autowired
	private ReportMapper reportMapper;
	
	public User getUser(String email, String passwd) throws DataAccessException {
		return userMapper.getUser(email, passwd);
	}
	
	public User getUserByEmail(String emailId) throws DataAccessException {
		return userMapper.getUserByEmail(emailId);
	}
	
	public User getUserByUserId(int userId) throws DataAccessException {
		return userMapper.getUserByUserId(userId);
	}
	
	public void createUser(User user) throws DataAccessException {
		userMapper.createUser(user);
	}

	public int updateUser(User user) throws DataAccessException {
		return userMapper.updateUser(user);
	}

	@Transactional
	public void deleteUser(User user) throws DataAccessException { // 알림, 좋아요, 신고, 베팅 목록 삭제
		int userId = user.getUserId();
		
		notiMapper.deleteNotisByUserId_a(userId);
		notiMapper.deleteNotisByUserId_g(userId);
		
		likeMapper.deletelikesByUserId_a(userId);
		likeMapper.deletelikesByUserId_g(userId);
		
		reportMapper.deleteReportsByUserId_a(userId);
		reportMapper.deleteReportsByUserId_g(userId);

		bidMapper.deleteBidsByUserId(userId);
		
		userMapper.deleteUser(user);
	}
	
	public List<Order> getAuctionOrderList(int orderId) throws DataAccessException { // 마이페이지 결제 목록 보기
		return userMapper.getAuctionOrderList(orderId);
	}
	
	public List<Order> getGroupBuyOrderList(int orderId) throws DataAccessException { // 마이페이지 결제 목록 보기
		return userMapper.getGroupBuyOrderList(orderId);
	}
	
//	public List<GroupBuy> getGroupBuyList(int orderId) throws DataAccessException { // 마이페이지 공동구매 등록 목록 보기
//		return userMapper.getGroupBuyList(orderId);
//	}
	
//	public List<Auction> getAuctionList(int orderId) throws DataAccessException { // 마이페이지 경매 등록 목록 보기
//		return userMapper.getAuctionList(orderId);
//	}

}
