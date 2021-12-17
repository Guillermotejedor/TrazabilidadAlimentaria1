package com.trazabilidad.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trazabilidad.modelo.Receta;
@Repository
public interface RecetaRepositorio extends JpaRepository<Receta,Long>{
	List<Receta> findByActivadoTrue();
	List<Receta> findByActivadoFalse();
	@Modifying
	@Transactional
	@Query("UPDATE Receta r SET r.nombrereceta = :nombrereceta,r.caducidad= :caducidad,r.productobase= :productobase WHERE r.idreceta = :idreceta")
	public void updateReceta(@Param("nombrereceta") String nombrereceta,@Param("caducidad") int caducidad,@Param("productobase") long productobase,@Param("idreceta") long idreceta);

}
