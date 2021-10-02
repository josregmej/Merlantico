package org.springframework.samples.merlantico.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.merlantico.model.ReservaHabitacion;

public interface ReservaHabitacionRepository extends JpaRepository<ReservaHabitacion, Integer>{
	
	ReservaHabitacion findById(int id);
	
	@Query("select h from ReservaHabitacion h where h.user.username like %?1")
	Collection<ReservaHabitacion> findReservaHabitacionByUserLike(String username);
}
