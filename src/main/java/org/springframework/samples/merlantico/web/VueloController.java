package org.springframework.samples.merlantico.web;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.merlantico.model.Vuelo;
import org.springframework.samples.merlantico.service.CompVuelosService;
import org.springframework.samples.merlantico.service.VueloService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VueloController {
	
	private static final String VIEWS_VUELOS_CREATE_OR_UPDATE_FORM = "vuelos/createOrUpdateVueloForm";
	private final VueloService vueloService;
	private final CompVuelosService compVueloService;
	
	@Autowired
	public VueloController(VueloService vueloService,CompVuelosService compVueloService) {
			this.vueloService = vueloService;
			this.compVueloService=compVueloService;
   	}
	
	@InitBinder("compVuelo")
	public void initCompVueloBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/vuelos/new")
	public String initCreationForm(Map<String, Object> model) {
		Vuelo vuelo = new Vuelo();
		model.put("vuelos", vuelo);
		return VIEWS_VUELOS_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/vuelos/new")
	public String processCreationForm(@Valid Vuelo vuelo, BindingResult result, Map<String, Object> model) {		
		if (result.hasErrors()) {
			model.put("vuelos", vuelo);
			return VIEWS_VUELOS_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.vueloService.saveVuelo(vuelo);
            return "redirect:/vuelos/"+vuelo.getId();
		}
	}
	
	@GetMapping(value = "/vuelos/{vueloId}/edit")
	public String initUpdateForm(@PathVariable("vueloId") int vueloId, ModelMap model) {
		Vuelo vuelo = this.vueloService.findVueloById(vueloId);
		model.put("vuelos",vuelo);
		return VIEWS_VUELOS_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/vuelos/{vueloId}/edit")
	public String processUpdateVueloForm(@Valid Vuelo vuelo, BindingResult result,
			@PathVariable("vueloId") int vueloId,ModelMap model) {
		if (result.hasErrors()) {
			model.put("vuelos",vuelo);
			return VIEWS_VUELOS_CREATE_OR_UPDATE_FORM;
		}
		else {
			vuelo.setId(vueloId);			
			this.vueloService.saveVuelo(vuelo);
			return "redirect:/vuelos/{vueloId}";
		}
	}
	
	@GetMapping(value = "/vuelos/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("vuelos", new Vuelo()); 
		return "vuelos/findVuelos";
	}
	
	
	@GetMapping(value = "/vuelos")
	public String processFindForm(Vuelo vuelo, BindingResult result, Map<String, Object> model) {

		if (vuelo.getOrigen() == null) {
			vuelo.setOrigen(""); // empty string signifies broadest possible search
		}

		Collection<Vuelo> results = this.vueloService.findByOrigen(vuelo.getOrigen());
		if (results.isEmpty()) {
			result.rejectValue("origen", "notFound", "not found");
			return "vuelos/vueloNoEncontrado";
		}
		else if (results.size() == 1) {
			vuelo = results.iterator().next();
			return "redirect:/vuelos/" + vuelo.getId();
		}
		else {
			model.put("selections", results);
			return "vuelos/vuelosListOrigen";
		}
	}
	
	@GetMapping("/vuelos/{vueloId}")
	public ModelAndView showVuelo(@PathVariable("vueloId") int vueloId) {
		ModelAndView mav = new ModelAndView("vuelos/vueloDetails");
		mav.addObject("vuelos", this.vueloService.findVueloById(vueloId));
		return mav;
	}
}
