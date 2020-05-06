package com.eglobal.bo.api.zip.repository.dao;


import java.util.List;

import com.eglobal.bo.api.zip.dto.CImagenesDTO;
import com.eglobal.bo.api.zip.dto.ParametrosDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;

public interface HtmlPlantillaDAO {
	List<CImagenesDTO> obtenerPlantilla(ParametrosDTO com) throws RestException;

}
