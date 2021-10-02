package org.springframework.samples.merlantico.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.merlantico.model.Actividad;
import org.springframework.samples.merlantico.model.ComentarioActividad;
import org.springframework.samples.merlantico.service.ActividadService;
import org.springframework.samples.merlantico.service.ComentarioActividadService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ComentarioActividadController {
	
	private static final String VIEWS_COMENTARIO_FORM = "actividades/createComentarioForm";
	private final ComentarioActividadService comentarioService;
	private final ActividadService actividadService;
	
	@Autowired
	public ComentarioActividadController(ComentarioActividadService comentarioService,ActividadService actividadService) {
			this.comentarioService = comentarioService;
			this.actividadService = actividadService;
   	}
	
	@ModelAttribute("actividad")
	public Actividad findActividad(@PathVariable("actividadId") int actividadId) {
		return this.actividadService.findActividadById(actividadId);
	}
	
	@InitBinder("actividad")
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "actividades/{actividadId}/comentarios/new")
	public String initCreationForm(Map<String, Object> model) {
		ComentarioActividad comentarioActividad = new ComentarioActividad();
		model.put("comentarioActividad", comentarioActividad);
		return VIEWS_COMENTARIO_FORM;
	}
	
	@PostMapping(value = "actividades/{actividadId}/comentarios/new")
	public String processCreationForm(@PathVariable("actividadId") int actividadId, @Valid ComentarioActividad comentarioActividad, BindingResult result, Map<String, Object> model) {		
		if (result.hasErrors()) {
			model.put("comentarioActividad",comentarioActividad);
			return VIEWS_COMENTARIO_FORM;
		}
		else {
			this.comentarioService.savec(actividadId, comentarioActividad);
            return "redirect:/actividades/"+actividadId;
		}
	}


}
