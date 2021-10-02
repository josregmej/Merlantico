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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.merlantico.configuration.SecurityConfiguration;
import org.springframework.samples.merlantico.model.User;
import org.springframework.samples.merlantico.service.ReservaActividadService;
import org.springframework.samples.merlantico.service.ReservaHabitacionService;
import org.springframework.samples.merlantico.service.ReservaVueloService;
import org.springframework.samples.merlantico.service.UserService;
import org.springframework.samples.merlantico.web.UserController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=UserController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class UserControllerTests {
	
	private static final String TEST_USER_ID = "luigi";
	
	@Autowired
	private UserController userController;

	@MockBean
	private UserService userService;
    @MockBean
    private ReservaActividadService reservaActividadService;
    @MockBean
    private ReservaHabitacionService reservaHabitacionService;
    @MockBean
    private ReservaVueloService reservaVueloService;
    
	@Autowired
	private MockMvc mockMvc;

	private User user;
	
	//Creamos el usuario
	@BeforeEach
	void setup() {

		user = new User();
		user.setUsername("mario");
		user.setPassword("Elpepe013");
		user.setTelefono("956444876");
		user.setDni("44068802R");
		//Deberá devolver el hotel
		given(this.userService.findByUsername(TEST_USER_ID)).willReturn(user);

	}
	

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/users/new")).andExpect(status().isOk()).andExpect(model().attributeExists("user"))
				.andExpect(view().name("users/createOrUpdateUserForm"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/users/new").param("username", "mario")
							.with(csrf())
							.param("password", "Elpepe013")
							.param("telefono", "956444876")
							.param("dni", "44068802R"))
				.andExpect(status().is3xxRedirection());
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/users/new").param("username", "mario")
				.with(csrf())
				.param("password", "Elpepe013")
				.param("telefono", "")
				.param("dni", "44068802R"))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("user"))
				.andExpect(model().attributeHasFieldErrors("user","telefono"))
				.andExpect(view().name("users/createOrUpdateUserForm"));
	}
	

	  @WithMockUser(value = "spring")
		@Test
		void testInitUpdateUserForm() throws Exception {
			mockMvc.perform(get("/users/{username}/edit", TEST_USER_ID)).andExpect(status().isOk())
			.andExpect(model().attribute("user", hasProperty("username", is("mario"))))
			.andExpect(model().attribute("user", hasProperty("password", is("Elpepe013"))))
			.andExpect(model().attribute("user", hasProperty("telefono", is("956444876"))))
			.andExpect(model().attribute("user", hasProperty("dni", is("44068802R"))))
			.andExpect(view().name("users/createOrUpdateUserForm"));
		}

	  
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		mockMvc.perform(post("/users/{username}/edit", TEST_USER_ID)
				.with(csrf())
				.param("username", "mario")
				.param("password", "Elpepe013")
				.param("telefono", "633897503")
				.param("dni", "44068802R"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/users/{username}"));
	}
	
	
	 @WithMockUser(value = "spring")
		@Test
		void testProcessUpdateFormHasErrors() throws Exception {
		    mockMvc.perform(post("/users/{username}/edit", TEST_USER_ID)
								.with(csrf())
								.param("username", "mario")
								.param("password", "Elpepe013")
								.param("telefono", "")
								.param("dni", "44068802R"))
					.andExpect(model().attributeHasErrors("user")).andExpect(status().isOk())
					.andExpect(view().name("users/createOrUpdateUserForm"));
		}
	 
     @WithMockUser(value = "spring")
	@Test
	void testShowUser() throws Exception {
		mockMvc.perform(get("/users/{username}/", TEST_USER_ID)).andExpect(status().isOk())
		.andExpect(model().attribute("user", hasProperty("username", is("mario"))))
		.andExpect(model().attribute("user", hasProperty("password", is("Elpepe013"))))
		.andExpect(model().attribute("user", hasProperty("telefono", is("956444876"))))
		.andExpect(model().attribute("user", hasProperty("dni", is("44068802R"))))
				.andExpect(view().name("users/userDetails"));
	}
	
}
