package com.trazabilidad.exportacion;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.trazabilidad.modelo.Lote;

public class ExportarLotes {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
    private List<Lote> lotes;
    
    public ExportarLotes(List<Lote> lotes) {
    	
    	this.lotes=lotes;    	
    	workbook=new XSSFWorkbook();
    	sheet = workbook.createSheet("Lotes");
    }
    
    
    private void writeHeaderLine() {
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font  = workbook.createFont();
       // XSSFColor myColor = new XSSFColor(Color.BLUE);
        font.setBold(true);
        font.setFontHeight(16);
        font.setColor(IndexedColors.WHITE.index);
        style.setFont(font);   
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
      
         
        createCell(row, 0, "Receta", style);      
        createCell(row, 1, "Lote", style);       
        createCell(row, 2, "Fecha elaboración", style);    
        createCell(row, 3, "Fecha caducidad", style);
        createCell(row, 4, "Cantidad Producida", style);
        createCell(row, 5, "Cantidad distribuida", style);
         
    }
	
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        //HSSFCell cell = (HSSFCell) row.createCell(columnCount);
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
     
    private void writeDataLines() {
        int rowCount = 1;
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
        for (Lote lote : lotes) {
        	XSSFRow row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, lote.getNombrereceta(), style);
            createCell(row, columnCount++, lote.getLote(), style);
            createCell(row, columnCount++, Date.from(lote.getFechelaboracion().atStartOfDay(defaultZoneId).toInstant()), datestyle);
            createCell(row, columnCount++, Date.from(lote.getFechcaducidad().atStartOfDay(defaultZoneId).toInstant()), datestyle);
            createCell(row, columnCount++, lote.getCantidadproducida(), style);
            createCell(row, columnCount++, lote.getCantidaddistribuida(), style);
             
        }
    }   
    
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
    
    
}
