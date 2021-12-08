package com.trazabilidad.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trazabilidad.modelo.Lote;

public interface LoteRepositorio extends JpaRepository<Lote, String>{

}
