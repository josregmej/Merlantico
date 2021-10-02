package org.springframework.samples.merlantico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.merlantico.model.InscripcionHotel;
import org.springframework.samples.merlantico.repository.InscripcionHotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InscripcionHotelService {
	
	private InscripcionHotelRepository inscripcionHotelRepository;
	
	@Autowired
	public InscripcionHotelService(InscripcionHotelRepository inscripcionHotelRepository) {
		this.inscripcionHotelRepository = inscripcionHotelRepository;
	}
	
	@Transactional(readOnly = true)
	public InscripcionHotel findInscripcionHotelById(int id) throws DataAccessException {
		return inscripcionHotelRepository.findById(id);
	}
	
	@Transactional
	public void saveInscripcionHotel(InscripcionHotel inscripcionHotel) throws DataAccessException {
		inscripcionHotelRepository.save(inscripcionHotel);                
	}

	@Transactional
	public List<InscripcionHotel> findAll() throws DataAccessException {
		return inscripcionHotelRepository.findAll();
	}
	
}
