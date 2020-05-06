package com.eglobal.bo.api.zip.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class ResponseRestApi<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty
	@NotBlank
	private HttpStatus httpStatus;
	
	@JsonProperty
	@NotBlank
	private Integer code;
	
	@JsonProperty
	@NotBlank
	private String message;
	
	@JsonProperty
	@NotBlank
	private String detailMessage;
	
	@JsonProperty
	private T dataObject;
	
	@JsonProperty
	private List<T> dataList;
	
	public ResponseRestApi(){
		
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

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

	public T getDataObject() {
		return dataObject;
	}

	public void setDataObject(T dataObject) {
		this.dataObject = dataObject;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
}
