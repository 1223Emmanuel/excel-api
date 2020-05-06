package com.eglobal.bo.api.zip.service;

import java.io.IOException;


import org.springframework.stereotype.Service;

import com.eglobal.bo.api.zip.dto.ParametrosDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;
import com.itextpdf.text.DocumentException;
@Service
public interface ZipFileExcelService {

	String zip(String string, ParametrosDTO bcom) throws RestException, IOException, DocumentException;


}
