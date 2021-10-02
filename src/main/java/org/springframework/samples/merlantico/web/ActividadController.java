package org.springframework.samples.merlantico.web;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.merlantico.model.Actividad;
import org.springframework.samples.merlantico.service.ActividadService;
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
public class ActividadController {
	
	private static final String VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM = "actividades/createOrUpdateActividadForm";
	private final ActividadService actividadService;
	
	@Autowired
	public ActividadController(ActividadService actividadService) {
			this.actividadService = actividadService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/actividades/new")
	public String initCreationForm(Map<String, Object> model) {
		Actividad actividad = new Actividad();
		model.put("actividades", actividad);
		return VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/actividades/new")
	public String processCreationForm(@Valid Actividad actividad, BindingResult result, Map<String, Object> model) {		
		if (result.hasErrors()) {
			model.put("actividades", actividad);
			return VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.actividadService.saveActividad(actividad);
            return "redirect:/actividades/"+actividad.getId();
		}
	}
	
	@GetMapping(value = "/actividades/{actividadId}/edit")
	public String initUpdateForm(@PathVariable("actividadId") int actividadId, ModelMap model) {
		Actividad actividad = this.actividadService.findActividadById(actividadId);
		model.put("actividades",actividad);
		return VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/actividades/{actividadId}/edit")
	public String processUpdateActividadForm(@Valid Actividad actividad, BindingResult result,
			@PathVariable("actividadId") int actividadId,ModelMap model) {
		if (result.hasErrors()) {
			model.put("actividades",actividad);
			return VIEWS_ACTIVIDAD_CREATE_OR_UPDATE_FORM;
		}
		else {
			actividad.setId(actividadId);			
			this.actividadService.saveActividad(actividad);
			return "redirect:/actividades/{actividadId}";
		}
	}
	
	@GetMapping(value = "/actividades/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("actividades", new Actividad()); 
		return "actividades/findActividades";
	}
	
	
	@GetMapping(value = "/actividades")
	public String processFindForm(Actividad actividad, BindingResult result, Map<String, Object> model) {

		if (actividad.getNombre() == null) {
			actividad.setNombre(""); // empty string signifies broadest possible search
		}

		Collection<Actividad> results = this.actividadService.findByNombre(actividad.getNombre());
		if (results.isEmpty()) {
			result.rejectValue("nombre", "notFound", "not found");
			return "actividades/actividadesNoEncontrada";
		}
		else if (results.size() == 1) {
			actividad = results.iterator().next();
			return "redirect:/actividades/" + actividad.getId();
		}
		else {
			model.put("selections", results);
			return "actividades/actividadesList";
		}
	}
	
	@GetMapping(value = "/actividades/findActividadesPrecio")
	public String initFindFormPrecio(Map<String, Object> model) {
		model.put("actividad", new Actividad()); 
		return "actividades/findActividadesPrecio";
	}
	
	@GetMapping(value = "/actividades/precio")
	public String processFindFormPrecio(Actividad actividad, BindingResult result, Map<String, Object> model) {

		if (actividad.getPrecio() == null) {
			actividad.setPrecio(999999); // empty string signifies broadest possible search
		}

		Collection<Actividad> results = this.actividadService.findByPrecio(actividad.getPrecio());
		if (results.isEmpty()) {
			result.rejectValue("precio", "notFound", "not found");
			return "actividades/findActividadesPrecio";
		}
		else if (results.size() == 1) {
			actividad = results.iterator().next();
			return "redirect:/actividades/" + actividad.getId();
		}
		else {
			model.put("selections", results);
			return "actividades/actividadesListPrecio";
		}
	}
	
	@GetMapping("/actividades/{actividadId}")
	public ModelAndView showActividad(@PathVariable("actividadId") int actividadId) {
		ModelAndView mav = new ModelAndView("actividades/actividadDetails");
		mav.addObject("actividades", this.actividadService.findActividadById(actividadId));
		return mav;
	}
}
