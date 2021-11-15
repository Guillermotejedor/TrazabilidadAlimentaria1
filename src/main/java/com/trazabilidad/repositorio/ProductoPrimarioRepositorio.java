package com.trazabilidad.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.trazabilidad.modelo.ProductoPrimario;

public interface ProductoPrimarioRepositorio extends CrudRepository<ProductoPrimario, Long> {
	
	List<ProductoPrimario> findByActivadoTrue();
	ProductoPrimario findById(long id);

}
