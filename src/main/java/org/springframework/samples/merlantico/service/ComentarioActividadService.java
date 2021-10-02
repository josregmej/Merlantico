package org.springframework.samples.merlantico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.merlantico.model.Actividad;
import org.springframework.samples.merlantico.model.ComentarioActividad;
import org.springframework.samples.merlantico.repository.ActividadRepository;
import org.springframework.samples.merlantico.repository.ComentarioActividadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComentarioActividadService {

	private ComentarioActividadRepository comentarioRepository;
	private ActividadRepository actividadRepository;
	
	@Autowired
	public ComentarioActividadService(ComentarioActividadRepository comentarioRepository,ActividadRepository actividadRepository) {
		this.comentarioRepository = comentarioRepository;
		this.actividadRepository=actividadRepository;
	}
	
	@Transactional(readOnly = true)
	public ComentarioActividad findComentarioById(int id) throws DataAccessException {
		return comentarioRepository.findById(id);
	}
	
	@Transactional
	public void saveComentario(ComentarioActividad comentario) throws DataAccessException {
		comentarioRepository.save(comentario); 
		
	}
	@Transactional
	public void savec(int id, ComentarioActividad comentario) throws DataAccessException{
		Actividad a = actividadRepository.findById(id);
        a.getComentarios().add(comentario);
        comentario.setActividad(a);
        comentarioRepository.save(comentario);
	}

	
}
