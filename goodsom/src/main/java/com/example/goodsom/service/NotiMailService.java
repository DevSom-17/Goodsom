package com.example.goodsom.service;

public interface NotiMailService {

	public void sendAuctionMessage(String to) throws Exception;
	
	public void sendGroupBuyCloseMessage(String to) throws Exception;
	
	public void sendGroupBuyAchieveMessage(String to) throws Exception;
}
