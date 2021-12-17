package com.trazabilidad.modelo;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="movimientos_lotes")
public class MovimientoLote {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id_Movimiento_L")
	private long idmovimientol;
	@Column(name="Destinatario")
	private String destinatario;
	@Column(name="Cantidad")
	private float cantidad;
	@Column(name="Lote")
	private String lote;
	@Column(name="Tipo_Movimiento")
	private String tipomovimiento;
	@Column(name="Fech_Movimiento")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate fechamovimiento;
	
	
	public MovimientoLote() {
		super();
	}


	public MovimientoLote(String destinatario, float cantidad, String lote, String tipomovimiento,
			LocalDate fechamovimiento) {
		super();
		this.destinatario = destinatario;
		this.cantidad = cantidad;
		this.lote = lote;
		this.tipomovimiento = tipomovimiento;
		this.fechamovimiento = fechamovimiento;
	}


	public String getDestinatario() {
		return destinatario;
	}


	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}


	public float getCantidad() {
		return cantidad;
	}


	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}


	public String getLote() {
		return lote;
	}


	public void setLote(String lote) {
		this.lote = lote;
	}


	public String getTipomovimiento() {
		return tipomovimiento;
	}


	public void setTipomovimiento(String tipomovimiento) {
		this.tipomovimiento = tipomovimiento;
	}


	public LocalDate getFechamovimiento() {
		return fechamovimiento;
	}


	public void setFechamovimiento(LocalDate fechamovimiento) {
		this.fechamovimiento = fechamovimiento;
	}


	@Override
	public int hashCode() {
		return Objects.hash(cantidad, destinatario, fechamovimiento, idmovimientol, lote, tipomovimiento);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimientoLote other = (MovimientoLote) obj;
		return Float.floatToIntBits(cantidad) == Float.floatToIntBits(other.cantidad)
				&& Objects.equals(destinatario, other.destinatario)
				&& Objects.equals(fechamovimiento, other.fechamovimiento) && idmovimientol == other.idmovimientol
				&& Objects.equals(lote, other.lote) && Objects.equals(tipomovimiento, other.tipomovimiento);
	}
	
	

	
	
	
	
	
}
