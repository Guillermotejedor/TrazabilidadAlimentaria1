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
	//Recuperamos todos los productos activados
	public List<ProductoPrimario> ProductosPrimariosActivados(){
		return repositorio.findByActivadoTrue();
	}
	//Recuperamos todos los Productos desactivados
	public List<ProductoPrimario> ProductoPrimarioDesactivado(){
		return repositorio.findByActivadoFalse();
	}
	
	
	//Buscamos un producto por id
	public ProductoPrimario ProductoPrimarioId(long id) throws Exception {
	
		return repositorio.findById(id);
		
	}
	
	public void Guardar(ProductoPrimario producto)   {
		
			repositorio.save(producto);
	}

	public List<ProductoPrimario> TodosProductosPrimarios(){
		return repositorio.findAll();
	}
	
	public ProductoPrimario BuscarPorNombre(String nombre) {
		return repositorio.findByNombreprimario(nombre);
	}
	
	public List<Object[]> MovientosEmpresa(long id_primarias){
		return repositorio.findMovimientoEmpresa(id_primarias);
	}
}
