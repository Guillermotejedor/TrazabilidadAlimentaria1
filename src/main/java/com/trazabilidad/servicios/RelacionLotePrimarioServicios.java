package com.trazabilidad.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trazabilidad.modelo.RelacionLotePrimario;
import com.trazabilidad.repositorio.RelacionLotePrimarioRepositorio;

@Service
public class RelacionLotePrimarioServicios {
	
	@Autowired
	RelacionLotePrimarioRepositorio repositorio;
	
	public void Guardar(RelacionLotePrimario relacionloteprimario) {
		repositorio.save(relacionloteprimario);
	}

}
