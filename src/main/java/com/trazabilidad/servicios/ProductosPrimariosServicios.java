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
	
	public void Guardar(ProductoPrimario producto) {
		//repositorio.save(producto);
		System.out.println("Guardar id -->"+producto.getId());
		repositorio.save(producto);
	}

	public List<ProductoPrimario> TodosProductosPrimarios(){
		return repositorio.findAll();
	}
}
