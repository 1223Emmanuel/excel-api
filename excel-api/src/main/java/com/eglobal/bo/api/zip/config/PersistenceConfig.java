package com.eglobal.bo.api.zip.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
@PropertySource("file:${jboss.server.config.dir}/excel-config.properties")
public class PersistenceConfig {

	@Value("${datasource.jndi}")
	private String datasourcejndi;

	@Value("${sqltype.connection}")
	private String url;

	@Bean(name = "comercioDataSource")
	public DataSource jndiDataSource() throws  NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName(datasourcejndi);
		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(false);
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();

	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("comercioDataSource") DataSource ds) {
		return new JdbcTemplate(ds);
	}
}
