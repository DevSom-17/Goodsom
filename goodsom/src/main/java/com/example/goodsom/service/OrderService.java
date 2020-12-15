package com.example.goodsom.service;

import java.util.List;

import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Order;
import com.example.goodsom.domain.User;

/**
 * @author Seonmi Hwang
 * @since 2020.05.04
 */

public interface OrderService {
	
	public User getUser(String emailId);
	
	public Order getOrder(int orderId); // DetailOrderController에서 사용
	
	List<Order> getOrdersByGroupBuyId(int groupBuyId);
	  
	List<Order> getOrdersByAuctionId(int auctionId);
	
	public int createOrder(Order order);

	public Order getOrderWithLineGroupBuys(int orderId);
	
	public GroupBuy getGroupBuy(int orderId);
	
	public Auction getAuction(int orderId);
	
	public List<Order> setAuctionInfo(List<Order> orderList);
	
	public List<Order> setGroupBuyInfo(List<Order> orderList);
}
