package com.example.goodsom.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Order;
import com.example.goodsom.domain.User;
import com.example.goodsom.dao.UserDao;
import com.example.goodsom.dao.mybatis.mapper.UserMapper;

/**
 * @author kimdahyee  | Seonmi-Hwang
 * @since  2020.06.12 | 2020.06.13
 */

@Repository
public class MybatisUserDao implements UserDao {
	
	@Autowired
	private UserMapper userMapper;
	
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

	public int deleteUser(User user) throws DataAccessException {
		return userMapper.deleteUser(user);
	}
	
	public List<Order> getAuctionOrderList(int orderId) throws DataAccessException { // 마이페이지 결제 목록 보기
		return userMapper.getAuctionOrderList(orderId);
	}
	
	public List<Order> getGroupBuyOrderList(int orderId) throws DataAccessException { // 마이페이지 결제 목록 보기
		return userMapper.getGroupBuyOrderList(orderId);
	}
	
	public List<GroupBuy> getGroupBuyList(int orderId) throws DataAccessException { // 마이페이지 공동구매 등록 목록 보기
		return userMapper.getGroupBuyList(orderId);
	}
	
	public List<Auction> getAuctionList(int orderId) throws DataAccessException { // 마이페이지 경매 등록 목록 보기
		return userMapper.getAuctionList(orderId);
	}
}
