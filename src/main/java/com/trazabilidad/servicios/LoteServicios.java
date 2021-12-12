package com.trazabilidad.servicios;

import java.util.List;

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
	
	public List<Lote> LotesActivos(){
		return repositorio.findLotesActivos();
	}
	
	public Lote LotePorLote(String lote) {
		return repositorio.findByLote(lote);
	}

}
