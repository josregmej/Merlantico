package org.springframework.samples.merlantico.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "habitaciones")
public class Habitacion extends BaseEntity{
	
	@Column(name = "nhabitacion")
	@NotNull
	private Integer nhabitacion;
	
	@Column(name = "ncamas")
	@Range(min=1,max=5)
	private Integer ncamas;
	
	@Column(name = "precio")
	@NotNull
	private Integer precio;
	
	@Column(name = "disponible")
	private Boolean disponible;
	
	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;
	
//	@ManyToMany(mappedBy = "habitaciones")
//	private Set<User> users;
	
//	@OneToOne
//	@JoinColumn(name = "reservahabitacion")
//	private ReservaHabitacion reservahabitacion;
	
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Integer getNhabitacion() {
		return nhabitacion;
	}

	public void setNhabitacion(Integer nhabitacion) {
		this.nhabitacion = nhabitacion;
	}

	public Integer getNcamas() {
		return ncamas;
	}

	public void setNcamas(Integer ncamas) {
		this.ncamas = ncamas;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

//	public Set<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}

//	public ReservaHabitacion getReservaHabitacion() {
//		return reservahabitacion;
//	}
//
//	public void setReservaHabitacion(ReservaHabitacion reservaHabitacion) {
//		this.reservahabitacion = reservaHabitacion;
//	}
}
