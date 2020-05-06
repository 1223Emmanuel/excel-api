package com.eglobal.bo.api.zip.exporters;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.eglobal.bo.api.zip.dto.LBitacoraDTO;
import com.eglobal.bo.api.zip.service.ExportExcelService;

@Service
@Named("exportaExcelService")

public class ExportExcelTramite implements ExportExcelService {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	static final Logger log = LogManager.getLogger(ExportExcelTramite.class);

	public ByteArrayOutputStream exportaExcel(List<LBitacoraDTO> lista, String adjunto, String descripcion) {
		ByteArrayOutputStream excel = null;
		try {

			String[] columns = obtieneColumnas();

			Workbook workbook = new HSSFWorkbook();

			// Crea hoja de excel
			Sheet sheet = workbook.createSheet(descripcion.trim());

			// Crea estilo de letra para los cabeceros
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.BLACK.getIndex());

			// Crea CellStyle
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Crea la fila del cabecero
			Row headerRow = sheet.createRow(0);

			// Crea las celdas de cauerdo al numero de columnas del adjunto
			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			// llena la tabla con la informacion
			sheet = obtieneDatos(workbook, sheet, lista);

			// Ajusta el contenido de las celdas
			for (int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}

			// Escribe el excel en bytes
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workbook.write(bos);
			excel = bos;
			bos.close();
			workbook.close();
		} catch (Exception e) {
			log.error("Error al crear el excel adjunto: " + e.getMessage());
		}
		return excel;
	}

	public Sheet obtieneDatos(Workbook workbook, Sheet sheet, List<LBitacoraDTO> lista) {
		Row row = null;
		try {
			/*
			 * CreationHelper helps us create instances of various things like DataFormat,
			 * Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
			 */
			CreationHelper createHelper = workbook.getCreationHelper();
			// Estilo para las fechas
			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
			int rowNum = 1;

			for (LBitacoraDTO tramite : lista) {
				row = sheet.createRow(rowNum++);
				Cell dateCell = row.createCell(0);
				dateCell.setCellValue((rowNum - 1));
				row.createCell(1)
						.setCellValue(tramite.getDpetnumctaclaro() == null ? "" : tramite.getDpetnumctaclaro());
				row.createCell(2).setCellValue(tramite.getDpetfetxn2() == null ? "" : tramite.getDpetfetxn2());
				row.createCell(3).setCellValue(tramite.getDpetimpotran() == null ? "" : tramite.getDpetimpotran());
				row.createCell(4).setCellValue(tramite.getDpetnumaut() == null ? "" : tramite.getDpetnumaut());
				row.createCell(5).setCellValue(tramite.getDpetfolegl());
				row.createCell(6).setCellValue(tramite.getCnegnumero() == null ? "" : tramite.getCnegnumero());
				row.createCell(7).setCellValue(tramite.getCtipdocclave() == null ? "" : tramite.getCtipdocclave());

			}

		} catch (Exception e) {
			log.error("Error al llenar el excel con la informacion del tramite " + e.getMessage());
		}
		return sheet;
	}

	public String[] obtieneColumnas() {
		String[] columnas = null;
		Field[] fields = null;

		fields = LBitacoraEnc.class.getDeclaredFields();

		int cont = 0;

		if (null != fields) {
			columnas = new String[fields.length];
			for (Field f : fields) {
				try {
					String columna = (String) f.get(f);
					columnas[cont] = columna;
					cont = cont + 1;
				} catch (IllegalArgumentException | IllegalAccessException e) {
					log.error("Error al obtener los cabeceros del adjunto " + e.getMessage());
				}
			}
		}

		return columnas;
	}

	@SuppressWarnings("unused")
	private String fillZero(String text, int maxSize) {
		StringBuilder zeroText = new StringBuilder();
		StringBuilder result = new StringBuilder();
		if (text != null) {
			int zeroCount = maxSize - text.length();
			if (zeroCount > 0) {
				for (int i = 1; i < zeroCount; i++) {
					zeroText.append("0");
				}
			}
			result = zeroText.append(text);
		}
		return result.toString();
	}

}
