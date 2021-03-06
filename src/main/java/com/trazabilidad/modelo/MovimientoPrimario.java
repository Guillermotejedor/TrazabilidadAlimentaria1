package com.trazabilidad.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="movimientos_primarios")
public class MovimientoPrimario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_movimiento_p")
	private long id;
	
	private long id_primarias;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fech_ejecucion;
	private float cantidad;
	private String lote;
	private String tipo_modificacion;
	private LocalDate fech_compra;
	private boolean activo;
	/*
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_primarias", nullable=true)
	private ProductoPrimario ProductoPrimario;
	*/
	public MovimientoPrimario() {
		super();
	}

	public MovimientoPrimario(long id_primarias, LocalDate fech_ejecucion, float cantidad, String lote,
			String tipomodificacion) {
		super();
		this.id_primarias = id_primarias;
		this.fech_ejecucion = fech_ejecucion;
		this.cantidad = cantidad;
		this.lote = lote;
		this.tipo_modificacion = tipomodificacion;
	}
	
	

	public MovimientoPrimario(long id_primarias, LocalDate fech_ejecucion, float cantidad, String lote,
			String tipo_modificacion, LocalDate fech_compra, boolean activo) {
		super();
		this.id_primarias = id_primarias;
		this.fech_ejecucion = fech_ejecucion;
		this.cantidad = cantidad;
		this.lote = lote;
		this.tipo_modificacion = tipo_modificacion;
		this.fech_compra = fech_compra;
		this.activo = activo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_primarias() {
		return id_primarias;
	}

	public void setId_primarias(long id_primarias) {
		this.id_primarias = id_primarias;
	}

	public LocalDate getFech_ejecucion() {
		return fech_ejecucion;
	}

	public void setFech_ejecucion(LocalDate fech_ejecucion) {
		this.fech_ejecucion = fech_ejecucion;
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

	public String getTipo_modificacion() {
		return tipo_modificacion;
	}

	public void setTipo_modificacion(String tipomodificacion) {
		this.tipo_modificacion = tipomodificacion;
	}
	
	
	
public LocalDate getFech_compra() {
		return fech_compra;
	}

	public void setFech_compra(LocalDate fech_compra) {
		this.fech_compra = fech_compra;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/*
	public ProductoPrimario getProducto() {
		return ProductoPrimario;
	}

	public void setProducto(ProductoPrimario producto) {
		this.ProductoPrimario = producto;
	}
*/
	@Override
	public String toString() {
		return "MovimientoPrimario [id=" + id + ", id_primarias=" + id_primarias + ", fech_ejecucion=" + fech_ejecucion
				+ ", cantidad=" + cantidad + ", lote=" + lote + ", tipo_modificacion=" + tipo_modificacion
				+  "]";
	}
//", ProductoPrimario=" + ProductoPrimario +
	
	
}
