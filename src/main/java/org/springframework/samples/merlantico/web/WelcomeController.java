package org.springframework.samples.merlantico.web;

import java.util.Map;

import org.springframework.samples.merlantico.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    
		List<Person> persons= new ArrayList<Person>();
		Person pedcarmor = new Person();
		pedcarmor.setFirstName("Pedro Pablo");
		pedcarmor.setLastName("Carvajal Moreno");
		
		Person javhidrod = new Person();
		javhidrod.setFirstName("Javier");
		javhidrod.setLastName("Hidalgo Rodríguez");
		
		Person enrmorvaz = new Person();
		enrmorvaz.setFirstName("Enrique");
		enrmorvaz.setLastName("Moreno Vázquez");
		
		Person pedmuncif = new Person();
		pedmuncif.setFirstName("Pedro Jesús");
		pedmuncif.setLastName("Muñoz Cifuentes");
		
	
		Person josregmej = new Person();
		josregmej.setFirstName("José Francisco");
		josregmej.setLastName("Regadera Mejías");
		
		persons.add(pedcarmor);persons.add(javhidrod); persons.add(enrmorvaz);
		persons.add(pedmuncif);persons.add(josregmej);
		
		model.put("persons", persons);
		model.put("title", "Proyecto DP1");
		model.put("group","G3-14");
		
	    return "welcome";
	  }
}
