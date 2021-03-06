package com.trazabilidad.controladores;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trazabilidad.exportacion.ExportarLotes;
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
	
	@GetMapping("/BuscarHistoricoLote/{receta}")
	public String BuscarHistoricoLote(Model model,@PathVariable String receta) {
		List<Receta> recetas= recetaservicios.TodasRecetas();
		List<Lote> lotes=loteservicios.LotesPorNombreReceta(receta);
		if(lotes.isEmpty()) {
			String recetamostrar="No se ha encontrado ninguna receta";
			model.addAttribute("receta", recetamostrar);
		}else {
			String recetamostrar=lotes.get(0).getNombrereceta();
			model.addAttribute("receta", recetamostrar);	
		}
		
		model.addAttribute("lotes", lotes);
		model.addAttribute("recetas",recetas);
		return "HistoricoLotes";
	}
	
	 @GetMapping("/lotes/export/excel")
	    public void exportToExcel(HttpServletResponse response) throws IOException {
	        response.setContentType("application/octet-stream");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=lotes_" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	         
	        List<Lote> lotes=loteservicios.LotesActivos();
	       
	        ExportarLotes excelExporter = new ExportarLotes(lotes);
	       
	        excelExporter.export(response);    
	    }   
	
}
