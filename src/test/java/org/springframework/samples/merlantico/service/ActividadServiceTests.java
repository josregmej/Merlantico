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
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.merlantico.model.Actividad;
import org.springframework.samples.merlantico.service.ActividadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class ActividadServiceTests {                
        @Autowired
	protected ActividadService actividadService;
    private static final Logger LOG = Logger.getLogger("org.springframework.samples.petclinic.service.ActividadServiceTests");    
    
    //Prueba H18+E1 - Alta de una actividad"
    @Test
    @Transactional
    public void shouldInsertActividad() {
    	
    	Collection<Actividad> actividades = this.actividadService.findByNombre("Escalada");
    	//No debe existir la actividad con ese nombre en la base de datos
    	int found = actividades.size();
    	LOG.log(Level.INFO,"==========================================================");
    	LOG.log(Level.INFO,"Actividad Escalada no encontrada. Found= "+found);
    	LOG.log(Level.INFO,"==========================================================");

    	Actividad actividad = new Actividad();
    	actividad.setNombre("Escalada");
    	actividad.setDescripcion("Muy buena ruta para realizar con los amigos y muy facil");
    	actividad.setDireccion("Sierra de Grazalema");
    	actividad.setProvincia("Sevilla");
    	actividad.setValoracion(4);
    	actividad.setPrecio(2);
    		  
    	this.actividadService.saveActividad(actividad);
    	LOG.log(Level.INFO,"assertThat"+actividad.getId());
    	assertThat(actividad.getId()).isNotEqualTo(0);
    		
    	//Comprobamos que se ha añadido sin problemas"
    	actividades = this.actividadService.findByNombre("Escalada");
    	assertThat(actividades.size()).isEqualTo(found+1);
    	LOG.log(Level.INFO,"Actividad Escalada encontrada. Found= "+actividades.size());
    	LOG.log(Level.INFO,"==========================================================");
    }
    	
    //Prueba H18-E1 - Alta de una actividad sin nombre, opinion, precio"
    @Test
    @Transactional
    public void shouldInsertActividadVacio() {
    	
    	Actividad actividad = new Actividad();
    	actividad.setNombre("");
    	actividad.setDescripcion("");
    	actividad.setDireccion("Sierra de Grazalema");
    	actividad.setValoracion(4);
    	actividad.setPrecio(5);
    		
        assertThat(actividad.getNombre().isEmpty()).isTrue();
    	assertThat(actividad.getDescripcion().isEmpty()).isTrue();
    	
    }
    
    //Prueba H11+E1 - Búsqueda de actividades"
	@Test
	void shouldFindActividadByName() {
		
		Actividad actividad = new Actividad();
		actividad.setNombre("Escalada");
		actividad.setDescripcion("Muy buena ruta para realizar con los amigos y muy facil");
		actividad.setDireccion("Sierra de Grazalema");
		actividad.setProvincia("Sevilla");
		actividad.setValoracion(4);
		actividad.setPrecio(2);           
                
		this.actividadService.saveActividad(actividad);
		Collection<Actividad> actividades = this.actividadService.findByNombre("Escalada");
		assertThat(actividades.size()).isEqualTo(1);
	}
	
	//Prueba H11-E1 Busqueda de actividad no existente
	@Test
	void shouldNotFindActividadByName() {

		Collection<Actividad> actividades = this.actividadService.findByNombre("Escalada");
		assertThat(actividades.isEmpty()).isTrue();
	}
}
