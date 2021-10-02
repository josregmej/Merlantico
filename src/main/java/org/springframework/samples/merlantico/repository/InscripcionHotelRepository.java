package org.springframework.samples.merlantico.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.merlantico.model.InscripcionHotel;


public interface InscripcionHotelRepository extends JpaRepository<InscripcionHotel, Long> {
		
	InscripcionHotel findById(int id);
}
