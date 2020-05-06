package com.eglobal.bo.api.zip.dto;

import java.util.List;


public class EnviaParamDTO {


	private String afiliacion;
	private String asunto;
	private String cc;
	private String fromEmail;
	private String mensaje;
	private String nombrePlantilla;
	private String numeroGrupo;
	private String toEmail;
	private List<AdjuntoDTO> adjunto;



	public String getAfiliacion() {
		return afiliacion;
	}

	public void setAfiliacion(String afiliacion) {
		this.afiliacion = afiliacion;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getNombrePlantilla() {
		return nombrePlantilla;
	}

	public void setNombrePlantilla(String nombrePlantilla) {
		this.nombrePlantilla = nombrePlantilla;
	}

	public String getNumeroGrupo() {
		return numeroGrupo;
	}

	public void setNumeroGrupo(String numeroGrupo) {
		this.numeroGrupo = numeroGrupo;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public List<AdjuntoDTO> getAdjunto() {
		return adjunto;
	}

	public void setAdjunto(List<AdjuntoDTO> adjunto) {
		this.adjunto = adjunto;
	}



}
