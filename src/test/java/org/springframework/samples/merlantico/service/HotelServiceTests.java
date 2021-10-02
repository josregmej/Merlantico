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
import org.springframework.samples.merlantico.model.Habitacion;
import org.springframework.samples.merlantico.model.Hotel;
import org.springframework.samples.merlantico.service.HotelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class HotelServiceTests {                
    @Autowired
	protected HotelService hotelService;
    private static final Logger LOG = Logger.getLogger("org.springframework.samples.petclinic.service.HotelServiceTests");    
    
	@Test
	//Prueba H1+E1 Busqueda general de hoteles
	void shouldFindHotelByName() {
		
		Hotel hotel = new Hotel();
		hotel.setNombre("HOTEL 2");
		hotel.setDireccion("Calle Cano");
		hotel.setEstrellas(2);
		hotel.setProvincia("Sevilla");
		hotel.setTelefono("322222222");
		
		
		Habitacion habitacion1= new Habitacion();		
		habitacion1.setDisponible(true);
		habitacion1.setNcamas(2);
		habitacion1.setNhabitacion(444);
		habitacion1.setPrecio(25);
		habitacion1.setHotel(hotel);
		Set<Habitacion> habitaciones= new HashSet<Habitacion>();
		habitaciones.add(habitacion1);
		hotel.setHabitaciones(habitaciones);              
                
		this.hotelService.saveHotel(hotel);
		Collection<Hotel> hoteles = this.hotelService.findByNombre("HOTEL 2");
		assertThat(hoteles.size()).isEqualTo(1);
	}
	
	//Prueba H1+E2 Busqueda de hotel dentro de una provincia
	@Test
	void shouldFindHotelByProvincia() {
		
		Collection<Hotel> hoteles = this.hotelService.findByProvincia("Sevilla");
		int encontrados= hoteles.size();
		assertThat(encontrados).isEqualTo(3);
	}
	
	//Prueba H1-E1 Busqueda de hotel no existente
	@Test
	void shouldNotFindHotelByName() {
	
		Collection<Hotel> hoteles = this.hotelService.findByNombre("ññññ");
		assertThat(hoteles.isEmpty()).isTrue();
	}
	
	//Level.INFO,"Prueba H2+E1 - Alta de un hotel
	@Test
	@Transactional
	public void shouldInsertHotel() {
		
		Collection<Hotel> hoteles = this.hotelService.findByNombre("Sunset");
		//No debe existir el hotel con ese nombre en la base de datos
		int found = hoteles.size();
		LOG.log(Level.INFO,"Tamaño 0");
		LOG.log(Level.INFO,"==========================================================");
		LOG.log(Level.INFO,"Hotel Sunset no encontrado. Found= "+found);
		LOG.log(Level.INFO,"==========================================================");

		Hotel hotel = new Hotel();
		hotel.setNombre("Sunset");
		hotel.setDireccion("Calle Overdrive");
		hotel.setEstrellas(4);
		hotel.setProvincia("Granada");
		hotel.setTelefono("666555111");
		System.out.println("HOTEL: "+hotel);
		System.out.println("ID HOTEL: "+hotel.getId());
//		hotel.setId(1234);
		System.out.println("ID HOTEL: "+hotel.getId());
		//Opcional, como está relacionado con habitaciones le creo 2 habitaciones"
			Habitacion hab1=new Habitacion();
				hab1.setNhabitacion(001);
				hab1.setNcamas(2);
				hab1.setPrecio(541);
				hab1.setDisponible(true);
				hab1.setHotel(hotel);
				System.out.println(hab1);
				
			Habitacion hab2=new Habitacion();
				hab2.setNhabitacion(002);
				hab2.setNcamas(2);
				hab2.setPrecio(541);
				hab2.setDisponible(true);
				hab2.setHotel(hotel);
                
				System.out.println(hab2);
				
			Set<Habitacion> habitaciones=new HashSet<Habitacion>(); 
				habitaciones.add(hab1);
				habitaciones.add(hab2);
        
		//Añado las habitaciones
        hotel.setHabitaciones(habitaciones);
         
		this.hotelService.saveHotel(hotel);
		LOG.log(Level.INFO,"assertThat"+hotel.getId());
		assertThat(hotel.getId()).isNotEqualTo(0);
		
		//Comprobamos que se ha añadido sin problemas"
		hoteles = this.hotelService.findByNombre("Sunset");
		assertThat(hoteles.size()).isEqualTo(found+1);
		LOG.log(Level.INFO,"Hotel Sunset encontrado. Found= "+hoteles.size());
		LOG.log(Level.INFO,"==========================================================");
    }
	
	//Prueba H2-E1 - Alta de un hotel sin nombre, provincia, precio
	@Test
	@Transactional
	public void shouldInsertHotelVacio() {
		Hotel hotel = new Hotel();
		hotel.setNombre("");
		hotel.setDireccion("Calle Overdrive");
		hotel.setEstrellas(4);
		hotel.setProvincia("");
	
		hotel.setTelefono("666555111");
		
        assertThat(hotel.getNombre().isEmpty()).isTrue();
		assertThat(hotel.getProvincia().isEmpty()).isTrue();
		
    }
	
	//Prueba H3+E1 - Baja de un hotel
	@Test
	void shouldDeleteHotel() {
		Hotel hotel = new Hotel();
		hotel.setNombre("Sunset");
		hotel.setDireccion("Calle Overdrive");
		hotel.setEstrellas(4);
		hotel.setProvincia("Granada");
		
		hotel.setTelefono("666555111");
		LOG.log(Level.INFO,"HOTEL: "+hotel);
		LOG.log(Level.INFO,"ID HOTEL: "+hotel.getId());
//		hotel.setId(1234);
		System.out.println("ID HOTEL: "+hotel.getId());
		//Opcional, como está relacionado con habitaciones le creo 2 habitaciones"
			Habitacion hab1=new Habitacion();
				hab1.setNhabitacion(001);
				hab1.setNcamas(2);
				hab1.setPrecio(541);
				hab1.setDisponible(true);
				hab1.setHotel(hotel);
				System.out.println(hab1);
				
			Habitacion hab2=new Habitacion();
				hab2.setNhabitacion(002);
				hab2.setNcamas(2);
				hab2.setPrecio(541);
				hab2.setDisponible(true);
				hab2.setHotel(hotel);
                
				System.out.println(hab2);
				
			Set<Habitacion> habitaciones=new HashSet<Habitacion>(); 
				habitaciones.add(hab1);
				habitaciones.add(hab2);
        
		//Añado las habitaciones"
        hotel.setHabitaciones(habitaciones);
         
		this.hotelService.saveHotel(hotel);
		Collection<Hotel> hoteles = this.hotelService.findByNombre("Sunset");
		int found = hoteles.size();
		LOG.log(Level.INFO,"Tamaño 1");
		LOG.log(Level.INFO,"==========================================================");
		LOG.log(Level.INFO,"Hotel Sunset encontrado. Found= "+found);
		LOG.log(Level.INFO,"==========================================================");
		
		//Comprobamos que se ha añadido sin problemas"
		this.hotelService.deleteHotel(hotel);
		hoteles = this.hotelService.findByNombre("Sunset");
		assertThat(hoteles.size()).isEqualTo(found-1);
		LOG.log(Level.INFO,"Hotel Sunset no encontrado. Found= "+hoteles.size());
	}
}
