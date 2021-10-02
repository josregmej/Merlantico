package org.springframework.samples.merlantico.web;

import static org.mockito.BDDMockito.given;
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
import org.springframework.samples.merlantico.model.Actividad;
import org.springframework.samples.merlantico.service.ActividadService;
import org.springframework.samples.merlantico.service.AgenActService;
import org.springframework.samples.merlantico.web.ActividadController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=ActividadController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ActividadControllerTests {
	
	private static final int TEST_ACTIVIDAD_ID = 1;
	
	@Autowired
	private ActividadController actividadController;
	
	@MockBean
	private ActividadService actividadService;
	
	@MockBean
	private AgenActService agenactService;
    
	@Autowired
	private MockMvc mockMvc;

	private Actividad actividad;
	
	//Creamos la actividad
	@BeforeEach
	void setup() {
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
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/actividades/new")).andExpect(status().isOk()).andExpect(model().attributeExists("actividades"))
				.andExpect(view().name("actividades/createOrUpdateActividadForm"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/actividades/new").param("nombre", "Ruta guiada")
							.with(csrf())
							.param("direccion", "123 Prueba Street")
							.param("descripcion", "Buena ruta")
							.param("provincia", "Sevilla")
							.param("valoracion", "4")
							.param("precio", "4175"))
				.andExpect(status().is3xxRedirection());
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/actividades/new")
							.with(csrf())
							.param("nombre", "Ruta guiada")
							.param("descripcion", "")
							.param("valoracion", "4")
							.param("direccion", "123 Prueba Street")
							.param("provincia", "Sevilla")
							.param("precio", "4175"))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("actividad"))
				.andExpect(model().attributeHasFieldErrors("actividad", "descripcion"))
				.andExpect(view().name("actividades/createOrUpdateActividadForm"));
	}
}
