package com.trazabilidad.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trazabilidad.modelo.ProductoPrimario;
import com.trazabilidad.modelo.Receta;
import com.trazabilidad.servicios.ProductosPrimariosServicios;
import com.trazabilidad.servicios.RecetaServicios;

@Controller
public class ControladorPrincipal {
	
	@Autowired
	ProductosPrimariosServicios productosservicios;
	
	@Autowired
	RecetaServicios	recetaservicios;
	
	
	
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
	
	//Cargo el listado de recetas
	@GetMapping("/ListaRecetas")
	public String Receta(Model model) {
		List<Receta> recetas=recetaservicios.RecetasActivadas();
		model.addAttribute("recetas", recetas);
		model.addAttribute("mensajeerror", "");
		return "ListaRecetas";
	}
	

}
