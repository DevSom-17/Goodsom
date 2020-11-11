package com.example.goodsom.controller.user;

import com.example.goodsom.domain.User;

/**
 * @author Yejin Lee
 * @since 2020.05.07
 */
public class UserForm { 
	
	private User user;
	private boolean newUser;
	private String repeatedPassword;
	
	public UserForm() {
		this.user = new User();
		this.newUser = true;
	}
	
	public UserForm(User user) {
		this.user = user;
		this.newUser = false;
	}
	
	public User getUser() {
		return user;
	}
	
	public boolean isNewUser() {
		return newUser;
	}
	
	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}
	
	public String getRepeatedPassword() {
		return repeatedPassword;
	}
}
