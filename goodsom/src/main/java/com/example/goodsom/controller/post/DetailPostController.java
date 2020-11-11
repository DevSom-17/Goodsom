package com.example.goodsom.controller.post;

import javax.servlet.http.HttpServletRequest;

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
public class DetailPostController {
	/*
	private PostService postService;
	
	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	
	@RequestMapping("/post/detail.do")
	public ModelAndView postDetail(HttpServletRequest request, @RequestParam("postId") int postId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("post/community_detail");
		mav.addObject("post", postService.getPost(postId));
		return mav;
	}
	*/
}
