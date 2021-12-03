package com.trazabilidad.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import com.trazabilidad.modelo.NombrePrimario;
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
	private List<NombrePrimario> nombresprimario;
	
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
		//inicializo listado de nombres ingredientes
		nombresprimario=new ArrayList<NombrePrimario>();
		Receta receta=new Receta(); 
		model.addAttribute("receta", receta);
		model.addAttribute("ingredientes", productoservicios.ProductosPrimariosActivados());
		
		return "/Receta";
	}
	
	
	
	
	@PostMapping(path = "/receta/nuevo/submit", params="addItem")
	public String AñadeProductoReceta(@ModelAttribute("receta") Receta receta,  HttpServletRequest request,Model model,
			@RequestParam("idreceta") long idreceta, @RequestParam("idproducto") long idproducto, @RequestParam("cantidad") float cantidad, @RequestParam("nombre") String nombre) {
		System.out.println("idreceta-->"+idreceta+" idproducto-->"+idproducto+" cantidad-->"+cantidad+"Receta nombre-->"+receta.getNombrereceta());
		
		nombresprimario.add(new NombrePrimario(idproducto,nombre));
		List<RelacionRecetaPrimario> añadidos=receta.getCantidadingrediente();
		RelacionRecetaPrimario añadir=new RelacionRecetaPrimario(new PrimaryKeyRelRecetasPrimarios(idproducto,idreceta),cantidad);
		if(añadidos.contains(añadir)) {
			model.addAttribute("mensaje", "Ingradiente ya añadido");
		}else {
			receta.AñadirCantidadIngrediente(añadir);
		}
		receta.setActivoingrediente(receta.getCantidadingrediente());
		model.addAttribute("productobase","");
		model.addAttribute("ingredientesnom", productoservicios.ProductosPrimariosActivados());
		if (AJAX_HEADER_VALUE.equals(request.getHeader(AJAX_HEADER_NAME))) {
            return "/Receta::#listaingredientes";
        } else {
            return "/Receta";
        }		
	}

	@PostMapping(path = "/receta/nuevo/submit", params="removeItem")
	public String EliminarProductoReceta(@ModelAttribute("receta") Receta receta,  HttpServletRequest request,Model model,
			@RequestParam("removeItem") long indice) {
		System.out.println("indice-->"+indice);
		RelacionRecetaPrimario eliminar=receta.getCantidadingrediente().get((int) indice);
		receta.EliminarCantidadIngrediente(eliminar);
		model.addAttribute("productobase","");
		model.addAttribute("ingredientesnom", productoservicios.ProductosPrimariosActivados());
		if (AJAX_HEADER_VALUE.equals(request.getHeader(AJAX_HEADER_NAME))) {
            return "/Receta::#listaingredientes";
        } else {
            return "/Receta";
        }		
	}
	
	@PostMapping("/Receta/new/submit")
	public String ProcesarReceta(@Valid Receta receta,BindingResult validar,Model model) {
		
		if(validar.hasErrors()) {
			model.addAttribute("ingredientes", productoservicios.ProductosPrimariosActivados());
			return "/Receta";
		}
		receta.setActivado(true);
		recetaservicios.Guardar(receta);
		List<RelacionRecetaPrimario> ingredientes=receta.getCantidadingrediente();
		for(RelacionRecetaPrimario ingrediente:ingredientes) {
			System.out.println("ingrediente.getPrimaryKeyRelRecetasPrimarios().getIdprimarias() ->"+ingrediente.getPrimaryKeyRelRecetasPrimarios().getIdprimarias());

			PrimaryKeyRelRecetasPrimarios key=new PrimaryKeyRelRecetasPrimarios(ingrediente.getPrimaryKeyRelRecetasPrimarios().getIdprimarias(),receta.getIdreceta());
			ingrediente.setPrimaryKeyRelRecetasPrimarios(key);
			ingrediente.setReceta(receta);
			System.out.println("receta.getIdreceta()-->"+receta.getIdreceta());
			ingrediente.setProducto(productoservicios.ProductoPrimarioId(ingrediente.getPrimaryKeyRelRecetasPrimarios().getIdprimarias()));
			cantidadservicios.Guardar(ingrediente);
		}
		List<Receta> recetas=recetaservicios.RecetasActivadas();
		model.addAttribute("recetas", recetas);
		return "ListaRecetas";
	}
}
