package com.trazabilidad.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trazabilidad.modelo.Receta;
import com.trazabilidad.repositorio.RecetaRepositorio;

@Service
public class RecetaServicios {
	
	@Autowired
	RecetaRepositorio  repositorio;
	
	public Receta Guardar(Receta receta) {
		return repositorio.save(receta);
	}
	
	public List<Receta> RecetasActivadas(){
		return repositorio.findByActivadoTrue();
	}
	
	public List<Receta> RecetasDesactivadas(){
		return repositorio.findByActivadoFalse();
	}

	public Receta RecetaPorId(long id) {
		return repositorio.getOne(id);
	}
	
	public void ActualizarReceta(String nombrereceta,int caducidad,long productobase,long idreceta) {
		repositorio.updateReceta(nombrereceta, caducidad, productobase, idreceta);
	}
}
