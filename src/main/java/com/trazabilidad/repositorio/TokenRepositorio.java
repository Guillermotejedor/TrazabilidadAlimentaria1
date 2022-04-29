package com.trazabilidad.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trazabilidad.modelo.Token;

public interface TokenRepositorio extends JpaRepository <Token, String>{
	Token findByToken(String token);

}
