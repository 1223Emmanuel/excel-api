package com.eglobal.bo.api.zip.security.apitoken;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import com.eglobal.bo.api.zip.exceptions.RestException;

@Configuration
@EnableWebSecurity
@PropertySource("file:${jboss.server.config.dir}/excel-config.properties")
@Order(1)
public class AuthTokenSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${api.http.auth.tokenName}")
	private String authHeaderName;

	@Value("${api.http.auth.tokenValue}")
	private String authHeaderValue;

	private Logger logger = LoggerFactory.getLogger(AuthTokenSecurityConfig.class);

	@Override
	protected void configure(HttpSecurity httpSecurity) throws  RestException, IOException {
		PreAuthTokenHeaderFilter filter = new PreAuthTokenHeaderFilter(authHeaderName);

			filter.setAuthenticationManager(authentication -> {

				if (!authHeaderValue.equals(authentication.getPrincipal())) {
					throw new BadCredentialsException("The API key was not found or not the expected value.");
				}
				authentication.setAuthenticated(true);
				return authentication;
			});

			try {
				securitySource(httpSecurity, filter);
			} catch (AuthenticationException e) {
				logger.error(e.getMessage());
			} catch (RestException e) {
				logger.error(e.getMessage());
				throw new RestException("Error en configure()");
			}

		}

	private void securitySource(HttpSecurity httpSecurity, PreAuthTokenHeaderFilter filter) throws RestException {
		try {
			httpSecurity.antMatcher("/**").csrf().disable().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilter(filter)
					.addFilterBefore(new ExceptionTranslationFilter(new Http403ForbiddenEntryPoint()),
							filter.getClass())
					.anonymous().and().authorizeRequests()
					.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**",
							"/swagger-ui.html", "/webjars/**")
					.permitAll().and().authorizeRequests().anyRequest().authenticated().and().headers().xssProtection()
					.block(false);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RestException("Error en configure()");
		} 
	}

}
