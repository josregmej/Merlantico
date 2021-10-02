package org.springframework.samples.merlantico.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
	private static final String PASSWORD_PATTERN= "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
	
	@Override
	public void initialize(ValidPassword validpassword) {
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		return password != null && password.matches(PASSWORD_PATTERN);
	}
	
}
