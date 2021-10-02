package org.springframework.samples.merlantico.model;

import java.util.ArrayList; 
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set; 

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.samples.merlantico.util.DniConstraint;
import org.springframework.samples.merlantico.util.ValidPassword;


@Entity
@Table(name = "users")
public class User {
	@Id
	@NotEmpty
	String username;
	
	//@NotEmpty
	//@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message="La contraseña debe tener 8 cáracteres minimo, una letra mayúscula y una minúscula, al menos un número")
	@ValidPassword
	String password;
	
	@Column(name = "telefono")
	@NotEmpty
	@Digits(fraction = 0, integer = 10, message = "Debe contener 9 números")
	private String telefono;
	
	@Column(name = "dni")
//	@NotEmpty
//	@Pattern(regexp = "^[0-9]{8,8}[A-Za-z]$", message="El DNI debe contener 8 números y una letra mayúscula.")
	@DniConstraint
	private String dni;
		
	boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;
	
	@ManyToMany(cascade = {
			CascadeType.ALL})
	@JoinTable(
			name = "users_actividades",
			joinColumns = {@JoinColumn(name = "username")},
	        inverseJoinColumns = {@JoinColumn(name = "actividades_id")}
			)
	private Set<Actividad> actividades;
	
	@ManyToMany(cascade = {
			CascadeType.ALL})
	@JoinTable(

			name = "users_vuelos",
			joinColumns = {@JoinColumn(name = "username")},
	        inverseJoinColumns = {@JoinColumn(name = "vuelos_id")}
			)
	private Set<Vuelo> vuelos;
	@ManyToMany(cascade = {
			CascadeType.ALL})
	@JoinTable(
			name = "users_habitaciones",
			joinColumns = {@JoinColumn(name = "username")},
	        inverseJoinColumns = {@JoinColumn(name = "habitacion_id")}
			)
	private Set<Habitacion> habitaciones;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<ReservaHabitacion> reservaHabitacion;
	
	public Set<ReservaHabitacion> getReservaHabitacion() {
		return reservaHabitacion;
	}

	public void setReservaHabitacion(Set<ReservaHabitacion> reservaHabitacion) {
		this.reservaHabitacion = reservaHabitacion;
	}

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<ReservaVuelo> reservaVuelo;
	
	public Set<ReservaVuelo> getReservaVuelo() {
		return reservaVuelo;
	}

	public void setReservaVuelo(Set<ReservaVuelo> reservaVuelo) {
		this.reservaVuelo = reservaVuelo;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<ReservaActividad> reservaActividad;
	
	public Set<ReservaActividad> getReservaActividad() {
		return reservaActividad;
	}

	public void setReservaActividad(Set<ReservaActividad> reservaActividad) {
		this.reservaActividad = reservaActividad;
	}
	
	
	
	
	
	public void setActividades(Set<Actividad> actividades) {
		this.actividades = actividades;
	}

	public void setVuelos(Set<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	public void setHabitaciones(Set<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Authorities> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}

	
	protected Set<Habitacion> getHabitacionesInternal() {
		if (this.habitaciones == null) {
			this.habitaciones = new HashSet<>();
		}
		return this.habitaciones;
	}

	protected void setHabitacionesInternal(Set<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public List<Habitacion> getHabitaciones() {
		List<Habitacion> sortedHabitaciones = new ArrayList<>(getHabitacionesInternal());
		PropertyComparator.sort(sortedHabitaciones, new MutableSortDefinition("nombre", true, true));
		return Collections.unmodifiableList(sortedHabitaciones);
	}

	public void addHabitacion(Habitacion habitacion) {
		getHabitacionesInternal().add(habitacion);
	}
	
	public boolean removeHabitacion(Habitacion habitacion) {
		return getHabitacionesInternal().remove(habitacion);
	}
	
	
	
	protected Set<Actividad> getActividadesInternal() {
		if (this.actividades == null) {
			this.actividades = new HashSet<>();
		}
		return this.actividades;
	}

	protected void setActividadesInternal(Set<Actividad> actividades) {
		this.actividades = actividades;
	}

	public List<Actividad> getActividades() {
		List<Actividad> sortedActividades = new ArrayList<>(getActividadesInternal());
		PropertyComparator.sort(sortedActividades, new MutableSortDefinition("nombre", true, true));
		return Collections.unmodifiableList(sortedActividades);
	}

	public void addActividad(Actividad actividad) {
		getActividadesInternal().add(actividad);
	}
	
	public boolean removeActividad(Actividad actividad) {
		return getActividadesInternal().remove(actividad);
	}
	
	
	
	protected Set<Vuelo> getVuelosInternal() {
		if (this.vuelos == null) {
			this.vuelos = new HashSet<>();
		}
		return this.vuelos;
	}

	protected void setVuelosInternal(Set<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	public List<Vuelo> getVuelos() {
		List<Vuelo> sortedVuelo = new ArrayList<>(getVuelosInternal());
		PropertyComparator.sort(sortedVuelo, new MutableSortDefinition("origen", true, true));
		return Collections.unmodifiableList(sortedVuelo);
	}

	
	
	
	
	
	
	public boolean isNew() {
		return this.username == null;
	}
}


