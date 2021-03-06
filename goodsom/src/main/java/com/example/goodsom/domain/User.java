package com.example.goodsom.domain;
import java.io.Serializable;

/**
 * @author Seonmi-Hwang
 * @since  2020.06.12 | 2020.11.30 update
 */

@SuppressWarnings("serial")
public class User implements Serializable {
	
	private int userId;
	private String email;
	private String passwd;
	private String userName;
	private String nickname;
	private String address;
	private String detailAddress;
	private String extraAddress;
	private int postcode;
	private String phone;
	private String refundBank;
	private String refundAccount;
	private int report;
	private int warning;
	private String code;
	
	
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
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDetailAddress() {
		return detailAddress;
	}
	
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	
	public String getExtraAddress() {
		return extraAddress;
	}
	
	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
	}
	
	public int getPostcode() {
		return postcode;
	}
	
	public void setPostcode(int postcode) {
		this.postcode = postcode;
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
	
	public int getReport() {
		return report;
	}

	public void setReport(int report) {
		this.report = report;
	}

	public int getWarning() {
		return warning;
	}

	public void setWarning(int warning) {
		this.warning = warning;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", passwd=" + passwd + ", userName=" + userName
				+ ", nickname=" + nickname + ", address=" + address + ", detailAddress=" + detailAddress
				+ ", extraAddress=" + extraAddress + ", postcode=" + postcode + ", phone=" + phone + ", refundBank="
				+ refundBank + ", refundAccount=" + refundAccount + ", report=" + report + ", warning=" + warning + "]";
	}

}
