package com.eglobal.bo.api.zip.repository.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eglobal.bo.api.zip.dto.LBitacoraDTO;
import com.eglobal.bo.api.zip.dto.ParametrosDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;
import com.eglobal.bo.api.zip.repository.dao.ConsultaDAO;
import com.eglobal.bo.api.zip.repository.dao.sp.BitacoraSP;

@Repository

public class ConsultaDAOImpl implements ConsultaDAO {
	@Value("${storedprocedure.SP_ObtenerDetalles2}")
	private String spObtener;

	@Value("${api.sec}")
	private String sec;

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("comercioDataSource")
	private DataSource jndi;

	public List<LBitacoraDTO> obtenerConsulta(ParametrosDTO com) throws RestException {
		return new BitacoraSP(jdbcTemplate, spObtener).obtener(com);
	}

}