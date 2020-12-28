package com.example.goodsom.controller.user;

/**
 * @author Seonmi Hwang
 * @since 2020.11.25
 */

public class CreateReportForm {
	private int id;
	private int reporterId; // 신고자
	private String content;
	private int userId; // 피신고자
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
		return "CreateReportForm [id=" + id + ", reporterId=" + reporterId + ", content=" + content + ", userId="
				+ userId + "]";
	}
	
}
