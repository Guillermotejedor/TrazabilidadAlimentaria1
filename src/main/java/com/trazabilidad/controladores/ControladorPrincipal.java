package com.trazabilidad.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorPrincipal {
	
	@GetMapping({"/","/index"})
	public String Bienvenido() {
		return "index";
	}
	

	

}
