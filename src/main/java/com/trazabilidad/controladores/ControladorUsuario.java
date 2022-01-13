package com.trazabilidad.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trazabilidad.modelo.Usuario;

@Controller
public class ControladorUsuario {
	
	@GetMapping("/Usuario")
	public String NuevoUsuario(Model model) {
		model.addAttribute("usuario",new Usuario());
		return "/admin/Usuario";
	}

}
