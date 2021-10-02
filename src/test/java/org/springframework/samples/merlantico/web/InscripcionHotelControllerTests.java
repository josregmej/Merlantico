package org.springframework.samples.merlantico.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.merlantico.configuration.SecurityConfiguration;
import org.springframework.samples.merlantico.model.InscripcionHotel;
import org.springframework.samples.merlantico.service.InscripcionHotelService;
import org.springframework.samples.merlantico.web.InscripcionHotelController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=InscripcionHotelController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class InscripcionHotelControllerTests {
	
private static final int TEST_HOTEL_ID = 1;
	
	@Autowired
	private InscripcionHotelController inscripcionHotelController;

	@MockBean
	private InscripcionHotelService inscripcionHotelService;
        
    
	@Autowired
	private MockMvc mockMvc;
	
	private InscripcionHotel inscripcionaza;
	
	@BeforeEach
	void setup() {

		inscripcionaza = new InscripcionHotel();
		inscripcionaza.setId(TEST_HOTEL_ID);
		inscripcionaza.setNombre("Hotelazo");
		inscripcionaza.setDireccion("Calle normal");
		inscripcionaza.setProvincia("Cadiz");
		inscripcionaza.setDescripcion("Nope");
		inscripcionaza.setActividades("Nope");
		given(this.inscripcionHotelService.findInscripcionHotelById(TEST_HOTEL_ID)).willReturn(inscripcionaza);

	}
	
	@WithMockUser(value = "spring")
    @Test
    void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/inscripciones/new")).andExpect(status().isOk()).andExpect(model().attributeExists("inscripcionHotel"))
				.andExpect(view().name("inscripcionhoteles/createOrUpdateInscripcionForm"));
	}

	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/inscripciones/new").param("nombre", "Lujo")
						.with(csrf())
						.param("direccion", "123 Prueba Street")
						.param("descripcion", "4")
						.param("provincia", "Malaga")
						.param("actividades", "013167638"))
			.andExpect(status().is2xxSuccessful()).andExpect(view().name("inscripcionhoteles/inscripcionExito"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/inscripciones/new").param("nombre", "Lujo")
						.with(csrf())
						.param("direccion", "")
						.param("descripcion", "4")
						.param("provincia", "Malaga")
						.param("actividades", "013167638"))
		.andExpect(status().isOk())
		.andExpect(model().attributeHasFieldErrors("inscripcionHotel"))
		.andExpect(view().name("inscripcionhoteles/createOrUpdateInscripcionForm"));
	}

	@WithMockUser(value = "spring")
    @Test
    void testProcessFindFormSuccess() throws Exception {
		given(this.inscripcionHotelService.findAll()).willReturn(Lists.newArrayList(inscripcionaza, new InscripcionHotel()));
		mockMvc.perform(get("/inscripciones")).andExpect(status().isOk()).andExpect(view().name("inscripcionhoteles/inscripcionList"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowInscripcionHotel() throws Exception {
		mockMvc.perform(get("/inscripciones/{inscripcionHotelId}", TEST_HOTEL_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("inscripcionHotel", hasProperty("nombre", is("Hotelazo"))))
				.andExpect(model().attribute("inscripcionHotel", hasProperty("direccion", is("Calle normal"))))
				.andExpect(model().attribute("inscripcionHotel", hasProperty("provincia", is("Cadiz"))))
				.andExpect(model().attribute("inscripcionHotel", hasProperty("descripcion", is("Nope"))))
				.andExpect(model().attribute("inscripcionHotel", hasProperty("actividades", is("Nope"))))
				.andExpect(view().name("inscripcionhoteles/inscripcionDetails"));
		}

}
