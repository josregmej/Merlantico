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
import org.springframework.samples.merlantico.service.ActividadService;
import org.springframework.samples.merlantico.service.AgenActService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class AgenActServiceTests {                
        @Autowired
	protected AgenActService agenActService;
    protected ActividadService actividadService;

    private static final Logger LOG = Logger.getLogger("org.springframework.samples.petclinic.service.AgenActServiceTests");
    
    @Test
    @Transactional
    public void shouldInsertAgenAct() {
    	Collection<AgenAct> agencias = this.agenActService.findByNombre("Liberty");
    	//"No debe existir la agencia con ese nombre en la base de datos"
    	int found = agencias.size();
    	//Tamaño 0;
    	LOG.log(Level.INFO,"==========================================================");
    	LOG.log(Level.INFO,"Agencia Liberty no encontrada. Encontrados = "+found);
    	LOG.log(Level.INFO,"==========================================================");
    	
    	AgenAct agenAct = new AgenAct();
    	agenAct.setNombre("Liberty");
    	agenAct.setSede("Malaga");
    	agenAct.setTelefono("945323429");
    	
    	Actividad actividad = new Actividad();
    	actividad.setNombre("Escalada");
    	actividad.setDescripcion("Muy buena ruta para realizar con los amigos y muy facil");
    	actividad.setDireccion("Sierra de Grazalema");
    	actividad.setProvincia("Sevilla");
    	actividad.setValoracion(4);
    	actividad.setPrecio(2);
    	Set<Actividad> actividades = new HashSet<Actividad>();	
    	actividades.add(actividad);
    	agenAct.setActividades(actividades);
    	
    	this.agenActService.saveAgenAct(agenAct);
    		
    	//Comprobamos que se ha añadido sin problemas"
    	agencias = this.agenActService.findByNombre("Liberty");
    	assertThat(agencias.size()).isEqualTo(found+1);
    	LOG.log(Level.INFO,"Agencia Liberty encontrada. Encontradas= "+actividades.size());
    	LOG.log(Level.INFO,"==========================================================");
    }
    	
    @Test
    @Transactional
    public void shouldInsertAgenActVacio() {
    	AgenAct agenAct = new AgenAct();
    	agenAct.setNombre("Liberty");
    	agenAct.setSede("");
    	agenAct.setTelefono("");
    		
        assertThat(agenAct.getSede().isEmpty()).isTrue();
    	assertThat(agenAct.getTelefono().isEmpty()).isTrue();
    }

	@Test
	void shouldFindAgenActName() {
		AgenAct agenAct = new AgenAct();
    	agenAct.setNombre("Liberty");
    	agenAct.setSede("Malaga");
    	agenAct.setTelefono("945323429");
    	
    	Actividad actividad = new Actividad();
    	actividad.setNombre("Escalada");
    	actividad.setDescripcion("Muy buena ruta para realizar con los amigos y muy facil");
    	actividad.setDireccion("Sierra de Grazalema");
    	actividad.setProvincia("Sevilla");
    	actividad.setValoracion(4);
    	actividad.setPrecio(2);
    	Set<Actividad> actividades = new HashSet<Actividad>();	
    	actividades.add(actividad);
    	agenAct.setActividades(actividades);         
                
    	this.agenActService.saveAgenAct(agenAct);
		
    	//Comprobamos que se ha añadido sin problemas
    	Collection<AgenAct> agencias = this.agenActService.findByNombre("Liberty");
		assertThat(agencias.size()).isEqualTo(1);
	}
	
	@Test
	void shouldNotFindActividadByName() {
		Collection<AgenAct> agencias = this.agenActService.findByNombre("Liberty");
		assertThat(agencias.isEmpty()).isTrue();
	}
}
