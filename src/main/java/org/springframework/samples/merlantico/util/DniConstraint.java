package org.springframework.samples.merlantico.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ContactDniValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DniConstraint {
	String message() default "DNI incorrecto debe contener 8 numeros y una letra";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}