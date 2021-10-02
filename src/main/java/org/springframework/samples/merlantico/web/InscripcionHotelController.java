package org.springframework.samples.merlantico.web;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.merlantico.model.InscripcionHotel;
import org.springframework.samples.merlantico.service.InscripcionHotelService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InscripcionHotelController {
	
	private static final String VIEWS_INSCRIPCIONES_CREATE_OR_UPDATE_FORM = "inscripcionhoteles/createOrUpdateInscripcionForm";
	private final InscripcionHotelService inscripcionHotelService;

	@Autowired
	public InscripcionHotelController(InscripcionHotelService inscripcionHotelService) {
			this.inscripcionHotelService = inscripcionHotelService;
   	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/inscripciones/new")
	public String initCreationForm(Map<String, Object> model) {
		InscripcionHotel inscripcionHotel = new InscripcionHotel();
		model.put("inscripcionHotel", inscripcionHotel);
		return VIEWS_INSCRIPCIONES_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/inscripciones/new")
	public String processCreationForm(@Valid InscripcionHotel inscripcionHotel, BindingResult result, Map<String, Object> model) {		
		if (result.hasErrors()) {
			model.put("inscripcionHotel", inscripcionHotel);
			return VIEWS_INSCRIPCIONES_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.inscripcionHotelService.saveInscripcionHotel(inscripcionHotel);
			return "inscripcionhoteles/inscripcionExito";
		}
	}
	
	@GetMapping(value = "/inscripciones")
	public String processFindForm(InscripcionHotel inscripcionHotel, BindingResult result, Map<String, Object> model) {

		if (inscripcionHotel.getNombre() == null) {
			inscripcionHotel.setNombre("");
		}

		List<InscripcionHotel> results = this.inscripcionHotelService.findAll();

		if (results.isEmpty()) {
				result.rejectValue("nombre", "notFound", "not found");
				return "hoteles/hotelNoEncontrado";
			}
			else if (results.size() == 1) {
				inscripcionHotel = results.iterator().next();
				return "redirect:/inscripciones/" + inscripcionHotel.getId();
			}
			else {
				model.put("selections", results);
				
				return "inscripcionhoteles/inscripcionList";
			}	
	}

	@GetMapping("/inscripciones/{inscripcionHotelId}")
    public ModelAndView showInscricpcionHotel(@PathVariable("inscripcionHotelId") int inscripcionHotelId) {
        ModelAndView mav = new ModelAndView("inscripcionhoteles/inscripcionDetails");
        mav.addObject("inscripcionHotel", this.inscripcionHotelService.findInscripcionHotelById(inscripcionHotelId));
        return mav;
    }
}
