package org.springframework.samples.merlantico.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.merlantico.model.ComentarioHotel;


public interface ComentarioHotelRepository extends JpaRepository<ComentarioHotel, String>{
	
	ComentarioHotel findById(int id) throws DataAccessException;
}
