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
import org.springframework.samples.merlantico.model.ComentarioActividad;
import org.springframework.samples.merlantico.service.ActividadService;
import org.springframework.samples.merlantico.service.ComentarioActividadService;
import org.springframework.samples.merlantico.web.ComentarioActividadController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=ComentarioActividadController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ComentarioActividadControllerTests {
	
	private static final int TEST_COMENTARIOACTIVIDAD_ID = 1;
	private static final int TEST_ACTIVIDAD_ID = 1;
	
	@Autowired
	private ComentarioActividadController comentarioActividadController;

	@MockBean
	private ComentarioActividadService comentarioActividadService;
	@MockBean
	private ActividadService actividadService;
        
    
	@Autowired
	private MockMvc mockMvc;

	private ComentarioActividad comentarioActividades;
	
	//Creamos el hotel
	@BeforeEach
	void setup() {

		Actividad actividad = new Actividad();
		actividad.setId(TEST_ACTIVIDAD_ID);
		actividad.setNombre("Escalada");
		actividad.setDescripcion("Buena ruta de escala con amigos");
		actividad.setValoracion(4);
		actividad.setDireccion("Sierra de Grazalema");
		actividad.setPrecio(2);
		
		comentarioActividades=new ComentarioActividad();
		comentarioActividades.setActividad(actividad);;
		comentarioActividades.setMensaje("Bueno");
		comentarioActividades.setId(TEST_COMENTARIOACTIVIDAD_ID);
		comentarioActividades.setPuntuacion(6);

		given(this.comentarioActividadService.findComentarioById(TEST_COMENTARIOACTIVIDAD_ID)).willReturn(comentarioActividades);
		given(this.actividadService.findActividadById(TEST_ACTIVIDAD_ID)).willReturn(actividad);
	}
	
	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/actividades/{actividadId}/comentarios/new",TEST_ACTIVIDAD_ID)).andExpect(status().isOk())
				.andExpect(view().name("actividades/createComentarioForm")).andExpect(model().attributeExists("comentarioActividad"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/actividades/{actividadId}/comentarios/new",TEST_ACTIVIDAD_ID)
							.with(csrf())
							.param("mensaje", "Lujo")
							.param("puntuacion", "4")
							)
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/actividades/"+TEST_ACTIVIDAD_ID));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/actividades/{actividadId}/comentarios/new",TEST_ACTIVIDAD_ID)
							.with(csrf())
							.param("mensaje", "")
							)
				.andExpect(status().isOk())
				.andExpect(model().attributeHasFieldErrors("comentarioActividad"))
				.andExpect(view().name("actividades/createComentarioForm"));
	}
}
