
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

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.merlantico.configuration.SecurityConfiguration;
import org.springframework.samples.merlantico.model.Actividad;
import org.springframework.samples.merlantico.model.ReservaActividad;
import org.springframework.samples.merlantico.service.ActividadService;
import org.springframework.samples.merlantico.service.ReservaActividadService;
import org.springframework.samples.merlantico.service.UserService;
import org.springframework.samples.merlantico.web.ReservaActividadController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/actividades/{actividadId}")
@WebMvcTest(controllers=ReservaActividadController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ReservaActividadControllerTests {
	
	private static final int TEST_RESERVAACTIVIDAD_ID = 1;
	
	private static final int TEST_ACTIVIDAD_ID = 2;
	
	@Autowired
	private ReservaActividadController reservaActividadController;

	@MockBean
	private ReservaActividadService reservaActividadService;
	@MockBean
	private ActividadService actividadService;
	@MockBean
	private UserService userService;
    
	@Autowired
	private MockMvc mockMvc;

	
	private ReservaActividad reservaActividades;
	
	
	//Creamos la reserva
	@BeforeEach
	void setup() {
		
		Actividad actividad = new Actividad();
		actividad.setId(TEST_ACTIVIDAD_ID);
		actividad.setNombre("Escalada");
		actividad.setDescripcion("Buena ruta de escala con amigos");
		actividad.setValoracion(4);
		actividad.setDireccion("Sierra de Grazalema");
		actividad.setPrecio(2);
		
		reservaActividades = new ReservaActividad();
		reservaActividades.setFechaReserva(LocalDate.of(2021, 4, 10));
		reservaActividades.setEntrada(LocalDate.of(2021, 4, 13));
		reservaActividades.setNumeroTarjeta("1111111111111111");
		reservaActividades.setCvc("333");
		reservaActividades.setPrecioFinal(100.0);
		reservaActividades.setActivdad(actividad);

		given(this.reservaActividadService.findReservaActividadById(TEST_RESERVAACTIVIDAD_ID)).willReturn(reservaActividades);
		given(this.actividadService.findActividadById(TEST_ACTIVIDAD_ID)).willReturn(actividad);	
	}
	

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/actividades/{actividadId}/reservaActividad/new",TEST_ACTIVIDAD_ID)).andExpect(status().isOk())
				.andExpect(view().name("reservaActividad/createReservaActividadForm")).andExpect(model().attributeExists("reservaActividad"));
	}

	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/actividades/{actividadId}/reservaActividad/new",TEST_ACTIVIDAD_ID)
							.with(csrf())
							.param("fechaReserva", "2021/02/10")
							.param("entrada", "2021/10/14")
							.param("numeroTarjeta", "2222222222222222")
							.param("cvc", "333")
							.param("precioFinal", "100.0"))
				.andExpect(status().is2xxSuccessful());
	}
	
	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/actividades/{actividadId}/reservaActividad/new",TEST_ACTIVIDAD_ID)
							.with(csrf())
							.param("fechaReserva", "2021/02/06")
							.param("entrada", "2021/10/13")
							.param("numeroTarjeta", "")
							.param("cvc", "333")
							.param("precioFinal", "100.0"))
				.andExpect(status().isOk())
				.andExpect(model().attributeDoesNotExist("reservaActividad"))
				.andExpect(view().name("reservaActividad/createReservaActividadForm"));
	}
	
    @WithMockUser(value = "spring")
	@Test
	void testShowReservaActividad() throws Exception {
		mockMvc.perform(get("/actividades/{actividadId}/reservaActividad/{reservaActividadId}",TEST_ACTIVIDAD_ID, TEST_RESERVAACTIVIDAD_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("reservaActividad", hasProperty("fechaReserva", is(LocalDate.of(2021, 4, 10)))))
				.andExpect(model().attribute("reservaActividad", hasProperty("entrada", is(LocalDate.of(2021, 4, 13)))))
				.andExpect(model().attribute("reservaActividad", hasProperty("numeroTarjeta", is("1111111111111111"))))
				.andExpect(model().attribute("reservaActividad", hasProperty("cvc", is("333"))))
				.andExpect(model().attribute("reservaActividad", hasProperty("precioFinal", is(100.0))))
				.andExpect(view().name("reservaActividad/reservaActividadDetails"));
	}
	
}


