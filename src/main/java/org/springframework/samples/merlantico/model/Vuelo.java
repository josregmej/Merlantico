package org.springframework.samples.merlantico.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "vuelos")
public class Vuelo extends BaseEntity {
	
	@Column(name = "billetes")
	private Integer billetes;

	@Column(name = "origen")
	@NotEmpty
	private String origen;
	
	@Column(name = "destino")
	@NotEmpty
	private String destino;
	
	@Column(name = "fechaIda")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaIda;
	
	@Column(name = "fechaVuelta")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaVuelta;
	
	@Column(name = "precio")
	private Integer precio;
	
	@Column(name = "numeroPlazas")
	private Integer numeroPlazas;
	
	@ManyToOne
	@JoinColumn(name = "compvuelo_id")
	private CompVuelos compVuelo;
	
	@ManyToMany(mappedBy = "vuelos")
	private Set<User> users;
	
	
//	@OneToOne
//	@JoinColumn(name = "reservavuelo")
//	private ReservaVuelo reservavuelo;
	
//	public ReservaVuelo getReservaVuelo() {
//		return reservavuelo;
//	}
//
//	public void setReservaVuelo(ReservaVuelo reservavuelo) {
//		this.reservavuelo = reservavuelo;
//	}
	
	
	
			
	public CompVuelos getCompVuelo() {
		return compVuelo;
	}

	public void setCompVuelo(CompVuelos compVuelo) {
		this.compVuelo = compVuelo;
	}

	public Integer getBilletes() {
		return billetes;
	}

	public void setBilletes(Integer billetes) {
		this.billetes = billetes;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public LocalDate getFechaIda() {
		return fechaIda;
	}

	public void setFechaIda(LocalDate fechaIda) {
		this.fechaIda = fechaIda;
	}

	public LocalDate getFechaVuelta() {
		return fechaVuelta;
	}

	public void setFechaVuelta(LocalDate fechaVuelta) {
		this.fechaVuelta = fechaVuelta;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	
	public Integer getNumeroPlazas() {
		return numeroPlazas;
	}

	public void setNumeroPlazas(Integer numeroPlazas) {
		this.numeroPlazas = numeroPlazas;
	}
	
	
	public Set<User> getUsersInternal() {
		if (this.users == null) {
			this.users = new HashSet<>();
		}
		return this.users;
	}

	public void setUsersInternal(Set<User> users) {
		this.users = users;
	}
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getId()).append("new", this.isNew()).append("billetes", this.getBilletes())
				.append("origen", this.origen).append("destino", this.destino).append("fechaIda", this.fechaIda).
				append("fechaVuelta", this.fechaVuelta).append("precio", this.precio).toString();
	}
}
