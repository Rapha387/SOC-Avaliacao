package br.com.soc.sistema.api;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import br.com.soc.sistema.vo.ExameRealizadoVo;

public class BaixarRelatorioExcel {

	public void baixar(List<ExameRealizadoVo> examesRealizados) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();

		Sheet sheet = workbook.createSheet("relatorioExamesRealizados");

		try {
			String[] headers = {"ID Funcionário", "Nome Funcionário", "ID Exame", "Nome Exame", "Data Exame"};
			
			Row headerRow = sheet.createRow(0);
			for(int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
	            cell.setCellValue(headers[i]);
	            
	            CellStyle headerStyle = workbook.createCellStyle();
	            
	            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
	            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	            
	            Font font = workbook.createFont();
	            font.setBold(true);
	            font.setColor(IndexedColors.WHITE.getIndex());
	            headerStyle.setFont(font);
	            cell.setCellStyle(headerStyle);
			}
			
			int rownum = 1;
			
			for(ExameRealizadoVo exame : examesRealizados) {
				Row row = sheet.createRow(rownum++);
				int cellnum = 0;
				
				Cell cellIdFuncionario = row.createCell(cellnum++);
				cellIdFuncionario.setCellValue(Integer.parseInt(exame.getFuncionarioVo().getRowid()));
			    Cell cellNomeFuncionario = row.createCell(cellnum++);
			    cellNomeFuncionario.setCellValue(exame.getFuncionarioVo().getNome());

			    Cell cellIdExame = row.createCell(cellnum++);
			    cellIdExame.setCellValue(Integer.parseInt(exame.getExameVo().getRowid()));
			    Cell cellNomeExame = row.createCell(cellnum++);
			    cellNomeExame.setCellValue(exame.getExameVo().getNome());
		
			    Cell cellDataExame = row.createCell(cellnum++);
			    cellDataExame.setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(exame.getDataExame()));
			}
			
			for (int i = 0; i < examesRealizados.size(); i++) {
	            sheet.autoSizeColumn(i);
	        }
			
	        try (FileOutputStream fileOut = new FileOutputStream(new File("relatorio.xls"))) {
	            workbook.write(fileOut);
	            workbook.close();
	        }catch (IOException e) {
	            throw new IOException("Não foi possivel baixar o arquivo");
	        }
		}catch(Exception e) {
			throw new Exception("Não foi possivel baixar o arquivo");
		}
	}
}
