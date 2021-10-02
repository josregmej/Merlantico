package org.springframework.samples.merlantico.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.merlantico.model.Actividad;

public interface ActividadRepository extends JpaRepository<Actividad, Long>  {
	
	//@Query("select u from Actividad u where u.nombre like %?1")
	Collection<Actividad> findByNombreIgnoreCaseContaining(String nombre);
	
	//@Query("select u from Hotel u where u.provincia like %?1%")
	Collection<Actividad> findByProvinciaIgnoreCaseContaining(String provincia);
	
	@Query("select a from Actividad a")
	public List<Actividad> findAllActividades();

	Actividad findById(int id);

	Collection<Actividad> findByPrecioLessThanEqual(Integer precio);
}
