package com.trazabilidad.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trazabilidad.modelo.RelacionRecetaPrimario;
import com.trazabilidad.primarykey.PrimaryKeyRelRecetasPrimarios;
@Repository
public interface RelacionRecetaPrimarioRepositorio extends JpaRepository<RelacionRecetaPrimario,PrimaryKeyRelRecetasPrimarios> {

	List<RelacionRecetaPrimario> findByPrimaryKeyRelRecetasPrimariosIdreceta(long idreceta);
}
