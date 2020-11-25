package com.example.goodsom.domain;

/**
 * @author Seonmi-Hwang
 * @since  2020.11.25
 */

public class Report_a {
	int auctionId;
	int reporterId;
	String content;
	int userId;
	
	public int getAuctionId() {
		return auctionId;
	}
	
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	
	public int getReporterId() {
		return reporterId;
	}
	
	public void setReporterId(int reporterId) {
		this.reporterId = reporterId;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Report_a [auctionId=" + auctionId + ", reporterId=" + reporterId + ", content=" + content + ", userId="
				+ userId + "]";
	}
}
