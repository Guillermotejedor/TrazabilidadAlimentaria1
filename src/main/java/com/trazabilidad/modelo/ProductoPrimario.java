package com.trazabilidad.modelo;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.trazabilidad.validacion.personalizada.RegSanitario;

@Entity
@Table(name="primarios")
public class ProductoPrimario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_primarias")
	private long id;
	@NotNull
	@Size(min=2,message="Especifique el Nombre del producto")
	private String nombre_primario;
	@NotNull
	@Size(min=2,message="Especifique el Nombre de la empresa")
	private String empresa;
	@Pattern(regexp="[A-Z]{2}[0-9]{7}[A-Z]{2}",message="Debe tener este formato ES0000000XX")
	@RegSanitario
	private String reg_sanitario;
	private LocalDate fech_alta;
	private float cantidad;
	private boolean activado;
	
	
	public ProductoPrimario() {
		super();
	}





	public ProductoPrimario(long id,String nombre_primario,String empresa, String reg_sanitario,LocalDate fech_alta, float cantidad, boolean activado) {
		super();
		this.id = id;
		this.nombre_primario = nombre_primario;
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


	public  LocalDate getFech_alta() {
		return fech_alta;
	}


	public void setFech_alta(LocalDate localDate) {
		this.fech_alta = localDate;
	}


	public float getCantidad() {
		return cantidad;
	}


	public void setCantidad(float cantidad) {
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
