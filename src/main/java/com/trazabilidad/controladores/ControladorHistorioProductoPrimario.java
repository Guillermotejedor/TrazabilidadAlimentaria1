package com.trazabilidad.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trazabilidad.modelo.MovimientoPrimario;
import com.trazabilidad.modelo.ProductoPrimario;
import com.trazabilidad.servicios.MovimientoPrimarioServicios;
import com.trazabilidad.servicios.ProductosPrimariosServicios;

@Controller
public class ControladorHistorioProductoPrimario {
	
	@Autowired
	ProductosPrimariosServicios productosservicios;
	@Autowired
	MovimientoPrimarioServicios movimientoservicios;
	
	@GetMapping("/HistoricoProductoPrimario/{id}")
	public String HistoricoProductoPrimario(Model model,@PathVariable Long id) {
		try {
			
		
		ProductoPrimario producto;
		List<MovimientoPrimario> movimientos;
		List<ProductoPrimario> productos=productosservicios.TodosProductosPrimarios();
		if(id==0){
			producto=productos.get(0);
			movimientos=producto.getMovimientos();
		}else {
			producto=productosservicios.ProductoPrimarioId(id);
			movimientos=producto.getMovimientos();
		}
			
		model.addAttribute("productos", productos);
		model.addAttribute("producto", producto.getNombreprimario());
		model.addAttribute("movimientos", movimientos);
		}catch(Exception e) {
			
		}
		return "/admin/HistoricoProductosPrimarios";
	}
	
	@GetMapping("/BuscarHistoricoProducto/{nombre}")
	public String MostrarHistoricoProducto(@PathVariable String nombre,Model model) {
		List<MovimientoPrimario> movimientos;
		String mensaje;
		List<ProductoPrimario> productos=productosservicios.TodosProductosPrimarios();
		ProductoPrimario producto=productosservicios.BuscarPorNombre(nombre);
		if(producto!=null) {
			movimientos=producto.getMovimientos();
			mensaje=producto.getNombreprimario();
		}else {
			mensaje="No se han encontrado productos por ese nombre";
			movimientos=new ArrayList<MovimientoPrimario>();
		}
		model.addAttribute("productos", productos);
		model.addAttribute("producto", mensaje);
		model.addAttribute("movimientos", movimientos);
		return "/admin/HistoricoProductosPrimarios";
		
	}
	
	@GetMapping("/MovimientoProductoPrimario/{id}")
	public String MovimientoProductoPrimario(@PathVariable long id,Model model)  {
		MovimientoPrimario movimiento=movimientoservicios.BucarPorId(id);
		ProductoPrimario producto=productosservicios.ProductoPrimarioId(movimiento.getId_primarias());
		model.addAttribute("nombreproducto", producto.getNombreprimario());
		model.addAttribute("empresa", producto.getEmpresa());
		model.addAttribute("tipomovimiento",movimiento.getTipo_modificacion());
		model.addAttribute("fecha",movimiento.getFech_ejecucion());
		model.addAttribute("cantidad",movimiento.getCantidad());
		model.addAttribute("lote",movimiento.getLote());
		
		return "/admin/MovimientoPrimario";
	}

}
