package com.trazabilidad.controladores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trazabilidad.modelo.Lote;
import com.trazabilidad.modelo.MovimientoPrimario;
import com.trazabilidad.modelo.ProductoPrimario;
import com.trazabilidad.modelo.Receta;
import com.trazabilidad.modelo.RelacionLotePrimario;
import com.trazabilidad.modelo.RelacionRecetaPrimario;
import com.trazabilidad.primarykey.PrimaryKeyRelLotePrimario;
import com.trazabilidad.servicios.LoteServicios;
import com.trazabilidad.servicios.MovimientoPrimarioServicios;
import com.trazabilidad.servicios.ProductosPrimariosServicios;
import com.trazabilidad.servicios.RecetaServicios;
import com.trazabilidad.servicios.RelacionLotePrimarioServicios;
import com.trazabilidad.servicios.RelacionRecetaPrimarioServicios;

@Controller
public class ControladorEjecutarReceta {
	@Autowired
	MovimientoPrimarioServicios movimientoservicio;
	@Autowired
	RecetaServicios recetaservicios;
	@Autowired
	RelacionRecetaPrimarioServicios relacionreceta;
	@Autowired
	ProductosPrimariosServicios 	productoservicios;
	@Autowired
	LoteServicios loteservicios;
	@Autowired
	RelacionLotePrimarioServicios relacionlote;
	
	@GetMapping("/EjecutarReceta/{idreceta}/{cantidad}")
	public String EjecutarReceta(@PathVariable long idreceta,@PathVariable float cantidad,Model model) {
		boolean noproducto=false;
		List<String> mensaje=new ArrayList<String>();
		
		ProductoPrimario producto;
		String fecha=LocalDate.now().toString().replace("-","");
		String tiempo=LocalTime.now().toString().replace(":","").substring(0, 6);
		String lote=fecha+tiempo;
		
		System.out.println("Idreceta-->"+idreceta+" Cantidad -->"+cantidad+" Lote -->"+lote);
		Receta receta=recetaservicios.RecetaPorId(idreceta);
		long idbase=receta.getProductobase();
		List<RelacionRecetaPrimario> ingredientes=relacionreceta.BuscarIdReceta(idreceta);
		//Comprobamos si tenemos en almacen cantidad suficiente para ejecutar la receta
		for(RelacionRecetaPrimario ingrediente:ingredientes) { 
			if(idbase==ingrediente.getPrimaryKeyRelRecetasPrimarios().getIdprimarias()) {
				producto=productoservicios.ProductoPrimarioId(idbase);
				if(producto.getCantidad()<cantidad) {
					noproducto=true;
					mensaje.add(" No hay "+ingrediente.getNombreprimario());
				}
				System.out.println("Valor de "+ingrediente.getNombreprimario()+" Cantidad ->"+cantidad);
				
			}else {
				producto=productoservicios.ProductoPrimarioId(ingrediente.getPrimaryKeyRelRecetasPrimarios().getIdprimarias());
				float cantidadusar=cantidad*ingrediente.getCantidad();
				if(producto.getCantidad()<cantidadusar) {
					noproducto=true;
					mensaje.add(" No hay "+ingrediente.getNombreprimario());
				}
				System.out.println("ingrediente -->"+ingrediente.getNombreprimario()+" Cantidadusar ->"+cantidad*ingrediente.getCantidad()+
						" cantidad almacen "+producto.getCantidad());
			}
		} // fin for
		// Si tenemos suficiente cantidad ejecutamos la creación
		if(!noproducto) {
			float cantidadresultante,total=0;
			List<RelacionLotePrimario> lotesprimarios=new ArrayList<RelacionLotePrimario>();
			for(RelacionRecetaPrimario componente:ingredientes) {
				producto=productoservicios.ProductoPrimarioId(componente.getPrimaryKeyRelRecetasPrimarios().getIdprimarias());
				if(idbase==componente.getPrimaryKeyRelRecetasPrimarios().getIdprimarias()) {
					cantidadresultante=cantidad;
					
				}else {
					cantidadresultante=cantidad*componente.getCantidad();
				}
				total+=cantidadresultante;
				producto.setCantidad(producto.getCantidad()-cantidadresultante);
				MovimientoPrimario movimiento=new MovimientoPrimario(producto.getId(),LocalDate.now(),cantidadresultante,lote,"Ejecución");
				productoservicios.Guardar(producto);
				movimientoservicio.Actualizar(movimiento);
				RelacionLotePrimario loteprimario=new RelacionLotePrimario(
						new PrimaryKeyRelLotePrimario(lote,producto.getId()),cantidadresultante,componente.getNombreprimario());
				lotesprimarios.add(loteprimario);
				
			}
			Lote lotenuevo=new Lote(lote,idreceta,total,LocalDate.now(),LocalDate.now().plusDays(receta.getCaducidad()),receta.getNombrereceta());
			loteservicios.Guardar(lotenuevo);
			
			for(RelacionLotePrimario nuevo:lotesprimarios) {
				relacionlote.Guardar(nuevo);
			}
			lotenuevo.setIngredientes(lotesprimarios);
			loteservicios.Guardar(lotenuevo);
			model.addAttribute("receta", receta.getNombrereceta());
			model.addAttribute("ejecucion", 0);
			model.addAttribute("lote", lotenuevo);
			return "Lote";
		}else {
			if(!mensaje.isEmpty())
				mensaje.add("No se ha podido ejecutar la receta");
			List<Receta> recetas=recetaservicios.RecetasActivadas();
			model.addAttribute("recetas", recetas);
			model.addAttribute("mensajeerror",mensaje);
			return "ListaRecetas";
		}
	
	}

}
