package org.springframework.samples.merlantico.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.merlantico.model.Habitacion;
import org.springframework.samples.merlantico.model.Hotel;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author Michael Isvy Simple test to make sure that Bean Validation is working (useful
 * when upgrading to a new version of Hibernate Validator/ Bean Validation)
 */
class ValidatorTests {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	void shouldNotValidateWhenNombreEmpty() {
		
		LocaleContextHolder.setLocale(Locale.getDefault());
		Hotel hotel = new Hotel();
		
		hotel.setNombre("");
		hotel.setDireccion("Calle Cano");
		hotel.setEstrellas(3);
		hotel.setProvincia("Sevilla");
		hotel.setTelefono("32222222");
		
		
		Habitacion habitacion1= new Habitacion();
		habitacion1.setDisponible(true);
		habitacion1.setNcamas(2);
		habitacion1.setNhabitacion(223);
		habitacion1.setPrecio(25);
		habitacion1.setHotel(hotel);
		Set<Habitacion> habitaciones= new HashSet<Habitacion>();
		habitaciones.add(habitacion1);
		hotel.setHabitaciones(habitaciones);

		Validator validator = createValidator();
		Set<ConstraintViolation<Hotel>> constraintViolations = validator.validate(hotel);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Hotel> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("nombre");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}

}
