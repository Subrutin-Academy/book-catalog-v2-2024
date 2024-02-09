package com.subrutin.catalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subrutin.catalog.security.filter.UsernamePasswordAuthProcessingFilter;
import com.subrutin.catalog.security.handler.UsernamePasswordAuthFailureHandler;
import com.subrutin.catalog.security.handler.UsernamePasswordAuthSucessHandler;
import com.subrutin.catalog.security.provider.UsernamePasswordAuthProvider;
import com.subrutin.catalog.security.util.JWTTokenFactory;
import com.subrutin.catalog.service.AppUserService;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

	private final static String AUTH_URL = "/v1/login";

	@Autowired
	private UsernamePasswordAuthProvider usernamePasswordAuthProvider;
	
	@Bean
	public AuthenticationSuccessHandler successHandler(ObjectMapper objectMapper, JWTTokenFactory factory) {
		return new UsernamePasswordAuthSucessHandler(objectMapper, factory);
	}

	@Bean
	public AuthenticationFailureHandler usernamePasswordAuthFailureHandler(ObjectMapper objectMapper) {
		return new UsernamePasswordAuthFailureHandler(objectMapper);
	}

	@Autowired
	void registerProvider(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(usernamePasswordAuthProvider);
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public UsernamePasswordAuthProcessingFilter usernamePasswordAuthProcessingFilter(ObjectMapper objectMapper,
			AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler,
			AuthenticationManager manager) {
		UsernamePasswordAuthProcessingFilter filter = new UsernamePasswordAuthProcessingFilter(AUTH_URL, objectMapper,
				successHandler, failureHandler);
		filter.setAuthenticationManager(manager);
		return filter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,
			UsernamePasswordAuthProcessingFilter usernamePasswordAuthProcessingFilter) throws Exception {
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()).csrf(csrf -> csrf.disable())
				.sessionManagement(
						(sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic(withDefaults());

		http.addFilterBefore(usernamePasswordAuthProcessingFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();

	}

}
