package com.example.goodsom.dao.mybatis.mapper;

import java.util.List;

import com.example.goodsom.domain.Order;
import com.example.goodsom.domain.User;

/**
 * @author kimdahyee | Seonmi-Hwang
 * @since 2020.06.12 | 2020.06.13
 */

public interface UserMapper {

	User getUserByEmail(String email);
	
	User getUserByUserId(int userId);
	
	void createUser(User user);

	int updateUser(User user);

	void deleteUser(User user);
	
	List<Order> getAuctionOrderList(int userId);
	
	List<Order> getGroupBuyOrderList(int userId);
	
}
