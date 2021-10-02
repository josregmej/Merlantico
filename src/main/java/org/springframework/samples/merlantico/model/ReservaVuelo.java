package org.springframework.samples.merlantico.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="reservasvuelo")

public class ReservaVuelo extends BaseEntity{

		@Column(name = "fechareserva")
		@DateTimeFormat(pattern = "yyyy/MM/dd")
		private LocalDate fechaReserva;
		
		@Column(name = "ida")
		@DateTimeFormat(pattern = "yyyy/MM/dd")
		@FutureOrPresent
		private LocalDate ida;
		
		@Column(name = "vuelta")
		@DateTimeFormat(pattern = "yyyy/MM/dd")
		@FutureOrPresent
		private LocalDate vuelta;
		
		@Column(name = "numeroTarjeta")
		@CreditCardNumber
		private String numeroTarjeta;
		
		@Column(name = "cvc")
		@Pattern(regexp="\\d{3}",message = "Debe contener 3 dígitos")
		private String cvc;
		
		@Column(name="precio")
		private Double precioFinal;
		
		@Column(name="codigo")
		@Pattern(regexp="^[B][I][E][N][V][E][N][I][D][O][D][P]|[D][E][S][C][U][E][N][T][O][1][0]|^$", message="El código no es válido")
		private String codigo;
		
		@ManyToOne
		@JoinColumn(name = "username")
		private User user;
		
		@OneToOne
		@JoinColumn(name = "vuelo_id")
		private Vuelo vuelo;

		public LocalDate getFechaReserva() {
			return fechaReserva;
		}

		public void setFechaReserva(LocalDate fechaReserva) {
			this.fechaReserva = fechaReserva;
		}

		public LocalDate getIda() {
			return ida;
		}

		public void setIda(LocalDate ida) {
			this.ida = ida;
		}

		public LocalDate getVuelta() {
			return vuelta;
		}

		public void setVuelta(LocalDate vuelta) {
			this.vuelta = vuelta;
		}

		public String getNumeroTarjeta() {
			return numeroTarjeta;
		}

		public void setNumeroTarjeta(String numeroTarjeta) {
			this.numeroTarjeta = numeroTarjeta;
		}

		public String getCvc() {
			return cvc;
		}

		public void setCvc(String cvc) {
			this.cvc = cvc;
		}

		public Double getPrecioFinal() {
			return precioFinal;
		}

		public void setPrecioFinal(Double precioFinal) {
			this.precioFinal = precioFinal;
		}

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Vuelo getVuelo() {
			return vuelo;
		}

		public void setVuelo(Vuelo vuelo) {
			this.vuelo = vuelo;
		}

		
		
}
