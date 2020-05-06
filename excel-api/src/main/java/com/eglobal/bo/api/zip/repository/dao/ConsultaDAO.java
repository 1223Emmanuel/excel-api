package com.eglobal.bo.api.zip.repository.dao;

import java.util.List;

import com.eglobal.bo.api.zip.dto.LBitacoraDTO;
import com.eglobal.bo.api.zip.dto.ParametrosDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;

public interface ConsultaDAO {

	List<LBitacoraDTO> obtenerConsulta(ParametrosDTO parametrosDTO) throws RestException;
}
