package org.springframework.samples.merlantico.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;


@Entity
@Table(name = "hoteles")
public class Hotel extends BaseEntity{
	
	@Column(name = "nombre")
	@NotEmpty
	private String nombre;

	@Column(name = "direccion")
	@NotEmpty
	private String direccion;
	
	@Column(name = "estrellas")
	@Range(min=1,max=5)
	private Integer estrellas;

	@Column(name = "provincia")
	@NotEmpty
	private String provincia;
	
	@Column(name = "telefono")
	@NotEmpty
	@Digits(fraction = 0, integer = 10, message = "Debe contener 9 dígitos")
	private String telefono;
	
	@Column(name = "valido")
	private Boolean valido;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
	private Set<Habitacion> habitaciones;
	
	
	public Set<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(Set<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "hotel")
	private List<ComentarioHotel> comentarios;
	
	
	public List<ComentarioHotel> getComentarios() {
		return comentarios;
	}
	
	public void setComentarios(List<ComentarioHotel> comentarios) {
		this.comentarios = comentarios;
	}

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

	public Integer getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(Integer estrellas) {
		this.estrellas = estrellas;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
    
    public Boolean getValido() {
		return valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
	}
	
}
