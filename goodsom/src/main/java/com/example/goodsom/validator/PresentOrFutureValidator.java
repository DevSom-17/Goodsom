package com.example.goodsom.validator;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PresentOrFutureValidator implements ConstraintValidator<PresentOrFuture, Date>{
	@Override
	public void initialize(final PresentOrFuture annotation) {}

	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {
		 Calendar cal = Calendar.getInstance();
         int day = cal.get(Calendar.DAY_OF_MONTH);

		 Calendar calendar = Calendar.getInstance(); 

		 calendar.set(Calendar.DATE, day-1);
         calendar.set(Calendar.HOUR_OF_DAY, 11);
         calendar.set(Calendar.MINUTE, 59);
         calendar.set(Calendar.SECOND, 59);
         
         Date today = calendar.getTime();

         // 오늘 이전이 아님(==오늘+내일) 또는 오늘 이후
         return !value.before(today) || value.after(today);
	}
	

}
