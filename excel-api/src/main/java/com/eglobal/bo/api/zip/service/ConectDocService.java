package com.eglobal.bo.api.zip.service;


import org.springframework.stereotype.Service;

import com.eglobal.bo.api.zip.dto.EnviaParamDocDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;
@Service
public interface ConectDocService {

		public String enviaDoc(EnviaParamDocDTO confp) throws RestException;
}
