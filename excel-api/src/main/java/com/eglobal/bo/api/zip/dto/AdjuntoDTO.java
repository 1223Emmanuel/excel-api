package com.eglobal.bo.api.zip.dto;

public class AdjuntoDTO {
	private byte[] contenido;
	private String contenidoBase64;
	private String folioEglobal;
	private String nombre;
	private String tipo;
	
	public AdjuntoDTO(){
		
	}
	
	public AdjuntoDTO(String nombre, byte[] contenido, String tipo,
			String folioEglobal) {
		super();
		this.setNombre(nombre);
		this.setContenido(contenido);
		this.setTipo(tipo);
		this.setFolioEglobal(folioEglobal);
	}


	public String getContenidoBase64() {
		return contenidoBase64;
	}
	public void setContenidoBase64(String contenidoBase64) {
		this.contenidoBase64 = contenidoBase64;
	}
	public String getFolioEglobal() {
		return folioEglobal;
	}
	public void setFolioEglobal(String folioEglobal) {
		this.folioEglobal = folioEglobal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public byte[] getContenido() {
		return contenido;
	}
	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}

}
