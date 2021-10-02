package org.springframework.samples.merlantico.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.merlantico.model.Actividad;
import org.springframework.samples.merlantico.model.Hotel;
import org.springframework.samples.merlantico.service.ActividadService;
import org.springframework.samples.merlantico.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class HotelActividadController {
	
	
	private final HotelService hotelService;
	private final ActividadService actividadService;

	@Autowired
	public HotelActividadController(HotelService hotelService, ActividadService actividadService) {
			this.hotelService = hotelService;
			this.actividadService = actividadService;
   	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/hotelActividad/find")
	public String initFindHotelActividadForm(Map<String, Object> model) {
		model.put("hotel", new Hotel()); 
		return "hotelActividad/search";
	}
	
	@GetMapping(value = "/hotelActividad")
	public String processFindEntitiesForm(Hotel hotel, BindingResult result, Map<String, Object> model) {
		

		if (hotel.getProvincia() == null) {
			hotel.setProvincia(""); // empty string signifies broadest possible search
		}

		Collection<Hotel> resultsH = this.hotelService.findByProvincia(hotel.getProvincia());
		Collection<Actividad> resultsA = this.actividadService.findByProvincia(hotel.getProvincia());
		
		if (resultsH.isEmpty()) {
				result.rejectValue("provincia", "notFound", "not found");
				return "hotelActividad/notFound";
			}
		else {
				model.put("selectionsH", resultsH);
				model.put("selectionsA", resultsA);
				return "hotelActividad/list";
		}
	}

	
}
