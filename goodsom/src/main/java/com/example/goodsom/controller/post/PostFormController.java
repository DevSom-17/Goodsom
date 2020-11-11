package com.example.goodsom.controller.post;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.goodsom.service.PostService;

/**
 * @author kimdahyee
 * @since 05.08.2020
 */

/*
@Controller
@RequestMapping({"/post/create.do", "/post/update.do"})
*/
public class PostFormController {
	/*
	@Autowired
	private PostService postService;
	
	private final String formViewName = "post/community_form";
	private final String detailViewName = "post/community_detail";
	
	@ModelAttribute("postForm")
	public PostForm formBacking(HttpServletRequest request, 
			@RequestParam("postId") int postId) throws Exception {
		String reqPage = request.getServletPath();

		if (reqPage.trim().equals("/community_form") && request.getMethod().equals("GET")) { // create
			return new PostForm(); 
		} else { // update or show(after create) Post
			return new PostForm(postService.getPost(postId));
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String form() {
		return formViewName;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String updateOrSubmit(HttpServletRequest request,
						PostForm postForm) {
		String reqPage = request.getServletPath();
		
		if (reqPage.trim().equals("community_form")) { // update
			postService.updatePost(postForm.getPost());
			return formViewName;
		} else { // show after create
			postService.createPost(postForm.getPost());
			return detailViewName;
		}
	}
	
	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	*/
}
