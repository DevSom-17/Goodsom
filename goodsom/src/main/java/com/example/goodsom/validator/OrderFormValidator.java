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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.cardBank", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.cardNo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.validDate", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.cvc", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.address1", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.address2", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.address3", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.phone", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.refundBank", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "order.refundAccount", "required");
		
		String cardNo = regReq.getOrder().getCardNo();
		if (!cardNo.equals("") && !cardNo.matches("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$")) {
			errors.rejectValue("order.cardNo", "typeMismatch"); // xxxx-xxxx-xxxx-xxxx인지 검증
		}
		
		String validDate = regReq.getOrder().getValidDate();
		if (!validDate.equals("") && !validDate.matches("^(0[1-9]|1[0-2])/[0-9]{2}$")) {
			errors.rejectValue("order.validDate", "typeMismatch"); // MM/YY 인지 검증
		}
		
		String cvc = regReq.getOrder().getCvc();
		if (!cvc.equals("") && !cvc.matches("^\\d{3}$")) {
			errors.rejectValue("order.cvc", "typeMismatch"); // 세 자리 숫자인지 검증
		}
		
		String phone = regReq.getOrder().getPhone();
		if (!phone.equals("") && !phone.matches("^[0][1]\\d{1}-\\d{3,4}-\\d{4}$")) {
			errors.rejectValue("order.phone", "typeMismatch"); // 01x-xxx-xxxx or 01x-xxxx-xxxx인지 검증
		}

	}

}
