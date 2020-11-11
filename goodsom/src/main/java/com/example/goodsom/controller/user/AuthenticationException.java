package com.example.goodsom.controller.user;

/**
 * @author Seonmi Hwang
 * @since 2020.06.28
 */

public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AuthenticationException(String message) {
		super(message);
	}

}
