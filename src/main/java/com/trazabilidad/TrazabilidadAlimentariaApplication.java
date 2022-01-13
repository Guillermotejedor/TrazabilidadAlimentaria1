package com.trazabilidad;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.trazabilidad.modelo.Usuario;
import com.trazabilidad.servicios.UsuarioServicios;

@SpringBootApplication
public class TrazabilidadAlimentariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrazabilidadAlimentariaApplication.class, args);
	}


	
}
