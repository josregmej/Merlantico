package org.springframework.samples.merlantico.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.merlantico.model.Hotel;
import org.springframework.samples.merlantico.repository.HotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HotelService {
	
	private HotelRepository hotelRepository;
	
	@Autowired
	public HotelService(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}
	
	@Transactional(readOnly = true)
	public Hotel findHotelById(int id) throws DataAccessException {
		return hotelRepository.findById(id);
	}
	
	@Transactional
	public void saveHotel(Hotel hotel) throws DataAccessException {
		hotelRepository.save(hotel);                
	}
	
	@Transactional
	public void deleteHotel(Hotel hotel) throws DataAccessException {
		hotelRepository.delete(hotel);            
	}

	@Transactional(readOnly = true)
	public Collection<Hotel> findByNombre(String nombre) throws DataAccessException {
		return hotelRepository.findByNombreIgnoreCaseContaining(nombre);
	}
	
	@Transactional(readOnly = true)
	public Collection<Hotel> findByProvincia(String provincia) throws DataAccessException {
		return hotelRepository.findByProvinciaIgnoreCaseContaining(provincia);
	}
	
	@Transactional(readOnly = true)
	public Collection<String> findProvincias() throws DataAccessException {
		return hotelRepository.findProvincias();
	}
}
