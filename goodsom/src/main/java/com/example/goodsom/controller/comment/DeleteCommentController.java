package com.example.goodsom.controller.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.service.CommentService;

/**
 * @author kimdahyee
 * @since 05.08.2020
 */

//@Controller
public class DeleteCommentController {
	
	/*
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("comment_p/delete.do")
	public ModelAndView handleRequest (
			@RequestParam("postId") int postId,
			@RequestParam("commentId") int commentId) {
		commentService.deleteComment_p(commentId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("post/community_detail");
		mav.addObject("comment_pList", commentService.getComment_pList(postId));
		return mav;
	}
	
	@RequestMapping("comment_q/delete.do")
	public ModelAndView handleRequest2 (
			@RequestParam("questionId") int questionId,
			@RequestParam("commentId") int commentId) {
		commentService.deleteComment_q(commentId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("question/question_detail");
		mav.addObject("comment_q", commentService.getComment_q(questionId));
		return mav;
	}
	*/
}
