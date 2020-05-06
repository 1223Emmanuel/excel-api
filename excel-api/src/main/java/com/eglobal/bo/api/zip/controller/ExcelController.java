package com.eglobal.bo.api.zip.controller;


import java.io.IOException;


import org.apache.commons.lang3.StringEscapeUtils;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eglobal.bo.api.zip.dto.ParametrosDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;
import com.eglobal.bo.api.zip.service.ExcelService;
import com.eglobal.bo.api.zip.service.HtmlService;
import com.eglobal.bo.api.zip.service.Pdfservice;
import com.eglobal.bo.api.zip.service.ZipFileExcelService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/")
public class ExcelController {

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(ExcelController.class);
	PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.BLOCKS);

	@Autowired
	private ExcelService excel;
	
	@Autowired
	private ZipFileExcelService zip;
	
	@Autowired
	private Pdfservice pdf;
	
	@Autowired
	private HtmlService html;

	
	@GetMapping("/excel")
	public String  obtieneExcel(ParametrosDTO parametros) throws RestException, IOException {
		String requestbody = null;
		String json = null;
		json = new ObjectMapper().writeValueAsString(parametros);
		requestbody = StringEscapeUtils.unescapeHtml3(policy.sanitize(json));

		ObjectMapper mapper = new ObjectMapper();
		ParametrosDTO bcom = mapper.readValue(requestbody, ParametrosDTO.class);
	
	return excel.obtenerParam((ParametrosDTO) bcom);
		
	}
	@GetMapping("/pdf")
	public String  obtienePdf(ParametrosDTO parametros) throws RestException,  IOException {
		String requestbody = null;
		String json = null;
		json = new ObjectMapper().writeValueAsString(parametros);
		requestbody = StringEscapeUtils.unescapeHtml3(policy.sanitize(json));

		ObjectMapper mapper = new ObjectMapper();
		ParametrosDTO bcom = mapper.readValue(requestbody, ParametrosDTO.class);
	
	return pdf.obtenerParamPdf((ParametrosDTO) bcom);
		
	}
	@GetMapping("/html")
	public String  obtieneHtml(ParametrosDTO parametros) throws RestException, IOException {
		String requestbody = null;
		String json = null;
		json = new ObjectMapper().writeValueAsString(parametros);
		requestbody = StringEscapeUtils.unescapeHtml3(policy.sanitize(json));

		ObjectMapper mapper = new ObjectMapper();
		ParametrosDTO bcom = mapper.readValue(requestbody, ParametrosDTO.class);
	
	return html.obtenerParamhtml((ParametrosDTO) bcom, null);
		
	}
	@PostMapping("/ObtieneZip")
	//public String  ObtieneZip(@RequestBody List<ParametrosDTO>  list  ) throws RestException, IOException 
	public String  obtieneZip(@RequestBody ParametrosDTO  param ) throws RestException, IOException, DocumentException {
		String requestbody = null;
		String json = null;
		json = new ObjectMapper().writeValueAsString(param);
		requestbody = StringEscapeUtils.unescapeHtml3(policy.sanitize(json));

		ObjectMapper mapper = new ObjectMapper();
		ParametrosDTO bcom = mapper.readValue(requestbody, ParametrosDTO.class);
		
		
	
			return   zip.zip("123",bcom);
		
	}
}
