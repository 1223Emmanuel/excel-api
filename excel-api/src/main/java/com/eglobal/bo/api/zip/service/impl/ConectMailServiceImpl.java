package com.eglobal.bo.api.zip.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eglobal.bo.api.zip.dto.EnviaParamDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;
import com.eglobal.bo.api.zip.exceptions.handle.ResponseRestApi;
import com.eglobal.bo.api.zip.service.ConectMailService;
import com.eglobal.bo.api.zip.utils.ClientTimeOut;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ConectMailServiceImpl implements ConectMailService {
	private static Logger logger = LoggerFactory.getLogger(ConectMailServiceImpl.class);
	PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);

	@Value("${targetMail.url}")
	private String urlZip;

	private static final String METHOD_ENVMAIL = "enviarCorreo";

	@SuppressWarnings("unchecked")
	private ResponseRestApi<String> conectServiceIntegrity(String json, String method) throws RestException {
		logger.info("***INICIA CONEXION Y ENVIO DE DATOS A email-api: {}", json);
		try {
			HttpHeaders headers = new HttpHeaders();
			ResponseRestApi<String> response = null;
			Map<String, String> parameterMap = new HashMap<String, String>(4);
			parameterMap.put("charset", "utf-8");
			headers.set("AUTH_API_KEY", "abcd123456");
			headers.setContentType(new MediaType("application", "json", parameterMap));

			String endpointRuta = this.urlZip + method;
			HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
			response = new RestTemplate(ClientTimeOut.getClientHttpRequestFactory()).postForObject(endpointRuta,
					requestEntity, ResponseRestApi.class);

			logger.info(String.format("Detalle Mensaje: %s", response.getDetailMessage()));
			logger.info("crea conexion");
			logger.info(String.format("Estatus: %s", response.getHttpStatus()));
			if (!(response.getCode() == 200 || response.getCode() == 202)) {
				throw new RestException("CONEXION DE SERVICIO: " + "Error en la respuesta del servicio codigo"
						+ response.getCode() + "");
			}
			logger.info("***TERMINA CONEXION Y ENVIO DE DATOS");
			return response;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	public <T> List<T> enviaMail(EnviaParamDTO param) {
		List<EnviaParamDTO> resultList = null;

		String json = null;

		// Create json
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			logger.info("***Empieza ENVIO DE DATOS: {}", param);
			json = objectMapper.writeValueAsString(param);
			ResponseRestApi responseService = conectServiceIntegrity(json, METHOD_ENVMAIL);
			logger.info("***termina ENVIO DE DATOS: {}");
		} catch (Exception e) {
			logger.info("********************Error************************");
			e.getMessage();

		}
		return null;

	}


}
