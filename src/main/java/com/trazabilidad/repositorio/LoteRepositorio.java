package com.trazabilidad.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trazabilidad.modelo.Lote;

public interface LoteRepositorio extends JpaRepository<Lote, String>{
	
	Lote findByLote(String lote);
	List<Lote> findByNombrereceta(String nombrereceta);
	@Query("select l from Lote l where l.cantidadproducida != l.cantidaddistribuida")
	List<Lote> findLotesActivos();
	
	List<Lote> findByIdreceta(long idreceta);

}
