package com.trazabilidad.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trazabilidad.modelo.Lote;
import com.trazabilidad.modelo.MovimientoLote;
import com.trazabilidad.modelo.ProductoPrimario;
import com.trazabilidad.modelo.Receta;
import com.trazabilidad.servicios.LoteServicios;
import com.trazabilidad.servicios.MovimientoLoteServicios;
import com.trazabilidad.servicios.RecetaServicios;

@Controller
public class ControladorTrazabilidad {
	@Autowired
	RecetaServicios recetaservicios;	
	@Autowired
	LoteServicios loteservicios;
	@Autowired
	MovimientoLoteServicios movimientoservicios;
	
	@GetMapping("/Trazabilidad")
	public String Trazabilidad(Model model) {
			
		
		List<Receta> recetas=recetaservicios.TrazabilidadReceta();
		Receta receta=recetas.get(0);
		String	nombrereceta=receta.getNombrereceta();
		List<Lote> lote=receta.getLotes();
		Lote lote1=lote.get(0);
		String nomlote=lote1.getLote();
		List<MovimientoLote> movimientos=lote1.getMovimientos();
		model.addAttribute("lote", nomlote);
		model.addAttribute("nombrereceta",nombrereceta );
		model.addAttribute("movimientos", movimientos);
		model.addAttribute("recetas", recetas);
		return "Trazabilidad";
	}
	
	@GetMapping("/Trazabilidad/{lote}")
	public String AbrirTrazabilidad(Model model,@PathVariable String lote) {
		System.out.println("Lote -->"+lote);
		Lote loteabrir=loteservicios.LotePorLote(lote);
		String nomlote=loteabrir.getLote();
		Receta receta=recetaservicios.RecetaPorId(loteabrir.getIdreceta());
		String nombrereceta=receta.getNombrereceta();
		List<MovimientoLote> movimientos=loteabrir.getMovimientos();
		model.addAttribute("lote", nomlote);
		model.addAttribute("nombrereceta",nombrereceta );
		model.addAttribute("movimientos", movimientos);
		model.addAttribute("recetas", recetaservicios.TrazabilidadReceta());
		return "Trazabilidad";
	}
	
	
	@GetMapping("/DetalleTrazabilidad/{idmovimiento}")
	public String DetalleTrazabilidad(Model model,@PathVariable long idmovimiento) {
		System.out.println("Idmovimiento-->"+idmovimiento);
		MovimientoLote movimiento=movimientoservicios.MovimientoPorId(idmovimiento);
		List<ProductoPrimario> producto=movimiento.getProductores();
		for(ProductoPrimario ver:producto) {
			System.out.println("Producto  "+ver.getNombreprimario());
		}
		model.addAttribute("movimiento", movimiento);
		return "DetalleTrazabilidad";
	}
}