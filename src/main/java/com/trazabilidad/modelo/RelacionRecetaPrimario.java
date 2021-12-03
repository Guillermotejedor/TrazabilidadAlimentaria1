package com.trazabilidad.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.trazabilidad.primarykey.PrimaryKeyRelRecetasPrimarios;

@Entity
@Table(name="rel_recetas_primarios")

public class RelacionRecetaPrimario  implements Serializable {
	
	@EmbeddedId
	private PrimaryKeyRelRecetasPrimarios primaryKeyRelRecetasPrimarios;
	
	@Column(name="Cantidad")
	private float cantidad;
	@ManyToOne
	@MapsId("idreceta")
	@JoinColumn(name = "id_receta")
	Receta activo;
	
	
	@ManyToOne
	@MapsId("idreceta")
	@JoinColumn(name = "id_receta")
	Receta receta;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
	@MapsId("idprimarias")
	@JoinColumn(name = "id_primarias",insertable=false,updatable=false)
	ProductoPrimario producto;
	
	
	public RelacionRecetaPrimario() {
		super();
	}



	public RelacionRecetaPrimario(PrimaryKeyRelRecetasPrimarios primaryKeyRelRecetasPrimarios, float cantidad) {
		super();
		this.primaryKeyRelRecetasPrimarios = primaryKeyRelRecetasPrimarios;
		this.cantidad = cantidad;
	}



	public RelacionRecetaPrimario(PrimaryKeyRelRecetasPrimarios primaryKeyRelRecetasPrimarios, float cantidad,
			Receta receta, ProductoPrimario producto) {
		super();
		this.primaryKeyRelRecetasPrimarios = primaryKeyRelRecetasPrimarios;
		this.cantidad = cantidad;
		this.receta = receta;
		this.producto = producto;
	}



	public PrimaryKeyRelRecetasPrimarios getPrimaryKeyRelRecetasPrimarios() {
		return primaryKeyRelRecetasPrimarios;
	}

	public void setPrimaryKeyRelRecetasPrimarios(PrimaryKeyRelRecetasPrimarios primaryKeyRelRecetasPrimarios) {
		this.primaryKeyRelRecetasPrimarios = primaryKeyRelRecetasPrimarios;
	}

	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	public ProductoPrimario getProducto() {
		return producto;
	}

	public void setProducto(ProductoPrimario producto) {
		this.producto = producto;
	}


	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}



	@Override
	public int hashCode() {
		return Objects.hash(cantidad, primaryKeyRelRecetasPrimarios, producto, receta);
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
				&& Objects.equals(primaryKeyRelRecetasPrimarios, other.primaryKeyRelRecetasPrimarios)
				&& Objects.equals(producto, other.producto) && Objects.equals(receta, other.receta);
	}



	

}
