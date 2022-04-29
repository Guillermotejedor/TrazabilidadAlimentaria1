package com.trazabilidad.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trazabilidad.modelo.Token;
import com.trazabilidad.repositorio.TokenRepositorio;

@Service
public class TokenServicios {
	
	@Autowired
	TokenRepositorio repositorio;
	
	public void Guardar(Token token) {
		repositorio.save(token);
	}
	
	public Token TokenPorToken(String token) {
		Token Tokenobj=repositorio.findByToken(token);
		return Tokenobj;
	}
}
