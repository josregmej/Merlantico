package org.springframework.samples.merlantico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.merlantico.model.Habitacion;
import org.springframework.samples.merlantico.model.Hotel;
import org.springframework.samples.merlantico.repository.HabitacionRepository;
import org.springframework.samples.merlantico.repository.HotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HabitacionService {
	
	
	private HabitacionRepository habitacionRepository;
	private HotelRepository hotelRepository;
	
	@Autowired
	public HabitacionService(HabitacionRepository habitacionRepository, HotelRepository hotelRepository) {
		this.habitacionRepository = habitacionRepository;
		this.hotelRepository = hotelRepository;
	}
	
	@Transactional(readOnly = true)
	public Habitacion findHabitacionByNhabitacion(int id) throws DataAccessException {
		return habitacionRepository.findByNhabitacionLike(id);
	}
	
	@Transactional
	public void saveHabitacion(Habitacion habitacion,int hotelId) throws DataAccessException {
		Hotel hotel = this.hotelRepository.findById(hotelId);
		habitacion.setHotel(hotel);
		habitacion.setDisponible(true);
		habitacionRepository.save(habitacion);                
	}
	
	@Transactional
	public void deleteHabitacion(Habitacion habitacion) throws DataAccessException {
		habitacionRepository.delete(habitacion);            
	}

}
