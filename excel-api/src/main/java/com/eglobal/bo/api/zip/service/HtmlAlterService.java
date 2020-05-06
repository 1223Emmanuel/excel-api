package com.eglobal.bo.api.zip.service;


import java.util.List;

import com.eglobal.bo.api.zip.dto.LBitacoraDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;

public interface HtmlAlterService {
	


	String alterhtml( List<LBitacoraDTO> bitacora)throws RestException;

}
