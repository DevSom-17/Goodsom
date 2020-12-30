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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.passwd", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.userName", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.nickname", "required");
		
		User user = regReq.getUser();

		if (user.getPasswd() != null && user.getPasswd().length() > 0) {
			if (!user.getPasswd().equals(regReq.getRepeatedPassword())) {
				errors.rejectValue("repeatedPassword", "invalidPassword");
			}
		}
		
		if (!user.getRefundAccount().equals("") && user.getRefundBank().equals("")) {
			errors.rejectValue("user.refundBank", "needBankInfo"); 
		}
		
		if (!user.getRefundBank().equals("") && user.getRefundAccount().equals("")) {
			errors.rejectValue("user.refundAccount", "needAccountInfo"); 
		}

	}
}
