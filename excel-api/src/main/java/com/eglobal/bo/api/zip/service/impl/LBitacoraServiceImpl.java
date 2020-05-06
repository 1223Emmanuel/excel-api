package com.eglobal.bo.api.zip.service.impl;

import java.util.List;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import com.eglobal.bo.api.zip.dto.LBitacoraDTO;
import com.eglobal.bo.api.zip.exporters.ExportExcelTramite;

public class LBitacoraServiceImpl {
	@Autowired
	private ExportExcelTramite excel;

	public String obtieneadjunto(List<LBitacoraDTO> listatramite, String codigo, String descripcion) {
		ByteArrayOutputStream adjunto = null;
		adjunto = excel.exportaExcel(listatramite, codigo, descripcion);

		return (Base64.getEncoder().encodeToString(adjunto.toByteArray()));

	}
}
