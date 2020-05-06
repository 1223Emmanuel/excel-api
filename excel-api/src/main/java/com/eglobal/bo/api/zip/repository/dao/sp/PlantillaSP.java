package com.eglobal.bo.api.zip.repository.dao.sp;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;

import com.eglobal.bo.api.zip.dto.CImagenesDTO;
import com.eglobal.bo.api.zip.dto.ParametrosDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;
import com.eglobal.bo.api.zip.utils.Constants;

public class PlantillaSP  extends StoredProcedure{
	private Map<String, Object> inputs = new HashMap<>();
	@SuppressWarnings("rawtypes")
	public PlantillaSP(JdbcTemplate jdbcTemplate, String spObtener) {
		super(jdbcTemplate, spObtener);
		
		RowMapper rowMapper = null;
		rowMapper = (RowMapper) new PlantillaMapper();
		declareParameter(new SqlParameter("Tipotramite", Types.INTEGER));
		declareParameter(new SqlReturnResultSet("ar_archivo", rowMapper));
		compile();
	}

	@SuppressWarnings("unchecked")
	public List<CImagenesDTO> obtenerPlantilla(ParametrosDTO com) throws RestException {
		List<CImagenesDTO> resp = null;

		try {
			inputs.put(Constants.TIPOTRAMITE, com.getTipotramite());
			Map<String, Object> rs1 = super.execute(inputs);
			resp = (List<CImagenesDTO>) rs1.get(("ar_archivo"));

			return resp;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RestException("128", "Error en base de datos");
		}

	}

}
