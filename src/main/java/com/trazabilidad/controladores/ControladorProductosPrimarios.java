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
	//Nuevo producto
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
		productosservicios.Guardar(producto);
		MovimientoPrimario actualizacion=new MovimientoPrimario(id,LocalDate.now(),cantidad,"","Actualizar");
		movimientoservicios.actualizar(actualizacion);
		return "redirect:/ListaProductosPrimarios";
	}
	
	//Disminuir cantidad a un producto
	@GetMapping("/ProductoPrimario/{id}/{cantidad}/disminuir")
	public String DisminuirProductoPrimario(@PathVariable long id,@PathVariable float cantidad) {
		System.out.println("Valor id"+id+" Valor cantida->"+cantidad);
		ProductoPrimario producto=productosservicios.ProductoPrimarioId(id);
		producto.setCantidad(producto.getCantidad()-cantidad);
		productosservicios.Guardar(producto);
		MovimientoPrimario actualizacion=new MovimientoPrimario(id,LocalDate.now(),cantidad,"","Desechado");
		movimientoservicios.actualizar(actualizacion);
		return "redirect:/ListaProductosPrimarios";
	}
	
	//Desactivar producto de la vista
	@GetMapping("/ListaProductosPrimarios/{id}/eliminar")
	public String DesactivarProductoPrimario(@PathVariable Long id,Model model) {
		ProductoPrimario producto=productosservicios.ProductoPrimarioId(id);
		producto.setActivado(false);	
		productosservicios.Guardar(producto);
		return "redirect:/ListaProductosPrimarios";
	}
	//Proceso formulario producto primario
	@PostMapping({"/ProductoPrimario/edit/submit","/ProductoPrimario/new/submit"})
	public String ProcesarProductoPrimario(@Valid @ModelAttribute("productoprimario") ProductoPrimario producto,BindingResult validarProducto) {
		
		if(validarProducto.hasErrors()) {
			return "/admin/ProductoPrimario";
		}else {
			System.out.println("id 1-->"+producto.getId()); 
			if(producto.getId()==0) {
				producto.setActivado(true);
				producto.setFech_alta(LocalDate.now());
				productosservicios.Guardar(producto);
				System.out.println(producto);
				MovimientoPrimario movimiento=new MovimientoPrimario(producto.getId(),LocalDate.now(),producto.getCantidad(),"","Alta");
				movimientoservicios.actualizar(movimiento);
			}else {
				MovimientoPrimario movimiento=new MovimientoPrimario(producto.getId(),LocalDate.now(),producto.getCantidad(),"","Formulario");
				movimientoservicios.actualizar(movimiento);
				productosservicios.Guardar(producto);
			
				
			}
			
			return "redirect:/ListaProductosPrimarios";
		}
	}	
	
	@GetMapping("/HistoricoProductoPrimario/{id}")
	public String HistoricoProductoPrimario(Model model,@PathVariable Long id) {
		ProductoPrimario producto;
		List<MovimientoPrimario> movimientos;
		List<ProductoPrimario> productos=productosservicios.TodosProductosPrimarios();
		if(id==0){
			producto=productos.get(0);
			System.out.println("Movimientos de -->"+producto.getId());
			movimientos=producto.getMovimientos();
		}else {
			producto=productosservicios.ProductoPrimarioId(id);
			movimientos=producto.getMovimientos();
		}
	
		System.out.println("Valors de movimientos-->"+movimientos);
		model.addAttribute("productos", productos);
		model.addAttribute("producto", producto.getNombre_primario());
		model.addAttribute("movimientos", movimientos);
		return "/admin/HistoricoProductosPrimarios";
	}
	/*
	@GetMapping("/BuscarHistoricoProducto/{nombre}")
	public String MostrarHistoricoProducto(@PathVariable String nombre,Model model) {
		ProductoPrimario producto=productosservicios.ProductoPrimarioId(nombre);
		
	}
	*/
	}
