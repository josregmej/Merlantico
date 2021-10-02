package org.springframework.samples.merlantico.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.merlantico.model.ReservaVuelo;

public interface ReservaVueloRepository extends JpaRepository<ReservaVuelo, Integer> {

	ReservaVuelo findById(int id);
	
	@Query("select v from ReservaVuelo v where v.user.username like %?1")
	Collection<ReservaVuelo> findReservaVueloByUserLike(String username);
	
	
	
}



