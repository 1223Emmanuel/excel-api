package com.eglobal.bo.api.zip.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.eglobal.bo.api.zip.dto.EnviaParamDocDTO;
import com.eglobal.bo.api.zip.dto.LBitacoraDTO;
import com.eglobal.bo.api.zip.dto.LBitacoraDocDTO;
import com.eglobal.bo.api.zip.dto.ParametrosDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;
import com.eglobal.bo.api.zip.repository.dao.ConsultaDAO;
import com.eglobal.bo.api.zip.service.ConectDocService;
import com.eglobal.bo.api.zip.service.Pdfservice;

@Service
public class PdfServiceImpl implements Pdfservice {

	private Logger logger = LoggerFactory.getLogger(PdfServiceImpl.class);

	@Autowired
	private ConsultaDAO consultadao;
	@Autowired
	private ConectDocService doc;

	public String obtenerParamPdf(ParametrosDTO com) throws RestException {
		String archivo = null;
		try {
			List<LBitacoraDTO> bitacora = consultadao.obtenerConsulta(com);
			List<LBitacoraDocDTO> lisAp = new ArrayList<LBitacoraDocDTO>();

			for (LBitacoraDTO bitacorapdf : bitacora)

			{
				LBitacoraDocDTO adqp = new LBitacoraDocDTO();
				adqp.setNOMBRE_NEG(bitacorapdf.getCnegnombre());
				adqp.setNUM_CUENTA(bitacorapdf.getDpetnumcta());
				adqp.setIMPORTE(bitacorapdf.getDpetimpotran());
				lisAp.add(adqp);
			}
			EnviaParamDocDTO confp = new EnviaParamDocDTO();

			confp.setMapDatos(lisAp);
			confp.setAdquirente("12");
			confp.setAgrupacion("");
			confp.setFechaSolicitud("");
			confp.setFormato("PDF");
			confp.setParametroDoc("");
			confp.setTipoDoc("21");

			archivo = doc.enviaDoc(confp);
//			byte [] barr = Base64.getDecoder().decode(archivo); 
//			ByteArrayInputStream stream = new ByteArrayInputStream(archivo.getBytes(StandardCharsets.UTF_8));
			return archivo;

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RestException("100", "Error en PDFServiceImplement");
		}

	}

}
