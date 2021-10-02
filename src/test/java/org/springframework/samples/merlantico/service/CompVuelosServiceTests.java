package org.springframework.samples.merlantico.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.merlantico.model.CompVuelos;
import org.springframework.samples.merlantico.model.Vuelo;
import org.springframework.samples.merlantico.service.CompVuelosService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class CompVuelosServiceTests {                
        @Autowired
	protected CompVuelosService compVueloService;

	@Test
	void shouldFindCompVuelo() {
		CompVuelos compañia = new CompVuelos();
		compañia.setNombre("Malayo");
		compañia.setPais("España");
		compañia.setSede("Madrid");
		
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
		Set<Vuelo> vuelos = new HashSet<Vuelo>();
		vuelos.add(vuelo);
		compañia.setVuelos(vuelos);
		
		this.compVueloService.saveCompVuelos(compañia);
		Collection<CompVuelos> compañias = this.compVueloService.findByNombre("Malayo");
		assertThat(compañias.size()).isEqualTo(1);
	}
	
	@Test
	void shouldNotFindCompVueloByName() {
		Collection<CompVuelos> compañias = this.compVueloService.findByNombre("Malayo");
		assertThat(compañias.isEmpty()).isTrue();
	}

	@Test
	void shouldFindCompVueloVacio() {
		CompVuelos compañia = new CompVuelos();
		compañia.setNombre("");
		compañia.setPais("España");
		compañia.setSede("Madrid");
		assertThat(compañia.getNombre().isEmpty()).isTrue();
	}
	
	@Test
	@Transactional
	public void shouldInsertVuelo() {
		
		Collection<CompVuelos> compañias = this.compVueloService.findByNombre("Lacayo");
		int found = compañias.size();

		CompVuelos compañia = new CompVuelos();
		compañia.setNombre("Lacayo");
		compañia.setPais("Reino Unido");
		compañia.setSede("Londres");
		
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
		Set<Vuelo> vuelos = new HashSet<Vuelo>();
		vuelos.add(vuelo);
		compañia.setVuelos(vuelos);
		
		this.compVueloService.saveCompVuelos(compañia);
		assertThat(compañia.getId()).isNotEqualTo(0);
		
		compañias = this.compVueloService.findByNombre("Lacayo");
		assertThat(vuelos.size()).isEqualTo(found+1);
    }
}