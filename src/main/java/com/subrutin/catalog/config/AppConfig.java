package com.subrutin.catalog.config;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

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
	public SecretKey key() {
		byte[] keyBytes = Decoders.BASE64.decode("wqeew12312321123dewr2345345435ertrteert4565644grtrytrytry564ewefsdfddfsfsd");
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	@Bean
	public JWTTokenFactory jwtTokenFactory(Key secret) {
		return new JWTTokenFactory(secret);
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
