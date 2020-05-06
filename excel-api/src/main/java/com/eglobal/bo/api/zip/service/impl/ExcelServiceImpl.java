
package com.eglobal.bo.api.zip.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.eglobal.bo.api.zip.dto.LBitacoraDTO;
import com.eglobal.bo.api.zip.dto.ParametrosDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;
import com.eglobal.bo.api.zip.repository.dao.ConsultaDAO;
import com.eglobal.bo.api.zip.service.ExcelService;
import com.eglobal.bo.api.zip.service.ExportExcelService;

@Service
public class ExcelServiceImpl implements ExcelService {


	private Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);

	@Autowired
	private ExportExcelService excel;
	@Autowired
	private ConsultaDAO consultadao;
	


	@Override
	public String obtenerParam(ParametrosDTO parametrosDTO) throws RestException {
		
		try {

			List<LBitacoraDTO> bitacora = consultadao.obtenerConsulta(parametrosDTO);

			
			ByteArrayOutputStream adjunto = null;
			adjunto = excel.exportaExcel(bitacora, "resultado", "prueba");

			return (Base64.getEncoder().encodeToString(adjunto.toByteArray()));
			
			

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RestException("101", "Error en ServiceImplement");
		}

	}

}
