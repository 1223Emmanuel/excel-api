package com.eglobal.bo.api.zip.repository.dao.sp;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eglobal.bo.api.zip.dto.LBitacoraDTO;


@SuppressWarnings("rawtypes")
public class BitacoraMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

		LBitacoraDTO bitacoraDTO = new LBitacoraDTO();

		bitacoraDTO.setCnegnumero(rs.getString("c_neg_numero"));
		bitacoraDTO.setCtipdocclave(rs.getString("c_tipdoc_clave"));
		bitacoraDTO.setCnegcolonia(rs.getString("c_neg_colonia"));
		bitacoraDTO.setCnegcodpos(rs.getString("c_neg_cod_pos"));
		bitacoraDTO.setCciunombre(rs.getString("c_ciu_nombre"));
		bitacoraDTO.setCnegnomres(rs.getString("c_neg_nom_res"));
		bitacoraDTO.setCnegnombre(rs.getString("c_neg_nombre"));
		bitacoraDTO.setCnegnumtel(rs.getString("c_neg_num_tel"));
		bitacoraDTO.setCnegcalle(rs.getString("c_neg_calle"));
		bitacoraDTO.setCnegplazoent(rs.getString("c_neg_plazo_ent"));
		bitacoraDTO.setDpetfetxn2(rs.getString("d_pet_fe_txn2"));
		bitacoraDTO.setDpetfolegl(rs.getInt("d_pet_fol_egl"));
		bitacoraDTO.setDpetimpotran(rs.getString("d_pet_impo_tran"));
		bitacoraDTO.setDpetnumaut(rs.getString("d_pet_num_aut"));
		bitacoraDTO.setDpetnumctaclaro(rs.getString("d_pet_num_cta_claro"));
		bitacoraDTO.setDpetnumcta(rs.getString("d_pet_num_cta"));
		bitacoraDTO.setFhvennat(rs.getString("fh_ven_nat"));
		bitacoraDTO.setFoliobancario(rs.getString("folio_bancario"));
		bitacoraDTO.setImgimagen( rs.getBytes("img_imagen"));

		return bitacoraDTO;
	}

}
