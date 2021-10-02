package org.springframework.samples.merlantico.service;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.merlantico.model.User;
import org.springframework.samples.merlantico.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class UserServiceTests {                
        @Autowired
	protected UserService userService;
        //Prueba busqueda de usuario
        @Test
    	void shouldFindUserByUserName() {
    		 User user=new User();
    		 user.setUsername("pruebador");
    		 user.setPassword("Contrasen4");
    		 user.setTelefono("645985985");
    		 user.setDni("20061859V");

    		 this.userService.saveUser(user);
    		 User usuario = this.userService.findByUsername("pruebador");
    		 assertThat(usuario.getUsername()).isEqualTo(user.getUsername());
    	}
       
        //Prueba H4 Alta de cliente
        @Test
    	@Transactional
    	public void shouldInsertUser() {
    		User usuarios = this.userService.findByUsername("pruebadorInsert");

    		User user=new User();
   		 	user.setUsername("pruebador");
   		 	user.setPassword("Contrasen4");
   		 	user.setTelefono("645985985");
   		 	user.setDni("20061859V");
    		String found = user.getDni();

            this.userService.saveUser(user); 
    		
    		//Comprobamos que se ha añadido sin problemas
    		usuarios = this.userService.findByUsername("pruebador");
    		assertThat(usuarios.getDni()).isEqualTo(found);
        }
        
        //Prueba H5 baja de cliente
        @Test
    	@Transactional
    	public void shouldDeleteUser() {
    		User usuarios = this.userService.findByUsername("pruebadorDelete");
        	User user=new User();
   		 	user.setUsername("pruebador");
   		 	user.setPassword("Contrasen4");
   		 	user.setTelefono("645985985");
   		 	user.setDni("20061859V");
   		 	
   		 	this.userService.saveUser(user); 
    		this.userService.deleteUser(user);
    		usuarios = this.userService.findByUsername("pruebador");

    		assertThat(usuarios).isNull();
        }

}
