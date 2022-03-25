package com.trazabilidad.controladores;


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
	
		Receta receta=new Receta(); 
		model.addAttribute("receta", receta);
		model.addAttribute("ingredientes", productoservicios.ProductosPrimariosActivados());
		
		return "/Receta";
	}
	@GetMapping("/Receta/{id}")
	public String  AbrirReceta(Model model,@PathVariable Long id) {
	
		Receta receta=recetaservicios.RecetaPorId(id); 
		System.out.println("IdBase -->"+receta.getProductobase());
		model.addAttribute("receta", receta);
		model.addAttribute("ingredientes", productoservicios.ProductosPrimariosActivados());
		model.addAttribute("productobase",receta.getProductobase());
		model.addAttribute("ingredientesnom", productoservicios.ProductosPrimariosActivados());
				
		return "/Receta";
	}
	
	
	
	@PostMapping(path = "/receta/nuevo/submit", params="addItem")
	public String AñadeProductoReceta(@ModelAttribute("receta") Receta receta,  HttpServletRequest request,Model model,
			@RequestParam("idreceta") long idreceta, @RequestParam("idproducto") long idproducto, @RequestParam("cantidad") float cantidad,@RequestParam("nombre") String nombre) {
		System.out.println("idreceta-->"+idreceta+" idproducto-->"+idproducto+" cantidad-->"+cantidad+"Receta nombre-->"+receta.getNombrereceta());
		
		boolean repetido=false;
		List<RelacionRecetaPrimario> añadidos=receta.getCantidadingrediente();
		RelacionRecetaPrimario añadir=new RelacionRecetaPrimario(new PrimaryKeyRelRecetasPrimarios(idproducto,idreceta),cantidad,nombre);
		for(RelacionRecetaPrimario añadido:añadidos) {
			if(nombre.equalsIgnoreCase(añadido.getNombreprimario()))
				repetido=true;
		}
		if(repetido) {
			model.addAttribute("mensaje", "Ingradiente ya añadido");
		}else {
			añadir.setNombreprimario(nombre);
			System.out.println("añadir-->"+añadir.getNombreprimario());
			receta.AñadirCantidadIngrediente(añadir);
			
			/*List<RelacionRecetaPrimario> ver=receta.getCantidadingrediente();
			for(RelacionRecetaPrimario rela:ver) {
				System.out.println("Valors de nom -->"+rela.getNombreprimario());
			}*/
		}
		/*/
		for(RelacionRecetaPrimario ver:añadidos) {
			System.out.println("Valor de nombre -->"+ver.getNombreprimario()+" Camtidad -->"+ver.getCantidad());
		}
		*/
		model.addAttribute("productobase",receta.getProductobase());
		model.addAttribute("ingredientesnom", productoservicios.ProductosPrimariosActivados());
		model.addAttribute("ingredientes", productoservicios.ProductosPrimariosActivados());
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
		System.out.println("Valor de idreceta"+eliminar.getPrimaryKeyRelRecetasPrimarios().getIdreceta());
		Long idreceta=eliminar.getPrimaryKeyRelRecetasPrimarios().getIdreceta();
		if(!idreceta.equals(0) ) {
			System.out.println("Elimino");
			cantidadservicios.Eliminar(eliminar);
		}
		receta.EliminarCantidadIngrediente(eliminar);
		
		model.addAttribute("productobase",receta.getProductobase());
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
			model.addAttribute("productobase",receta.getProductobase());
			model.addAttribute("ingredientes", productoservicios.ProductosPrimariosActivados());
			return "/Receta";
		}else {
			try {
				
				receta.setActivado(true);
				recetaservicios.Guardar(receta);
				List<RelacionRecetaPrimario> ingredientes=receta.getCantidadingrediente();
				for(RelacionRecetaPrimario ingrediente:ingredientes) {
						
					PrimaryKeyRelRecetasPrimarios key=new PrimaryKeyRelRecetasPrimarios(ingrediente.getPrimaryKeyRelRecetasPrimarios().getIdprimarias(),receta.getIdreceta());
					ingrediente.setPrimaryKeyRelRecetasPrimarios(key);
					cantidadservicios.Guardar(ingrediente);
				}
			}catch(Exception e) {
				model.addAttribute("errorduplicado", "Nombre de la Receta ya esta dado de alta");
				model.addAttribute("productobase",receta.getProductobase());
				model.addAttribute("ingredientes", productoservicios.ProductosPrimariosActivados());
				return "/Receta";
			}
				
				return "redirect:/ListaRecetas";
		}
	}
	
	@PostMapping("/Receta/edit/submit")
	public String ActualizarReceta(@Valid Receta receta,BindingResult validacion,Model model) {
		
		if(validacion.hasErrors()) {
			model.addAttribute("productobase",receta.getProductobase());
			model.addAttribute("ingredientes", productoservicios.ProductosPrimariosActivados());
			return "/Receta";
		}else {
			
			List<RelacionRecetaPrimario> ingredientes=receta.getCantidadingrediente();
			for(RelacionRecetaPrimario ingrediente:ingredientes) {
				System.out.println("Cantidad-->"+ingrediente.getCantidad());
				cantidadservicios.Guardar(ingrediente);
				} // fin for
			
		
			recetaservicios.ActualizarReceta(receta.getNombrereceta(), receta.getCaducidad(), receta.getProductobase(),receta.getIdreceta());
			return "redirect:/ListaRecetas";
		} //fin else
	}
	
	@GetMapping("/Receta/{idreceta}/desactivar")
	public String DesactivarReceta(@PathVariable long idreceta) {
		System.out.println("Valor de idreceta "+idreceta);
		Receta receta=recetaservicios.RecetaPorId(idreceta);
		receta.setActivado(false);
		recetaservicios.Guardar(receta);
		return "redirect:/ListaRecetas";
	}
	
	@GetMapping("/RecetaDesactivadas")
	public String RecetasDesactivadas(Model model) {
		model.addAttribute("recetas",recetaservicios.RecetasDesactivadas());
		return "ListaRecetasDesactivadas";
	}
	
	@GetMapping("/ActivarReceta/{id}")
	public String ActivarReceta(@PathVariable long id) {
		Receta receta=recetaservicios.RecetaPorId(id);
		receta.setActivado(true);
		recetaservicios.Guardar(receta);
		
		return "redirect:/RecetaDesactivadas";
	}
}
