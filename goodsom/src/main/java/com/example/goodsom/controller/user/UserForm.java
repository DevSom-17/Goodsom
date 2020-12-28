package com.example.goodsom.controller.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.goodsom.domain.User;

/**
 * @author Yejin Lee
 * @since 2020.05.07
 */
public class UserForm { 
	
	private User user;
	private boolean newUser;
	private String repeatedOriginPassword;
	private String newPassword;
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
	
	public String getRepeatedOriginPassword() {
		return repeatedOriginPassword;
	}

	public void setRepeatedOriginPassword(String repeatedOriginPassword) {
		this.repeatedOriginPassword = repeatedOriginPassword;
	}
	
	public boolean isOriginPasswordMatched(String repeatedOriginPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (!encoder.matches(repeatedOriginPassword, user.getPasswd())) {
			return false;
		}
		return true;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}
	
	public String getRepeatedPassword() {
		return repeatedPassword;
	}
}
