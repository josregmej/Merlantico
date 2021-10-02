package org.springframework.samples.merlantico.web;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.samples.merlantico.service.HotelService;
import org.springframework.samples.merlantico.web.HotelController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=HotelController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class HotelControllerTests {
	
	private static final int TEST_HOTEL_ID = 1;
	
	@Autowired
	private HotelController hotelController;

	@MockBean
	private HotelService hotelService;
        
    
	@Autowired
	private MockMvc mockMvc;

	private Hotel hotelazo;
	
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

	}
	

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/hoteles/new")).andExpect(status().isOk()).andExpect(model().attributeExists("hotel"))
				.andExpect(view().name("hoteles/createOrUpdateHotelForm"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/hoteles/new").param("nombre", "Lujo")
							.with(csrf())
							.param("direccion", "123 Prueba Street")
							.param("estrellas", "4")
							.param("provincia", "Malaga")
							.param("telefono", "013167638"))
				.andExpect(status().is3xxRedirection());
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/hoteles/new")
							.with(csrf())
							.param("nombre", "Joe")
							.param("direccion", "123 Prueba Street")
							.param("estrellas", "4")
							.param("provincia", "Sevilla")
							.param("telefono", "01316763284325234"))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("hotel"))
				.andExpect(model().attributeHasFieldErrors("hotel","telefono"))
				.andExpect(view().name("hoteles/createOrUpdateHotelForm"));
	}

	  @WithMockUser(value = "spring")
		@Test
		void testInitUpdateHotelForm() throws Exception {
			mockMvc.perform(get("/hoteles/{hotelId}/edit", TEST_HOTEL_ID)).andExpect(status().isOk())
			.andExpect(model().attribute("hotel", hasProperty("nombre", is("Hotelazo"))))
			.andExpect(model().attribute("hotel", hasProperty("direccion", is("Calle normal"))))
			.andExpect(model().attribute("hotel", hasProperty("estrellas", is(5))))
			.andExpect(model().attribute("hotel", hasProperty("provincia", is("Cadiz"))))
			.andExpect(model().attribute("hotel", hasProperty("telefono", is("945122241"))))
					.andExpect(view().name("hoteles/createOrUpdateHotelForm"));
		}

	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		mockMvc.perform(post("/hoteles/{hotelId}/edit", TEST_HOTEL_ID)
							.with(csrf())
							.param("nombre", "Joe")
							.param("direccion", "123 Prueba Street")
							.param("estrellas", "4")
							.param("provincia", "Sevilla")
							.param("telefono", "013167648"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/hoteles/{hotelId}"));
	}
	
	 @WithMockUser(value = "spring")
		@Test
		void testProcessUpdateFormHasErrors() throws Exception {
			mockMvc.perform(post("/hoteles/{hotelId}/edit", TEST_HOTEL_ID)
								.with(csrf())
								.param("nombre", "Joe")
								.param("direccion", "123 Prueba Street")
								.param("estrellas", "4")
								.param("telefono", "013167648"))
					.andExpect(model().attributeHasErrors("hotel")).andExpect(status().isOk())
					.andExpect(view().name("hoteles/createOrUpdateHotelForm"));
		}
	 @WithMockUser(value = "spring")
     @Test
	void testInitFindForm() throws Exception {
		mockMvc.perform(get("/hoteles/find")).andExpect(status().isOk()).andExpect(model().attributeExists("hotel"))
				.andExpect(view().name("hoteles/findHoteles"));
	}
	 @WithMockUser(value = "spring")
     @Test
	void testProcessFindFormSuccess() throws Exception {
		given(this.hotelService.findByNombre("")).willReturn(Lists.newArrayList(hotelazo, new Hotel()));

		mockMvc.perform(get("/hoteles")).andExpect(status().isOk()).andExpect(view().name("hoteles/hotelesList"));
	}
	 
	 @WithMockUser(value = "spring")
     @Test
	void testinitFindProvinciaForm() throws Exception {
		mockMvc.perform(get("/hoteles/findProvincias")).andExpect(status().isOk()).andExpect(model().attributeExists("hotel"))
				.andExpect(view().name("hoteles/findProvincias"));
	}
	 
	 @WithMockUser(value = "spring")
     @Test
	void testProcessFindProvForm() throws Exception {
		given(this.hotelService.findByProvincia(hotelazo.getProvincia())).willReturn(Lists.newArrayList(hotelazo));

		mockMvc.perform(get("/hoteles/provincias").param("provincia", "Cadiz")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/hoteles/" + TEST_HOTEL_ID));
	}
	 
     @WithMockUser(value = "spring")
	@Test
	void testShowHotel() throws Exception {
		mockMvc.perform(get("/hoteles/{hotelId}/", TEST_HOTEL_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("hotel", hasProperty("nombre", is("Hotelazo"))))
				.andExpect(model().attribute("hotel", hasProperty("direccion", is("Calle normal"))))
				.andExpect(model().attribute("hotel", hasProperty("estrellas", is(5))))
				.andExpect(model().attribute("hotel", hasProperty("provincia", is("Cadiz"))))
				.andExpect(model().attribute("hotel", hasProperty("telefono", is("945122241"))))
				.andExpect(view().name("hoteles/hotelDetails"));
	}
}
