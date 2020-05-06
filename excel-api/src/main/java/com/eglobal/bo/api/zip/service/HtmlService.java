package com.eglobal.bo.api.zip.service;

import java.util.List;

import com.eglobal.bo.api.zip.dto.CImagenesDTO;
import com.eglobal.bo.api.zip.dto.ParametrosDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;

public interface HtmlService {

	String obtenerParamhtml(ParametrosDTO bcom, List<CImagenesDTO> html) throws RestException;

}
