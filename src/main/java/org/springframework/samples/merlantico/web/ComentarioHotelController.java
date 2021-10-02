package org.springframework.samples.merlantico.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.merlantico.model.ComentarioHotel;
import org.springframework.samples.merlantico.model.Hotel;
import org.springframework.samples.merlantico.service.ComentarioHotelService;
import org.springframework.samples.merlantico.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ComentarioHotelController {
	private static final String VIEWS_COMENTARIO_FORM = "hoteles/createComentarioForm";
	private final ComentarioHotelService comentarioService;
	private final HotelService hotelService;
	
	@Autowired
	public ComentarioHotelController(ComentarioHotelService comentarioService,HotelService hotelService) {
			this.comentarioService = comentarioService;
			this.hotelService = hotelService;
   	}
	
	@ModelAttribute("hotel")
	public Hotel findHotel(@PathVariable("hotelId") int hotelId) {
		return this.hotelService.findHotelById(hotelId);
	}
	
	@InitBinder("hotel")
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "hoteles/{hotelId}/comentarios/new")
	public String initCreationForm(Map<String, Object> model) {
		ComentarioHotel comentarioHotel = new ComentarioHotel();
		model.put("comentarioHotel", comentarioHotel);
		return VIEWS_COMENTARIO_FORM;
	}
	
	@PostMapping(value = "hoteles/{hotelId}/comentarios/new")
	public String processCreationForm(@PathVariable("hotelId") int hotelId, @Valid ComentarioHotel comentarioHotel, BindingResult result,Map<String, Object> model) {		
		if (result.hasErrors()) {
			model.put("comentarioHotel", comentarioHotel);
			return VIEWS_COMENTARIO_FORM;
		}
		else {
			this.comentarioService.savec(hotelId, comentarioHotel);
            return "redirect:/hoteles/"+hotelId;
		}
	}
}
