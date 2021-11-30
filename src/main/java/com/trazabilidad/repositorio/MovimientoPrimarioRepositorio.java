package com.trazabilidad.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trazabilidad.modelo.MovimientoPrimario;
@Repository
public interface MovimientoPrimarioRepositorio extends JpaRepository<MovimientoPrimario, Long>{
	MovimientoPrimario findById(long id); 

}
