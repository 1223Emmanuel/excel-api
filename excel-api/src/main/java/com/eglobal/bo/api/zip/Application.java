package com.eglobal.bo.api.zip;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		InitApplication.addInitHooks(builder.application());
		return builder.sources(InitApplication.class);
	}

	@Bean("jasyptStringEncryptor")
	public StringEncryptor stringEncryptor() {
		EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
		StandardPBEStringEncryptor standard = new StandardPBEStringEncryptor();
		config.setAlgorithm("PBEWithSHA1AndDESede");
		config.setKeyObtentionIterations("100000");
		config.setPassword("Off1c319#");
		standard.setConfig(config);
		// String pwd = standard.encrypt("");
		return standard;
	}

}
