package com.trazabilidad.controladores;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

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
	//Cargo el listado de productos
	@GetMapping("/ListaProductosPrimarios")
	public String ProductoPrimario(Model model) {
		
		List<ProductoPrimario> productos=productosservicios.ProductosPrimariosActivados();
		model.addAttribute("productos", productos);
		return "admin/ListaProductosPrimarios";
	}
	@GetMapping("/ProductoPrimario")
	public String NuevoProductoPrimario(Model model) {
		model.addAttribute("productoprimario", new ProductoPrimario());
		return "/admin/ProductoPrimario";
	}
	//Edito un producto
	@GetMapping("/ProductoPrimario/{id}")
	public String EditarProductoPrimario(Model model,@PathVariable long id) {
		ProductoPrimario producto=productosservicios.ProductoPrimarioId(id);
		System.out.println("producto id 1-->"+producto.getId());
		model.addAttribute("productoprimario", producto);
		return "/admin/ProductoPrimario";
	}
	
	//Añadir cantidad a un producto
	@GetMapping("/ProductoPrimario/{id}/{cantidad}/añadir")
	public String AñadirProductoPrimario(@PathVariable long id,@PathVariable float cantidad) {
		System.out.println("Valor id"+id+" Valor cantida->"+cantidad);
		ProductoPrimario producto=productosservicios.ProductoPrimarioId(id);
		producto.setCantidad(producto.getCantidad()+cantidad);
		productosservicios.guardar(producto);
		MovimientoPrimario actualizacion=new MovimientoPrimario(id,LocalDate.now(),cantidad,"","Actualizar");
		movimientoservicios.actualizar(actualizacion);
		return "redirect:/ListaProductosPrimarios";
	}
	//Desactivar producto de la vista
	@GetMapping("/ListaProductosPrimarios/{id}/eliminar")
	public String DesactivarProductoPrimario(@PathVariable Long id,Model model) {
		ProductoPrimario producto=productosservicios.ProductoPrimarioId(id);
		producto.setActivado(false);	
		productosservicios.guardar(producto);
		return "redirect:/ListaProductosPrimarios";
	}
	
	@PostMapping({"/ProductoPrimario/edit/submit","/ProductoPrimario/new/submit"})
	public String ProcesarProductoPrimario(@Valid @ModelAttribute("productoprimario") ProductoPrimario producto,BindingResult validarProducto) {
		
		if(validarProducto.hasErrors()) {
			return "/admin/ProductoPrimario";
		}else {
			
			if(producto.getId()==0) {
				producto.setActivado(true);
				producto.setFech_alta(LocalDate.now());
				productosservicios.guardar(producto);
				System.out.println(producto);
				MovimientoPrimario movimiento=new MovimientoPrimario(producto.getId(),LocalDate.now(),producto.getCantidad(),"","Alta");
				movimientoservicios.actualizar(movimiento);
			}else {
				productosservicios.guardar(producto);
			}
			
			return "redirect:/ListaProductosPrimarios";
		}
	}	
	/*
	@PostMapping("/ProductoPrimario/edit/submit")
	public String procesarFormulario(@ModelAttribute("productoprimario") ProductoPrimario producto) {
		System.out.println("producto id 2 -->"+producto.getId());
			return "redirect:/ListaProductosPrimarios";
		
		
	}
	*/
	
	}
