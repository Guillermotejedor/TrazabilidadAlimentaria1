package com.trazabilidad.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trazabilidad.modelo.RelacionRecetaPrimario;
import com.trazabilidad.primarykey.PrimaryKeyRelRecetasPrimarios;
import com.trazabilidad.repositorio.RelacionRecetaPrimarioRepositorio;

@Service
public class RelacionRecetaPrimarioServicios {
	
	@Autowired
	RelacionRecetaPrimarioRepositorio repositorio;
	
	
	public RelacionRecetaPrimario Guardar(RelacionRecetaPrimario relrecetaprimario) {
		return repositorio.save(relrecetaprimario);
	}
	
	public List<RelacionRecetaPrimario> BuscarIdReceta(long id){
		return repositorio.findByPrimaryKeyRelRecetasPrimariosIdreceta(id);
	}
	
	public void Eliminar(RelacionRecetaPrimario relrecetaprimario) {
		repositorio.delete(relrecetaprimario);
	}
	
	public RelacionRecetaPrimario BuscarPrimaryKey(PrimaryKeyRelRecetasPrimarios key) {
		return repositorio.findByPrimaryKeyRelRecetasPrimarios(key);
	}
}
