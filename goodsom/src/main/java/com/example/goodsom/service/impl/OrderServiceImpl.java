package com.example.goodsom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.goodsom.dao.AuctionDao;
import com.example.goodsom.dao.GroupBuyDao;
import com.example.goodsom.dao.OrderDao;
import com.example.goodsom.dao.UserDao;
import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Order;
import com.example.goodsom.domain.User;
import com.example.goodsom.service.OrderService;

/**
 * @author Seonmi Hwang
 * @since 2020.05.06
 */

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private GroupBuyDao groupBuyDao;
	@Autowired
	private AuctionDao auctionDao;
	@Autowired
	private UserDao userDao;

	@Override
	public User getUser(String emailId) {
		return userDao.getUserByEmail(emailId);
	}

	@Override
	public Order getOrder(int orderId) {
		return orderDao.getOrder(orderId);
	}
	
	@Override
	public List<Order> getOrdersByGroupBuyId(int groupBuyId) {
		return orderDao.getOrdersByGroupBuyId(groupBuyId);
	}
	  
	@Override
	public List<Order> getOrdersByAuctionId(int auctionId) {
		return orderDao.getOrdersByAuctionId(auctionId);
	}

	@Override
	public int createOrder(Order order) {
		try {
			orderDao.createOrder(order);
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	@Override
	public Order getOrderWithLineGroupBuys(int orderId) {
		return orderDao.getOrderWithLineGroupBuys(orderId);
	}
	
	/* 추가 메소드 */
	
	public GroupBuy getGroupBuy(int orderId) {
		int groupBuyId = orderDao.getGroupBuyId(orderId);
		return groupBuyDao.getGroupBuy(groupBuyId);
	}
	
	public Auction getAuction(int orderId) {
		int auctionId = orderDao.getAuctionId(orderId);
		return auctionDao.getAuction(auctionId);
	}
	
	
	/* Business Logic (?) */
	
	public List<Order> setAuctionInfo(List<Order> auctionOrderList) {
		
		if (auctionOrderList == null) {
			return null;
		}
		
		for (Order order : auctionOrderList) {
			int auctionId = order.getAuctionId();
			order.setAuction(auctionDao.getAuction(auctionId));
			order.setMenuId(1);
		}
		return auctionOrderList;
	}
	
	public List<Order> setGroupBuyInfo(List<Order> groupBuyOrderList) {
		
		if (groupBuyOrderList == null) {
			return null;
		}
		
		for (Order order : groupBuyOrderList) {
			int groupBuyId = order.getGroupBuyId();
			order.setGroupBuy(groupBuyDao.getGroupBuy(groupBuyId));
			order.setMenuId(2);
		}
		return groupBuyOrderList;
	}

}
