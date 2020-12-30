package com.example.goodsom.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PresentOrFutureValidator.class)
@Documented
public @interface PresentOrFuture {
	String message() default "{PresentOrFuture.message}";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
