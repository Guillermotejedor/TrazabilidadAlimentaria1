package com.trazabilidad.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trazabilidad.modelo.MovimientoPrimario;
import com.trazabilidad.repositorio.MovimientoPrimarioRepositorio;

@Service
public class MovimientoPrimarioServicios {
	
	@Autowired
	MovimientoPrimarioRepositorio  repositorio;
	
	public MovimientoPrimario Actualizar(MovimientoPrimario movimiento) {
		return repositorio.save(movimiento);
	}
	
	public MovimientoPrimario BucarPorId(long id) {
		return repositorio.findById(id);
	}

}
