package org.springframework.samples.merlantico.web;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.merlantico.model.CompVuelos;
import org.springframework.samples.merlantico.service.CompVuelosService;
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
public class CompVuelosController {
	
	private static final String VIEWS_COMPVUELOS_CREATE_OR_UPDATE_FORM = "compvuelos/createOrUpdateCompVuelosForm";
	private final CompVuelosService compVuelosService;
	
	@Autowired
	public CompVuelosController(CompVuelosService compVuelosService) {
			this.compVuelosService = compVuelosService;
   	}
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/compvuelos/new")
	public String initCreationForm(Map<String, Object> model) {
		CompVuelos compVuelos = new CompVuelos();
		model.put("compvuelos", compVuelos);
		return VIEWS_COMPVUELOS_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/compvuelos/new")
	public String processCreationForm(@Valid CompVuelos compVuelos, BindingResult result, Map<String, Object> model) {		
		if (result.hasErrors()) {
			model.put("compvuelos", compVuelos);
			return VIEWS_COMPVUELOS_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.compVuelosService.saveCompVuelos(compVuelos);
            return "redirect:/compvuelos/" + compVuelos.getId();
		}
	}
	
	@GetMapping(value = "/compvuelos/{compVuelosId}/edit")
	public String initUpdateForm(@PathVariable("compVuelosId") int compVuelosId, ModelMap model) {
		CompVuelos compVuelos = this.compVuelosService.findCompVuelosById(compVuelosId);
		model.put("compvuelos",compVuelos);
		return VIEWS_COMPVUELOS_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/compvuelos/{compVuelosId}/edit")
	public String processUpdateAgenActForm(@Valid CompVuelos compVuelos, BindingResult result,
			@PathVariable("compVuelosId") int compVuelosId,ModelMap model) {
		if (result.hasErrors()) {
			model.put("compvuelos",compVuelos);
			return VIEWS_COMPVUELOS_CREATE_OR_UPDATE_FORM;
		}
		else {
			compVuelos.setId(compVuelosId);			
			this.compVuelosService.saveCompVuelos(compVuelos);
			return "redirect:/compvuelos/{compVuelosId}";
		}
	}
	
	@GetMapping(value = "/compvuelos/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("compvuelos", new CompVuelos()); 
		return "compvuelos/findCompVuelos";
	}
	
	
	@GetMapping(value = "/compvuelos")
	public String processFindForm(CompVuelos compvuelos, BindingResult result, Map<String, Object> model) {

		if (compvuelos.getNombre() == null) {
			compvuelos.setNombre(""); // empty string signifies broadest possible search
		}

		Collection<CompVuelos> results = this.compVuelosService.findByNombre(compvuelos.getNombre());
		if (results.isEmpty()) {
			result.rejectValue("nombre", "notFound", "not found");
			return "compvuelos/findCompVuelos";
		}
		else if (results.size() == 1) {
			compvuelos = results.iterator().next();
			return "redirect:/compvuelos/" + compvuelos.getId();
		}
		else {
			model.put("selections", results);
			return "compvuelos/compVuelosList";
		}
	}
	
	@GetMapping("/compvuelos/{compVuelosId}")
	public ModelAndView showAgenAct(@PathVariable("compVuelosId") int compvuelosId) {
		ModelAndView mav = new ModelAndView("compvuelos/compvuelosDetails");
		mav.addObject("compvuelos", this.compVuelosService.findCompVuelosById(compvuelosId));
		return mav;
	}
}
