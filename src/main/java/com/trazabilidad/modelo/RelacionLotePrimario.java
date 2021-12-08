package com.trazabilidad.modelo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.trazabilidad.primarykey.PrimaryKeyRelLotePrimario;

@Entity
@Table(name="rel_lotes_primarias")
public class RelacionLotePrimario {
	
	@EmbeddedId
	private PrimaryKeyRelLotePrimario primarykeyrelloteprimario;
	@Column(name="Cantidad")
	private float cantidad;
	@Column(name="Nombre_Primario")
	private String nombre_primario;
	
	
	
	
	
	public RelacionLotePrimario() {
		super();
	}
	public RelacionLotePrimario(PrimaryKeyRelLotePrimario primarykeyrelloteprimario, float cantidad,
			String nombreprimario) {
		super();
		this.primarykeyrelloteprimario = primarykeyrelloteprimario;
		this.cantidad = cantidad;
		this.nombre_primario = nombreprimario;
	}
	public PrimaryKeyRelLotePrimario getPrimarykeyrelloteprimario() {
		return primarykeyrelloteprimario;
	}
	public void setPrimarykeyrelloteprimario(PrimaryKeyRelLotePrimario primarykeyrelloteprimario) {
		this.primarykeyrelloteprimario = primarykeyrelloteprimario;
	}
	public float getCantidad() {
		return cantidad;
	}
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	public String getNombre_primario() {
		return nombre_primario;
	}
	public void setNombre_primario(String nombreprimario) {
		this.nombre_primario = nombreprimario;
	}
	
	

}
