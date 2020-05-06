package com.eglobal.bo.api.zip.repository.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eglobal.bo.api.zip.dto.CImagenesDTO;
import com.eglobal.bo.api.zip.dto.ParametrosDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;
import com.eglobal.bo.api.zip.repository.dao.HtmlPlantillaDAO;
import com.eglobal.bo.api.zip.repository.dao.sp.PlantillaSP;

@Repository
public class HtmlPlantillaDAOImpl implements HtmlPlantillaDAO {

	@Value("${storedprocedure.sp_ObtienePlantilla}")
	private String spObtenerPlantilla;

	@Value("${api.sec}")
	private String sec;

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("comercioDataSource")
	private DataSource jndi;

	@Override
	public List<CImagenesDTO> obtenerPlantilla(ParametrosDTO com) throws RestException {
		return new PlantillaSP(jdbcTemplate, spObtenerPlantilla).obtenerPlantilla(com);
	}

}
