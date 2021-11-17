package com.trazabilidad.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trazabilidad.modelo.ProductoPrimario;
import com.trazabilidad.repositorio.ProductoPrimarioRepositorio;

@Service
public class ProductosPrimariosServicios {
	
	@Autowired
	ProductoPrimarioRepositorio repositorio;
	
	public List<ProductoPrimario> ProductosPrimariosActivados(){
		return repositorio.findByActivadoTrue();
	}
	
	public ProductoPrimario ProductoPrimarioId(long id) {
		return repositorio.findById(id);
	}
	
	public void guardar(ProductoPrimario producto) {
		//repositorio.save(producto);
		repositorio.saveAndFlush(producto);
	}

}
