package com.trazabilidad.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trazabilidad.modelo.Lote;
import com.trazabilidad.modelo.MovimientoLote;
import com.trazabilidad.modelo.Receta;
import com.trazabilidad.servicios.LoteServicios;
import com.trazabilidad.servicios.MovimientoLoteServicios;
import com.trazabilidad.servicios.RecetaServicios;

@Controller
public class ControladorLotes {
		
	@Autowired
	LoteServicios loteservicios;
	@Autowired
	RecetaServicios recetaservicios;
	@Autowired
	MovimientoLoteServicios movimientoservicios;
		
	@GetMapping("/lote/{ejecucion}/{lote}")
	public String AbrirLote(Model model,@PathVariable boolean ejecucion,@PathVariable String lote) {
		
		Lote loteeditar=loteservicios.LotePorLote(lote);
		Receta receta=recetaservicios.RecetaPorId(loteeditar.getIdreceta());
		model.addAttribute("receta", receta.getNombrereceta());
		if (ejecucion)
			model.addAttribute("ejecucion", 1);
		else
			model.addAttribute("ejecucion", 0);
		model.addAttribute("lote", loteeditar);
		return "lote";
	}

	@GetMapping("/DistribuirLote/{lote}/{cantidad}/{tipomovimiento}/{destinatario}")
	public String DistribuirLote(@PathVariable String lote,@PathVariable float cantidad,@PathVariable String tipomovimiento,
			@PathVariable String destinatario) {
		System.out.println("lote-->"+lote+" cantidad -->"+cantidad+" tipo movimiento-->"+tipomovimiento+" destinatario -->"+destinatario);
		Lote lotedistribuir=loteservicios.LotePorLote(lote);
		//lotedistribuir.setCantidadproducida(lotedistribuir.getCantidadproducida()-cantidad);
		lotedistribuir.setCantidaddistribuida(lotedistribuir.getCantidaddistribuida()+cantidad);
		loteservicios.Guardar(lotedistribuir);
		MovimientoLote movimiento=new MovimientoLote(destinatario,cantidad,lote,tipomovimiento,LocalDate.now());
		movimientoservicios.Guardar(movimiento);
		return "redirect:/ListaLotes";
	}
	
	@GetMapping("/HistoricoLotes")
	public String HistoricoLotes(Model model) {
		List<Receta> recetas= recetaservicios.TodasRecetas();
		List<Lote> lotes=new ArrayList<>();
		String receta="";
		if(!recetas.isEmpty()) {
			lotes=loteservicios.LotesPorIdreceta(recetas.get(0).getIdreceta());
			receta=recetas.get(0).getNombrereceta();
		}
		model.addAttribute("receta", receta);
		model.addAttribute("lotes", lotes);
		model.addAttribute("recetas",recetas);
		return "HistoricoLotes";
	}
	
	@GetMapping("/HistoricoLote/{receta}")
	public String HistoricoElaborados(Model model,@PathVariable long receta) {
		List<Receta> recetas= recetaservicios.TodasRecetas();
		List<Lote> lotes=loteservicios.LotesPorIdreceta(receta);
		String recetamostrar=lotes.get(0).getNombrereceta();
		model.addAttribute("receta", recetamostrar);
		model.addAttribute("lotes", lotes);
		model.addAttribute("recetas",recetas);
		return "HistoricoLotes";
	}
	
}
