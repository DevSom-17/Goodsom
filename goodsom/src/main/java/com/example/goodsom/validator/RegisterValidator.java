package com.example.goodsom.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.goodsom.controller.user.UserForm;
import com.example.goodsom.domain.User;

/**
 * @author Seonmi Hwang
 * @since 2020.06.28	| 2020.12.27
 */

public class RegisterValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserForm regReq = (UserForm) target;

		// 필수 입력 항목
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.passwd", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.userName", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.nickname", "required");
		
		User user = regReq.getUser();
		
		String emailRegax = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$";
		if (!user.getEmail().equals("") && !user.getEmail().matches(emailRegax)) {
			errors.rejectValue("user.email", "typeMismatch"); // email type 검증
		}
		
		if (user.getPasswd() != null && user.getPasswd().length() > 0) {
			if (!user.getPasswd().equals(regReq.getRepeatedPassword())) {
				errors.rejectValue("repeatedPassword", "invalidPassword");
			}
		}

		String phone = regReq.getUser().getPhone();
		if (!phone.equals("") && !phone.matches("^[0][1]\\d{1}-\\d{3,4}-\\d{4}$")) {
			errors.rejectValue("user.phone", "typeMismatch"); // 01x-xxx-xxxx or 01x-xxxx-xxxx인지 검증
		}

	}
}
