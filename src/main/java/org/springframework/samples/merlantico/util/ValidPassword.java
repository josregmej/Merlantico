

package org.springframework.samples.merlantico.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
	String message() default "Minimo 8 caracteres y debe contener una minúscula, una mayúscula y un número";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
