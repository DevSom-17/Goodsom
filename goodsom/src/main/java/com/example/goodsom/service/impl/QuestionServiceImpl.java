package com.example.goodsom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodsom.dao.QuestionDao;
import com.example.goodsom.domain.Question;
import com.example.goodsom.service.QuestionService;

/**
 * @author Seonmi Hwang
 * @since 2020.05.07
 */

//@Service
public class QuestionServiceImpl implements QuestionService {
	/*
	@Autowired
	private QuestionDao questionDao;
	
	public void createQuestion(Question question) {
		questionDao.createQuestion(question);
	}
	
	public void updateQuestion(Question question) {
		questionDao.updateQuestion(question);
	}
	
	public void deleteQuestion(int questionId) {
		questionDao.deleteQuestion(questionId);
	}
	
	public Question getQuestion(int questionId) {
		return questionDao.getQuestion(questionId);
	}
	*/
}
