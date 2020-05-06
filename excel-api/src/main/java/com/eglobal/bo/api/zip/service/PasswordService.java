package com.eglobal.bo.api.zip.service;


import com.eglobal.bo.api.zip.exceptions.RestException;

public interface PasswordService {


	String obtenerPassword() throws RestException;
}
