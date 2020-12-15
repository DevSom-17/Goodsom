package com.example.goodsom.dao.mybatis.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.goodsom.domain.Order;

/**
 * @author Seonmi Hwang
 * @since 2020.06.11
 */

public interface OrderMapper {

	  Order getOrder(int orderId) throws DataAccessException;
	  
	  List<Order> getOrdersByGroupBuyId(int groupBuyId) throws DataAccessException;

	  List<Order> getOrdersByAuctionId(int auctionId) throws DataAccessException;
	  
	  int createOrder(Order order) throws DataAccessException;
	  
	  Order getOrderWithLineGroupBuys(int orderId) throws DataAccessException; 

	// orderList에서 공동구매 정보 출력을 위해서 필요
	  int getGroupBuyId(int orderId) throws DataAccessException; // From LineGroupBuys

	// orderList에서 경매 정보 출력을 위해서 필요
	  int getAuctionId(int orderId) throws DataAccessException; // From SuccessBidders
	  
	  int deleteOrderByUserId(int userId) throws DataAccessException; // 회원 탈퇴 시 실행
}
