package com.trazabilidad.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trazabilidad.modelo.ProductoPrimario;
import com.trazabilidad.modelo.Receta;
import com.trazabilidad.modelo.Lote;
import com.trazabilidad.servicios.LoteServicios;
import com.trazabilidad.servicios.ProductosPrimariosServicios;
import com.trazabilidad.servicios.RecetaServicios;

@Controller
public class ControladorPrincipal {
	
	@Autowired
	ProductosPrimariosServicios productosservicios;
	
	@Autowired
	RecetaServicios	recetaservicios;
	@Autowired
	LoteServicios loteservicios;
	
	
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
	
	//Cargo el listado de recetas
	@GetMapping("/ListaLotes")
	public String Lote(Model model) {
		List<Lote> lotes=loteservicios.LotesActivos();
		model.addAttribute("lotes", lotes);		
		return "ListaLotes";
	}
	
	@GetMapping("/Trazabilidad")
	public String Trazabilidad(Model model) {
		List<Receta> recetas=recetaservicios.RecetasActivadas();
		for(Receta receta:recetas) {
			System.out.println("Nombre receta "+receta.getNombrereceta());
			for(Lote lote:receta.getLotes()) {
				System.out.println("Lote "+lote.getLote());
			}
		}
		model.addAttribute("recetas", recetas);
		return "Trazabilidad";
	}
}
