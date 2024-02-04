package com.subrutin.catalog.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.subrutin.catalog.validator.AuthorNameValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = AuthorNameValidator.class)
public @interface ValidAuthorName {

	String message() default "author name invalid";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

	
}
