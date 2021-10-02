package org.springframework.samples.merlantico.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.merlantico.configuration.SecurityConfiguration;
import org.springframework.samples.merlantico.model.Actividad;
import org.springframework.samples.merlantico.model.Hotel;
import org.springframework.samples.merlantico.service.ActividadService;
import org.springframework.samples.merlantico.service.HotelService;
import org.springframework.samples.merlantico.web.HotelActividadController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=HotelActividadController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class HotelActividadControllerTests {


	private static final int TEST_HOTEL_ID = 1;
	private static final int TEST_ACTIVIDAD_ID = 2;

		
		@Autowired
		private HotelActividadController hotelActividadController;

		@MockBean
		private HotelService hotelService;
	        
	    @MockBean
	    private ActividadService actividadService;
	    
	    private Hotel hotelazo;
	    private Actividad actividad;
	    
	  //Creamos el hotel
	  	@BeforeEach
	  	void setup() {

	  		hotelazo = new Hotel();
	  		hotelazo.setId(TEST_HOTEL_ID);
	  		hotelazo.setNombre("Hotelazo");
	  		hotelazo.setDireccion("Calle normal");
	  		hotelazo.setProvincia("Cadiz");
	  		hotelazo.setEstrellas(5);
	  		
	  		hotelazo.setTelefono("945122241");
	  		//Deberá devolver el hotel
	  		given(this.hotelService.findHotelById(TEST_HOTEL_ID)).willReturn(hotelazo);
	  		
	  		actividad = new Actividad();
			actividad.setId(TEST_ACTIVIDAD_ID);
			actividad.setNombre("Escalada");
			actividad.setDescripcion("Buena ruta de escala con amigos");
			actividad.setValoracion(4);
			actividad.setDireccion("Sierra de Grazalema");
			actividad.setPrecio(2);
			
			given(this.actividadService.findActividadById(TEST_ACTIVIDAD_ID)).willReturn(actividad);
			//System.out.println(actividad);
	  	}
	    
	  	@WithMockUser(value = "spring")
	    @Test
		void testInitFindForm() throws Exception {
			mockMvc.perform(get("/hotelActividad/find")).andExpect(status().isOk())
			.andExpect(model().attributeExists("hotel"))
					.andExpect(view().name("hotelActividad/search"));
		}
	  	
		@WithMockUser(value = "spring")
	    @Test
		void testProcessFindFormSuccess() throws Exception {
			given(this.hotelService.findByProvincia("")).willReturn(Lists.newArrayList(hotelazo, new Hotel()));

			mockMvc.perform(get("/hotelActividad")).andExpect(status().isOk()).andExpect(view().name("hotelActividad/list"));
		}
		
		@Autowired
		private MockMvc mockMvc;

}
