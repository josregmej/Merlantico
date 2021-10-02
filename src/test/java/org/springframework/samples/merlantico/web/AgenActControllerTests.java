package org.springframework.samples.merlantico.web;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
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
import org.springframework.samples.merlantico.model.AgenAct;
import org.springframework.samples.merlantico.service.AgenActService;
import org.springframework.samples.merlantico.web.AgenActController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=AgenActController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class AgenActControllerTests {
	
	private static final int TEST_AGENACT_ID = 1;
	
	@Autowired
	private AgenActController agenActController;
	
	@MockBean
	private AgenActService agenActService;
    
	@Autowired
	private MockMvc mockMvc;

	private AgenAct agenAct;
	
	//Creamos la actividad
	@BeforeEach
	void setup() {
		agenAct = new AgenAct();
		agenAct.setId(TEST_AGENACT_ID);
		agenAct.setNombre("Liberty");
		agenAct.setSede("Sevilla");
		agenAct.setTelefono("945323849");
		
		given(this.agenActService.findAgenActById(TEST_AGENACT_ID)).willReturn(agenAct);
	}

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/agenacts/new")).andExpect(status().isOk()).andExpect(model().attributeExists("agenact"))
				.andExpect(view().name("agenacts/createOrUpdateAgenActForm"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/agenacts/new")
							.with(csrf())
							.param("nombre", "Agencia 1")
							.param("sede", "Madrid")
							.param("telefono", "985478392"))
				.andExpect(status().is3xxRedirection());
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/agenacts/new")
							.with(csrf())
							.param("nombre", "Agencia 1")
							.param("sede", "Sevilla")
							.param("telefono", "9854783922222"))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("agenAct"))
				.andExpect(model().attributeHasFieldErrors("agenAct","telefono"))
				.andExpect(view().name("agenacts/createOrUpdateAgenActForm"));
	}
	
	 @WithMockUser(value = "spring")
		@Test
		void testInitUpdateHotelForm() throws Exception {
			mockMvc.perform(get("/agenacts/{agenactId}/edit", TEST_AGENACT_ID)).andExpect(status().isOk())
			.andExpect(model().attribute("agenact", hasProperty("nombre", is("Liberty"))))
			.andExpect(model().attribute("agenact", hasProperty("sede", is("Sevilla"))))
			.andExpect(model().attribute("agenact", hasProperty("telefono", is("945323849"))))
					.andExpect(view().name("agenacts/createOrUpdateAgenActForm"));
		}

	
	@WithMockUser(value = "spring")
		@Test
	void testProcessUpdateFormSuccess() throws Exception {
		mockMvc.perform(post("/agenacts/{agenactId}/edit", TEST_AGENACT_ID)
							.with(csrf())
							.param("nombre", "Agencia 3")
							.param("sede", "Malaga")
							.param("telefono", "945323849"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/agenacts/{agenactId}"));
	}
	
	 @WithMockUser(value = "spring")
		@Test
		void testProcessUpdateFormHasErrors() throws Exception {
			mockMvc.perform(post("/agenacts/{agenactId}/edit", TEST_AGENACT_ID)
								.with(csrf())
								.param("nombre", "Agencia 3")
								.param("sede", "Malaga")
								.param("telefono", "222222222222222222222222"))
					.andExpect(model().attributeHasErrors("agenAct")).andExpect(status().isOk())
					.andExpect(view().name("agenacts/createOrUpdateAgenActForm"));
		}
	 @WithMockUser(value = "spring")
	 	@Test
	void testInitFindForm() throws Exception {
		mockMvc.perform(get("/agenacts/find")).andExpect(status().isOk()).andExpect(model().attributeExists("agenact"))
				.andExpect(view().name("agenacts/findAgenActs"));
	}
	 @WithMockUser(value = "spring")
	 	@Test
	void testProcessFindFormSuccess() throws Exception {
		given(this.agenActService.findByNombre("")).willReturn(Lists.newArrayList(agenAct, new AgenAct()));

		mockMvc.perform(get("/agenacts")).andExpect(status().isOk()).andExpect(view().name("agenacts/agenActsList"));
	}
	 
	  @WithMockUser(value = "spring")
		@Test
		void testShowAgenAct() throws Exception {
			mockMvc.perform(get("/agenacts/{agenactId}/", TEST_AGENACT_ID)).andExpect(status().isOk())
					.andExpect(model().attribute("agenact", hasProperty("nombre", is("Liberty"))))
					.andExpect(model().attribute("agenact", hasProperty("sede", is("Sevilla"))))
					.andExpect(model().attribute("agenact", hasProperty("telefono", is("945323849"))))
					.andExpect(view().name("agenacts/agenactDetails"));
		}
}
