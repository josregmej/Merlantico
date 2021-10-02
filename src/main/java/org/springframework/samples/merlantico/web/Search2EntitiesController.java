package org.springframework.samples.merlantico.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.merlantico.model.Hotel;
import org.springframework.samples.merlantico.model.Vuelo;
import org.springframework.samples.merlantico.service.HotelService;
import org.springframework.samples.merlantico.service.VueloService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class Search2EntitiesController {
	
	private final HotelService hotelService;
	private final VueloService vueloService;
	
	@Autowired
	public Search2EntitiesController(HotelService hotelService, VueloService vueloService) {
			this.hotelService = hotelService;
			this.vueloService = vueloService;
   	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/search/find")
	public String initFindEntitiesForm(Map<String, Object> model) {
		model.put("hotel", new Hotel()); 
		return "search/findEntities";
	}
	
	@GetMapping(value = "/search")
	public String processFindEntitiesForm(Hotel hotel, BindingResult result, Map<String, Object> model) {
		

		if (hotel.getProvincia() == null) {
			hotel.setProvincia(""); // empty string signifies broadest possible search
		}

		Collection<Hotel> resultsH = this.hotelService.findByProvincia(hotel.getProvincia());
		Collection<Vuelo> resultsV = this.vueloService.findByDestino(hotel.getProvincia());
		
		if (resultsH.isEmpty()) {
				result.rejectValue("provincia", "notFound", "not found");
				return "search/notFound";
			}
		else {
				model.put("selectionsH", resultsH);
				model.put("selectionsV", resultsV);
				return "search/entitiesList";
		}
	}

}
