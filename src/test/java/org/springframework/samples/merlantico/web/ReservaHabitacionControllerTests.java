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

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.merlantico.configuration.SecurityConfiguration;
import org.springframework.samples.merlantico.model.ReservaHabitacion;
import org.springframework.samples.merlantico.service.HabitacionService;
import org.springframework.samples.merlantico.service.ReservaHabitacionService;
import org.springframework.samples.merlantico.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=ReservaHabitacionController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ReservaHabitacionControllerTests {
	
	private static final int TEST_RESERVAHABITACION_ID = 1;
	
	@Autowired
	private ReservaHabitacionController reservaHabitacionController;

	@MockBean
	private ReservaHabitacionService reservaHabitacionService;
	@MockBean
	private HabitacionService habitacionService;
	@MockBean
	private UserService userService;
    
	@Autowired
	private MockMvc mockMvc;

	
	private ReservaHabitacion reservaHabitacion;
	
	
	//Creamos la reserva
	@BeforeEach
	void setup() {

		reservaHabitacion = new ReservaHabitacion();
		reservaHabitacion.setFechaReserva(LocalDate.now());
		reservaHabitacion.setEntrada(LocalDate.of(2021, 4, 15));
		reservaHabitacion.setSalida(LocalDate.of(2021, 4, 20));
		reservaHabitacion.setNumeroTarjeta("1111111111111111");;
		reservaHabitacion.setCvc("333");
		reservaHabitacion.setPrecioFinal(100.0);

		//Deberá devolver el hotel
		given(this.reservaHabitacionService.findReservaHabitacionById(TEST_RESERVAHABITACION_ID)).willReturn(reservaHabitacion);

	}
	

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/hoteles/{hotelId}/{nhabitacion}/reservaHabitacion/new",2,123)).andExpect(status().isOk()).andExpect(model().attributeExists("reservaHabitacion"))
				.andExpect(view().name("reservaHabitacion/createReservaHabitacionForm"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/hoteles/{hotelId}/{nhabitacion}/reservaHabitacion/new",2,123)
							.with(csrf())
							.param("entrada", "2021/10/13")
							.param("salida", "2021/10/17")
							.param("numeroTarjeta", "1111111111111111")
							.param("cvc", "333"))
							.andExpect(status().is2xxSuccessful());
	}
	
	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/hoteles/{hotelId}/{nhabitacion}/reservaHabitacion/new",2,123)
							.with(csrf())
							//.param("fechaReserva", "2021/02/06")
							.param("entrada", "2021/10/13")
							.param("salida", "2021/10/17")
							.param("numeroTarjeta", "")
							.param("cvc", "333"))
							//.param("precioFinal", "100.0"))
				.andExpect(status().isOk())
				//.andExpect(model().attributeHasErrors("reservaHabitacion"))
				//.andExpect(model().attributeHasFieldErrors("reservaHabitacion","numeroTarjeta"))
				.andExpect(model().attributeDoesNotExist("reservaHabitacion"))
				.andExpect(view().name("reservaHabitacion/createReservaHabitacionForm"));
	}
	
     @WithMockUser(value = "spring")
	@Test
	void testShowReservaHabitacion() throws Exception {
		mockMvc.perform(get("/hoteles/{hotelId}/{nhabitacion}/reservaHabitacion/{reservaHabitacionId}",2,123,TEST_RESERVAHABITACION_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("reservaHabitacion", hasProperty("fechaReserva", is(LocalDate.now()))))
				.andExpect(model().attribute("reservaHabitacion", hasProperty("entrada", is(LocalDate.of(2021, 4, 15)))))
				.andExpect(model().attribute("reservaHabitacion", hasProperty("salida",is(LocalDate.of(2021, 4, 20)))))
				.andExpect(model().attribute("reservaHabitacion", hasProperty("numeroTarjeta", is("1111111111111111"))))
				.andExpect(model().attribute("reservaHabitacion", hasProperty("cvc", is("333"))))
				.andExpect(model().attribute("reservaHabitacion", hasProperty("precioFinal", is(100.0))))
				.andExpect(view().name("reservaHabitacion/reservaHabitacionDetails"));
	}
	
	
}
