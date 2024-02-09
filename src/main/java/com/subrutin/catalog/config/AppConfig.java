package com.subrutin.catalog.config;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subrutin.catalog.domain.Author;
import com.subrutin.catalog.domain.Book;
import com.subrutin.catalog.repository.BookRepository;
import com.subrutin.catalog.repository.impl.BookRepositoryImpl;
import com.subrutin.catalog.security.util.JWTTokenFactory;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Configuration
public class AppConfig {
	
	@Bean
	public Key key() {
		byte[] keyBytes = Decoders.BASE64.decode("lkqelwklkdalldsakakdsqw9wqe98ewq9pqwe989089821kjkwjqekl98kljaksdjklsdaj");
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	@Bean
	public JWTTokenFactory jwtTokenFactory(Key key) {
		return new JWTTokenFactory(key);
	}
 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
 
}
