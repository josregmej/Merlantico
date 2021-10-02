package org.springframework.samples.merlantico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "inscripcionhoteles")
public class InscripcionHotel extends BaseEntity{
	
	@Column(name = "nombre")
	@NotEmpty
	private String nombre;

	@Column(name = "direccion")
	@NotEmpty
	private String direccion;
	
	@Column(name = "descripcion")
	@NotEmpty
	private String descripcion;

	@Column(name = "provincia")
	@NotEmpty
	private String provincia;
	
	@Column(name = "actividades")
	@NotEmpty
	private String actividades;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getActividades() {
		return actividades;
	}

	public void setActividades(String actividades) {
		this.actividades = actividades;
	}
	
	
	
	
}
	