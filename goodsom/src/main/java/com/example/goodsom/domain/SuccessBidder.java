package com.example.goodsom.domain;
/**
 * @author Seonmi Hwang
 * @since 2020.06.27
 */

public class SuccessBidder {
	int bidId;
	int userId;
	int orderId;
	int auctionId;

	public int getBidId() {
		return bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	@Override
	public String toString() {
		return "SuccessBidder [bidId=" + bidId + ", userId=" + userId + ", orderId=" + orderId + ", auctionId="
				+ auctionId + "]";
	}
	
}
