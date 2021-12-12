package com.trazabilidad.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="Fech_Elaboracion")
	private LocalDate fechelaboracion;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="Fech_Caducidad")
	private LocalDate fechcaducidad;
	@Column(name="Nombre_Receta")
	private String nombrereceta;
	
	
	@OneToMany(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="lote",referencedColumnName="lote")
	private List<RelacionLotePrimario> ingredientes;
	
	public Lote() {
		super();
	}

	
	
	public Lote(String lote, long idreceta, float cantidad, LocalDate fechelaboracion, LocalDate fechcaducidad,
			String nombrereceta) {
		super();
		this.lote = lote;
		this.idreceta = idreceta;
		this.cantidad = cantidad;
		this.fechelaboracion = fechelaboracion;
		this.fechcaducidad = fechcaducidad;
		this.nombrereceta = nombrereceta;
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
	public List<RelacionLotePrimario> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(List<RelacionLotePrimario> ingredientes) {
		this.ingredientes = ingredientes;
	}



	public String getNombrereceta() {
		return nombrereceta;
	}



	public void setNombrereceta(String nombrereceta) {
		this.nombrereceta = nombrereceta;
	}
	
	
	

}
