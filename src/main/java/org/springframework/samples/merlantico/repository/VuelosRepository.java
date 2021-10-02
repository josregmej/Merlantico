package org.springframework.samples.merlantico.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.merlantico.model.Vuelo;


public interface VuelosRepository extends JpaRepository<Vuelo, Integer>  {
	
	//@Query("select v from Vuelo v where v.origen like %?1")
	public Collection<Vuelo> findByOrigenIgnoreCaseContaining(String origen);
	
	//@Query("select v from Vuelo v where v.destino like %?1")
	public Collection<Vuelo> findByDestinoIgnoreCaseContaining(String destino);
	
	@Query("select v from Vuelo v")
	public List<Vuelo> findAllDestinos();
	
	Vuelo findById(int id) throws DataAccessException;
}
