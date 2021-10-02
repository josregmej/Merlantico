package org.springframework.samples.merlantico.repository;

import java.util.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.merlantico.model.AgenAct;


public interface AgenActRepository extends JpaRepository<AgenAct, Integer>  {

    //@Query("select a from AgenAct a where a.nombre like %?1")
    Collection<AgenAct> findByNombreIgnoreCaseContaining(String nombre);

	AgenAct findById(int id) throws DataAccessException;
}
