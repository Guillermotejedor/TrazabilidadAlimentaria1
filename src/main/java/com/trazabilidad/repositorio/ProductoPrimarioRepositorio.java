package com.trazabilidad.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.trazabilidad.modelo.ProductoPrimario;

public interface ProductoPrimarioRepositorio extends JpaRepository<ProductoPrimario, Long> {
	
	List<ProductoPrimario> findByActivadoTrue();
	ProductoPrimario findById(long id);

}
