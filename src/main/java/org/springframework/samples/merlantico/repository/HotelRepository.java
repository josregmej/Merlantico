package org.springframework.samples.merlantico.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.merlantico.model.Hotel;


public interface HotelRepository extends JpaRepository<Hotel, Long> {
	
//	@Query("select u from Hotel u where u.nombre like %?1%")
	Collection<Hotel> findByNombreIgnoreCaseContaining(String nombre);
	
	
	//@Query("select u from Hotel u where u.provincia like %?1%")
	Collection<Hotel> findByProvinciaIgnoreCaseContaining(String provincia);
	
	@Query("select u.provincia from Hotel u")
	public Collection<String> findProvincias();
	
	Hotel findById(int id);
}
