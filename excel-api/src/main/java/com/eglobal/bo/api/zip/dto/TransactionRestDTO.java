package com.eglobal.bo.api.zip.dto;

import java.io.Serializable;

public class TransactionRestDTO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6775207026990162663L;
	
	private Boolean isValid;
	private String msg;
	private T dto;
	
	public TransactionRestDTO(Boolean isValid, T dto) {
		super();
		this.isValid = isValid;
		this.dto = dto;
	}
	
	public TransactionRestDTO(Boolean isValid, String msg, T dto) {
		super();
		this.isValid = isValid;
		this.msg = msg;
		this.dto = dto;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getDto() {
		return dto;
	}

	public void setDto(T dto) {
		this.dto = dto;
	}
}
