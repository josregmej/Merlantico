package org.springframework.samples.merlantico.service;

import static org.assertj.core.api.Assertions.assertThat;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.merlantico.model.InscripcionHotel;
import org.springframework.samples.merlantico.service.InscripcionHotelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class InscripcionHotelServiceTests { 
	@Autowired
	protected InscripcionHotelService inscripcionhotelService;
	
	@Test
	@Transactional
	public void shouldInsertHotel() {
		Integer id = 777;
		InscripcionHotel prueba = this.inscripcionhotelService.findInscripcionHotelById(id);
		
		InscripcionHotel inscripcion = new InscripcionHotel();
		inscripcion.setNombre("Prueba");
		inscripcion.setDireccion("Calle insertada");
		inscripcion.setDescripcion("Muy divertido");
		inscripcion.setProvincia("Granada");
		inscripcion.setActividades("Surf");
		
		this.inscripcionhotelService.saveInscripcionHotel(inscripcion);
		id = inscripcion.getId();
		
		prueba = this.inscripcionhotelService.findInscripcionHotelById(id);
        assertThat(prueba.getNombre().isEmpty()).isFalse();
		
	}
}
