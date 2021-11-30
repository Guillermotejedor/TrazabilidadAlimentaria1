package com.trazabilidad.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;


import com.trazabilidad.modelo.ProductoPrimario;
import com.trazabilidad.modelo.Receta;
import com.trazabilidad.modelo.RelacionRecetaPrimario;
import com.trazabilidad.primarykey.PrimaryKeyRelRecetasPrimarios;
import com.trazabilidad.servicios.ProductosPrimariosServicios;
import com.trazabilidad.servicios.RecetaServicios;
import com.trazabilidad.servicios.RelacionRecetaPrimarioServicios;

@ControllerAdvice
@Controller
public class ControladorRecetas {
	
	private static final String AJAX_HEADER_NAME = "X-Requested-With";
    private static final String AJAX_HEADER_VALUE = "XMLHttpRequest";
	
	
	@Autowired
	ProductosPrimariosServicios productoservicios;
	@Autowired
	RecetaServicios recetaservicios;
	@Autowired
	RelacionRecetaPrimarioServicios cantidadservicios;

	
	
	public ControladorRecetas(ProductosPrimariosServicios productoservicios, RecetaServicios recetaservicios,
			RelacionRecetaPrimarioServicios cantidadservicios) {
		super();
		this.productoservicios = productoservicios;
		this.recetaservicios = recetaservicios;
		this.cantidadservicios = cantidadservicios;
	}




	@GetMapping("/Receta")
	public String  NuevaReceta(Model model) {
		Receta receta=new Receta(); //recetaservicios.RecetaPorId(4);
		//System.out.println("Nombre--"+receta.getNombrereceta());
		List<ProductoPrimario> productosactivados=productoservicios.ProductosPrimariosActivados();
		//List<RelacionRecetaPrimario> relacion=receta.getRelrecetaprimario();
		for(ProductoPrimario p:productosactivados) {
			System.out.println("VER 1 --->"+p.getNombreprimario());
		}
		//receta.setIngrediente(productosactivados);
		model.addAttribute("receta", receta);
		model.addAttribute("ingredientes", productoservicios.ProductosPrimariosActivados());
		
		return "/Receta";
	}
	
	
	
	
	@PostMapping(path = "/receta/nuevo/submit", params="addItem")
	public String AñadeProductoReceta(@ModelAttribute("receta") Receta receta,  HttpServletRequest request,Model model,
			@RequestParam("idreceta") long idreceta, @RequestParam("idproducto") long idproducto, @RequestParam("cantidad") float cantidad) {
		System.out.println("idreceta-->"+idreceta+" idproducto-->"+idproducto+" cantidad-->"+cantidad+"Receta nombre-->"+receta.getNombrereceta());
		//receta.AñadirCantidadIngrediente(new RelacionRecetaPrimario());
		receta.AñadirCantidadIngrediente(new RelacionRecetaPrimario(new PrimaryKeyRelRecetasPrimarios(70,9),cantidad));
		//List<ProductoPrimario> ingredientes=productoservicios.ProductosPrimariosActivados();
		//ProductoPrimario producto=productoservicios.ProductoPrimarioId(idproducto);
		//ingredientes.remove(producto);
		//model.addAttribute("ingredientes", ingredientes);
		//recetaservicios.Guardar(receta);
		
		if (AJAX_HEADER_VALUE.equals(request.getHeader(AJAX_HEADER_NAME))) {
            return "/Receta::#listaingredientes";
        } else {
            return "/Receta";
        }		
	}

}
