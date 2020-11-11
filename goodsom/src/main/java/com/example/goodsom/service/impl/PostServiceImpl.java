package com.example.goodsom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodsom.dao.PostDao;
import com.example.goodsom.domain.Post;
import com.example.goodsom.service.PostService;

/**
 * @author kimdahyee
 * @since 05.06.2020
 */

//@Service("postServiceImpl")
public class PostServiceImpl implements PostService {

	/*
	@Autowired
	private PostDao postDao;
	
	@Override
	public List<Post> getPostList() {
		return postDao.getPostList();
	}

	@Override
	public Post getPost(int postId) {
		return postDao.getPost(postId);
	}

	@Override
	public Post createPost(Post post) {
		return postDao.createPost(post);
	}

	@Override
	public void updatePost(Post post) {
		postDao.updatePost(post);
	}

	@Override
	public void deletePost(int postId) {
		postDao.deletePost(postId);
	}

	@Override
	public List<Post> getPostByKeyword(String keyword) {
		return postDao.getPostByKeyword(keyword);
	}
	*/
}
