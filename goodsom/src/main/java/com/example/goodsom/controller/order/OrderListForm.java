package com.example.goodsom.controller.order;

import java.util.ArrayList;
import java.util.List;

import com.example.goodsom.domain.Order;

/**
 * @author Seonmi Hwang
 * @since 2020.12.16
 */

public class OrderListForm {
	private int groupBuyId;
	private int auctionId;
	
	List<Order> orderList = new ArrayList<>();

	public int getGroupBuyId() {
		return groupBuyId;
	}

	public void setGroupBuyId(int groupBuyId) {
		this.groupBuyId = groupBuyId;
	}

	public int getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	@Override
	public String toString() {
		return "OrderListForm [orderList=" + orderList + "]";
	}

}
