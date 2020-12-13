package com.example.goodsom.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
public class Bid implements Serializable {
	int bidId;
	int userId;
	int auctionId;
	@NotNull
	int bidPrice;
	boolean isBidded;
	Date bidDate;
	User user = new User();
	
	String auctionTitle;
	
	int notiId;
	public Bid() {
		
	}
	
	public Bid(int userId, int auctionId, int bidPrice, Date bidDate) {
		this.userId = userId;
		this.auctionId = auctionId;
		this.bidPrice = bidPrice;
		this.bidDate = bidDate;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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
	
	public int getAuctionId() {
		return auctionId;
	}
	
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	
	public int getBidPrice() {
		return bidPrice;
	}
	
	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}
	
	public boolean getIsBidded() {
		return isBidded;
	}
	
	public void setIsBidded(boolean isBidded) {
		this.isBidded = isBidded;
	}
	
	public Date getBidDate() {
		return bidDate;
	}
	
	public void setBidDate(Date bidDate) {
		this.bidDate = bidDate;
	}
	
	public String getAuctionTitle() {
		return auctionTitle;
	}
	
	public void setAuctionTitle(String auctionTitle) {
		this.auctionTitle = auctionTitle;
	}

	public int getNotiId() {
		return notiId;
	}
	
	public void getNotiId(int notiId) {
		this.notiId = notiId;
	}
	
	public String toString() {
		String str = "Bid [ bidId: " + bidId + ", userId" + userId + ", auctionId" + auctionId 
				 + ", bidPrice" + bidPrice + ", isBidded" + isBidded + ", bidDate" + bidDate;
		
		return str;
	}
}
