package com.eglobal.bo.api.zip.repository.dao.sp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext applicationContext;



    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext (ApplicationContext applicationContext) {
        ApplicationContextProvider.applicationContext = applicationContext;
        Environment env = applicationContext.getEnvironment();
        System.out.println(env.getProperty("db.user")); // access them 
    }
    @Bean
    public ApplicationContextProvider contextProvider() {
        return new ApplicationContextProvider();
    }
}