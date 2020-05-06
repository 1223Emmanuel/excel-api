package com.eglobal.bo.api.zip.dto;

import java.util.List;

public class EnviaParamDocDTO {

	private String adquirente;
	private String agrupacion;
	private String fechaSolicitud;
	private String formato;
	private List<LBitacoraDocDTO> mapDatos;
	private String parametroDoc;
	private String tipoDoc;
	
	
	public String getAdquirente() {
		return adquirente;
	}
	public void setAdquirente(String adquirente) {
		this.adquirente = adquirente;
	}
	public String getAgrupacion() {
		return agrupacion;
	}
	public void setAgrupacion(String agrupacion) {
		this.agrupacion = agrupacion;
	}
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getParametroDoc() {
		return parametroDoc;
	}
	public void setParametroDoc(String parametroDoc) {
		this.parametroDoc = parametroDoc;
	}
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public List<LBitacoraDocDTO> getMapDatos() {
		return mapDatos;
	}
	public void setMapDatos(List<LBitacoraDocDTO> mapDatos) {
		this.mapDatos = mapDatos;
	}
	
	
	

}
