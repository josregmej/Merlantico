package org.springframework.samples.merlantico.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.merlantico.model.Hotel;
import org.springframework.samples.merlantico.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HotelController {
	
	private static final String VIEWS_HOTELES_CREATE_OR_UPDATE_FORM = "hoteles/createOrUpdateHotelForm";
	private final HotelService hotelService;

	@Autowired
	public HotelController(HotelService hotelService) {
			this.hotelService = hotelService;
   	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/hoteles/new")
	public String initCreationForm(Map<String, Object> model) {
		Hotel hotel = new Hotel();
		model.put("hotel", hotel);
		return VIEWS_HOTELES_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/hoteles/new")
	public String processCreationForm(@Valid Hotel hotel, BindingResult result, Map<String, Object> model) {		
		if (result.hasErrors()) {
			model.put("hotel", hotel);
			return VIEWS_HOTELES_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.hotelService.saveHotel(hotel);
			return "redirect:/hoteles/"+hotel.getId();
		}
	}
	
	@GetMapping(value = "/hoteles/{hotelId}/edit")
	public String initUpdateForm(@PathVariable("hotelId") int hotelId, ModelMap model) {
		Hotel hotel = this.hotelService.findHotelById(hotelId);
		model.put("hotel",hotel);
		return VIEWS_HOTELES_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/hoteles/{hotelId}/edit")
	public String processUpdateHotelForm(@Valid Hotel hotel, BindingResult result,
			@PathVariable("hotelId") int hotelId,ModelMap model) {
		if (result.hasErrors()) {
			model.put("hotel",hotel);
			return VIEWS_HOTELES_CREATE_OR_UPDATE_FORM;
		}
		else {
			hotel.setId(hotelId);			
			this.hotelService.saveHotel(hotel);
			return "redirect:/hoteles/{hotelId}";
		}
	}
	
	@GetMapping(value = "/hoteles/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("hotel", new Hotel()); 
		return "hoteles/findHoteles";
	}
	
	@GetMapping(value = "/hoteles/findProvincias")
	public String initFindProvinciaForm(Map<String, Object> model) {
		model.put("hotel", new Hotel()); 
		return "hoteles/findProvincias";
	}
	
	@GetMapping(value = "/hoteles/provincias")
	public String processFindProvForm(Hotel hotel, BindingResult result, Map<String, Object> model) {

		if (hotel.getProvincia() == null) {
			hotel.setProvincia("");// empty string signifies broadest possible search
		}

		Collection<Hotel> results = this.hotelService.findByProvincia(hotel.getProvincia());
		
		if (results.isEmpty()) {
				result.rejectValue("nombre", "notFound", "not found");
				return "hoteles/hotelNoEncontrado";
			}
			else if (results.size() == 1) {
				hotel = results.iterator().next();
				return "redirect:/hoteles/" + hotel.getId();
			}
			else {
				model.put("selections", results);
				return "hoteles/hotelesListProvincia";
			}
	}
	
	@GetMapping(value = "/hoteles")
	public String processFindForm(Hotel hotel, BindingResult result, Map<String, Object> model) {

		if (hotel.getNombre() == null) {
			hotel.setNombre("");
		}

		Collection<Hotel> results = this.hotelService.findByNombre(hotel.getNombre());
		Collection<String> resultsProv = this.hotelService.findProvincias();

		if (results.isEmpty()) {
				result.rejectValue("nombre", "notFound", "not found");
				return "hoteles/hotelNoEncontrado";
			}
			else if (results.size() == 1) {
				hotel = results.iterator().next();
				return "redirect:/hoteles/" + hotel.getId();
			}
			else {
				model.put("selections", results);
				model.put("provincias", resultsProv);
				return "hoteles/hotelesList";
			}	
	}
	
	@GetMapping("/hoteles/{hotelId}")
	public ModelAndView showHotel(@PathVariable("hotelId") int hotelId) {
		ModelAndView mav = new ModelAndView("hoteles/hotelDetails");
		mav.addObject("hotel", this.hotelService.findHotelById(hotelId));
		return mav;
	}
	
	@RequestMapping(value = "/hoteles/{hotelId}/delete")
	public String deleteHotel(@PathVariable("hotelId") final int hotelId, final ModelMap model) {
		Hotel hotel= this.hotelService.findHotelById(hotelId);
		this.hotelService.deleteHotel(hotel);
		return "redirect:/hoteles";
	}
}
