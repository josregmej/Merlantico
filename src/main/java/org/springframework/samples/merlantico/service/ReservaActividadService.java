package org.springframework.samples.merlantico.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.merlantico.model.Actividad;
import org.springframework.samples.merlantico.model.ReservaActividad;
import org.springframework.samples.merlantico.model.User;
import org.springframework.samples.merlantico.repository.ActividadRepository;
import org.springframework.samples.merlantico.repository.ReservaActividadRepository;
import org.springframework.samples.merlantico.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaActividadService {

	private ReservaActividadRepository reservaActividadRepository;
	private ActividadRepository actividadRepository;
	private UserRepository userRepository;
	
	
	@Autowired
	public ReservaActividadService(ReservaActividadRepository reservaActividadRepository,ActividadRepository actividadRepository, UserRepository userRepository) {
		this.reservaActividadRepository = reservaActividadRepository;
		this.actividadRepository =  actividadRepository;
		this.userRepository = userRepository;
	}
	
	@Transactional
	public void saveReservaActividad(ReservaActividad reserva) throws DataAccessException {
		reservaActividadRepository.save(reserva);                
	}
	
	@Transactional
	public void deleteReservaActividad(ReservaActividad reserva) throws DataAccessException {
		reservaActividadRepository.delete(reserva);                
	}

	@Transactional(readOnly = true)
	public ReservaActividad findReservaActividadById(int id) throws DataAccessException {
		return reservaActividadRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Collection<ReservaActividad> buscarReservaActividad(String username) throws DataAccessException {
		return reservaActividadRepository.findReservaActividadByUserLike(username);                
	}

	@Transactional
	public ReservaActividad createReservaActividad(ReservaActividad reservaActividad, int actividadId) {
		reservaActividad.setFechaReserva(LocalDate.now());
		Actividad a = this.actividadRepository.findById(actividadId);
		reservaActividad.setEntrada(a.getFecha());
		return reservaActividad;
	}

	@Transactional
	public void asignarReserva(ReservaActividad reservaActividad, String userName, int actividadId) {
		// TODO Auto-generated method stub
		User user= this.userRepository.findByUsernameLike(userName);
		Actividad a = this.actividadRepository.findById(actividadId);
		Set<User> ls = a.getUsers();
		ls.add(user);
		a.setUsers(ls);
		reservaActividad.setPrecioFinal(Double.valueOf(a.getPrecio()));
		reservaActividad.setActivdad(a);
		reservaActividad.setUser(user);
		this.saveReservaActividad(reservaActividad);
		
	}

	
	
//	@Transactional(readOnly = true)
//	public Collection<ReservaHabitacion> findByUser(String username) throws DataAccessException {
//		return reservaHabitacionRepository.findByUserLike(username);
//	}
}
