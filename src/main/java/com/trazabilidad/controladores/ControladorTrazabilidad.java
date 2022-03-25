package com.trazabilidad.controladores;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trazabilidad.exportacion.ExportarTrazabilidad;
import com.trazabilidad.modelo.Lote;
import com.trazabilidad.modelo.MovimientoLote;
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
		String mensaje=nombrereceta+" Lote: "+nomlote;
		List<MovimientoLote> movimientos=lote1.getMovimientos();
		model.addAttribute("lote", nomlote);
		model.addAttribute("mensaje",mensaje );
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
		String mensaje=nombrereceta+" Lote: "+nomlote;
		List<MovimientoLote> movimientos=loteabrir.getMovimientos();
		model.addAttribute("lote", nomlote);
		model.addAttribute("mensaje",mensaje );
		model.addAttribute("movimientos", movimientos);
		model.addAttribute("recetas", recetaservicios.TrazabilidadReceta());
		return "Trazabilidad";
	}
	
	
	@GetMapping("/DetalleTrazabilidad/{idmovimiento}")
	public String DetalleTrazabilidad(Model model,@PathVariable long idmovimiento) {
		System.out.println("Idmovimiento-->"+idmovimiento);
		MovimientoLote movimiento=movimientoservicios.MovimientoPorId(idmovimiento);
		/*
		List<ProductoPrimario> producto=movimiento.getProductores();
		for(ProductoPrimario ver:producto) {
			System.out.println("Producto  "+ver.getNombreprimario());
		}*/
		model.addAttribute("movimiento", movimiento);
		return "DetalleTrazabilidad";
	}
	
	@GetMapping("/BuscarTrazabilidad/{lote}")
	public String BuscarTrazabilidad(Model model,@PathVariable String lote) {
		System.out.println("Valor de lote-->"+lote);
		String mensaje;
		List<MovimientoLote> movimientos=new ArrayList<>();
		Lote loteabrir=loteservicios.LotePorLote(lote);
		if(loteabrir!=null) {
			String nomlote=loteabrir.getLote();
			Receta receta=recetaservicios.RecetaPorId(loteabrir.getIdreceta());
			String nombrereceta=receta.getNombrereceta();
			mensaje=nombrereceta+" Lote: "+nomlote;
			movimientos=loteabrir.getMovimientos();
			model.addAttribute("lote", nomlote);
		}else {
			mensaje="No se han encontrado Lote con el valor introducido";
		}
		
		model.addAttribute("mensaje",mensaje );
		model.addAttribute("movimientos", movimientos);
		model.addAttribute("recetas", recetaservicios.TrazabilidadReceta());
		return "Trazabilidad";
	
	}
	
	@GetMapping("/Trazabilidad/Export/Excel/{lote}")
    public void ExportarExcel(HttpServletResponse response,@PathVariable("lote") String lote) throws IOException {
		
		 Lote loteabrir=loteservicios.LotePorLote(lote);
		 Receta receta=recetaservicios.RecetaPorId(loteabrir.getIdreceta());
		 String nombrereceta=receta.getNombrereceta();
		
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        System.out.println("Lote --->"+lote);
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Trazabilidad_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<MovimientoLote> movimientos=movimientoservicios.MovimientoPorLote(lote);
       
       ExportarTrazabilidad excelExporter = new ExportarTrazabilidad(nombrereceta,lote,movimientos);
       
       excelExporter.export(response);    
    }
}
