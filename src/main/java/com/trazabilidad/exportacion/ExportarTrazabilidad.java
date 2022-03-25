package com.trazabilidad.exportacion;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



import com.trazabilidad.modelo.MovimientoLote;
import com.trazabilidad.modelo.ProductoPrimario;


public class ExportarTrazabilidad {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<MovimientoLote> movimientos;
	private int rowCount = 0;
	private static List<CellRangeAddress> cellRangeAddressList = new ArrayList<CellRangeAddress>();
	
	public ExportarTrazabilidad(String Receta,String lote,List<MovimientoLote> movimientos) {
		this.movimientos=movimientos;
    	workbook=new XSSFWorkbook();
    	sheet = workbook.createSheet("Trazabilidad");
    	
    	   CellStyle style = workbook.createCellStyle();
           XSSFFont font  = workbook.createFont();
           font.setBold(true);
           font.setFontHeight(16);
           font.setColor(IndexedColors.WHITE.index);
           style.setFont(font);   
           style.setFillForegroundColor(IndexedColors.DARK_BLUE.index);
           style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
       	 Row row = sheet.createRow(rowCount++);
       	 createCell(row, 0, "Producto:", style); 
       	 createCell(row, 1, Receta, style);  		
            CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 1, 3);
            sheet.addMergedRegion(cellRangeAddress);
    		 cellRangeAddressList.add(cellRangeAddress);
       	  row = sheet.createRow(rowCount++);
       	 createCell(row, 0, "Lote:", style); 
       	 createCell(row, 1, lote, style);  		
            cellRangeAddress = new CellRangeAddress(1, 1, 1, 3);
            sheet.addMergedRegion(cellRangeAddress);
    		 cellRangeAddressList.add(cellRangeAddress);
   
	}
	private void escribircabecera(boolean cabecera) {
		 Row row = sheet.createRow(rowCount++);
         
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font  = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(16);
	        font.setColor(IndexedColors.WHITE.index);
	        style.setFont(font);   
	        style.setFillForegroundColor(IndexedColors.DARK_BLUE.index);
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	      
	        if(cabecera) {
	        	createCell(row, 0, "Tipo Movimiento", style);      
		        createCell(row, 1, "Destinatario", style);       
		        createCell(row, 2, "Fecha Movimiento", style);    
		        createCell(row, 3, "Cantidad", style);
	        }else {
	        	row = sheet.createRow(rowCount++);;
	        	createCell(row, 0, "Ingrediente", style);      
		        createCell(row, 1, "Empresa", style);       
		        createCell(row, 2, "Reg.Sanitario", style);    
		        
	        }
	        

	         
	}
	  private void createCell(Row row, int columnCount, Object value, CellStyle style) {
	        sheet.autoSizeColumn(columnCount);
	        XSSFCell cell =(XSSFCell) row.createCell(columnCount);
	     
	        if (value instanceof Date) {
	            cell.setCellValue((Date) value);
	        } else if (value instanceof Float) {
	            cell.setCellValue((Float) value);
	        }else {
	            cell.setCellValue((String) value);
	        }
	        cell.setCellStyle(style);
	    }
	     
	
	private void escribirlineasdatos(boolean cabecera) {
		
        XSSFDataFormat df = workbook.createDataFormat();
        CellStyle style = workbook.createCellStyle();
        CellStyle datestyle = workbook.createCellStyle();
        XSSFFont font  = workbook.createFont();
        //CreationHelper createHelper = workbook.getCreationHelper();
        
        font.setFontHeight(14);
        style.setFont(font);
        datestyle.setDataFormat(df.getFormat("yyyy/MM/dd"));
        datestyle.setFont(font);
        //datestyle.setDataFormat(createHelper.createDataFormat().getFormat("dd.mm.yyyy"));
        ZoneId defaultZoneId =ZoneId.of("US/Eastern"); //ZoneId.systemDefault();      
        if(cabecera) {
        	 for (MovimientoLote movimiento: movimientos) {
             	XSSFRow row = sheet.createRow(rowCount++);
                 int columnCount = 0;             
                 createCell(row, columnCount++, movimiento.getTipomovimiento(), style);
                 createCell(row, columnCount++, movimiento.getDestinatario(), style);
                 createCell(row, columnCount++, Date.from(movimiento.getFechamovimiento().atStartOfDay(defaultZoneId).toInstant()), datestyle);
                 createCell(row, columnCount++, movimiento.getCantidad(), style);
                }
        }else {
        	if(!movimientos.isEmpty()) {        
        	MovimientoLote movimiento=movimientos.get(0);
        	List<ProductoPrimario> ingredientes=movimiento.getProductores();
        	for(ProductoPrimario ingrediente:ingredientes) {
        		XSSFRow row = sheet.createRow(rowCount++);
                int columnCount = 0;             
                createCell(row, columnCount++, ingrediente.getNombreprimario(), style);
                createCell(row, columnCount++, ingrediente.getEmpresa(), style);
                createCell(row, columnCount++, ingrediente.getReg_sanitario(), style);
        		
        	}
        	}
        }
       
	}
	
    public void export(HttpServletResponse response) throws IOException {
        escribircabecera(true);
        escribirlineasdatos(true);
        escribircabecera(false);
        escribirlineasdatos(false);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }


}
