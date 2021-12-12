package com.trazabilidad.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trazabilidad.modelo.Lote;
import com.trazabilidad.modelo.Receta;
import com.trazabilidad.servicios.LoteServicios;
import com.trazabilidad.servicios.RecetaServicios;

@Controller
public class ControladorLotes {
		
	@Autowired
	LoteServicios loteservicios;
	@Autowired
	RecetaServicios recetaservicios;
		
	@GetMapping("/lote/{lote}")
	public String AbrirLote(Model model,@PathVariable String lote) {
		Lote loteeditar=loteservicios.LotePorLote(lote);
		Receta receta=recetaservicios.RecetaPorId(loteeditar.getIdreceta());
		model.addAttribute("receta", receta.getNombrereceta());
		model.addAttribute("ejecucion", 1);
		model.addAttribute("lote", loteeditar);
		return "lote";
	}

}
