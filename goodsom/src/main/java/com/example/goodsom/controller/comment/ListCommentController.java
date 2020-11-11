package com.example.goodsom.controller.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.controller.user.UserSession;
import com.example.goodsom.service.CommentService;

/**
 * @author kimdahyee
 * @since 05.08.2020
 */

/*
@Controller
@SessionAttributes("userSession")
*/
public class ListCommentController {

	/*
	@Autowired
	CommentService commentService;
	
	@RequestMapping("/post/detail.do")
	public ModelAndView handleRequest(
			@ModelAttribute("userSession") UserSession userSession,
			@RequestParam("postId") int postId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("post/community_detail");
		mav.addObject("comment_pList", commentService.getComment_pList(postId));
		return mav;
	}
	
	@RequestMapping("/question/detail.do")
	public ModelAndView handleRequest2(
			@ModelAttribute("userSession") UserSession userSession,
			@RequestParam("questionId") int questionId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("question/question_detail");
		mav.addObject("comment_q", commentService.getComment_q(questionId));
		return mav;
	}
	*/
}
