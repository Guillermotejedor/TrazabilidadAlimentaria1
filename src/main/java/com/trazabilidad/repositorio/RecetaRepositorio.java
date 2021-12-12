package com.trazabilidad.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trazabilidad.modelo.Receta;
@Repository
public interface RecetaRepositorio extends JpaRepository<Receta,Long>{
	List<Receta> findByActivadoTrue();
	List<Receta> findByActivadoFalse();

}
