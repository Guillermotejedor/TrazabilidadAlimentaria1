package com.trazabilidad.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;


import com.trazabilidad.modelo.MovimientoPrimario;

public interface MovimientoPrimarioRepositorio extends JpaRepository<MovimientoPrimario, Long>{

}
