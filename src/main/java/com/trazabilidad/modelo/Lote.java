package com.trazabilidad.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lotes")
public class Lote {
	@Id
	@Column(name="Lote")
	private String lote;
	@Column(name="Id_Receta")
	private long idreceta;
	@Column(name="Cantidad")
	private float cantidad;
	@Column(name="Fech_Elaboracion")
	private LocalDate fechelaboracion;
	@Column(name="Fech_Caducidad")
	private LocalDate fechcaducidad;
	
	
	
	public Lote() {
		super();
	}
	public Lote(String lote, long idreceta, float cantidad, LocalDate fechelaboracion, LocalDate fechcaducidad) {
		super();
		this.lote = lote;
		this.idreceta = idreceta;
		this.cantidad = cantidad;
		this.fechelaboracion = fechelaboracion;
		this.fechcaducidad = fechcaducidad;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public long getIdreceta() {
		return idreceta;
	}
	public void setIdreceta(long idreceta) {
		this.idreceta = idreceta;
	}
	public float getCantidad() {
		return cantidad;
	}
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	public LocalDate getFechelaboracion() {
		return fechelaboracion;
	}
	public void setFechelaboracion(LocalDate fechelaboracion) {
		this.fechelaboracion = fechelaboracion;
	}
	public LocalDate getFechcaducidad() {
		return fechcaducidad;
	}
	public void setFechcaducidad(LocalDate fechcaducidad) {
		this.fechcaducidad = fechcaducidad;
	}
	
	
	

}
