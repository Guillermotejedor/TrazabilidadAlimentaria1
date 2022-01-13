package com.trazabilidad.modelo;

import java.io.Serializable;

import java.util.Objects;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.Table;

import com.trazabilidad.primarykey.PrimaryKeyRelRecetasPrimarios;

@Entity
@Table(name="rel_recetas_primarios")

public class RelacionRecetaPrimario  implements Serializable {
	
	@EmbeddedId
	private PrimaryKeyRelRecetasPrimarios primaryKeyRelRecetasPrimarios;
	
	@Column(name="Cantidad")
	private float cantidad;
	@Column(name="Nombreprimario")
	private String nombreprimario;

	


	public RelacionRecetaPrimario(PrimaryKeyRelRecetasPrimarios primaryKeyRelRecetasPrimarios, float cantidad,
			String nombreprimario) {
		super();
		this.primaryKeyRelRecetasPrimarios = primaryKeyRelRecetasPrimarios;
		this.cantidad = cantidad;
		this.nombreprimario = nombreprimario;
	}


	public RelacionRecetaPrimario() {
		super();
	}






	public PrimaryKeyRelRecetasPrimarios getPrimaryKeyRelRecetasPrimarios() {
		return primaryKeyRelRecetasPrimarios;
	}

	public void setPrimaryKeyRelRecetasPrimarios(PrimaryKeyRelRecetasPrimarios primaryKeyRelRecetasPrimarios) {
		this.primaryKeyRelRecetasPrimarios = primaryKeyRelRecetasPrimarios;
	}


	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}



	public String getNombreprimario() {
		return nombreprimario;
	}



	public void setNombreprimario(String nombreprimario) {
		this.nombreprimario = nombreprimario;
	}


	

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, nombreprimario, primaryKeyRelRecetasPrimarios);//, producto, receta);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelacionRecetaPrimario other = (RelacionRecetaPrimario) obj;
		return Float.floatToIntBits(cantidad) == Float.floatToIntBits(other.cantidad)
				&& Objects.equals(nombreprimario, other.nombreprimario)
				&& Objects.equals(primaryKeyRelRecetasPrimarios, other.primaryKeyRelRecetasPrimarios);
				//&& Objects.equals(producto, other.producto) && Objects.equals(receta, other.receta);
	}

/*
	@Override
	public String toString() {
		return "RelacionRecetaPrimario [primaryKeyRelRecetasPrimarios=" + primaryKeyRelRecetasPrimarios + ", cantidad="
				+ cantidad + ", nombreprimario=" + nombreprimario +", receta=" + receta
				+ ", producto=" + producto + "]";
	}
*/

	



	

}
