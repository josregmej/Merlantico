package org.springframework.samples.merlantico.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.merlantico.model.ReservaVuelo;
import org.springframework.samples.merlantico.model.User;
import org.springframework.samples.merlantico.model.Vuelo;
import org.springframework.samples.merlantico.repository.ReservaVueloRepository;
import org.springframework.samples.merlantico.repository.UserRepository;
import org.springframework.samples.merlantico.repository.VuelosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaVueloService {

	private ReservaVueloRepository reservaVueloRepository;
	private VuelosRepository vueloRepository;
	private UserRepository userRepository;
	
	private static final String BIENVENIDODP = "BIENVENIDODP";
	private static final String DESCUENTO10 = "DESCUENTO10";
	
	@Autowired
	public ReservaVueloService(ReservaVueloRepository reservaVueloRepository, VuelosRepository vueloRepository, UserRepository userRepository) {
		this.reservaVueloRepository = reservaVueloRepository;
		this.vueloRepository = vueloRepository;
		this.userRepository = userRepository;
	}
	
	@Transactional
	public void saveReservaVuelo(ReservaVuelo reserva) throws DataAccessException {
		reservaVueloRepository.save(reserva);                
	}
	
	@Transactional
	public void deleteReservaVuelo(ReservaVuelo reserva) throws DataAccessException {
		reservaVueloRepository.delete(reserva);                
	}

	@Transactional(readOnly = true)
	public ReservaVuelo findReservaVueloById(int id) throws DataAccessException {
		return reservaVueloRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Collection<ReservaVuelo> buscarReservaVuelo(String username) throws DataAccessException {
		return reservaVueloRepository.findReservaVueloByUserLike(username);                
	}

	@Transactional
	public ReservaVuelo createReservaVuelo(ReservaVuelo reservaVuelo) {
		reservaVuelo.setFechaReserva(LocalDate.now());
		return reservaVuelo;
	}

	@Transactional
	public void asignarReserva(ReservaVuelo reservaVuelo, int vueloId, String userName) {
		Vuelo v = this.vueloRepository.findById(vueloId);
		
		User user= this.userRepository.findByUsernameLike(userName);
		Set<User> ls = v.getUsers();
		ls.add(user);
		v.setUsers(ls);
		v.setNumeroPlazas(v.getNumeroPlazas()-v.getBilletes());
		if(reservaVuelo.getCodigo().equals(BIENVENIDODP)) {
			reservaVuelo.setPrecioFinal(Double.valueOf(v.getPrecio()*v.getBilletes())*0.95);
		}else if(reservaVuelo.getCodigo().equals(DESCUENTO10)) {
			reservaVuelo.setPrecioFinal(Double.valueOf(v.getPrecio()*v.getBilletes())*0.90);
		}else {
			reservaVuelo.setPrecioFinal(Double.valueOf(v.getPrecio()*v.getBilletes()));
		}
		
		reservaVuelo.setIda(v.getFechaIda());
		reservaVuelo.setVuelta(v.getFechaVuelta());
		reservaVuelo.setVuelo(v);
		reservaVuelo.setUser(user);
		this.saveReservaVuelo(reservaVuelo);
		
	}
	
//	@Transactional(readOnly = true)
//	public Collection<ReservaHabitacion> findByUser(String username) throws DataAccessException {
//		return reservaHabitacionRepository.findByUserLike(username);
//	}
}
