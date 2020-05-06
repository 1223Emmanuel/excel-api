package com.eglobal.bo.api.zip.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.eglobal.bo.api.zip.dto.LBitacoraDTO;

public interface ExportExcelService {

	ByteArrayOutputStream exportaExcel(List<LBitacoraDTO> bitacora, String string, String string2);

}
