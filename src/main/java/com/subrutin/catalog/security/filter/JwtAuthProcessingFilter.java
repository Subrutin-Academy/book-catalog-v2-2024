package com.subrutin.catalog.security.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.subrutin.catalog.security.model.RawAccessJwtToken;
import com.subrutin.catalog.security.util.TokenExtractor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthProcessingFilter extends AbstractAuthenticationProcessingFilter {
	
	private final TokenExtractor tokenExtractor;

	public JwtAuthProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher,
			TokenExtractor tokenExtractor) {
		super(requiresAuthenticationRequestMatcher);
		// TODO Auto-generated constructor stub
		this.tokenExtractor = tokenExtractor;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		//intercept, mengambil header dari request
		String authorizationHeader =  request.getHeader("Authorization");
		
		//extract header tersebut untuk mendapatkan jwt-nya
		String jwt = tokenExtractor.extract(authorizationHeader);
		
		//bungkus object token untuk proses otentikasi
		RawAccessJwtToken rawToken = new RawAccessJwtToken(jwt);
		return null;
	}

}
