package com.example.goodsom.controller.comment;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.goodsom.service.CommentService;

/**
 * @author kimdahyee
 * @since 05.08.2020
 * requestMapping이 제대로 이루어진건지 모르겠음
 */

//@Controller
public class CommentFormController {
	
	/*
	@Autowired
	private CommentService commentService;
	
	private final String formViewName_p = "post/community_detail";
	private final String detailViewName_p = "post/community_detail";
	
	private final String formViewName_q = "question/question_detail";
	private final String detailViewName_q = "question/question_detail";
	
	//Post
	@ModelAttribute("CommentForm_p")
	public CommentForm formBacking(HttpServletRequest request, 
			@RequestParam("postId") int postId,
			@RequestParam("commentId") int commentId) throws Exception {
		String reqPage = request.getServletPath();

		if (reqPage.trim().equals("/post_detail") && request.getMethod().equals("GET")) { // create
			return new CommentForm(0); 
		} else { // update or show(after create) Post
			return new CommentForm(commentService.getComment_p(postId));
		}
	}
	
	@RequestMapping(value = "/comment_p/create.do", method = RequestMethod.GET)
	public String form() {
		return formViewName_p;
	}
	
	@RequestMapping(value = "/comment_p/update.do", method = RequestMethod.POST)
	public String updateOrSubmit(HttpServletRequest request,
						CommentForm commentForm) {
		String reqPage = request.getServletPath();
		
		if (reqPage.trim().equals("post_detail")) { // update
			commentService.updateComment_p(commentForm.getComment_p());
			return formViewName_p;
		} else { // show after create
			commentService.createCommnet_p(commentForm.getComment_p());
			return detailViewName_p;
		}
	}
	
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	//Question
	@ModelAttribute("CommentForm_q")
	public CommentForm formBacking2(HttpServletRequest request, 
			@RequestParam("questionId") int questionId,
			@RequestParam("commentId") int commentId) throws Exception {
		String reqPage = request.getServletPath();

		if (reqPage.trim().equals("/question_detail") && request.getMethod().equals("GET")) { // create
			return new CommentForm(1); 
		} else { // update or show(after create) Post
			return new CommentForm(commentService.getComment_q(questionId));
		}
	}
	
	@RequestMapping(value = "/comment_q/create.do", method = RequestMethod.GET)
	public String form2() {
		return formViewName_q;
	}
	
	@RequestMapping(value = "/comment_q/update.do", method = RequestMethod.POST)
	public String updateOrSubmit2(HttpServletRequest request,
						CommentForm commentForm) {
		String reqPage = request.getServletPath();
		
		if (reqPage.trim().equals("question_detail")) { // update
			commentService.updateComment_q(commentForm.getComment_q());
			return formViewName_q;
		} else { // show after create
			commentService.createComment_q(commentForm.getComment_q());
			return detailViewName_q;
		}
	}
	*/
}
