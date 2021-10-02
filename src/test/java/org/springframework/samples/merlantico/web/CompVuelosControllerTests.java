package org.springframework.samples.merlantico.web;

import static org.mockito.BDDMockito.given;
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
import org.springframework.samples.merlantico.model.CompVuelos;
import org.springframework.samples.merlantico.service.CompVuelosService;
import org.springframework.samples.merlantico.web.CompVuelosController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;

@WebMvcTest(controllers=CompVuelosController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class CompVuelosControllerTests {
	
	private static final int TEST_COMPVUELO_ID = 1;
	
	@Autowired
	private CompVuelosController compVuelosController;
	
	@MockBean
	private CompVuelosService compVuelosService;
    
	@Autowired
	private MockMvc mockMvc;

	private CompVuelos compVuelo;

	@BeforeEach
	void setup() {
		compVuelo = new CompVuelos();
		compVuelo.setId(TEST_COMPVUELO_ID);
		compVuelo.setNombre("Iberia");
		compVuelo.setPais("España");
		compVuelo.setSede("Madrid");
		
		given(this.compVuelosService.findCompVuelosById(TEST_COMPVUELO_ID)).willReturn(compVuelo);
	}

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/compvuelos/new")).andExpect(status().isOk()).andExpect(model().attributeExists("compvuelos"))
				.andExpect(view().name("compvuelos/createOrUpdateCompVuelosForm"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/compvuelos/new").param("nombre", "Ryanair")
							.with(csrf())
							.param("pais", "Inglaterra")
							.param("sede", "Londres"))
				.andExpect(status().is3xxRedirection());
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/compvuelos/new")
							.with(csrf())
							.param("nombre", "Ryanair")
							.param("pais", "")
							.param("sede", "Londres"))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("compVuelos"))
				.andExpect(model().attributeHasFieldErrors("compVuelos", "pais"))
				.andExpect(view().name("compvuelos/createOrUpdateCompVuelosForm"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testInitFindForm() throws Exception {
	    mockMvc.perform(get("/compvuelos/find")).andExpect(status().isOk()).andExpect(model().attributeExists("compvuelos"))
		    .andExpect(view().name("compvuelos/findCompVuelos"));
    }
	
	@WithMockUser(value = "spring")
	@Test
	void testShowCompVuelo() throws Exception {
		mockMvc.perform(get("/compvuelos/{compVuelosId}", TEST_COMPVUELO_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("compvuelos", hasProperty("nombre", is("Iberia"))))
				.andExpect(model().attribute("compvuelos", hasProperty("pais", is("España"))))
				.andExpect(model().attribute("compvuelos", hasProperty("sede", is("Madrid"))))
				.andExpect(view().name("compvuelos/compvuelosDetails"));
	}
	
	   @WithMockUser(value = "spring")
		@Test
		void testProcessUpdateCompVueloFormSuccess() throws Exception {
			mockMvc.perform(post("/compvuelos/{compVuelosId}/edit", TEST_COMPVUELO_ID)
								.with(csrf())
								.param("nombre", "Ryanair")
								.param("pais", "España")
								.param("sede", "Londres"))
					.andExpect(status().is3xxRedirection())
					.andExpect(view().name("redirect:/compvuelos/{compVuelosId}"));
		}
	   
	   @WithMockUser(value = "spring")
		@Test
		void testProcessUpdateCompVueloFormHasErrors() throws Exception {
			mockMvc.perform(post("/compvuelos/{compVuelosId}/edit" , TEST_COMPVUELO_ID)
								.with(csrf())
								.param("nombre", "Ryanair")
								.param("pais", "")
								.param("sede", "Londres"))
			    .andExpect(status().isOk())
			    .andExpect(model().attributeHasErrors("compVuelos"))
			    .andExpect(model().attributeHasFieldErrors("compVuelos", "pais"))
			    .andExpect(view().name("compvuelos/createOrUpdateCompVuelosForm"));
		}
	   
	   @WithMockUser(value = "spring")
		@Test
		void testInitUpdateCompVueloForm() throws Exception {
			mockMvc.perform(get("/compvuelos/{compVuelosId}/edit", TEST_COMPVUELO_ID)).andExpect(status().isOk())
					.andExpect(model().attributeExists("compvuelos"))
					.andExpect(model().attribute("compvuelos", hasProperty("nombre", is("Iberia"))))
					.andExpect(model().attribute("compvuelos", hasProperty("pais", is("España"))))
					.andExpect(model().attribute("compvuelos", hasProperty("sede", is("Madrid"))))
					.andExpect(view().name("compvuelos/createOrUpdateCompVuelosForm"));
		}
	   
	   @WithMockUser(value = "spring")
       @Test
	void testProcessFindFormSuccess() throws Exception {
		given(this.compVuelosService.findByNombre("Iberia")).willReturn(Lists.newArrayList(compVuelo, new CompVuelos()));

		mockMvc.perform(get("/compvuelos")).andExpect(status().isOk()).andExpect(view().name("compvuelos/findCompVuelos"));
	}

	
	
}
