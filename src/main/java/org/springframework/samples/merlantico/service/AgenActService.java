package org.springframework.samples.merlantico.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.merlantico.model.AgenAct;
import org.springframework.samples.merlantico.repository.AgenActRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AgenActService {
	
	private AgenActRepository agenActRepository;
	
	@Autowired
	public AgenActService(AgenActRepository agenActRepository) {
		this.agenActRepository = agenActRepository;
	}
	
	@Transactional(readOnly = true)
	public AgenAct findAgenActById(int id) throws DataAccessException {
		return agenActRepository.findById(id);
	}

	@Transactional
	public void saveAgenAct(AgenAct agenAct) throws DataAccessException {
		agenActRepository.save(agenAct);                
	}

	@Transactional(readOnly = true)
	public Collection<AgenAct> findByNombre(String name) throws DataAccessException {
		return agenActRepository.findByNombreIgnoreCaseContaining(name);
	}
	
}
