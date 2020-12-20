package com.example.goodsom.service;

public interface NotiMailService {

//	경매
	public void sendAuctionMessage(String to) throws Exception;
	
	public void sendAuctionWriterMessage(String to) throws Exception;
	
//	공동구매
	public void sendGroupBuyCloseMessage(String to) throws Exception;
	
	public void sendGroupBuyAchieveMessage(String to) throws Exception;
	
	public void sendGroupBuyWriterCloseMessage(String to) throws Exception;
	
	public void sendGroupBuyWriterAchieveMessage(String to) throws Exception;
}
