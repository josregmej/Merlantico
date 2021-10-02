package org.springframework.samples.merlantico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.merlantico.model.Habitacion;

public interface HabitacionRepository  extends JpaRepository<Habitacion, Long> {

	@Query("select u from Habitacion u where u.nhabitacion like ?1")
	Habitacion findByNhabitacionLike(Integer nhabitacion);
}
