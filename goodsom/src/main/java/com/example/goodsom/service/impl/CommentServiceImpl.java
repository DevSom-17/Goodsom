package com.example.goodsom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodsom.dao.Comment_pDao;
import com.example.goodsom.dao.Comment_qDao;
import com.example.goodsom.domain.Comment_p;
import com.example.goodsom.domain.Comment_q;
import com.example.goodsom.service.CommentService;

/**
 * @author kimdahyee
 * @since 05.06.2020
 */

//@Service
public class CommentServiceImpl implements CommentService {

	/*
	@Autowired
	private Comment_pDao comment_pDao;
	
	@Autowired
	private Comment_qDao comment_qDao;
	
	@Override
	public List<Comment_p> getComment_pList(int postId) {
		return comment_pDao.getComment_pList(postId);
	}
	
	@Override
	public Comment_p getComment_p(int postId) {
		return comment_pDao.getComment_p(postId);
	}

	@Override
	public Comment_q getComment_q(int questionId) {
		return comment_qDao.getComment_q(questionId);
	}

	@Override
	public Comment_p createCommnet_p(Comment_p comment) {
		return comment_pDao.createCommnet_p(comment);
	}

	@Override
	public Comment_q createComment_q(Comment_q comment) {
		return comment_qDao.createComment_q(comment);
	}

	@Override
	public void updateComment_p(Comment_p comment) {
		comment_pDao.updateComment_p(comment);
	}

	@Override
	public void updateComment_q(Comment_q comment) {
		comment_qDao.updateComment_q(comment);		
	}

	@Override
	public void deleteComment_p(int commentId) {
		comment_pDao.deleteComment_p(commentId);
	}

	@Override
	public void deleteComment_q(int commentId) {
		comment_qDao.deleteComment_q(commentId);
	}
	*/
}
