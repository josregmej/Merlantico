package org.springframework.samples.merlantico.web;

import java.util.Collection;
import java.util.Map; 

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.merlantico.model.ReservaActividad;
import org.springframework.samples.merlantico.model.ReservaHabitacion;
import org.springframework.samples.merlantico.model.ReservaVuelo;
import org.springframework.samples.merlantico.model.User;
import org.springframework.samples.merlantico.service.ReservaActividadService;
import org.springframework.samples.merlantico.service.ReservaHabitacionService;
import org.springframework.samples.merlantico.service.ReservaVueloService;
import org.springframework.samples.merlantico.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class UserController {

	private static final String VIEWS_USER_CREATE_OR_UPDATE_FORM = "users/createOrUpdateUserForm";
	private static final String VIEWS_USER_DELETE_EXITO= "users/exitoDelete";
	private static final String VIEW_USER_HISTORIAL = "users/historial";
	private final UserService userService;
	private final ReservaActividadService reservaActividadService;
	private final ReservaVueloService reservaVueloService;
	private final ReservaHabitacionService reservaHabitacionService;
	
	@Autowired
	public UserController(UserService userservice,ReservaActividadService reservaActividadService,ReservaHabitacionService reservaHabitacionService,ReservaVueloService reservaVueloService) {
		this.userService = userservice;
		this.reservaActividadService = reservaActividadService;
		this.reservaVueloService = reservaVueloService;
		this.reservaHabitacionService = reservaHabitacionService;
		
	}

//	@InitBinder
//	public void setAllowedFields(WebDataBinder dataBinder) {
//		dataBinder.setDisallowedFields("username");
//	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		User user = new User();
		model.put("user", user);
		return VIEWS_USER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid User user, BindingResult result, Map<String, Object> model) {
		if (result.hasErrors()) {
			model.put("user", user);
			return VIEWS_USER_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.userService.saveUser(user);
			return "redirect:/";
		}
	}
	
	@GetMapping(value = "/users/{username}/edit")
	public String initUpdateForm(@PathVariable("username") String username, ModelMap model) {
		User user = this.userService.findByUsername(username);
		model.put("user", user);
		return VIEWS_USER_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/users/{username}/edit")
	public String processUpdateUserForm(@Valid User user, BindingResult result,
			@PathVariable("username") String username, ModelMap model) {
		if (result.hasErrors()) {
			model.put("user", user);
			return VIEWS_USER_CREATE_OR_UPDATE_FORM;
		}
		else {
			if (user.getUsername().equals(username)) {
				user.setUsername(user.getUsername());
			}else {
				user.setUsername(username);
			}
			this.userService.saveUser(user);
			return "redirect:/users/{username}";
		}
	}
	
	@GetMapping("/users/{username}")
	public ModelAndView showUser(@PathVariable("username") String username) {
		ModelAndView mav = new ModelAndView("users/userDetails");
		mav.addObject("user", this.userService.findByUsername(username));
		return mav;
	}
	
	@RequestMapping(value = "/users/{username}/delete")
    public String deleteUser(@PathVariable("username") final String username, final ModelMap model) {
        User user= this.userService.findByUsername(username);
        this.userService.deleteUser(user);
        return VIEWS_USER_DELETE_EXITO;
    }
	
	@RequestMapping(value = "/users/{username}/historial")
    public String historialUser(@PathVariable("username") final String username, Map<String, Object> model) {
        Collection<ReservaHabitacion> hh = this.reservaHabitacionService.buscarReservaHabitacion(username);
    	model.put("selectionsHabitacion", hh);
        Collection<ReservaActividad> ha = this.reservaActividadService.buscarReservaActividad(username);
        model.put("selectionsActividad", ha);
        Collection<ReservaVuelo> hv = this.reservaVueloService.buscarReservaVuelo(username);
        model.put("selectionsVuelo", hv);
        return VIEW_USER_HISTORIAL;
    }
}