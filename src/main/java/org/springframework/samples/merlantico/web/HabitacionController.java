package org.springframework.samples.merlantico.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.merlantico.model.Habitacion;
import org.springframework.samples.merlantico.model.Hotel;
import org.springframework.samples.merlantico.service.HabitacionService;
import org.springframework.samples.merlantico.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hoteles/{hotelId}")
public class HabitacionController {
	
	private static final String VIEWS_HABITACION_CREATE_OR_UPDATE_FORM = "habitaciones/createOrUpdateHabitacionForm";
	
	private final HabitacionService habitacionService;
	private final HotelService hotelService;

	@Autowired
	public HabitacionController(HabitacionService habitacionService,HotelService hotelService) {
			this.habitacionService = habitacionService;
			this.hotelService= hotelService;
   	}
	
	@GetMapping(value = "/habitaciones/new")
	public String initCreationForm(Map<String, Object> model) {
		Habitacion habitacion = new Habitacion();
		model.put("habitacion", habitacion);
		return VIEWS_HABITACION_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/habitaciones/new")
	public String processCreationForm(@PathVariable("hotelId") int hotelId, @Valid Habitacion hab, BindingResult result, Map<String, Object> model) {		
		
		if (result.hasErrors()) {
			model.put("habitacion", hab);
			return VIEWS_HABITACION_CREATE_OR_UPDATE_FORM;
		}
		else {
			
			this.habitacionService.saveHabitacion(hab,hotelId);
			return "redirect:/hoteles/"+hotelId;
		}
	}
	
	@ModelAttribute("hotel")
	public Hotel findHotel(@PathVariable("hotelId") int hotelId) {
		return this.hotelService.findHotelById(hotelId);
	}
	
	@InitBinder("hotel")
	public void initHotelBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

}
