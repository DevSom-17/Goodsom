package com.example.goodsom.domain;
import java.io.Serializable;

/**
 * @author kimdahyee
 * @since  2020.06.12
 */

@SuppressWarnings("serial")
public class User implements Serializable {
	
	int userId;
	String email;
	String passwd;
	String userName;
	String nickname;
	String address1;
	String address2;
	String address3;
	String phone;
	String refundBank;
	String refundAccount;
	
	/*
	public User() {}
	
	public User(String emailId, String password) {
		this.emailId = emailId;
		this.password = password;
	}

	public User(int userId, String emailId, String password, String nickname, String address1, String address2, String address3, String phone, String refundBank, String refundAccount) {
		this.userId = userId;
		this.emailId = emailId;
		this.password = password;
		this.nickname = nickname;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.phone = phone;
		this.refundBank = refundBank;
		this.refundAccount = refundAccount;
	} */
	
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getPasswd() {
		return passwd;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getRefundBank() {
		return refundBank;
	}

	public void setRefundBank(String refundBank) {
		this.refundBank = refundBank;
	}

	public String getRefundAccount() {
		return refundAccount;
	}

	public void setRefundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public boolean matchPassword(String inputPassword) {
		return passwd.equals(inputPassword);
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", passwd=" + passwd + ", userName=" + userName
				+ ", nickname=" + nickname + ", address1=" + address1 + ", address2=" + address2 + ", address3="
				+ address3 + ", phone=" + phone + ", refundBank=" + refundBank + ", refundAccount=" + refundAccount
				+ "]";
	}
	
}
