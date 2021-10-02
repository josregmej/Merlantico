package org.springframework.samples.merlantico.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.merlantico.model.ReservaActividad;

public interface ReservaActividadRepository extends JpaRepository<ReservaActividad, Integer>{

	ReservaActividad findById(int id);
	
	@Query("select a from ReservaActividad a where a.user.username like %?1")
	Collection<ReservaActividad> findReservaActividadByUserLike(String username);
}
