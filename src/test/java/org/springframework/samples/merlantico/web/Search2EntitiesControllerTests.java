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
import org.springframework.samples.merlantico.model.Hotel;
import org.springframework.samples.merlantico.model.Vuelo;
import org.springframework.samples.merlantico.service.HotelService;
import org.springframework.samples.merlantico.service.VueloService;
import org.springframework.samples.merlantico.web.Search2EntitiesController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=Search2EntitiesController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class Search2EntitiesControllerTests {

private static final int TEST_HOTEL_ID = 1;
private static final int TEST_VUELO_ID = 2;

	
	@Autowired
	private Search2EntitiesController search2EntitiesController;

	@MockBean
	private HotelService hotelService;
        
    @MockBean
    private VueloService vueloService;
    
    private Hotel hotelazo;
    private Vuelo vuelo;
    
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
  		
  		vuelo = new Vuelo();
		vuelo.setId(TEST_VUELO_ID);
		vuelo.setBilletes(2);
		vuelo.setDestino("Malaga");
		vuelo.setOrigen("Sevilla");
		vuelo.setPrecio(12);
		LocalDate fechaIda=LocalDate.of(2021, 10, 26);
		vuelo.setFechaIda(fechaIda);
		LocalDate fechaVuelta=LocalDate.of(2021, 11, 4);
		vuelo.setFechaVuelta(fechaVuelta);
		
		given(this.vueloService.findVueloById(TEST_VUELO_ID)).willReturn(vuelo);

  	}
    
  	@WithMockUser(value = "spring")
    @Test
	void testInitFindForm() throws Exception {
		mockMvc.perform(get("/search/find")).andExpect(status().isOk())
		.andExpect(model().attributeExists("hotel"))
				.andExpect(view().name("search/findEntities"));
	}
	 @WithMockUser(value = "spring")
    @Test
	void testProcessFindFormSuccess() throws Exception {
		given(this.hotelService.findByProvincia("")).willReturn(Lists.newArrayList(hotelazo, new Hotel()));

		mockMvc.perform(get("/search")).andExpect(status().isOk()).andExpect(view().name("search/entitiesList"));
	}
	@Autowired
	private MockMvc mockMvc;
	
	
}
