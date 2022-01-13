package com.trazabilidad.controladores;

import java.time.LocalDate;


import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.trazabilidad.modelo.MovimientoPrimario;
import com.trazabilidad.modelo.ProductoPrimario;
import com.trazabilidad.servicios.MovimientoPrimarioServicios;
import com.trazabilidad.servicios.ProductosPrimariosServicios;

@Controller
public class ControladorProductosPrimarios {
	
	@Autowired
	ProductosPrimariosServicios productosservicios;
	@Autowired
	MovimientoPrimarioServicios movimientoservicios;
	
	@GetMapping("/Modal")
	public String modal() {
		return "Modal2";
	}

	//Nuevo producto
	@GetMapping("/ProductoPrimario")
	public String NuevoProductoPrimario(Model model) {
		model.addAttribute("errorduplicado", "");
		model.addAttribute("productoprimario", new ProductoPrimario());
		return "/admin/ProductoPrimario";
	}
	//Edito un producto
	@GetMapping("/ProductoPrimario/{id}")
	public String EditarProductoPrimario(Model model,@PathVariable long id) {
		
			ProductoPrimario producto=productosservicios.ProductoPrimarioId(id);
			model.addAttribute("errorduplicado", "");
			model.addAttribute("productoprimario", producto);
		
		return "/admin/ProductoPrimario";
	}
	
	//Añadir cantidad a un producto
	@GetMapping("/ProductoPrimario/{id}/{cantidad}/añadir")
	public String AñadirProductoPrimario(@PathVariable long id,@PathVariable float cantidad)  {
		
			ProductoPrimario producto=productosservicios.ProductoPrimarioId(id);
			producto.setCantidad(producto.getCantidad()+cantidad);
			productosservicios.Guardar(producto);
			MovimientoPrimario actualizacion=new MovimientoPrimario(id,LocalDate.now(),cantidad,"","Actualizar");
			movimientoservicios.Actualizar(actualizacion);
		
		return "redirect:/ListaProductosPrimarios";
	}
	
	//Disminuir cantidad a un producto
	@GetMapping("/ProductoPrimario/{id}/{cantidad}/disminuir")
	public String DisminuirProductoPrimario(@PathVariable long id,@PathVariable float cantidad)  {
		
			
			ProductoPrimario producto=productosservicios.ProductoPrimarioId(id);
			producto.setCantidad(producto.getCantidad()-cantidad);
			productosservicios.Guardar(producto);
			MovimientoPrimario actualizacion=new MovimientoPrimario(id,LocalDate.now(),cantidad,"","Desechado");
			movimientoservicios.Actualizar(actualizacion);
		
		return "redirect:/ListaProductosPrimarios";
	}
	
	//Desactivar producto de la vista
	@GetMapping("/ListaProductosPrimarios/{id}/eliminar")
	public String DesactivarProductoPrimario(@PathVariable Long id,Model model)  {
			
		ProductoPrimario producto=productosservicios.ProductoPrimarioId(id);
		producto.setActivado(false);	
		productosservicios.Guardar(producto);
	
		return "redirect:/ListaProductosPrimarios";
	}
	//Proceso formulario producto primario
	@PostMapping({"/ProductoPrimario/edit/submit","/ProductoPrimario/new/submit"})
	public String ProcesarProductoPrimario(@Valid @ModelAttribute("productoprimario") ProductoPrimario producto,BindingResult validarProducto,Model model) throws Exception {
		
		if(validarProducto.hasErrors()) {
			return "/admin/ProductoPrimario";
		}else {
			try {
			if(producto.getId()==0) {
				producto.setActivado(true);
				producto.setFech_alta(LocalDate.now());
				productosservicios.Guardar(producto);
				System.out.println(producto);
				MovimientoPrimario movimiento=new MovimientoPrimario(producto.getId(),LocalDate.now(),producto.getCantidad(),"","Alta");
				movimientoservicios.Actualizar(movimiento);
			}else {
				MovimientoPrimario movimiento=new MovimientoPrimario(producto.getId(),LocalDate.now(),producto.getCantidad(),"","Formulario");
				movimientoservicios.Actualizar(movimiento);
				productosservicios.Guardar(producto);
			
				
			}
			}catch(Exception e) {
				
				model.addAttribute("errorduplicado", "Nombre del Producto ya dado de alta");
				model.addAttribute("productoprimario", producto);
				return "/admin/ProductoPrimario";
			}
			
			return "redirect:/ListaProductosPrimarios";
		}
	}	
	
	@GetMapping("/ActivarProductoPrimario")
	public String ActivarProductoPrimario(Model model) {
		model.addAttribute("productos", productosservicios.ProductoPrimarioDesactivado());
		return "/admin/ListaProductosPrimariosDesactivados";
	}
	
	@GetMapping("/ActivarProductoPrimario/{id}")
	public String ActivarProductoDesactivado(@PathVariable long id,Model model) throws Exception {
		ProductoPrimario producto=productosservicios.ProductoPrimarioId(id);
		producto.setActivado(true);
		productosservicios.Guardar(producto);
		model.addAttribute("productos", productosservicios.ProductoPrimarioDesactivado());
		return "/admin/ListaProductosPrimariosDesactivados";
		
	}
	
	}
