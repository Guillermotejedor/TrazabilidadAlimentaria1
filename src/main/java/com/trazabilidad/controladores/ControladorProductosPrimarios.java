package com.trazabilidad.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.trazabilidad.modelo.ProductoPrimario;
import com.trazabilidad.servicios.ProductosPrimariosServicios;

@Controller
public class ControladorProductosPrimarios {
	
	@Autowired
	ProductosPrimariosServicios productosservicios;
	
	
	//Cargo el listado de productos
	@GetMapping("/ListaProductosPrimarios")
	public String ProductoPrimario(Model model) {
		
		List<ProductoPrimario> productos=productosservicios.ProductosPrimariosActivados();
		model.addAttribute("productos", productos);
		return "admin/ListaProductosPrimarios";
	}
	//Edito un producto
	@GetMapping("/ProductoPrimario/{id}")
	public String EditarProductoPrimario(Model model,@PathVariable long id) {
		ProductoPrimario producto=productosservicios.ProductoPrimarioId(id);
		model.addAttribute("productoprimario", producto);
		return "/admin/ProductoPrimario";
	}

	@GetMapping("/ListaProductosPrimarios/{id}/eliminar")
	public String eliminar(@PathVariable Long id,Model model) {
		ProductoPrimario producto=productosservicios.ProductoPrimarioId(id);
		producto.setActivado(false);	
		productosservicios.guardar(producto);
		return "redirect:/ListaProductosPrimarios";
	}
	
	
	}
