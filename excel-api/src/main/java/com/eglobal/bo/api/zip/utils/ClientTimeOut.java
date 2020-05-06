package com.eglobal.bo.api.zip.utils;

import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class ClientTimeOut {
	 private ClientTimeOut() {
		    throw new IllegalStateException("Utility class");
		  }
	public static SimpleClientHttpRequestFactory getClientHttpRequestFactory() 
	{
	    SimpleClientHttpRequestFactory clientHttpRequestFactory
	                      = new SimpleClientHttpRequestFactory();
	    //Connect timeout
	    clientHttpRequestFactory.setConnectTimeout(10_000);
	    
	    //Read timeout
	    clientHttpRequestFactory.setReadTimeout(10_000);
	    return clientHttpRequestFactory;
	    
	}
}

