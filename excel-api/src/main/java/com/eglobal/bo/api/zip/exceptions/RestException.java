package com.eglobal.bo.api.zip.exceptions;

import java.io.Serializable;

/**
 * Excepci@oacute;n definida para tratamiento de los errores que se presenten al
 * interatuar con la api
 * 
 * @author oiml
 *
 */
public class RestException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private  String message;
	private  String detailMessage;

	private String errorCode;
	
	private String errorMessage;

	public RestException(String message) {
		super(message);
		this.message = message;
	}

	public RestException(String message, String detailMessage) {
		super(message);
		this.message = message;
		this.detailMessage = detailMessage;
	}
	 @Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}


