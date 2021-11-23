package com.trazabilidad.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trazabilidad.modelo.ProductoPrimario;

public interface ProductoPrimarioRepositorio extends JpaRepository<ProductoPrimario, Long> {
	
	List<ProductoPrimario> findByActivadoTrue();
	ProductoPrimario findById(long id) throws Exception;
	ProductoPrimario findByNombreprimario(String nombre_primario);
	@Query("select p.empresa as empresa, m.fech_ejecucion as fecha,m.tipo_modificacion as tipo_movimiento,m.cantidad as cantidad from ProductoPrimario p join MovimientoPrimario m on m.id_primarias= p.id where p.id=:id")
	List<Object[]> findMovimientoEmpresa(@Param("id") long id_primarias);
	
}
