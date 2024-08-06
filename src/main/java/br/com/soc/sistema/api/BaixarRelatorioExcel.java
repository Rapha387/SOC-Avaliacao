package br.com.soc.sistema.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class BaixarRelatorioExcel {

	public void baixar()  {
		HSSFWorkbook workbook = new HSSFWorkbook();

        // Criar uma planilha
		HSSFSheet sheet = workbook.createSheet("MinhaPlanilha");

        // Criar uma linha
        Row row = sheet.createRow(0);

        // Criar células na linha
        Cell cell1 = row.createCell(0);
        cell1.setCellValue("Nome");

        Cell cell2 = row.createCell(1);
        cell2.setCellValue("Idade");

        // Adicionar mais linhas e células
        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("João");
        row1.createCell(1).setCellValue(25);

        Row row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("Maria");
        row2.createCell(1).setCellValue(30);

        // Ajustar o tamanho das colunas
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);

        // Escrever o workbook em um arquivo
        try (FileOutputStream fileOut = new FileOutputStream(new File("C:/teste/novo.xls"))) {
            workbook.write(fileOut);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
         e.printStackTrace();
        }

        // Fechar o workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
}
