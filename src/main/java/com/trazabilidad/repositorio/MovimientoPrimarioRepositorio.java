package com.trazabilidad.repositorio;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.trazabilidad.modelo.MovimientoPrimario;
@Repository
public interface MovimientoPrimarioRepositorio extends JpaRepository<MovimientoPrimario, Long>{
	MovimientoPrimario findById(long id); 
	@Query("SELECT mv FROM MovimientoPrimario mv WHERE mv.id_primarias= ?1 and mv.activo= ?2")
	MovimientoPrimario fechacompra(long id_primarias,boolean activado);
	

}
