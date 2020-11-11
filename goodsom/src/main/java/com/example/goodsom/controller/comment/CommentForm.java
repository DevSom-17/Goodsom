package com.example.goodsom.controller.comment;

import java.io.Serializable;

import com.example.goodsom.domain.Comment_p;
import com.example.goodsom.domain.Comment_q;

/**
 * @author kimdahyee
 * @since 05.08.2020
 */

//@SuppressWarnings("serial")
public class CommentForm implements Serializable {

	/*
	private int num; //comment_p인지 comment_q인지 구별하기 위한
	
	private Comment_p comment_p;
	private Comment_q comment_q;
	
	private boolean newComment_p;
	private boolean newComment_q;
	
	public Comment_p getComment_p() {
		return comment_p;
	}
	
	public Comment_q getComment_q() {
		return comment_q;
	}
	
	public CommentForm(int num) {
		if (num == 0) {
			this.comment_p = new Comment_p();
			this.newComment_p = true;
		} else {
			this.comment_q = new Comment_q();
			this.newComment_q = true;
		}
	}
	
	public CommentForm(Comment_p comment_p) {
		this.comment_p = comment_p;
		this.newComment_p = false;
	}
	
	public CommentForm(Comment_q comment_q) {
		this.comment_q = comment_q;
		this.newComment_p = false;
	}
	
	public boolean isNewComment_p() {
		return newComment_p;
	}
	
	public boolean isNewComment_q() {
		return newComment_q;
	}
	*/
}
