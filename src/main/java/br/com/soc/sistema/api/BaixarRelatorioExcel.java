package br.com.soc.sistema.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.soc.sistema.vo.ExameRealizadoVo;

public class BaixarRelatorioExcel {

	public void baixar(List<ExameRealizadoVo> examesRealizados) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFSheet sheet = workbook.createSheet("relatorioExamesRealizados");

		int rownum = 0;
		
		try {
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
