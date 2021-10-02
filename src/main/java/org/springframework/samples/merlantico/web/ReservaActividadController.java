package org.springframework.samples.merlantico.web;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.merlantico.model.Actividad;
import org.springframework.samples.merlantico.model.ReservaActividad;
import org.springframework.samples.merlantico.model.User;
import org.springframework.samples.merlantico.service.ActividadService;
import org.springframework.samples.merlantico.service.ReservaActividadService;
import org.springframework.samples.merlantico.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/actividades/{actividadId}")
public class ReservaActividadController {

	private ReservaActividadService reservaActividadService;
	private ActividadService actividadService;
	private UserService userService;
	private static final String VIEWS_RESERVAACTIVIDAD_CREATE_FORM = "reservaActividad/createReservaActividadForm";
	
	@Autowired
	public ReservaActividadController(final ReservaActividadService reservaActividadService,final ActividadService actividadService, final UserService userService) {
		this.reservaActividadService = reservaActividadService;
		this.actividadService=actividadService;
		this.userService= userService;
	}

	@GetMapping(value = "reservaActividad/new")
	public String initCreationForm(Map<String, Object> model) {
		ReservaActividad reservaActividad = new ReservaActividad();
		model.put("reservaActividad", reservaActividad);
		return VIEWS_RESERVAACTIVIDAD_CREATE_FORM;
	}

	@PostMapping(value = "reservaActividad/new")
	public String processCreationForm(@PathVariable("actividadId") int actividadId, @Valid ReservaActividad reservaActividad, BindingResult result,Map<String, Object> model) {
		
		reservaActividad = this.reservaActividadService.createReservaActividad(reservaActividad,actividadId);
		
		if (result.hasErrors()) {
			model.put("reservaActividad", reservaActividad);

			return VIEWS_RESERVAACTIVIDAD_CREATE_FORM;
		} else {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDetails userDetails = null;
			if (principal instanceof UserDetails) {
				userDetails = (UserDetails) principal;
				}
			String userName = userDetails.getUsername();
			this.reservaActividadService.asignarReserva(reservaActividad, userName, actividadId);			
			return "redirect:"+reservaActividad.getId();
		}
	}
	
	@GetMapping("reservaActividad/{reservaActivdadId}")
	public ModelAndView showReservaActivdad(@PathVariable("reservaActivdadId") int reservaActividadId) {
		ModelAndView mav = new ModelAndView("reservaActividad/reservaActividadDetails");
		mav.addObject("reservaActividad", this.reservaActividadService.findReservaActividadById(reservaActividadId));
		return mav;
	}
}
