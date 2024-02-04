package com.subrutin.catalog.validator;

import org.springframework.stereotype.Component;

import com.subrutin.catalog.validator.annotation.ValidAuthorName;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class AuthorNameValidator implements ConstraintValidator<ValidAuthorName, String>{

	@Override
	public boolean isValid(String authorName, ConstraintValidatorContext context) {
		return !authorName.equalsIgnoreCase("Tedy");
	}

}
