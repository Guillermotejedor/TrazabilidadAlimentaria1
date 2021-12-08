package com.trazabilidad.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trazabilidad.modelo.Lote;
import com.trazabilidad.repositorio.LoteRepositorio;
@Service
public class LoteServicios {
	
	@Autowired
	LoteRepositorio repositorio;
	
	public void Guardar(Lote lote) {
		repositorio.save(lote);
	}

}
