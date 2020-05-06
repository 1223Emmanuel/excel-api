package com.eglobal.bo.api.zip.dto;

public class ParametrosDTO {
	
	private int Tipotramite;
	private int NumeroGrupo;       
	private int Afiliacion;      
	private String FechaSol;   
	private String  FiltroPorGrupo;
	
	
	public int getTipotramite() {
		return Tipotramite;
	}
	public void setTipotramite(int tipotramite) {
		Tipotramite = tipotramite;
	}
	public int getNumeroGrupo() {
		return NumeroGrupo;
	}
	public void setNumeroGrupo(int numeroGrupo) {
		NumeroGrupo = numeroGrupo;
	}
	public int getAfiliacion() {
		return Afiliacion;
	}
	public void setAfiliacion(int afiliacion) {
		Afiliacion = afiliacion;
	}

	public String getFiltroPorGrupo() {
		return FiltroPorGrupo;
	}
	public void setFiltroPorGrupo(String filtroPorGrupo) {
		FiltroPorGrupo = filtroPorGrupo;
	}
	public String getFechaSol() {
		return FechaSol;
	}
	public void setFechaSol(String fechaSol) {
		FechaSol = fechaSol;
	}    
}
