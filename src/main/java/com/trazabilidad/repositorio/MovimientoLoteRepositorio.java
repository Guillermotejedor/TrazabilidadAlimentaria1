package com.trazabilidad.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trazabilidad.modelo.MovimientoLote;
@Repository
public interface MovimientoLoteRepositorio extends JpaRepository<MovimientoLote,Long> {
	MovimientoLote findByIdmovimientol(long idmovimiento);
	List<MovimientoLote> findBylote(String lote);

}
