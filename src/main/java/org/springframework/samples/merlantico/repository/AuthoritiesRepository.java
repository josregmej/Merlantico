package org.springframework.samples.merlantico.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.merlantico.model.Authorities;



public interface AuthoritiesRepository extends  CrudRepository<Authorities, String>{
	
}
