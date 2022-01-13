package com.trazabilidad.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.trazabilidad.validacion.personalizada.RegSanitario;

@Entity
@Table(name="primarios")
public class ProductoPrimario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_primarias")
	private long id;
	@Column(name="nombre_primario")
	@NotNull
	@Size(min=2,message="Especifique el Nombre del producto")
	private String nombreprimario;
	@NotNull
	@Size(min=2,message="Especifique el Nombre de la empresa")
	private String empresa;
	@Pattern(regexp="[A-Z]{2}[0-9]{7}[A-Z]{2}",message="Debe tener este formato ES0000000XX")
	@RegSanitario
	private String reg_sanitario;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fech_alta;
	@Min(value=0,message="{ProductoPrimario.cantidad}")
	private float cantidad;
	private boolean activado;
	
	@JoinColumn(name="id_primarias",referencedColumnName="id_primarias",insertable=false,updatable=false)
	@OneToMany
	private List<MovimientoPrimario> movimientos=new ArrayList<MovimientoPrimario>();
	/*
	@OneToMany(mappedBy = "producto")   
	private List<RelacionRecetaPrimario> relrecetaprimario;
	*/
	public ProductoPrimario() {
		super();
	}





	public ProductoPrimario(long id,String nombre_primario,String empresa, String reg_sanitario,LocalDate fech_alta, float cantidad, boolean activado) {
		super();
		this.id = id;
		this.nombreprimario = nombre_primario;
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


	public String getNombreprimario() {
		return nombreprimario;
	}


	public void setNombreprimario(String nombre) {
		this.nombreprimario = nombre;
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





	public List<MovimientoPrimario> getMovimientos() {
		return movimientos;
	}





	public void setMovimientos(List<MovimientoPrimario> movimientos) {
		this.movimientos = movimientos;
	}
	/*
	public List<RelacionRecetaPrimario> getRelrecetaprimario() {
		return relrecetaprimario;
	}

	public void setRelrecetaprimario(List<RelacionRecetaPrimario> relrecetaprimario) {
		this.relrecetaprimario = relrecetaprimario;
	}

*/
	@Override
	public String toString() {
		return "ProductoPrimario [id=" + id + ", nombre_primario=" + nombreprimario + ", empresa=" + empresa
				+ ", reg_sanitario=" + reg_sanitario + ", fech_alta=" + fech_alta + ", cantidad=" + cantidad
				+ ", activado=" + activado + ", movimientos=" + movimientos + "]";
	}
	
	




}
