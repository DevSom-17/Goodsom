package com.example.goodsom.domain;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Seonmi-Hwang
 * @since  2020.06.12 | 2020.11.30 update
 */

@SuppressWarnings("serial")
public class User implements UserDetails {
	
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
	  private String auth;
	
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
	
	public boolean matchPassword(String inputPassword) {
		return passwd.equals(inputPassword);
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
	// 사용자의 권한을 콜렉션 형태로 반환
		  // 단, 클래스 자료형은 GrantedAuthority를 구현해야함
		  @Override
		  public Collection<? extends GrantedAuthority> getAuthorities() {
		    Set<GrantedAuthority> roles = new HashSet<>();
		    for (String role : auth.split(",")) {
		      roles.add(new SimpleGrantedAuthority(role));
		    }
		    return roles;
		  }

		  // 사용자의 id를 반환 (unique한 값)
		  @Override
		  public String getUsername() {
		    return email;
		  }

		  // 사용자의 password를 반환
		  @Override
		  public String getPassword() {
		    return passwd;
		  }

		  // 계정 만료 여부 반환
		  @Override
		  public boolean isAccountNonExpired() {
		    // 만료되었는지 확인하는 로직
		    return true; // true -> 만료되지 않았음
		  }

		  // 계정 잠금 여부 반환
		  @Override
		  public boolean isAccountNonLocked() {
		    // 계정 잠금되었는지 확인하는 로직
		    return true; // true -> 잠금되지 않았음
		  }

		  // 패스워드의 만료 여부 반환
		  @Override
		  public boolean isCredentialsNonExpired() {
		    // 패스워드가 만료되었는지 확인하는 로직
		    return true; // true -> 만료되지 않았음
		  }

		  // 계정 사용 가능 여부 반환
		  @Override
		  public boolean isEnabled() {
		    // 계정이 사용 가능한지 확인하는 로직
		    return true; // true -> 사용 가능
		  }

}
