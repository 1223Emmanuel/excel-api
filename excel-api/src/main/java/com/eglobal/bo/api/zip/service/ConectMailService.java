package com.eglobal.bo.api.zip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eglobal.bo.api.zip.dto.EnviaParamDTO;
@Service
public interface  ConectMailService {


	public <T> List<T> enviaMail(EnviaParamDTO param);



}
