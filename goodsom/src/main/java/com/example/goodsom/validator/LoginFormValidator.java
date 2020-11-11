package com.example.goodsom.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.goodsom.domain.User;
import org.springframework.stereotype.Component;

/**
 * @author Yejin Lee  |  kimdahyee
 * @since 2020.05.07  |  2020.06.12
 */

@Component
public class LoginFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
	
	}
}
