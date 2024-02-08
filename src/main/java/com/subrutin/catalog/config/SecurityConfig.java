package com.subrutin.catalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subrutin.catalog.security.handler.UsernamePasswordAuthFailureHandler;
import com.subrutin.catalog.security.handler.UsernamePasswordAuthSucessHandler;
import com.subrutin.catalog.service.AppUserService;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	private AppUserService appUserService;
	
	@Bean
	public AuthenticationSuccessHandler usernamePasswordAuthSuccessHandler(ObjectMapper objectMapper) {
		return new UsernamePasswordAuthSucessHandler(objectMapper);
	}
	
	@Bean
	public AuthenticationFailureHandler usernamePasswordAuthFailureHandler(ObjectMapper objectMapper) {
		return new UsernamePasswordAuthFailureHandler(objectMapper);
	}
	
	@Autowired
	void registerProvider(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
		auth.userDetailsService(appUserService).passwordEncoder(passwordEncoder);
	}

}
