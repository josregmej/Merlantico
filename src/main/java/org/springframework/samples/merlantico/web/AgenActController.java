package org.springframework.samples.merlantico.web;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.merlantico.model.AgenAct;
import org.springframework.samples.merlantico.service.AgenActService;
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
public class AgenActController {
	
	private static final String VIEWS_AGENACTS_CREATE_OR_UPDATE_FORM = "agenacts/createOrUpdateAgenActForm";
	private final AgenActService agenActService;
	
	@Autowired
	public AgenActController(AgenActService agenActService) {
			this.agenActService = agenActService;
   	}
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/agenacts/new")
	public String initCreationForm(Map<String, Object> model) {
		AgenAct agenAct = new AgenAct();
		model.put("agenact", agenAct);
		return VIEWS_AGENACTS_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/agenacts/new")
    public String processCreationForm(@Valid AgenAct agenAct, BindingResult result, Map<String, Object> model) {
        if (result.hasErrors()) {
            model.put("agenact",agenAct);
            return VIEWS_AGENACTS_CREATE_OR_UPDATE_FORM;
        }
        else {
            this.agenActService.saveAgenAct(agenAct);
            return "redirect:/agenacts/"+agenAct.getId();
        }
    }
	
	@GetMapping(value = "/agenacts/{agenactId}/edit")
	public String initUpdateForm(@PathVariable("agenactId") int agenActId, ModelMap model) {
		AgenAct agenAct = this.agenActService.findAgenActById(agenActId);
		model.put("agenact",agenAct);
		return VIEWS_AGENACTS_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/agenacts/{agenactId}/edit")
	public String processUpdateAgenActForm(@Valid AgenAct agenAct, BindingResult result,
			@PathVariable("agenactId") int agenactId,ModelMap model) {
		if (result.hasErrors()) {
			model.put("agenact",agenAct);
			return VIEWS_AGENACTS_CREATE_OR_UPDATE_FORM;
		}
		else {
			agenAct.setId(agenactId);			
			this.agenActService.saveAgenAct(agenAct);
			return "redirect:/agenacts/{agenactId}";
		}
	}
	
	@GetMapping(value = "/agenacts/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("agenact", new AgenAct()); 
		return "agenacts/findAgenActs";
	}
	
	
	@GetMapping(value = "/agenacts")
	public String processFindForm(AgenAct agenact, BindingResult result, Map<String, Object> model) {

		if (agenact.getNombre() == null) {
			agenact.setNombre(""); // empty string signifies broadest possible search
		}

		Collection<AgenAct> results = this.agenActService.findByNombre(agenact.getNombre());
		if (results.isEmpty()) {
			result.rejectValue("nombre", "notFound", "not found");
			return "agenacts/findAgenActs";
		}
		else if (results.size() == 1) {
			agenact = results.iterator().next();
			return "redirect:/agenacts/" + agenact.getId();
		}
		else {
			model.put("selections", results);
			return "agenacts/agenActsList";
		}
	}
	
	@GetMapping("/agenacts/{agenactId}")
	public ModelAndView showAgenAct(@PathVariable("agenactId") int agenactId) {
		ModelAndView mav = new ModelAndView("agenacts/agenactDetails");
		mav.addObject("agenact", this.agenActService.findAgenActById(agenactId));
		return mav;
	}
}
