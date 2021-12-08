package com.trazabilidad.primarykey;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class PrimaryKeyRelLotePrimario implements Serializable {
	
	@Column(name="Lote")
	private String lote;
	@Column(name="Id_Primarias")
	private long idprimarias;
	
		
	public PrimaryKeyRelLotePrimario() {
		super();
	}

	public PrimaryKeyRelLotePrimario(String lote, long idprimarias) {
		super();
		this.lote = lote;
		this.idprimarias = idprimarias;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public long getIdprimarias() {
		return idprimarias;
	}

	public void setIdprimarias(long idprimarias) {
		this.idprimarias = idprimarias;
	}
	
	

}
