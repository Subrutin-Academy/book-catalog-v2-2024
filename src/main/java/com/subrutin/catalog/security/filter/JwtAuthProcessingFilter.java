package com.subrutin.catalog.security.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.subrutin.catalog.security.model.AnonymousAuthentication;
import com.subrutin.catalog.security.model.JwtAuthenticatonToken;
import com.subrutin.catalog.security.model.RawAccessJwtToken;
import com.subrutin.catalog.security.util.TokenExtractor;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthProcessingFilter extends AbstractAuthenticationProcessingFilter {
	
	private final TokenExtractor tokenExtractor;
	
	private final AuthenticationFailureHandler failureHandler;

	public JwtAuthProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher,
			TokenExtractor tokenExtractor,
			AuthenticationFailureHandler failureHandler) {
		super(requiresAuthenticationRequestMatcher);
		this.tokenExtractor = tokenExtractor;
		this.failureHandler = failureHandler;
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

		return this.getAuthenticationManager().authenticate(new JwtAuthenticatonToken(rawToken));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		SecurityContext ctx = SecurityContextHolder.createEmptyContext();
		ctx.setAuthentication(authResult);
		SecurityContextHolder.setContext(ctx);
		chain.doFilter(request, response);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthentication());

		this.failureHandler.onAuthenticationFailure(request, response, failed);
	}
	
	

}
