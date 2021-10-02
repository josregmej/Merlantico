package org.springframework.samples.merlantico.web;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.merlantico.model.ReservaVuelo;
import org.springframework.samples.merlantico.model.User;
import org.springframework.samples.merlantico.model.Vuelo;
import org.springframework.samples.merlantico.service.ReservaVueloService;
import org.springframework.samples.merlantico.service.UserService;
import org.springframework.samples.merlantico.service.VueloService;
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
@RequestMapping("/vuelos/{vueloId}")
public class ReservaVueloController {

	private ReservaVueloService reservaVueloService;
	private VueloService vueloService;
	private UserService userService;
	private static final String VIEWS_RESERVAVUELO_CREATE_FORM = "reservaVuelo/createReservaVueloForm";
	
	@Autowired
	public ReservaVueloController(final ReservaVueloService reservaVueloService,final VueloService vueloService, final UserService userService) {
		this.reservaVueloService = reservaVueloService;
		this.vueloService=vueloService;
		this.userService= userService;
	}

	@GetMapping(value = "reservaVuelo/new")
	public String initCreationForm(Map<String, Object> model) {
		ReservaVuelo reservaVuelo = new ReservaVuelo();
		model.put("reservaVuelo", reservaVuelo);
		return VIEWS_RESERVAVUELO_CREATE_FORM;
	}

	@PostMapping(value = "reservaVuelo/new")
	public String processCreationForm(@PathVariable("vueloId") int vueloId,
			@Valid ReservaVuelo reservaVuelo, BindingResult result, Map<String, Object> model) {
		reservaVuelo = this.reservaVueloService.createReservaVuelo(reservaVuelo);
				
		
		if (result.hasErrors()) {	
			model.put("reservaVuelo", reservaVuelo);
			return VIEWS_RESERVAVUELO_CREATE_FORM;
		} else {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDetails userDetails = null;
			if (principal instanceof UserDetails) {
				userDetails = (UserDetails) principal;
				}
			
			String userName = userDetails.getUsername();
			this.reservaVueloService.asignarReserva(reservaVuelo,vueloId,userName);			
			return "redirect:"+reservaVuelo.getId();
		}
	}
	
	@GetMapping("reservaVuelo/{reservaVueloId}")
	public ModelAndView showReservaVuelo(@PathVariable("reservaVueloId") int reservaVueloId) {
		ModelAndView mav = new ModelAndView("reservaVuelo/reservaVueloDetails");
		mav.addObject("reservaVuelo", this.reservaVueloService.findReservaVueloById(reservaVueloId));
		return mav;
	}
}
