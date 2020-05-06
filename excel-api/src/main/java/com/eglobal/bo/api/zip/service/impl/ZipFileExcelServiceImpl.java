package com.eglobal.bo.api.zip.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eglobal.bo.api.zip.dto.AdjuntoDTO;
import com.eglobal.bo.api.zip.dto.EnviaParamDTO;
import com.eglobal.bo.api.zip.dto.ParametrosDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;
import com.eglobal.bo.api.zip.service.ConectMailService;
import com.eglobal.bo.api.zip.service.ExcelService;
import com.eglobal.bo.api.zip.service.HtmlService;
import com.eglobal.bo.api.zip.service.PasswordService;
import com.eglobal.bo.api.zip.service.Pdfservice;
import com.eglobal.bo.api.zip.service.ZipFileExcelService;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.DocumentException;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

@Service
public class ZipFileExcelServiceImpl implements ZipFileExcelService {
	static final Logger log = LogManager.getLogger(ZipFileExcelServiceImpl.class);
	@Autowired
	private ExcelService excel;

	@Autowired
	private Pdfservice pdf;

	@Autowired
	private HtmlService html;

	@Autowired
	private PasswordService pwd;

	@Autowired
	private ConectMailService mail;

	@Transactional
	public String zip(String fileName, ParametrosDTO bcom) throws RestException, IOException, DocumentException {
		try {

			compressWithPassword("C:\\Users\\Emmanuel\\Downloads\\Archivos zip\\", bcom);

		} catch (ZipException e) {
			log.error("Error  " + e.getMessage());
		}
		return fileName;
	}

	@SuppressWarnings("unused")
	public char[] pass(EnviaParamDTO param) throws RestException, IOException {
		// CreaciondeContraseña
		String pass = pwd.obtenerPassword();
		char[] c = pass.toCharArray();
		byte[] bytesp = Charset.forName("UTF-8").encode(CharBuffer.wrap(c)).array();
		String encoded = Base64.getEncoder().encodeToString(bytesp);

		AdjuntoDTO adqp = new AdjuntoDTO();
		adqp.setTipo("txt");
		adqp.setNombre("pass");
		adqp.setFolioEglobal("1234");
		adqp.setContenidoBase64(encoded);
		List<AdjuntoDTO> lisAp = new ArrayList<AdjuntoDTO>();
		lisAp.add(adqp);
		EnviaParamDTO confp = new EnviaParamDTO();
		confp.setAdjunto(lisAp);
		confp.setAfiliacion("1234");
		confp.setAsunto("Pass Prueba");
		confp.setCc("");
		confp.setFromEmail("test_smtp@eglobal.com.mx");
		confp.setMensaje("pass de prueba");
		confp.setNombrePlantilla("Prueba");
		confp.setNumeroGrupo("Prueba");
		confp.setToEmail("erezaa@eglobal.com.mx");

		List<EnviaParamDTO> pruebap = mail.enviaMail(confp);
		char[] c1 = pass.toCharArray();
		try (FileWriter fw = new FileWriter("C:\\Users\\Emmanuel\\Downloads\\Archivos zip\\password.txt")) {
			fw.write(c1);
			fw.flush();
			return c1;
		}

	}

	public void compressWithPassword(String sourcePath, ParametrosDTO bcom) throws RestException, IOException {

		ZipFile zipFile = new ZipFile(sourcePath + "Archivos.zip", pass(null));

		ArrayList<File> fileList = new ArrayList<>();
		// ObtieneExcelEndoder

		String adjunto = excel.obtenerParam(bcom);
		byte[] excelBytes = Base64.getDecoder().decode(adjunto);
		try (FileOutputStream fos = new FileOutputStream("Excel.xls")) {
			fos.write(excelBytes);
			fos.flush();
		}
		// ObtienePDFEndoder
		// ByteArrayInputStream bis = pdf.obtenerParamPdf(bcom);
		String bis = pdf.obtenerParamPdf(bcom);
		byte[] bytes = Base64.getDecoder().decode(bis);
		try (FileOutputStream fos1 = new FileOutputStream("tablaPdf.pdf")) {
			fos1.write(bytes);
			fos1.flush();
		}
		// string HTML
		// ConvertirHTML
		String pruebah = html.obtenerParamhtml(bcom, null);
		File html1 = new File("plantillahtml.html");
		try (FileWriter fooWriter = new FileWriter(html1, false)) { // true to append

			fooWriter.write(pruebah);
			fooWriter.flush();
			fooWriter.flush();
		}
		// ConvertirdePDFaHTML
		HtmlConverter.convertToPdf(new FileInputStream(html1), new FileOutputStream("plantillapdf.pdf"));

		// AgregaArchicosazip
		fileList.add(new File("plantillapdf.pdf"));
		fileList.add(new File("plantillahtml.html"));
		fileList.add(new File("tablaPdf.pdf"));
		fileList.add(new File("Excel.xls"));

		ZipParameters zipParameters = new ZipParameters();
		zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);
		zipParameters.setCompressionLevel(CompressionLevel.ULTRA);
		zipParameters.setEncryptFiles(true);
		zipParameters.setEncryptionMethod(EncryptionMethod.AES);
		zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);

		zipFile.addFiles(fileList, zipParameters);

//		//////////////////////////////////
	
		///////////////////////////////////// ZIP/////////////////////////////////////////
		ByteArrayOutputStream zip = createOutputStream(zipFile.getFile());
		String zipstring = Base64.getEncoder().encodeToString(zip.toByteArray());
		AdjuntoDTO adq = new AdjuntoDTO();
		adq.setTipo("zip");
		adq.setContenidoBase64(zipstring);
		adq.setNombre("Archivos");
		adq.setFolioEglobal("1234");
		List<AdjuntoDTO> lisA = new ArrayList<AdjuntoDTO>();
		lisA.add(adq);

		EnviaParamDTO conf = new EnviaParamDTO();
		conf.setAdjunto(lisA);
		conf.setAfiliacion("1234");
		conf.setAsunto("correo de prueba");
		conf.setCc("");
		conf.setFromEmail("test_smtp@eglobal.com.mx");
		conf.setMensaje("mensaje de prueba");
		conf.setNombrePlantilla("Prueba");
		conf.setNumeroGrupo("Prueba");
		conf.setToEmail("erezaa@eglobal.com.mx");
		@SuppressWarnings("unused")
		List<EnviaParamDTO> prueba = mail.enviaMail(conf);

	}

	private ByteArrayOutputStream createOutputStream(File file) throws RestException {
		try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];

			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum); // no doubt here is 0
			}
			fis.close();
			return bos;
		} catch (IOException ex) {
			log.error(ex.getMessage());
			throw new RestException("Error al escribir archivo");
		}

	}

}
