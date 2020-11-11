package com.example.goodsom.domain;
import java.util.Date;

public class Question {
	int questionId;
	String title;
	String content;
	Date uploadDate;
	int groupBuyId;
	int userId;

	Comment_q comment = new Comment_q();
	
	public Comment_q getComment() {
		return comment;
	}

	public void setComment(Comment_q comment) {
		this.comment = comment;
	}

	public Question() {
		
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getGroupBuyId() {
		return groupBuyId;
	}
	
	public void setGroupBuyId(int groupBuyId) {
		this.groupBuyId = groupBuyId;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}	
	
	public Date getUplodatDate() {
		return uploadDate;
	}
	
	public void setUplodatDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	public int getQuestionId() {
		return questionId;
	}
	
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public Date getUploadDate() {
		return uploadDate;
	}
	
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
