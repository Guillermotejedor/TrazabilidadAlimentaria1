package com.trazabilidad.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trazabilidad.modelo.Lote;

public interface LoteRepositorio extends JpaRepository<Lote, String>{
	
	Lote findByLote(String lote);
	@Query("select l from Lote l where l.cantidad > 0")
	List<Lote> findLotesActivos();

}