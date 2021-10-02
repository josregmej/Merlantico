package org.springframework.samples.merlantico.service;


import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.merlantico.model.Habitacion;
import org.springframework.samples.merlantico.model.ReservaHabitacion;
import org.springframework.samples.merlantico.model.User;
import org.springframework.samples.merlantico.repository.HabitacionRepository;
import org.springframework.samples.merlantico.repository.ReservaHabitacionRepository;
import org.springframework.samples.merlantico.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaHabitacionService {

	private ReservaHabitacionRepository reservaHabitacionRepository;
	private HabitacionRepository habitacionRepository;
	private UserRepository userRepository;
	
	@Autowired
	public ReservaHabitacionService(ReservaHabitacionRepository reservaHabitacionRepository,HabitacionRepository habitacionRepository, UserRepository userRepository) {
		this.reservaHabitacionRepository = reservaHabitacionRepository;
		this.habitacionRepository = habitacionRepository;
		this.userRepository = userRepository;
		
	}
	
	@Transactional
	public void saveReservaHabitacion(ReservaHabitacion reserva) throws DataAccessException {
		reservaHabitacionRepository.save(reserva);                
	}
	
	@Transactional
	public void deleteReservaHabitacion(ReservaHabitacion reserva) throws DataAccessException {
		reservaHabitacionRepository.delete(reserva);                
	}

	@Transactional(readOnly = true)
	public ReservaHabitacion findReservaHabitacionById(int id) throws DataAccessException {
		return reservaHabitacionRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Collection<ReservaHabitacion> buscarReservaHabitacion(String username) throws DataAccessException {
		return reservaHabitacionRepository.findReservaHabitacionByUserLike(username);                
	}

	@Transactional
	public ReservaHabitacion createReservaHabitacion(ReservaHabitacion reservaHabitacion) {
		reservaHabitacion.setFechaReserva(LocalDate.now());
		return reservaHabitacion;
	}

	@Transactional
	public void asignarReserva(@Valid ReservaHabitacion reservaHabitacion, String userName, int nhabitacion) {
		User user= this.userRepository.findByUsernameLike(userName);
		Habitacion h = this.habitacionRepository.findByNhabitacionLike(nhabitacion);
		//Set<User> ls = h.getUsers();
		//ls.add(user);
		//h.setUsers(ls);
		h.setDisponible(false);
		reservaHabitacion.setHabitacion(h);
		reservaHabitacion.setUser(user);
		LocalDate entrada = reservaHabitacion.getEntrada();
		LocalDate salida = reservaHabitacion.getSalida();
		Double dias = (double) DAYS.between(entrada, salida);
		Double precio = dias*h.getPrecio();
		reservaHabitacion.setPrecioFinal(precio);
		this.saveReservaHabitacion(reservaHabitacion);
		habitacionRepository.save(h);
		
	}
	
//	@Transactional(readOnly = true)
//	public Collection<ReservaHabitacion> findByUser(String username) throws DataAccessException {
//		return reservaHabitacionRepository.findByUserLike(username);
//	}
}
