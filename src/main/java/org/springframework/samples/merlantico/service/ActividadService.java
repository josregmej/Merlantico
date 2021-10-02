package org.springframework.samples.merlantico.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.merlantico.model.Actividad;
import org.springframework.samples.merlantico.repository.ActividadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ActividadService {
	
	private ActividadRepository actividadRepository;
	
	@Autowired
	public ActividadService(ActividadRepository actividadRepository) {
		this.actividadRepository = actividadRepository;
	}
	
	@Transactional(readOnly = true)
	public Actividad findActividadById(int id) throws DataAccessException {
		return actividadRepository.findById(id);
	}

	@Transactional
	public void saveActividad(Actividad actividad) throws DataAccessException {
		actividadRepository.save(actividad);                
	}

	@Transactional(readOnly = true)
	public Collection<Actividad> findByNombre(String nombre) throws DataAccessException {
		return actividadRepository.findByNombreIgnoreCaseContaining(nombre);
	}
	
	@Transactional(readOnly = true)
	public List<Actividad> findAllActividades() throws DataAccessException {
		return actividadRepository.findAllActividades();
	}
    @Transactional(readOnly = true)
	public Collection<Actividad> findByPrecio(Integer precio) throws DataAccessException{
		return actividadRepository.findByPrecioLessThanEqual(precio);
	}
    @Transactional(readOnly = true)
	public Collection<Actividad> findByProvincia(String provincia) throws DataAccessException{
		return actividadRepository.findByProvinciaIgnoreCaseContaining(provincia);
	}

}
