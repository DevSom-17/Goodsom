package com.example.goodsom.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.service.PostService;

/**
 * @author kimdahyee
 * @since 05.08.2020
 */

//@Controller
public class DeletePostController {
	/*
	@Autowired
	private PostService postService;
	
	@RequestMapping("post/delete.do")
	public ModelAndView handleRequest (
			@RequestParam("postId") int postId) {
		postService.deletePost(postId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("post/community_detail");
		mav.addObject("postList", postService.getPostList());
		return mav;
	}
	*/
}
