package org.springframework.samples.merlantico.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.merlantico.model.Vuelo;
import org.springframework.samples.merlantico.repository.VuelosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class VueloService {
	
	private VuelosRepository vueloRepository;
	
	@Autowired
	public VueloService(VuelosRepository vuelosRepository) {
		this.vueloRepository = vuelosRepository;
	}
	
	@Transactional(readOnly = true)
	public Vuelo findVueloById(int id) throws DataAccessException {
		return vueloRepository.findById(id);
	}

	@Transactional
	public void saveVuelo(Vuelo vuelo) throws DataAccessException {
		vueloRepository.save(vuelo);                
	}

	@Transactional(readOnly = true)
	public Collection<Vuelo> findByOrigen(String origen) throws DataAccessException {
		return vueloRepository.findByOrigenIgnoreCaseContaining(origen);
	}
	
	@Transactional(readOnly = true)
	public List<Vuelo> findAllDestinos() throws DataAccessException {
		return vueloRepository.findAllDestinos();
	}
	
	@Transactional(readOnly = true)
	public Collection<Vuelo> findByDestino(String destino) throws DataAccessException {
		return vueloRepository.findByDestinoIgnoreCaseContaining(destino);
	}
}
