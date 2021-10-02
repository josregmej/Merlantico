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
import org.springframework.samples.merlantico.model.ComentarioHotel;
import org.springframework.samples.merlantico.model.Hotel;
import org.springframework.samples.merlantico.service.ComentarioHotelService;
import org.springframework.samples.merlantico.service.HotelService;
import org.springframework.samples.merlantico.web.ComentarioHotelController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=ComentarioHotelController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ComentarioHotelControllerTests {
	
	private static final int TEST_COMENTARIOHOTEL_ID = 1;
	private static final int TEST_HOTEL_ID = 1;
	
	@Autowired
	private ComentarioHotelController comentarioHotelController;

	@MockBean
	private ComentarioHotelService comentarioHotelService;
	@MockBean
	private HotelService hotelService;
        
    
	@Autowired
	private MockMvc mockMvc;

	private ComentarioHotel comentario;
	
	//Creamos el hotel
	@BeforeEach
	void setup() {

		Hotel hotelazo = new Hotel();
		hotelazo.setId(TEST_HOTEL_ID);
		hotelazo.setNombre("Hotelazo");
		hotelazo.setDireccion("Calle normal");
		hotelazo.setProvincia("Cadiz");
		hotelazo.setEstrellas(5);
		hotelazo.setTelefono("945122241");
		
		comentario=new ComentarioHotel();
		comentario.setHotel(hotelazo);
		comentario.setMensaje("Bueno");
		comentario.setId(TEST_COMENTARIOHOTEL_ID);
		comentario.setPuntuacion(6);
		//Deberá devolver el hotel
		given(this.comentarioHotelService.findComentarioById(TEST_COMENTARIOHOTEL_ID)).willReturn(new ComentarioHotel());
		given(this.hotelService.findHotelById(TEST_HOTEL_ID)).willReturn(new Hotel());
	}
	
	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/hoteles/{hotelId}/comentarios/new",TEST_HOTEL_ID)).andExpect(status().isOk()).andExpect(model().attributeExists("comentarioHotel"))
				.andExpect(view().name("hoteles/createComentarioForm"));

	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/hoteles/{hotelId}/comentarios/new",TEST_HOTEL_ID)
							.with(csrf())
							.param("mensaje", "Lujo")
							.param("puntuacion", "4")
							)
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/hoteles/"+TEST_HOTEL_ID));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/hoteles/{hotelId}/comentarios/new",TEST_HOTEL_ID)
							.with(csrf())
							.param("mensaje", "")
							.param("puntuacion", "4")
							)
		.andExpect(status().isOk())		
		.andExpect(model().attributeHasNoErrors("hotel"))
		.andExpect(model().attributeHasFieldErrors("comentarioHotel"))
		.andExpect(view().name("hoteles/createComentarioForm"));
	}
}
