/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.merlantico.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.merlantico.model.ReservaVuelo;
import org.springframework.samples.merlantico.model.User;
import org.springframework.samples.merlantico.model.Vuelo;
import org.springframework.samples.merlantico.service.ReservaVueloService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class ReservaVueloServiceTests {                
        @Autowired
	protected ReservaVueloService reservaVueloService; 

    private static final Logger LOG = Logger.getLogger("org.springframework.samples.petclinic.service.ReservaHabitacionServiceTests");            
        
    @Test
    @Transactional
    public void shouldInsertReservaVuelo() {
    	Collection<ReservaVuelo> reservaVuelos = this.reservaVueloService.buscarReservaVuelo("enrmorvaz");
    	//No debe existir la ReservaVuelo con ese asociado a ese nombre de usuario en la base de datos
    	int found = reservaVuelos.size();
    	//Tamaño 0
    	LOG.log(Level.INFO,"==========================================================");
    	LOG.log(Level.INFO,"ReservaVuelo Usuario enrmorvaz no encontrada. Found= "+found);
    	LOG.log(Level.INFO,"==========================================================");

    	Vuelo vuelo = new Vuelo();
		vuelo.setBilletes(451);
		vuelo.setDestino("Cadiz");
		vuelo.setOrigen("Sevilla");
		vuelo.setPrecio(12);
		LocalDate fechaIda=LocalDate.of(2021, 10, 26);
		vuelo.setFechaIda(fechaIda);
		LocalDate fechaVuelta=LocalDate.of(2021, 11, 4);
		vuelo.setFechaIda(fechaVuelta);
		vuelo.setBilletes(3);
    	
    	ReservaVuelo reservaVuelo = new ReservaVuelo();
    	reservaVuelo.setFechaReserva(LocalDate.now());
    	reservaVuelo.setIda(vuelo.getFechaIda());
    	reservaVuelo.setVuelta(vuelo.getFechaVuelta());
    	reservaVuelo.setNumeroTarjeta("371449635398431");
    	reservaVuelo.setCvc("123");
    	reservaVuelo.setPrecioFinal(20.0);
    	User usuario = new User();
    	usuario.setUsername("enrmorvaz");
    	reservaVuelo.setUser(usuario);
    	
    	this.reservaVueloService.saveReservaVuelo(reservaVuelo);
    	
    	System.out.println("assertThat"+reservaVuelo.getId());
    	assertThat(reservaVuelo.getId()).isNotEqualTo(0);
    		
    	//Comprobamos que se ha añadido sin problemas
    	reservaVuelos = this.reservaVueloService.buscarReservaVuelo("enrmorvaz");
    	assertThat(reservaVuelos.size()).isEqualTo(found+1);
    	LOG.log(Level.INFO,"reservaVuelo enrmorvaz encontrada. Found= "+reservaVuelos.size());
    	LOG.log(Level.INFO,"==========================================================");
    }
    	
    @Test
    @Transactional
    public void shouldInsertReservaVueloVacio() {
    	
    	Vuelo vuelo = new Vuelo();
		vuelo.setBilletes(451);
		vuelo.setDestino("Cadiz");
		vuelo.setOrigen("Sevilla");
		vuelo.setPrecio(12);
		LocalDate fechaIda=LocalDate.of(2021, 10, 26);
		vuelo.setFechaIda(fechaIda);
		LocalDate fechaVuelta=LocalDate.of(2021, 11, 4);
		vuelo.setFechaIda(fechaVuelta);
		vuelo.setBilletes(3);
    	
    	ReservaVuelo reservaVuelo = new ReservaVuelo();
    	reservaVuelo.setFechaReserva(LocalDate.now());
    	reservaVuelo.setIda(vuelo.getFechaIda());
    	reservaVuelo.setVuelta(vuelo.getFechaVuelta());
    	reservaVuelo.setNumeroTarjeta("");
    	reservaVuelo.setCvc("");
    	reservaVuelo.setPrecioFinal(20.0);
    	User usuario = new User();
    	usuario.setUsername("enrmorvaz");
    	reservaVuelo.setUser(usuario);
        assertThat(reservaVuelo.getNumeroTarjeta().isEmpty()).isTrue();
    	assertThat(reservaVuelo.getCvc().isEmpty()).isTrue();
    }
    

	@Test
	void shouldFindReservaVueloByName() {
		Vuelo vuelo = new Vuelo();
		vuelo.setBilletes(451);
		vuelo.setDestino("Cadiz");
		vuelo.setOrigen("Sevilla");
		vuelo.setPrecio(12);
		LocalDate fechaIda=LocalDate.of(2021, 10, 26);
		vuelo.setFechaIda(fechaIda);
		LocalDate fechaVuelta=LocalDate.of(2021, 11, 4);
		vuelo.setFechaIda(fechaVuelta);
		vuelo.setBilletes(3);
		
		ReservaVuelo reservaVuelo = new ReservaVuelo();
    	reservaVuelo.setFechaReserva(LocalDate.now());
    	reservaVuelo.setIda(vuelo.getFechaIda());
    	reservaVuelo.setVuelta(vuelo.getFechaVuelta());
    	reservaVuelo.setNumeroTarjeta("371449635398431");
    	reservaVuelo.setCvc("123");
    	reservaVuelo.setPrecioFinal(20.0);
    	User usuario = new User();
    	usuario.setUsername("enrmorvaz");
    	reservaVuelo.setUser(usuario);
                
		this.reservaVueloService.saveReservaVuelo(reservaVuelo);
		Collection<ReservaVuelo> reservaVuelos = this.reservaVueloService.buscarReservaVuelo("enrmorvaz");
		assertThat(reservaVuelos.size()).isEqualTo(1);
	}
	

	@Test
	void shouldNotFindReservaVueloByName() {
		Collection<ReservaVuelo> reservaVuelos = this.reservaVueloService.buscarReservaVuelo("enrmorvaz");
		assertThat(reservaVuelos.isEmpty()).isTrue();
	}
}
