package com.eglobal.bo.api.zip;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class InitApplication {

	private static final Logger log = LoggerFactory.getLogger(AppInitializator.class);

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(InitApplication.class);
		addInitHooks(application);
		application.run(args);
	}

	static void addInitHooks(SpringApplication application) {
		application.addListeners((ApplicationListener<ApplicationStartingEvent>) event -> {
			System.out.println("ApplicationStartingEvent hook ...");
		});
		application.addListeners((ApplicationListener<ApplicationEnvironmentPreparedEvent>) event -> {
			String version = event.getEnvironment().getProperty("java.runtime.version");
			log.info("Running with Java {}", version);
		});
		application.addListeners((ApplicationListener<ApplicationContextInitializedEvent>) event -> {
			log.info("ApplicationContextInitializedEvent hook ...");
		});
		application.addListeners((ApplicationListener<ApplicationPreparedEvent>) event -> {
			log.info("ApplicationPreparedEvent hook ...");
		});
		application.addListeners((ApplicationListener<ApplicationStartedEvent>) event -> {
			log.info("ApplicationStartedEvent hook ...");
		});
		application.addListeners((ApplicationListener<ApplicationReadyEvent>) event -> {
			log.info("ApplicationReadyEvent hook ...");
		});
	}

	@PostConstruct
	private void init() {
		log.info("InitApplication initialization logic ...");
		// ...
	}
}
