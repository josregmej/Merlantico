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
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.merlantico.model.Actividad;
import org.springframework.samples.merlantico.model.AgenAct;
import org.springframework.samples.merlantico.model.ReservaActividad;
import org.springframework.samples.merlantico.model.User;
import org.springframework.samples.merlantico.service.ActividadService;
import org.springframework.samples.merlantico.service.ReservaActividadService;
import org.springframework.samples.merlantico.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class ReservaActividadServiceTests {                
    @Autowired
	protected ReservaActividadService reservaActividadService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected ActividadService actividadService;
    
    private static final Logger LOG = Logger.getLogger("org.springframework.samples.petclinic.service.ReservaActividadServiceTests");    

    @Test
    @Transactional
    public void shouldInsertReservaActividad() {
    	Collection<ReservaActividad> reservaActividades = this.reservaActividadService.buscarReservaActividad("enrmorvaz");
    	//No debe existir la Reservaactividad con ese asociado a ese nombre de usuario en la base de datos
    	int found = reservaActividades.size();
    	//Tamaño 0
    	LOG.log(Level.INFO,"==========================================================");
    	LOG.log(Level.INFO,"ReservaActividad Usuario enrmorvaz no encontrada. Found= "+found);
    	LOG.log(Level.INFO,"==========================================================");

    	//Creamos la actividad
    	Actividad actividad = new Actividad();
    	
    	actividad.setNombre("ACTIVIDAD");
    	actividad.setDescripcion("DESC");
    	actividad.setValoracion(3);
    	actividad.setDireccion("DIRECCION");
    	actividad.setProvincia("Sevilla");
    	actividad.setPrecio(12);
    	actividad.setFecha(LocalDate.of(2023, 3, 3));
    	this.actividadService.saveActividad(actividad);
    	//Creamos la reserva
    	ReservaActividad reservaActividad = new ReservaActividad();
    	reservaActividad.setFechaReserva(LocalDate.now());
    	reservaActividad.setEntrada(actividad.getFecha());
    	reservaActividad.setNumeroTarjeta("371449635398431");
    	reservaActividad.setCvc("123");
    	reservaActividad.setPrecioFinal(20.0);
    	reservaActividad.setActivdad(actividad);
    	//Creamos usuario
    	User usuario = new User();
    	usuario.setUsername("enrmorvaz");
    	usuario.setDni("42932326Q");
    	usuario.setTelefono("999999999");
    	usuario.setPassword("Admin123");
    	reservaActividad.setUser(usuario);
    	this.userService.saveUser(usuario);
    	this.reservaActividadService.saveReservaActividad(reservaActividad);
    	
    	LOG.log(Level.INFO,"assertThat"+reservaActividad.getId());
    	assertThat(reservaActividad.getId()).isNotEqualTo(0);
    		
    	//Comprobamos que se ha añadido sin problemas
    	reservaActividades = this.reservaActividadService.buscarReservaActividad("enrmorvaz");
    	LOG.log(Level.INFO,"=====================================");
    	LOG.log(Level.INFO,""+reservaActividades);
    	LOG.log(Level.INFO,"=====================================");
    	assertThat(reservaActividades.size()).isEqualTo(found+1);
    	LOG.log(Level.INFO,"reservaActividad enrmorvaz encontrada. Found= "+reservaActividades.size());
    	LOG.log(Level.INFO,"==========================================================");
    }
    	
    @Test
    @Transactional
    public void shouldInsertReservaActividadVacio() {
    	
    	//Creamos la actividad
    	Actividad actividad = new Actividad();
    	actividad.setId(123);
    	actividad.setNombre("ACTIVIDAD");
    	actividad.setDescripcion("DESC");
    	actividad.setValoracion(3);
    	actividad.setDireccion("DIRECCION");
    	actividad.setProvincia("Sevilla");
    	actividad.setPrecio(12);
    	actividad.setFecha(LocalDate.of(2021, 3, 3));
    	//Creamos la agencia
    	AgenAct agenAct = new AgenAct();
    	agenAct.setId(345);
    	agenAct.setNombre("AGENCIA");
    	agenAct.setSede("Sevilla");
    	agenAct.setTelefono("123456789");
    	Set<Actividad> acs = new HashSet<Actividad>();
    	acs.add(actividad);
    	agenAct.setActividades(acs);
    	//Creamos la reserva
    	ReservaActividad reservaActividad = new ReservaActividad();
    	reservaActividad.setFechaReserva(LocalDate.now());
    	reservaActividad.setEntrada(actividad.getFecha());
    	reservaActividad.setNumeroTarjeta("");
    	reservaActividad.setCvc("");
    	reservaActividad.setPrecioFinal(20.0);
    	reservaActividad.setActivdad(actividad);
    	//Creamos usuario
    	User usuario = new User();
    	usuario.setUsername("enrmorvaz");
    	reservaActividad.setUser(usuario);
    
    	
        assertThat(reservaActividad.getNumeroTarjeta().isEmpty()).isTrue();
    	assertThat(reservaActividad.getCvc().isEmpty()).isTrue();
    }
    

	@Test
	void shouldFindReservaActividadByName() {
	
		
		//Creamos la actividad
    	Actividad actividad = new Actividad();
    	
    	actividad.setNombre("ACTIVIDAD");
    	actividad.setDescripcion("DESC");
    	actividad.setValoracion(3);
    	actividad.setDireccion("DIRECCION");
    	actividad.setProvincia("Sevilla");
    	actividad.setPrecio(12);
    	actividad.setFecha(LocalDate.of(2023, 3, 3));
    	this.actividadService.saveActividad(actividad);
    	//Creamos la reserva
    	ReservaActividad reservaActividad = new ReservaActividad();
    	reservaActividad.setFechaReserva(LocalDate.now());
    	reservaActividad.setEntrada(actividad.getFecha());
    	reservaActividad.setNumeroTarjeta("371449635398431");
    	reservaActividad.setCvc("123");
    	reservaActividad.setPrecioFinal(20.0);
    	reservaActividad.setActivdad(actividad);
    	//Creamos usuario
    	User usuario = new User();
    	usuario.setUsername("enrmorvaz");
    	usuario.setDni("42932326Q");
    	usuario.setTelefono("999999999");
    	usuario.setPassword("Admin123");
    	reservaActividad.setUser(usuario);
    	this.userService.saveUser(usuario);
    	              
		this.reservaActividadService.saveReservaActividad(reservaActividad);
		Collection<ReservaActividad> reservaActividades = this.reservaActividadService.buscarReservaActividad("enrmorvaz");
		LOG.log(Level.INFO,"=====================================");
		LOG.log(Level.INFO,""+reservaActividades);
		LOG.log(Level.INFO,"=====================================");
		assertThat(reservaActividades.size()).isEqualTo(1);
	}
	

	@Test
	void shouldNotFindReservaActividadByName() {
		Collection<ReservaActividad> reservaActividad = this.reservaActividadService.buscarReservaActividad("enrmorvaz");
		assertThat(reservaActividad.isEmpty()).isTrue();
	}
}
