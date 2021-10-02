package org.springframework.samples.merlantico.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactDniValidator implements ConstraintValidator<DniConstraint, String> {

	@Override
	public void initialize(DniConstraint contactDni) {
	}
	
	@Override
	public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
		return contactField != null && contactField.matches("^[0-9]{8,8}[A-Za-z]$");
	}

}