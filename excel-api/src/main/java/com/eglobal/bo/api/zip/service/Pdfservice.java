package com.eglobal.bo.api.zip.service;

import org.springframework.stereotype.Service;

import com.eglobal.bo.api.zip.dto.ParametrosDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;

@Service
public interface Pdfservice {



	public String obtenerParamPdf(ParametrosDTO bcom) throws RestException;


}
