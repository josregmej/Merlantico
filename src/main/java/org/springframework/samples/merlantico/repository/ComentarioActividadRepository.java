package org.springframework.samples.merlantico.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.merlantico.model.ComentarioActividad;


public interface ComentarioActividadRepository extends JpaRepository<ComentarioActividad, String>{

	ComentarioActividad findById(int id) throws DataAccessException;
}
