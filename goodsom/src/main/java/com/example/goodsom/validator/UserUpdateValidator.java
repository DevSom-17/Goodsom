package com.example.goodsom.validator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.goodsom.controller.user.UserForm;
import com.example.goodsom.domain.User;

/**
 * @author Seonmi Hwang
 * @since 2020.06.28	| 2020.12.27
 */

public class UserUpdateValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserForm regReq = (UserForm) target;

		// 필수 입력 항목
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.nickname", "required");
		
		User user = regReq.getUser();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (!encoder.matches(regReq.getRepeatedOriginPassword(), user.getPasswd())) {
			errors.rejectValue("repeatedOriginPassword", "notMatchOriginPassword");
		}
		
		if (regReq.getNewPassword() != null && regReq.getNewPassword().length() > 0) {
			if (!regReq.getNewPassword().equals(regReq.getRepeatedPassword())) {
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
