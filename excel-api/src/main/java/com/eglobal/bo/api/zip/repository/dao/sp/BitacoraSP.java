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

import com.eglobal.bo.api.zip.dto.LBitacoraDTO;
import com.eglobal.bo.api.zip.dto.ParametrosDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;

import com.eglobal.bo.api.zip.utils.Constants;

public class BitacoraSP extends StoredProcedure {
	private Map<String, Object> inputs = new HashMap<>();

	@SuppressWarnings("rawtypes")
	public BitacoraSP(JdbcTemplate jdbcTemplate, String spObtener) {
		super(jdbcTemplate, spObtener);

		RowMapper rowMapper = null;
		rowMapper = (RowMapper) new BitacoraMapper();
		declareParameter(new SqlParameter("Tipotramite", Types.INTEGER));
		declareParameter(new SqlParameter("NumeroGrupo", Types.INTEGER));
		declareParameter(new SqlParameter("Afiliacion", Types.INTEGER));
		declareParameter(new SqlParameter("FechaSol", Types.VARCHAR));
		declareParameter(new SqlParameter("FiltroPorGrupo", Types.VARCHAR));
		declareParameter(new SqlReturnResultSet(Constants.RESULTSET, rowMapper));
		declareParameter(new SqlReturnResultSet(Constants.RESULTSETIMAGE, rowMapper));
		compile();
	}

	@SuppressWarnings({ "unchecked" })
	public List<LBitacoraDTO> obtener(ParametrosDTO parametros) throws RestException {

		List<LBitacoraDTO> resp = null;

		try {
			inputs.put(Constants.TIPOTRAMITE, parametros.getTipotramite());
			inputs.put(Constants.NUMEROGRUPO, parametros.getNumeroGrupo());
			inputs.put(Constants.AFILIACION, parametros.getAfiliacion());
			inputs.put(Constants.FECHASOL, parametros.getFechaSol());
			inputs.put(Constants.FILTROPORGRUPO, parametros.getFiltroPorGrupo());

			Map<String, Object> rs1 = super.execute(inputs);
//

			resp = (List<LBitacoraDTO>) rs1.get((Constants.RESULTSET));

			return resp;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RestException("128", "Error en base de datos");
		}

	}
	


	


}
