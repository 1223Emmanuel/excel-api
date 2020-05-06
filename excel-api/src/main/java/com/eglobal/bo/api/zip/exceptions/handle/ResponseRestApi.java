package com.eglobal.bo.api.zip.exceptions.handle;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase que define el formato de respuesta de las peticiones
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class ResponseRestApi<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 567899936723330699L;

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

	@JsonProperty
	private Map<String, List<T>> dataMap;
	
	public ResponseRestApi() {
	}

	public ResponseRestApi(HttpStatus httpStatus, String message, String detailMessage, Map<String, List<T>> dataMap) {
		this.httpStatus = httpStatus;
		this.message = message;
		this.detailMessage = detailMessage;
		this.dataMap = dataMap;
	}

	public ResponseRestApi(HttpStatus httpStatus, Map<String, List<T>> map) {
		this.httpStatus = httpStatus;
		this.dataMap = map;
	}

	public ResponseRestApi(HttpStatus httpStatus, String message, String detailMessage, List<T> dataList) {
		this.httpStatus = httpStatus;
		this.code = httpStatus.value();
		this.message = message;
		this.detailMessage = detailMessage;
		this.dataList = dataList;
	}

	public ResponseRestApi(HttpStatus httpStatus, T dataObject) {
		this.httpStatus = httpStatus;
		this.code = httpStatus.value();

		this.dataObject = dataObject;
	}
	
	public ResponseRestApi(T dataObject) {
		this.dataObject = dataObject;
	}
	
	public ResponseRestApi(List<T> dataList) {
		this.dataList = dataList;
	}

	public ResponseRestApi(HttpStatus httpStatus, String message, String detailMessage) {
		this.httpStatus = httpStatus;
		this.code = httpStatus.value();
		this.message = message;
		this.detailMessage = detailMessage;
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

	public Map<String, List<T>> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, List<T>> dataMap) {
		this.dataMap = dataMap;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseRestApi [");
		if (httpStatus != null)
			builder.append("httpStatus = ").append(httpStatus).append(", ");
		if (code != null)
			builder.append("code = ").append(code).append(", ");
		if (message != null)
			builder.append("message = ").append(message).append(", ");
		if (detailMessage != null)
			builder.append("detailMessage = ").append(detailMessage).append(", ");
		if (dataList != null)
			builder.append("dataList = ").append(dataList).append(", ");
		if (dataObject != null)
			builder.append("dataObject = ").append(dataObject);
		builder.append("]");
		return builder.toString();
	}
}
