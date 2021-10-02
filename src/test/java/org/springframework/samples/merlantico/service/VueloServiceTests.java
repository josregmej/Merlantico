package org.springframework.samples.merlantico.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.merlantico.model.Vuelo;
import org.springframework.samples.merlantico.service.VueloService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class VueloServiceTests {                
        @Autowired
	protected VueloService vueloService;

        
    //Prueba H6+E1 Búsqueda de vuelos
	@Test
	void shouldFindVuelo() {
		Vuelo vuelo = new Vuelo();
		vuelo.setBilletes(451);
		vuelo.setDestino("Cadiz");
		vuelo.setOrigen("Sevilla");
		vuelo.setPrecio(12);
		LocalDate fechaIda=LocalDate.of(2020, 10, 26);
		vuelo.setFechaIda(fechaIda);
		LocalDate fechaVuelta=LocalDate.of(2020, 11, 4);
		vuelo.setFechaIda(fechaVuelta);
		vuelo.setBilletes(3);
		
		this.vueloService.saveVuelo(vuelo);
		Collection<Vuelo> vuelos = this.vueloService.findByOrigen("Sevilla");
		assertThat(vuelos.size()).isEqualTo(4);
	}
	
	//Prueba H6-E1 Búsqueda de vuelo inexistente
	@Test
	void shouldFindVueloNoDisponible() {
		Collection<Vuelo> vuelos = this.vueloService.findByOrigen("Murcia");
		assertThat(vuelos.isEmpty()).isTrue();
	}
	
	//Prueba H6-E2 Búsqueda de vuelo con parámetros vacíos
	@Test
	void shouldFindVueloVacio() {
		Vuelo vuelo = new Vuelo();
		vuelo.setBilletes(451);
		vuelo.setDestino("");
		vuelo.setOrigen("Sevilla");
		vuelo.setPrecio(12);
		LocalDate fechaIda=LocalDate.of(2020, 10, 26);
		vuelo.setFechaIda(fechaIda);
		LocalDate fechaVuelta=LocalDate.of(2020, 10, 4);
		vuelo.setFechaIda(fechaVuelta);
		vuelo.setBilletes(3);
		
		assertThat(vuelo.getDestino().isEmpty()).isTrue();
	}
	
	//Alta de vuelo
	@Test
	@Transactional
	public void shouldInsertVuelo() {
		
		Collection<Vuelo> vuelos = this.vueloService.findByOrigen("Sevilla");
		int found = vuelos.size();

		Vuelo vuelo = new Vuelo();
		vuelo.setBilletes(451);
		vuelo.setDestino("Cadiz");
		vuelo.setOrigen("Sevilla");
		vuelo.setPrecio(12);
		LocalDate fechaIda=LocalDate.of(2020, 10, 26);
		vuelo.setFechaIda(fechaIda);
		LocalDate fechaVuelta=LocalDate.of(2020, 11, 4);
		vuelo.setFechaIda(fechaVuelta);
		vuelo.setBilletes(3);
		
		this.vueloService.saveVuelo(vuelo);
		assertThat(vuelo.getId()).isNotEqualTo(0);
		
		vuelos = this.vueloService.findByOrigen("Sevilla");
		assertThat(vuelos.size()).isEqualTo(found+1);
    }
}