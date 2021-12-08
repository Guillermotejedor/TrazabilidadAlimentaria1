package com.trazabilidad.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trazabilidad.modelo.RelacionLotePrimario;
import com.trazabilidad.primarykey.PrimaryKeyRelLotePrimario;

public interface RelacionLotePrimarioRepositorio extends JpaRepository<RelacionLotePrimario,PrimaryKeyRelLotePrimario> {

}
