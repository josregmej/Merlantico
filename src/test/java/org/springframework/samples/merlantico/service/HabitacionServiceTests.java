package org.springframework.samples.merlantico.service;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.merlantico.model.Habitacion;
import org.springframework.samples.merlantico.service.HabitacionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class HabitacionServiceTests {
	@Autowired
	protected HabitacionService habitacionService;
        
	@Test
	void shouldFindHabitacionByNumber() {
		Habitacion habitacion1= new Habitacion();		
		habitacion1.setDisponible(true);
		habitacion1.setNcamas(2);
		habitacion1.setNhabitacion(444);
		habitacion1.setPrecio(25);
		
		this.habitacionService.saveHabitacion(habitacion1,1);
		
		Set<Habitacion> habitaciones= new HashSet<Habitacion>();
		habitaciones.add(habitacion1);
		assertThat(habitaciones.size()).isEqualTo(1);
	}
	
	@Test
	void shouldFindHabitacionById() {
		Habitacion hab= this.habitacionService.findHabitacionByNhabitacion(444);
		Set<Habitacion> habitaciones= new HashSet<Habitacion>();
		habitaciones.add(hab);
		assertThat(habitaciones.size()).isEqualTo(1);
	}
	
	@Test
	void shouldNotFindHabitacionById() {
		Habitacion hab= this.habitacionService.findHabitacionByNhabitacion(0000);
		assertThat(hab).isNull();
	}
	
	@Test
	@Transactional
	public void shouldInsertHabitacion() {
		Habitacion habitacion1= new Habitacion();		
		habitacion1.setDisponible(true);
		habitacion1.setNcamas(2);
		habitacion1.setNhabitacion(444);
		habitacion1.setPrecio(25);
		
		this.habitacionService.saveHabitacion(habitacion1, 1);
		Habitacion hab = this.habitacionService.findHabitacionByNhabitacion(444);
		assertThat(hab.getNhabitacion()).isEqualTo(444);
		
	}
	
	@Test
	void shouldDeleteHabitacion() {
		Habitacion hab1=new Habitacion();
		hab1.setNhabitacion(001);
		hab1.setNcamas(2);
		hab1.setPrecio(541);
		hab1.setDisponible(true);
		
		this.habitacionService.saveHabitacion(hab1, 1);
		Habitacion hab = this.habitacionService.findHabitacionByNhabitacion(001);
		assertThat(hab.getNhabitacion()).isEqualTo(001);
		
		this.habitacionService.deleteHabitacion(hab);
		hab = this.habitacionService.findHabitacionByNhabitacion(001);
		assertThat(hab).isNull();
	}
}
