package com.trazabilidad.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trazabilidad.modelo.ProductoPrimario;
import com.trazabilidad.servicios.ProductosPrimariosServicios;

@Controller
public class ControladorPrincipal {
	
	@Autowired
	ProductosPrimariosServicios productosservicios;
	
	
	@GetMapping({"/","/index"})
	public String Bienvenido() {
		return "index";
	}
	
	//Cargo el listado de productos
	@GetMapping("/ListaProductosPrimarios")
	public String ProductoPrimario(Model model) {
		
		List<ProductoPrimario> productos=productosservicios.ProductosPrimariosActivados();
		model.addAttribute("productos", productos);
		return "admin/ListaProductosPrimarios";
	}

	

}
