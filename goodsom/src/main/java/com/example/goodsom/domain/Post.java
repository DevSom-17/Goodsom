package com.example.goodsom.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
	int postId;
	String title;
	String content;
	Date uploadDate;
	String img;
	int count;
	int userId;
	List<Comment_p> comment_p = new ArrayList<Comment_p>();
	int menuId;
	int catId;
	
	public Post() {

	}
	
	public int getMenuId() {
		return menuId;
	}
	
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getPostId() {
		return postId;
	}
	
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getUploadDate() {
		return uploadDate;
	}
	
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public List<Comment_p> getComment_p() {
		return comment_p;
	}
	
	public void setComment_p(List<Comment_p> comment_p) {
		this.comment_p = comment_p;
	}

}
