package com.trazabilidad.controladores;

import java.time.LocalDate;
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
		
	@GetMapping("/lote/{lote}")
	public String AbrirLote(Model model,@PathVariable String lote) {
		Lote loteeditar=loteservicios.LotePorLote(lote);
		Receta receta=recetaservicios.RecetaPorId(loteeditar.getIdreceta());
		model.addAttribute("receta", receta.getNombrereceta());
		model.addAttribute("ejecucion", 1);
		model.addAttribute("lote", loteeditar);
		return "lote";
	}

	@GetMapping("/DistribuirLote/{lote}/{cantidad}/{tipomovimiento}/{destinatario}")
	public String DistribuirLote(@PathVariable String lote,@PathVariable float cantidad,@PathVariable String tipomovimiento,
			@PathVariable String destinatario) {
		System.out.println("lote-->"+lote+" cantidad -->"+cantidad+" tipo movimiento-->"+tipomovimiento+" destinatario -->"+destinatario);
		Lote lotedistribuir=loteservicios.LotePorLote(lote);
		lotedistribuir.setCantidad(lotedistribuir.getCantidad()-cantidad);
		loteservicios.Guardar(lotedistribuir);
		MovimientoLote movimiento=new MovimientoLote(destinatario,cantidad,lote,tipomovimiento,LocalDate.now());
		movimientoservicios.Guardar(movimiento);
		return "redirect:/ListaLotes";
	}
	
}
