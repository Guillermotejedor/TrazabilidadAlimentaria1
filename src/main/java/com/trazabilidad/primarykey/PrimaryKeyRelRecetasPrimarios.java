package com.trazabilidad.primarykey;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
@Embeddable
public class PrimaryKeyRelRecetasPrimarios implements Serializable {

	@Column(name="id_primarias")
	private long idprimarias;	

	
	@Column(name="id_receta",insertable=false, updatable=false)
	public long idreceta;
	
	
	public PrimaryKeyRelRecetasPrimarios() {
		super();
	}
	public PrimaryKeyRelRecetasPrimarios(long idprimarias, long idreceta) {
		super();
		this.idprimarias = idprimarias;
		this.idreceta = idreceta;
	}
	public long getIdprimarias() {
		return idprimarias;
	}
	public void setIdprimarias(long idprimarias) {
		this.idprimarias = idprimarias;
	}
	public long getIdreceta() {
		return idreceta;
	}
	public void setIdreceta(long idreceta) {
		this.idreceta = idreceta;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idreceta, idprimarias);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrimaryKeyRelRecetasPrimarios other = (PrimaryKeyRelRecetasPrimarios) obj;
		return idreceta == other.idreceta && idprimarias == other.idprimarias;
	}
	
	
}
