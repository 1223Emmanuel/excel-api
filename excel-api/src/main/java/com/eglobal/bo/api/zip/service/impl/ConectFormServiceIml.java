package com.eglobal.bo.api.zip.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.eglobal.bo.api.zip.dto.EnviaParamDocDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;
import com.eglobal.bo.api.zip.exceptions.handle.ResponseRestApi;
import com.eglobal.bo.api.zip.service.ConectDocService;
import com.eglobal.bo.api.zip.utils.ClientTimeOut;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class ConectFormServiceIml  implements ConectDocService{
//	private static Logger logger = LoggerFactory.getLogger(ConectMailServiceImpl.class);
//	PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
//
//	@Value("${targetDoc.url}")
//	private String urlDoc;
//
//	private static final String METHOD_ENVDOC = "generarDocumento/";
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private ResponseRestApi conectServiceIntegrity(String json, String method) throws RestException {
//		logger.info("***INICIA CONEXION Y ENVIO DE DATOS A generador-doc: {}", json);
//		try {
//			HttpHeaders headers = new HttpHeaders();
//			ResponseRestApi<String> response = null;
//			Map<String, String> parameterMap = new HashMap<String, String>(4);
//			parameterMap.put("charset", "utf-8");
//			headers.set("AUTH_API_KEY", "abcd123456");
//			headers.setContentType(new MediaType("application", "json", parameterMap));
//
//			String endpointRuta = this.urlDoc + method;
//			HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
//			response = new RestTemplate(ClientTimeOut.getClientHttpRequestFactory()).postForObject(endpointRuta,
//					requestEntity, ResponseRestApi.class);
//			logger.info(String.format("Detalle Mensaje: %s", response.getDetailMessage()));
//			logger.info(String.format("Estatus: %s", response.getHttpStatus()));
//			if (!(response.getCode() == 200 || response.getCode() == 202)) {
//				throw new RestException("CONEXION DE SERVICIO: " + "Error en la respuesta del servicio codigo"
//						+ response.getCode() + "");
//			}
//			logger.info("***TERMINA CONEXION Y ENVIO DE DATOS");
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return null;
//
//	}
//
//	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
//	@Override
//	public <T> Object enviaDoc(EnviaParamDocDTO param) {
//		List<EnviaParamDocDTO> resultList = null;
//
//		String json = null;
//
//		// Create json
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			logger.info("***Empieza ENVIO DE DATOS: {}", param);
//			json = objectMapper.writeValueAsString(param);
//			ResponseRestApi responseService = conectServiceIntegrity(json, METHOD_ENVDOC);
//			Object resp = responseService.getDataObject();
//			logger.info("***termina ENVIO DE DATOS: {}" ,resp);
//			
//		} catch (Exception e) {
//			logger.info("********************Error************************");
//			e.getMessage();
//
//		}
//		return null;
//
//	}

private Logger logger = LoggerFactory.getLogger(ConectFormServiceIml.class);
	//estas ya estan en el properties
	@Value("${api.client.url}")
	private String apiUrl;

	@Value("${api.client.port}")
	private String port;
	
	@Value("${api.key}")
	private String apiKey;

	private RestTemplate restTemplate;
	
	private HttpHeaders headers;
	
	PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.BLOCKS).and(Sanitizers.LINKS);

	@SuppressWarnings("unchecked")
	@Override
	public String enviaDoc(EnviaParamDocDTO param) throws RestException {
		String postForObject = null;
		try {
			headers = new HttpHeaders();
			final Map<String, String> parameterMap = new HashMap<String, String>();
			
			parameterMap.put("charset", "utf-8");
			headers.setContentType(new MediaType("application", "json", parameterMap));

			headers.add("AUTH_API_KEY", apiKey);
			
			restTemplate = new RestTemplate(ClientTimeOut.getClientHttpRequestFactory());
			
			String json = new ObjectMapper().writeValueAsString(param);
			logger.info("***INICIA CONEXION Y ENVIO DE DATOS A generador-doc: {}", json);
			String jsonSanitized = StringEscapeUtils.unescapeHtml3(policy.sanitize(json));
			String urlSanitized = StringEscapeUtils.unescapeHtml3(policy.sanitize(construirURL()));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(jsonSanitized, headers);
			
			
			ResponseRestApi<String> response = restTemplate.postForObject(urlSanitized, requestEntity, ResponseRestApi.class);

			postForObject = response.getDataObject();

		} catch (JsonProcessingException jE) {
			logger.error("Error al procesar el formato JSON de parámetros: " + jE.getMessage());
		} catch (RestClientException rE) {
			logger.error("El servicio de generacion de documentos no se encuentra disponible: " + rE.getMessage());
		}
		//este es el retorno 
		return postForObject;
	}
	
	private String construirURL() {
		
		StringBuilder url = new StringBuilder();
		url.append("http://").append("localhost").append(":").append("8080");
		url.append("/generador-doc/generarDocumento/");
		return url.toString();
	}
}



