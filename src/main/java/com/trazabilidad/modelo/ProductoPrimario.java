package com.trazabilidad.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="primarios")
public class ProductoPrimario {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_primarias")
	private long id;
	private String nombre_primario;
	private String empresa;
	private String reg_sanitario;
	private Date fech_alta;
	private int cantidad;
	private boolean activado;
	
	
	public ProductoPrimario() {
		super();
	}


	public ProductoPrimario(String nombre, String empresa, String reg_sanitario, Date fech_alta, int cantidad,
			boolean activado) {
		super();
		this.nombre_primario = nombre;
		this.empresa = empresa;
		this.reg_sanitario = reg_sanitario;
		this.fech_alta = fech_alta;
		this.cantidad = cantidad;
		this.activado = activado;
	}


	public long getId() {
		return id;
	}


	public void setId(long id_primarias) {
		this.id = id_primarias;
	}


	public String getNombre_primario() {
		return nombre_primario;
	}


	public void setNombre_primario(String nombre) {
		this.nombre_primario = nombre;
	}


	public String getEmpresa() {
		return empresa;
	}


	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}


	public String getReg_sanitario() {
		return reg_sanitario;
	}


	public void setReg_sanitario(String reg_sanitario) {
		this.reg_sanitario = reg_sanitario;
	}


	public Date getFech_alta() {
		return fech_alta;
	}


	public void setFech_alta(Date fech_alta) {
		this.fech_alta = fech_alta;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public boolean isActivado() {
		return activado;
	}


	public void setActivado(boolean activado) {
		this.activado = activado;
	}


	@Override
	public String toString() {
		return "ProductosPrimarios [id_primarias=" + id + ", nombre=" + nombre_primario + ", empresa=" + empresa
				+ ", reg_sanitario=" + reg_sanitario + ", fech_alta=" + fech_alta + ", cantidad=" + cantidad
				+ ", activado=" + activado + "]";
	}


}
