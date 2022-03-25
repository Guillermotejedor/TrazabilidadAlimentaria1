package com.trazabilidad.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trazabilidad.modelo.MovimientoLote;
import com.trazabilidad.repositorio.MovimientoLoteRepositorio;

@Service
public class MovimientoLoteServicios {
	@Autowired
	MovimientoLoteRepositorio repositorio;
	
	
	public void Guardar(MovimientoLote lote) {
		repositorio.save(lote);
	}
	
	public MovimientoLote MovimientoPorId(long idmovimientol) {
		return repositorio.findByIdmovimientol(idmovimientol);
	}
	
	public List<MovimientoLote> MovimientoPorLote(String lote){
		return repositorio.findBylote(lote);
	}
}
