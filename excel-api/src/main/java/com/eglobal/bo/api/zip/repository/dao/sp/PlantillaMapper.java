package com.eglobal.bo.api.zip.repository.dao.sp;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eglobal.bo.api.zip.dto.CImagenesDTO;

@SuppressWarnings("rawtypes")
public class PlantillaMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

		CImagenesDTO imagesDTO = new CImagenesDTO();

		imagesDTO.setIdplantilla(rs.getInt("id_plantilla"));
		imagesDTO.setAassunto(rs.getString("a_asunto"));
		imagesDTO.setCmcuerpomensaje(rs.getString("cm_cuerpo_mensaje"));
		imagesDTO.setArarchivo(rs.getString("ar_archivo"));

		return imagesDTO;

	}
}