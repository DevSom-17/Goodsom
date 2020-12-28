package com.example.goodsom.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.goodsom.controller.order.OrderForm;

/**
 * @author Seonmi Hwang
 * @since 2020.06.27
 */

public class OrderFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return OrderForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrderForm regReq = (OrderForm) target;

		// 필수 입력 항목
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.phone", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.bank", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.account", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.address", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.depositTime", "required");
		
		String phone = regReq.getOrder().getPhone();
		if (!phone.equals("") && !phone.matches("^[0][1]\\d{1}-\\d{3,4}-\\d{4}$")) {
			errors.rejectValue("order.phone", "typeMismatch"); // 01x-xxx-xxxx or 01x-xxxx-xxxx인지 검증
		}

		String depositTime = regReq.getOrder().getDepositTime();
		if (!depositTime.equals("") && !depositTime.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2}$")) {
			errors.rejectValue("order.depositTime", "typeMismatch"); // yyyy-MM-DD HH:mm:ss
		}
	}

}
