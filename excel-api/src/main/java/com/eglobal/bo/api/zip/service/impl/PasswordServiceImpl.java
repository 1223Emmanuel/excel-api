package com.eglobal.bo.api.zip.service.impl;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eglobal.bo.api.zip.exceptions.RestException;
import com.eglobal.bo.api.zip.service.PasswordService;

@Service
public class PasswordServiceImpl implements PasswordService {

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(PasswordServiceImpl.class);

	public static final String VERSION = ("1.0");

	public static final String CHARACTER_SET = ("aA1bB2cC3dD4eE5fF6gG8hH9iI0jJkKlLmMnNoOpP&qQrRsStT_uUvV-wWxX+yYzZ");
	private static final Random RANDOM = new Random();

	@Override
	public String obtenerPassword() throws RestException {
		StringBuilder myString = new StringBuilder();
		int passwordLength = 8;
		for (int i = 0; i < passwordLength; i++) {
			myString.append(" ");

		}
		String password = myString.toString();
		for (int i = 0; i < password.length(); i++) {
			if (i == 0) {
				password = String.valueOf(CHARACTER_SET.charAt(RANDOM.nextInt(65)))
						+ password.substring(1, password.length());
			} else {
				password = password.substring(0, i) + String.valueOf(CHARACTER_SET.charAt(RANDOM.nextInt(65)))
						+ password.substring(i + 1, password.length());
			}
		}
		return password;
	}
}
