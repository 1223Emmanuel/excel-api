package com.eglobal.bo.api.zip.service;

import java.io.ByteArrayInputStream;

import java.util.List;

import com.eglobal.bo.api.zip.dto.LBitacoraDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;

public interface ExportPdfService {

	ByteArrayInputStream exportaPdf(List<LBitacoraDTO> bitacora) throws RestException;

}
