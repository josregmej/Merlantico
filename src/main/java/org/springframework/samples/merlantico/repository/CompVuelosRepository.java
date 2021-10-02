package org.springframework.samples.merlantico.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.merlantico.model.CompVuelos;


public interface CompVuelosRepository extends JpaRepository<CompVuelos, Integer>  {
		
	//@Query("select c from CompVuelos c where c.nombre like %?1")
	Collection<CompVuelos> findByNombreIgnoreCaseContaining(String nombre);

	
	CompVuelos findById(int id) throws DataAccessException;;
}
