package org.springframework.samples.merlantico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;


@Entity
@Table(name = "comentariosactividad")
public class ComentarioActividad extends BaseEntity{

	@Column(name = "puntuacion")
	@Range(min=1,max=10)
	private Integer puntuacion;
	
	@Column(name = "mensaje")
	@NotEmpty
	private String mensaje;

	@ManyToOne
	@JoinColumn(name = "actividad_id")
	private Actividad actividad;

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	
	
	
}
