package com.example.goodsom.controller.user;

import java.io.Serializable;

import com.example.goodsom.domain.User;

/**
 * @author Yejin Lee
 * @since 2020.05.01
 */

@SuppressWarnings("serial")
public class UserSession implements Serializable {
	
	private User user;

	public UserSession(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
