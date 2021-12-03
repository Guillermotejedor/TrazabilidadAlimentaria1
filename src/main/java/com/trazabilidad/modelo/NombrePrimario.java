package com.trazabilidad.modelo;

import org.springframework.stereotype.Service;

@Service
public class NombrePrimario {

	private long idprimarias;
	private String nombre;
	
	
	public NombrePrimario() {
		super();
	}

	

	public NombrePrimario(long idprimarias, String nombre) {
		super();
		this.idprimarias = idprimarias;
		this.nombre = nombre;
	}



	public long getIdprimarias() {
		return idprimarias;
	}


	public void setIdprimarias(long idprimarias) {
		this.idprimarias = idprimarias;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	
	
}
